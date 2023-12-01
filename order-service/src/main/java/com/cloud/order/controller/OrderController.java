package com.cloud.order.controller;

import com.cloud.feign.clients.UserClient;
import com.cloud.feign.pojo.User;
import com.cloud.order.pojo.Order;
import com.cloud.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    //@Autowired
    //private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    @GetMapping("/{id}")
    public Order getById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        // 使用RestTemplate发起远程调用
        //String url = "http://user-service/users/" + order.getUserId();
        //User user = restTemplate.getForObject(url, User.class);
        // 使用Feign发起远程调用
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        return order;
    }
}
