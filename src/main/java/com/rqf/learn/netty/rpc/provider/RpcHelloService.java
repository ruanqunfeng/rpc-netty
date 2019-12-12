package com.rqf.learn.netty.rpc.provider;

import com.rqf.learn.netty.rpc.api.IRpcHelloService;

/**
 * @author Zed
 * @version 1.0
 * @date 2019/12/11 17:17
 */
public class RpcHelloService implements IRpcHelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name + "!";
    }
}
