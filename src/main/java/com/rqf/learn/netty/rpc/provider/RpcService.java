package com.rqf.learn.netty.rpc.provider;

import com.rqf.learn.netty.rpc.api.IRpcService;

/**
 * @author Zed
 * @version 1.0
 * @date 2019/12/11 17:17
 */
public class RpcService implements IRpcService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
