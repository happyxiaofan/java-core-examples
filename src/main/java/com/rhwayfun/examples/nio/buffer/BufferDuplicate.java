package com.rhwayfun.examples.nio.buffer;

import java.nio.CharBuffer;

/**
 * Created with IntelliJ IDEA.
 *
 * @description: 复制缓冲区
 * @author: rhwayfun
 * @since: 2016-04-18 上午9:41
 */
public class BufferDuplicate {
    public static void main(String[] args) {
        String s = "windows";
        CharBuffer buffer = CharBuffer.allocate(10);
        for (int i = 0; i < s.length(); i++) buffer.put(s.charAt(i));
        buffer.flip();
        System.out.println("position-->" + buffer.position()
                + "\nlimit-->" + buffer.limit()
                + "\ncapacity-->" + buffer.capacity()
                + "\nreadonly-->" + buffer.isReadOnly());
        // 修改position和limit后将缓冲区复制给一个新的缓冲区
        buffer.position(3).limit(6).mark().position(5);
        // 执行以上的操作，dupBuffer的元素就是dow了吗？
        CharBuffer dupBuffer = buffer.duplicate();

        // 还原buffer
        buffer.clear();

        // 测试dupBuffer
        // 实际上使用duplicate方法复制的是原来缓冲区的引用，但是
        // 不会复制数据，就是两个缓冲区共享数据，其中一个缓冲区的
        // 对数据的修改可以立即被另一个缓冲区对象感知
        // 对dupBuffer的第一个位置的元素修改为p
        dupBuffer.put('p');
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get() + "\t");
        }
        System.out.println("\n**********");
        while (dupBuffer.hasRemaining()){
            System.out.print(dupBuffer.get() + "\t");
        }
        System.out.println(buffer.equals(dupBuffer));
    }
}
