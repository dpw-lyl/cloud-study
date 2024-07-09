package com.dpw.lyl.join.good.job.iot.web.netty.server;

import com.dpw.lyl.join.good.job.iot.web.netty.handler.NettyServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @Author: dengpw
 * @createTime: 2022年08月22日 09:41:27
 * @version: 1.0.0
 * @Description: netty服务端
 */
@Slf4j
@Component("localNettyServer")
public class NettyServer {

    private final ServerBootstrap bootstrap = new ServerBootstrap();

    private final EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup();

    private final EventLoopGroup workEventLoopGroup = new NioEventLoopGroup();

    @Autowired
    @Qualifier("nettyServerChannelInitializer")
    private NettyServerChannelInitializer nettyServerChannelInitializer;

    @Resource
    private InetSocketAddress serverInetSocketAddress;

    @Qualifier("nettyServerPoolTaskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor nettyServerPoolTaskExecutor;

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 15:26:33
     * @description: 使用线程池启动服务端 避免导致服务器http请求阻塞
     * @param:
     * @return: void
     */
    @PostConstruct
    public void startByAsync(){
        nettyServerPoolTaskExecutor.submit(this::runNettyServer);
    }

    /**
     * @author: dengpw
     * @createTime: 2022年08月22 10:06:03
     * @description: 启动创建netty服务
     * @param:  todo
     * @return: void
     */
    public void runNettyServer(){
        log.info("创建netty服务开始*****当前线程：{}",Thread.currentThread());
        bootstrap.group(bossEventLoopGroup,workEventLoopGroup).channel(NioServerSocketChannel.class)
                .childHandler(nettyServerChannelInitializer)
                .localAddress(serverInetSocketAddress)
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,true);
        try {
            ChannelFuture channelServerFuture = bootstrap.bind(serverInetSocketAddress).sync();
            log.info("netty服务开启监听端口：{}",serverInetSocketAddress.getPort());
            channelServerFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("netty服务端异常：{}",e.getMessage());
        } finally {
            log.info("netty服务执行完成，关闭服务，当前线程：{}",Thread.currentThread());
            if(!bossEventLoopGroup.isShutdown()){
                bossEventLoopGroup.shutdownGracefully();
            }
            if(!workEventLoopGroup.isShutdown()){
                workEventLoopGroup.shutdownGracefully();
            }
        }

    }


    /**
     * @author: dengpw
     * @createTime: 2022年08月22 10:31:39
     * @description: 容器销毁时自动关闭netty服务
     * @param:
     * @return: void
     */
    @PreDestroy
    public void stopNettyServer(){
        //关闭netty服务
        log.info("关闭netty服务，当前线程：{}",Thread.currentThread());
        if(!bossEventLoopGroup.isShutdown()){
            bossEventLoopGroup.shutdownGracefully();
        }
        if(!workEventLoopGroup.isShutdown()){
            workEventLoopGroup.shutdownGracefully();
        }
    }

}
