package ps.demo.controller;

import io.swagger.v3.oas.annotations.Operation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import ps.demo.common.*;
import ps.demo.dto.MyMockDto;
import ps.demo.dto.MyMockReq;
import ps.demo.service.MockerService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/mock")
public class MockerController extends MyBaseController {

    @Autowired
    private MockerService mockerService;


    @Operation(summary = "To create a mocked api endpoint",
            description = "<pre>" +
                    "Create a mocked endpoint by calling this endpoint with below example body. \n" +
                    "And then invoke the mocked endpoint by calling: /mock/api/myrest-api \n" +
                    "The script in the header and body is parsed by thymleaf TemplateEngine. \n" +
                    "{\n" +
                    "    \"uri\": \"myrest-api\",\n" +
                    "    \"regexMatch\": true,\n" +
                    "    \"method\": \"get\",\n" +
                    "    \"status\": 200,\n" +
                    "    \"headers\": \"{\\\"myresponse-header-str\\\": \\\"[(${#strings.randomAlphanumeric(8)})]\\\"}\",\n" +
                    "    \"body\": \"{\\\"requestMethod\\\": \\\"[(${request.method})]-[(${env.TMP})]\\\", \\\"randomStr\\\": \\\"[(${#strings.randomAlphanumeric(8)})]-[(${#strings.arrayJoin(#numbers.sequence(1, 5), ',')})]\\\"}\"\n" +
                    "}" +
                    "</pre>"
    )
    @PostMapping(value = "/create-mock", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public MyBaseResponse save(@RequestBody @Validated MyMockReq myMockReq, HttpServletRequest request) {
        MyMockDto myMockDto = new MyMockDto();

        myMockDto.setRegexMatch(null != request.getParameter("regexMatch"));

        MyMapperUtil.convert(myMockReq, myMockDto);
        //MyBeanUtil.copyProperties(myMockReq, myMockDto);
        if(null != mockerService.findByUri(myMockDto.getUri())) {
            throw new MyClientErrorException(CodeEnum.DUPLICATED_KEY);
        }
        mockerService.save(myMockDto);
        return MyBaseResponse.success();
    }

    @Operation(summary = "This is the endpoint for mocked api endpoints",
            description = "Not able to directly use on swagger as request can be variant.")
    @RequestMapping("/api/**")
    public ResponseEntity<String> mockUriTest(HttpServletRequest request) {
        String pattern = (String)
                request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String searchTerm = new AntPathMatcher().extractPathWithinPattern(pattern,
                request.getServletPath());

        String method = request.getMethod();

        List<MyMockDto> myMockDtos = mockerService.findAll();
        MyMockDto myMockDto = null;
        for (MyMockDto mockDto : myMockDtos) {
            String uri = mockDto.getUri() + "";
            if (mockDto.getMethod().trim().equalsIgnoreCase(method)
                    && (uri.equals(searchTerm) || (mockDto.getRegexMatch() &&
                    MyRegexUtil.isMatche(searchTerm, uri)))) {
                myMockDto = mockDto;
                break;
            }
        }

        HttpHeaders headers = new HttpHeaders();
        if (myMockDto == null) {
            headers.setContentType(MediaType.TEXT_PLAIN);
            return new ResponseEntity("Not Found!", headers, HttpStatus.OK);
        }
        Map<String, Object> mockHeaders = MyJsonUtil.json2SimpleMap(myMockDto.getHeaders());
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("request", request);
        for (Map.Entry<String, Object> entry : mockHeaders.entrySet()) {
            String key = entry.getKey() + "";
            String value = MyThymeleafUtil.processText(entry.getValue() + "", ctx);
            headers.add(key, value);
        }
        if (!headers.containsKey("Content-Type")) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        String body = MyThymeleafUtil.processText(myMockDto.getBody(), ctx);
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(body,
                headers, HttpStatus.valueOf(myMockDto.getStatus()));

        return responseEntity;
    }
}
