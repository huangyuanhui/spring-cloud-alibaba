package com.cloud.order.clients;

import com.cloud.order.config.MyFeignClientConfiguration;
import com.cloud.order.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "user-service", configuration = MyFeignClientConfiguration.class)
@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/users/{id}")
    User findById(@PathVariable("id") Long id);
}
