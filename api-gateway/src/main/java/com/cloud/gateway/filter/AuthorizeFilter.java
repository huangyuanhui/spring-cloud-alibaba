package com.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component  // 过滤器生效，让AuthorizeFilter作为一个bean注入到Spring容器中
// @Order(-1)  // 顺序注解，可能有很多过滤器，这里定义过滤器执行顺序，值越小优先级越高
public class AuthorizeFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1：获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        // 2：获取参数中的authorization参数
        String auth = queryParams.getFirst("authorization");
        // 3：判断参数值是否等于admin
        if ("admin".equals(auth)) {
            // 4：是则放行
            return chain.filter(exchange);
        }
        // 5：否则拦截
        // 5.1：注意设置状态码用户体验友好一点
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        // 5.2：拦截请求
        return exchange.getResponse().setComplete();
    }

    /**
     * 定义顺序注解，可能有很多过滤器，这里定义过滤器执行顺序，值越小优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }
}
