package com.cloud.order.controller;

import com.cloud.order.pojo.Order;
import com.cloud.order.pojo.User;
import com.cloud.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public Order getById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        User user =
                restTemplate.getForObject("http://user-service/users/" + order.getUserId(), User.class);
        order.setUser(user);
        return order;
    }
}
