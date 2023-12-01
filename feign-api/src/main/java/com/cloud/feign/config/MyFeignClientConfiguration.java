package com.cloud.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * 这个类没有加@Configuration注解，配置是不会生效的，接下来要做的事就是
 * 让这个配置生效，生效有两种方式：
 * 一种是加在具体的FeignClient上，只针对当前的服务生效；
 * 一种是加在启动类的EnableFeignClients上，代表对全局有效
 */
public class MyFeignClientConfiguration {

    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.BASIC;
    }
}
