package me.hjjang.mvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.hjjang.mvc.basic.HelloData;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age= {}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("Json V1 OK");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody = {}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age= {}", helloData.getUsername(), helloData.getAge());

        return "Json V2 OK";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("username={}, age= {}", helloData.getUsername(), helloData.getAge());
        return "Json V3 OK";
    }

    /**
     * @RequestBody를 생략하면 @ModelAttribute로 인식한다.
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3-1")
    public String requestBodyJsonV3_1(HelloData helloData) {
        log.info("username={}, age= {}", helloData.getUsername(), helloData.getAge());
        return "Json V4-1 OK";
    }

    /**
     * Content-Type이 application/json 인지 꼭 확인 그래야 JSON을 처리할수 있는 HTTP 컨버터가 실행된다,
     */

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> data) {
        HelloData helloData = data.getBody();
        log.info("username={}, age= {}", helloData.getUsername(), helloData.getAge());
        return "Json V4 OK";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("username={}, age= {}", helloData.getUsername(), helloData.getAge());
        return helloData;
    }
}
