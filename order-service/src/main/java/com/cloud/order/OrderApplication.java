package com.cloud.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.cloud.order.mapper")
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }


    /**
     * 负载均衡
     * @return
     */
    @LoadBalanced
    /**
     * 使用RestTemplate实现服务远程调用
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
