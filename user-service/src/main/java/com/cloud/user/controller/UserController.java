package com.cloud.user.controller;

import com.cloud.user.config.PatternProperties;
import com.cloud.user.pojo.User;
import com.cloud.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/users")
//@RefreshScope
public class UserController {

    @Autowired
    private IUserService userService;

    //    @Value("${pattern.dateformat}")
//    private String dateformat;
    @Autowired
    private PatternProperties properties;

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id,
                        @RequestHeader(value = "Truth", required = false) String truth) {
        log.error(truth);
        return userService.getById(id);
    }

    @GetMapping("/prop")
    public PatternProperties properties() {
        return properties;
    }

    @GetMapping("/now")
    public String now() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(properties.getDateformat()));
    }
}
