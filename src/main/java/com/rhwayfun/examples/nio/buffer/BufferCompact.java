package com.rhwayfun.examples.nio.buffer;

import java.nio.CharBuffer;

/**
 * Created with IntelliJ IDEA.
 *
 * @description: 使用compact方法释放一部分数据
 * @author: rhwayfun
 * @since: 2016-04-17 下午8:36
 */
public class BufferCompact {
    public static void main(String[] args) {
        String s = "hello";

        CharBuffer buffer = CharBuffer.allocate(10);
        for (int i = 0; i < s.length(); i++){
            buffer.put(s.charAt(i));
        }

        // 将第一个位置的字符h换为M，并填入一个新的字符w
        buffer.put(0,'M').put('w');

        // 现在缓冲区的元素是Mellow，position的位置是6，limit和capacity都是10
        // 调用flip方法得到有效元素的位置，其实也就是position的位置
        buffer.flip();

        // 将M和e两个个字符释放掉
        System.out.println("release char " + buffer.get());
        System.out.println("release char " + buffer.get());

        // 调用compact方法可以回收M和e字符占用的空间
        // 调用compact后position位置是4，
        // 0-3位置的字符依次是llow，4-5位置的字符依次是ow
        // 虽然4-5位置有字符，但是调用put方法后会被重写
        // 并且调用compact方法后limit又回到了capacity的位置，也就是10
        buffer.compact();

        // 将4-5位置的字符修改为ab
        buffer.put('a').put('b');

        // 输出缓冲区的元素，根据上面的分析，最后输出的字符应该是llowab
        // 需要先调用flip方法将position设为0，这样才能读到完整的字符
        buffer.flip();
        while (buffer.hasRemaining()){
            System.out.println("print output " + buffer.get());
        }
    }
}
