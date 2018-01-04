package com.java.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/**
 * Created by lyl_b on 2017/12/20.
 */
public class SocketChannel2 {
    public static void main(String[] args) throws Exception{
        writeChannel();
    }

    public static void writeChannel() throws Exception{
        java.nio.channels.SocketChannel socketChannel = java.nio.channels.SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888));
        socketChannel.configureBlocking(false);
        while(true){

            String data = "Hello Word!";
            ByteBuffer byteBuffer = ByteBuffer.allocate(data.getBytes().length);
            byteBuffer.clear();
            byteBuffer.put(data.getBytes());
            byteBuffer.flip();
            while(byteBuffer.hasRemaining()){
                socketChannel.write(byteBuffer);
            }
        }
    }

}
