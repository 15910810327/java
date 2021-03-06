package com.java.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Created by lyl_b on 2017/12/20.
 */
public class HansServer {
    // 用于检测所有Channel状态的Selector
    private Selector selector = null;
    private SelectionKey sk = null;

    public void init() throws IOException {

        new ServerThread().start();
        String line = null;
        // 创建键盘输入流
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            // 读取键盘输入
            line = scan.nextLine();
            // 遍历该selector里注册的所有SelectKey
            for (SelectionKey key : selector.keys()) {
                // 获取该key对应的Channel
                Channel targetChannel = key.channel();
                // 如果该channel是SocketChannel对象
                if (targetChannel instanceof SocketChannel) {
                    // 将读到的内容写入该Channel中
                    SocketChannel dest = (SocketChannel) targetChannel;
                    dest.write(StandardCharsets.UTF_8.encode(line));
                }
            }
        }
    }

    private class ServerThread extends Thread{
            public void run() {
                try{
                    selector = Selector.open();
                    // 通过open方法来打开一个未绑定的ServerSocketChannel实例
                    ServerSocketChannel server = ServerSocketChannel.open();
                    InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 30000);
                    // 将该ServerSocketChannel绑定到指定IP地址
                    server.socket().bind(isa);
                    // 设置ServerSocket以非阻塞方式工作
                    server.configureBlocking(false);
                    // 将server注册到指定Selector对象
                    server.register(selector, SelectionKey.OP_ACCEPT);
                    while (selector.select() > 0) {
                        // 依次处理selector上的每个已选择的SelectionKey
                        for (SelectionKey sk : selector.selectedKeys()) {
                            // 如果sk对应的通道包含客户端的连接请求
                            if (sk.isAcceptable()) {
                                // 调用accept方法接受连接，产生服务器端对应的SocketChannel
                                SocketChannel sc = server.accept();
                                // 设置采用非阻塞模式
                                sc.configureBlocking(false);
                                // 将该SocketChannel也注册到selector
                                sc.register(selector, SelectionKey.OP_READ);
                            }
                            // 如果sk对应的通道有数据需要读取
                            if (sk.isReadable()) {
                                // 获取该SelectionKey对应的Channel，该Channel中有可读的数据
                                SocketChannel sc = (SocketChannel) sk.channel();
                                // 定义准备执行读取数据的ByteBuffer
                                ByteBuffer buff = ByteBuffer.allocate(1024);
                                String content = "";
                                // 开始读取数据
                                try {
                                    while (sc.read(buff) > 0) {
                                        buff.flip();
                                        content += StandardCharsets.UTF_8.decode(buff);
                                    }
                                    // 打印从该sk对应的Channel里读取到的数据
                                    System.out.println("Client：" + content);
                                }
                                // 如果捕捉到该sk对应的Channel出现了异常，即表明该Channel
                                // 对应的Client出现了问题，所以从Selector中取消sk的注册
                                catch (IOException ex) {
                                    // 从Selector中删除指定的SelectionKey
                                    sk.cancel();
                                    if (sk.channel() != null) {
                                        sk.channel().close();
                                    }
                                }
                            }
                            // 从selector上的已选择Key集中删除正在处理的SelectionKey
//                            selector.selectedKeys().remove(sk);
                        }
                    }
                }catch (Exception e){

                }
        }
    }


    public static void main(String[] args) throws IOException {
        new HansServer().init();
    }
}