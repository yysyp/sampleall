package ps.demo.controller;

import com.google.common.html.HtmlEscapers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ps.demo.common.MyRestTemplateUtil;

@Slf4j
@RestController
@RequestMapping("/proxy")
public class ProxyController {

    @ResponseBody
    @GetMapping("/call")
    public String proxy(
            @RequestParam(value = "url", defaultValue = "www.bing.com", required = false) String host,
            @RequestParam(value = "port", defaultValue = "80", required = false) String port) {
        log.info("proxy port={}", port);
        String response = MyRestTemplateUtil.getInstance().getForString("http://"+host+":"+port+"/");
        return HtmlEscapers.htmlEscaper().escape(StringUtils.abbreviate(response, 80));
    }
}