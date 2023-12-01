package com.cloud.feign.clients;

import com.cloud.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/users/{id}")
    User findById(@PathVariable("id") Long id);
}
