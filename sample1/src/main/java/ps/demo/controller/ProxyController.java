package ps.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ps.demo.common.MyRestTemplateUtil;

@RestController(value = "/proxy")
public class ProxyController {

    @ResponseBody
    @GetMapping("/bing")
    public String proxy() {
        String response = MyRestTemplateUtil.getInstance().getForString("http://www.bing.com/");
        return StringUtils.abbreviate(response, 80);
    }
}