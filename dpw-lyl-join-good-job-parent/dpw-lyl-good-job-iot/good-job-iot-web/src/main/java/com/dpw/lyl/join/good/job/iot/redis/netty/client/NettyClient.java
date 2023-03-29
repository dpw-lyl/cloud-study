package com.dpw.lyl.join.good.job.iot.redis.netty.client;


import com.octv.cloud.tour.travel.netty.handler.ClientChannelInitializer;
import com.octv.cloud.tour.travel.netty.template.NettyClientTemplate;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @Author: dengpw
 * @createTime: 2022年08月19日 09:57:27
 * @version: 1.0.0
 * @Description: 测试netty客户端
 */
@Slf4j
@Component
public class NettyClient {

    private Bootstrap bootstrap;

    private EventLoopGroup eventLoopGroup;

    private ChannelFuture channelFuture;

    private Channel channel;

    /**
     * 是否主动关闭
     */
    private volatile boolean activeShutdown = false;

    /**
     * 是否已经连接成功
     */
    private volatile boolean connected = false;

    @Autowired
    @Qualifier("clientInetSocketAddress")
    private InetSocketAddress clientInetSocketAddress;

    @Resource
    private ClientChannelInitializer clientChannelInitializer;

    @Qualifier("nettyClientThreadPoolTaskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor nettyClientThreadPoolTaskExecutor;

    @Resource
    private NettyClientTemplate nettyClientTemplate;


    /**
     * @author: dengpw
     * @createTime: 2022年08月25 16:04:07
     * @description: 是否已经连接
     * @param:
     * @return: boolean
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月25 16:04:36
     * @description: 主动关闭连接
     * @param:
     * @return: void
     */
    public void close() {
        activeShutdown = true;
        channelFuture.channel().close();
    }

    @PostConstruct
    public void initClient() {
        nettyClientThreadPoolTaskExecutor.submit(this::startNettyClient);
        while (!this.isConnected()) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("客户端连接成功...");
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月24 14:43:53
     * @description: 开启服务
     * @param:
     * @return: void
     */
    public void startNettyClient() {
        bootstrap = new Bootstrap();
        eventLoopGroup = new NioEventLoopGroup();
        // 设置相关参数
        bootstrap.group(eventLoopGroup)     // 设置客户端的线程池
                .channel(NioSocketChannel.class)    // 设置客户端网络套接字通道类型
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(clientChannelInitializer // 设置客户端的线程池对应的 NioEventLoop 设置对应的事件处理器
                );
        connect(clientInetSocketAddress.getHostName(), clientInetSocketAddress.getPort());
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月23 18:06:12
     * @description: 链接服务器
     * @param:
     * @return: void
     */
    public void connect(String host, int port) {
        try {
            // 开始连接服务器, 并进行同步操作
            // ChannelFuture 类分析 , Netty 异步模型
            // sync 作用是该方法不会再次阻塞
            channelFuture = bootstrap.connect(host, port).sync();
            channelFuture.addListener((ChannelFutureListener) future -> {
                        if (future.isSuccess()) {
                            log.info(String.format("客户端启动成功，并监听端口：%s ", clientInetSocketAddress.getPort()));
                        } else {
                            //重连服务端
                            final EventLoop eventExecutors = channelFuture.channel().eventLoop();
                            eventExecutors.schedule(this::initClient, 1, TimeUnit.SECONDS);
                        }
                    }
            );
            channel = channelFuture.sync().channel();
            synchronized (this) {
                //连接成功后标志赋值以及通知等待的线程，链接已经建立
                this.connected = true;
                this.notifyAll();
            }
            log.info("客户端连接服务器成功 ...");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (!activeShutdown) {
                //不是主动断开需要重新链接,在channelInactive中实现了，这里不写相关逻辑
               // log.info("链接发生异常或网络原因，尝试重新连接...");
                //final EventLoop eventExecutors = channelFuture.channel().eventLoop();
                //eventExecutors.schedule(this::initClient, 1, TimeUnit.SECONDS);
            } else {
                channel = null;
                eventLoopGroup.shutdownGracefully();
                connected = false;
            }
        }
    }


    /**
     * @author: dengpw
     * @createTime: 2022年08月19 16:33:12
     * @description: 销毁时关闭连接
     * @param:
     * @return: void
     */
    @PreDestroy
    public void closeNettyClient() {
        log.info("关闭netty客户端，当前线程：{}", Thread.currentThread());
        close();
        if (!eventLoopGroup.isShutdown()) {
            eventLoopGroup.shutdownGracefully();
        }

    }
}

