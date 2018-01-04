package com.java.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * Created by lyl_b on 2017/12/18.
 */
public class FileChannel {

    public static void main(String[] args) throws Exception{
//        new FileChannel().setByte();
        new FileChannel().getByte();
    }

    /**
     * 使用fileChannel写入
     * @throws Exception
     */
    public void setByte() throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\JAVA\\IntelliJIDEA2016.3.3\\workspace\\java\\java-nio\\file\\fileChannel","rw");
        java.nio.channels.FileChannel inChannel = randomAccessFile.getChannel();
        ByteBuffer wirte = ByteBuffer.allocate(20);
        wirte.putInt(1);
        wirte.putFloat(1.1f);
        wirte.putFloat(1.12f);
        wirte.putFloat(6.12f);
        wirte.putFloat(9.12f);
        wirte.flip();
        inChannel.write(wirte);
        inChannel.close();
    }

    /**
     *
     * 使用fileChannel读取
     * @throws Exception
     */
    public void getByte() throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\JAVA\\IntelliJIDEA2016.3.3\\workspace\\java\\java-nio\\file\\fileChannel","rw");

        java.nio.channels.FileChannel inChannel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate((int)randomAccessFile.length());

        int bytesRead = inChannel.read(byteBuffer);

        while(bytesRead != -1){
            byteBuffer.flip();
            System.out.println(byteBuffer.getInt()+"----"+byteBuffer.getFloat()+"----"+byteBuffer.getFloat()+"----"+byteBuffer.getFloat()+"----"+byteBuffer.getFloat());

            byteBuffer.clear();
            bytesRead = inChannel.read(byteBuffer);
        }
        randomAccessFile.close();
    }
}
