package com.rqf.learn.netty.rpc.consumer;

import com.rqf.learn.netty.rpc.api.IRpcHelloService;
import com.rqf.learn.netty.rpc.api.IRpcService;
import com.rqf.learn.netty.rpc.consumer.proto.RpcProxy;

/**
 * @author Zed
 * @version 1.0
 * @date 2019/12/11 17:18
 */
public class RpcConsumer {
    public static void main(String[] args) {
        IRpcHelloService s1 = RpcProxy.create(IRpcHelloService.class);

        System.out.println(s1.sayHello("Zed"));

        IRpcService s2 = RpcProxy.create(IRpcService.class);
        System.out.println(s2.add(10, 2));
        System.out.println(s2.sub(10, 2));
        System.out.println(s2.mult(10, 2));
        System.out.println(s2.div(10, 2));
    }
}
