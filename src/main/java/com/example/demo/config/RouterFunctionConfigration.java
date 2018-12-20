package com.example.demo.config;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * 路由器函数 配置 tidai xml
 */
@Configuration
public class RouterFunctionConfigration {

    /**
     * Servlet 请求借口 ：ServletRequest 或者HttpServletReequest
     * 响应接口：ServletRequest 或者HttpServletRequest
     * Spring 5。0 重新定义了服务请求和响应接口
     * 请求借口ServletRequest
     * 响应接口ServletResponse
     * 即可支持Servlet规范也可以支持自定义，比如netty（web Server）
     * 以本例：
     * 定义get请求，并且返回所有的用户对象 uri /person/find/all
     * flux 是0-n个对象集合
     * mono是 0-1个对象集合
     *
     * Reaction 中的mono和flux是异步处理
     * 集合对象基本上是同步处理（阻塞）
     * 都是发布者 publisher
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository) {

      return  RouterFunctions.route(RequestPredicates.GET("/person/find/all"),request -> {
            //返回所有用户对象
            Collection<User> users = userRepository.findAll();
            Flux<User> userFlux = Flux.fromIterable(users);
            return ServerResponse.ok().body(userFlux,User.class);

        });

    }

}
