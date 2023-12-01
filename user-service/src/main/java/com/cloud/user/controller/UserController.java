package com.cloud.user.controller;

import com.cloud.user.config.PatternProperties;
import com.cloud.user.pojo.User;
import com.cloud.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public User getById(@PathVariable("id") Long id) {
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
