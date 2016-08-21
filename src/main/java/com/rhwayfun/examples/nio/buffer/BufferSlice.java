package com.rhwayfun.examples.nio.buffer;

import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * Created with IntelliJ IDEA.
 *
 * @description: 分割缓冲区
 * @author: rhwayfun
 * @since: 2016-04-18 上午10:05
 */
public class BufferSlice {
    public static void main(String[] args) {
        String s = "project";
        CharBuffer buffer = CharBuffer.wrap(s);
        buffer.position(0).limit(3);
        // 将原来的缓冲区分割出一部分给新的缓冲区对象
        // 但是原来缓冲区的数据无影响
        CharBuffer sliceBuffer = buffer.slice();
        buffer.clear();

        while (buffer.hasRemaining()){
            System.out.print(buffer.get() + "\t");
        }
        System.out.println();
        while (sliceBuffer.hasRemaining()){
            System.out.print(sliceBuffer.get() + "\t");
        }
        System.out.println(ByteOrder.nativeOrder());
    }
}
