package com.rqf.learn.netty.rpc.api;

/**
 * @author Zed
 * @version 1.0
 * @date 2019/12/11 16:12
 */
public interface IRpcService {
    int add(int a, int b);
    int sub(int a, int b);
    int mult(int a, int b);
    int div(int a, int b);
}
