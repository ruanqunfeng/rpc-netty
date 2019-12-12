package com.rqf.learn.netty.rpc.registry;

import com.rqf.learn.netty.rpc.protocol.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zed
 * @version 1.0
 * @date 2019/12/11 17:29
 */
public class RegistryHandler extends ChannelInboundHandlerAdapter {

    /**
     * 保存所有可用的服务
     */
    private Map<String, Object> registryMap = new HashMap<>();

    /**
     * 保存所有相关的服务名
     */
    private List<String> classNames = new ArrayList<>();

    /**
     * 1.根据包名把所有符合条件的class都扫描出来，放到一个容器中
     * 2.给每一个服务起一个名字，作为服务名，放到一个容器中
     * 3.当客户端连接过来之后，获取自定义的协议请求
     * 4.解析请求，去容器中查找服务
     * 5.调用服务，返回响应
     */

    public RegistryHandler() {
        scannerClass("com.rqf.learn.netty.rpc.provider");
        doRegistry();
    }

    /**
     * 扫描指定路径下的服务
     *
     * @param packageName
     */
    private void scannerClass(String packageName) {
        URL url = this.getClass().getResource("/" + packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scannerClass(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    /**
     * 根据扫描到的服务，进行服务注册
     */
    private void doRegistry() {
        if (classNames.isEmpty()) {
            return;
        }
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                // 使用接口作为服务名
                Class<?> i = clazz.getInterfaces()[0];
                registryMap.put(i.getName(), clazz.newInstance());

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();

        RpcRequest request = (RpcRequest) msg;

        if (registryMap.containsKey(request.getClassName())) {
            Object target = registryMap.get(request.getClassName());
            Method method = target.getClass().getMethod(request.getMethodName(), request.getParames());
            result = method.invoke(target, request.getValues());
        }

        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
