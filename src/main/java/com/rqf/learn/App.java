package com.rqf.learn;

import com.rqf.learn.netty.rpc.registry.RpcRegistry;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new RpcRegistry(8080).start();
    }
}
