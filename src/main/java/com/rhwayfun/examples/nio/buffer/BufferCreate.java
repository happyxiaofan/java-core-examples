package com.rhwayfun.examples.nio.buffer;

import java.nio.CharBuffer;

/**
 * Created with IntelliJ IDEA.
 *
 * @description: 使用外部数组创建缓冲区
 * @author: rhwayfun
 * @since: 2016-04-18 上午9:30
 */
public class BufferCreate {
    public static void main(String[] args) {
        char[] ar = new StringBuilder().append("java nio").toString().toCharArray();

        // 使用wrap方法复制一个缓冲区，复制的缓冲区并不是ar的子集
        // 该缓冲区仍然可以存取整个数组的元素
        CharBuffer buffer = CharBuffer.wrap(ar);
        while (buffer.hasRemaining()){
            System.out.print(buffer.get() + " ");
        }

        System.out.println();
        CharBuffer buffer2 = CharBuffer.wrap(ar,4,4);
        while (buffer2.hasRemaining()){
            System.out.print(buffer2.get() + " ");
        }


    }
}
