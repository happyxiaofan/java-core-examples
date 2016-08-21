package com.rhwayfun.examples.nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * Created with IntelliJ IDEA.
 *
 * @description: 从字节缓冲区创建视图缓冲区
 * @author: rhwayfun
 * @since: 2016-04-18 上午10:39
 */
public class BufferCharView {
    public static void main(String[] args) {
        // 创建一个字节缓冲区，使用大端字节顺序
        ByteBuffer byteBuffer = ByteBuffer.allocate(7).order(ByteOrder.BIG_ENDIAN);
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        byteBuffer.put(0, (byte) 0);
        byteBuffer.put(1, (byte) 'H');
        byteBuffer.put(2, (byte) 0);
        byteBuffer.put(3, (byte) 'i');
        byteBuffer.put(4, (byte) 0);
        byteBuffer.put(5, (byte) '!');
        byteBuffer.put(6, (byte) 0);

        println(byteBuffer);
        println(charBuffer);
    }

    private static void println(Buffer buffer) {
        System.out.println("position-->" + buffer.position()
            + "; limit-->" + buffer.limit()
            + "; capacity-->" + buffer.capacity()
            + "; buffer-->" + buffer.toString());
    }
}
