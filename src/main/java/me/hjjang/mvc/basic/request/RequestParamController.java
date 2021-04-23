package me.hjjang.mvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import me.hjjang.mvc.basic.HelloData;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("v1 username = {}, age = {}", username, age);

        response.getWriter().write("ok v1");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("v2 memberName = {}, memberAge = {}", memberName, memberAge);

        return "ok v2";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        log.info("v3 username = {}, age = {}", username, age);

        return "ok v3";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,
            int age) {
        log.info("v4 username = {}, age = {}", username, age);

        return "ok v4";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        log.info("Required username = {}, age = {}", username, age);

        return "ok Required";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age) {
        log.info("Default username = {}, age = {}", username, age);

        return "ok Required";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("Map username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));

        return "ok Required";
    }

    @ResponseBody
    @RequestMapping("/request-param-multimap")
    public String requestParamMultiMap(@RequestParam MultiValueMap<String, Object> paramMap) {
        log.info("MultiMap username = {}", paramMap.get("username"));

        return "ok Required";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v0")
    public String modelAttributeV0(@RequestParam String username, @RequestParam int age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username : {}, age : {}", username, age);
        log.info("V0 HelloData : {}", helloData);
        return "ok V0 ModelAttribute ";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("V1 HelloData : {}", helloData);
        return "ok V1 ModelAttribute ";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {

        log.info("V2 HelloData : {}", helloData);
        return "ok V2 ModelAttribute ";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v3")
    public String modelAttributeV3(HelloData helloData, String username, int age) {
        log.info("username : {}, age : {}", username, age);
        log.info("V3 HelloData : {}", helloData);
        return "ok V3 ModelAttribute ";
    }
}
