package com.java.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * Created by lyl_b on 2017/12/20.
 */
public class ServerSocketChannel {

    public static void main(String[] args) throws Exception {
        java.nio.channels.ServerSocketChannel serverSocketChannel = java.nio.channels.ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        while(true){
            java.nio.channels.SocketChannel socketChannel = serverSocketChannel.accept();
            while (true){
                if(socketChannel != null ){

                    ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                    try {
                        socketChannel.read(byteBuffer);
                        System.out.println(byteBuffer.toString());
                    } catch (IOException e) {
                        System.out.println("关闭了一个链接");
                        socketChannel = null;
                    }
                }
            }
        }
    }
}
