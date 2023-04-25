package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/first-service")
public class Controller {

    Environment env;

    @Autowired
    public Controller(Environment environment) {
        this.env = environment;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "퍼스트 서비스";
    }
    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header){
        log.info(header);
        return "퍼스트 서비스 메시지";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request){
        log.info("Server port={}", request.getServerPort());
        return String.format("퍼스트 서비스 체크 server port %s", env.getProperty("local.server.port"));
    }
}
