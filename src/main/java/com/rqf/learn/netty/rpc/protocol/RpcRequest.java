package com.rqf.learn.netty.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Zed
 * @version 1.0
 * @date 2019/12/11 16:48
 */
@Data
public class RpcRequest implements Serializable {
    /**
     *类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数类型
     */
    private Class<?>[] parames;
    /**
     * 参数
     */
    private Object[] values;
}
