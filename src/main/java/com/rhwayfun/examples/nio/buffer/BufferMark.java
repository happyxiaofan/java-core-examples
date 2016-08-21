package com.rhwayfun.examples.nio.buffer;

import java.nio.CharBuffer;

/**
 * Created with IntelliJ IDEA.
 *
 * @description: 使用mark方法记录读取的位置
 * @author: rhwayfun
 * @since: 2016-04-17 下午9:02
 */
public class BufferMark {
    public static void main(String[] args) {
        String s = "Mellow";
        CharBuffer buffer = CharBuffer.allocate(10);
        for (int i = 0; i < s.length(); i++){
            buffer.put(s.charAt(i));
        }
        // 执行上述代码后，position的位置是6，limit和capacity都是10
        // 执行flip方法翻转
        buffer.flip();
        // 现在position的位置是0，limit是原来position的值，也就是6
        // 把2位置进行标记，并将当前position的值设为4
        buffer.position(2).mark().position(4);
        // 将当前position位置后的字符释放
        System.out.print("first output: ");
        while (buffer.hasRemaining()){
            System.out.print(buffer.get() + " ");
        }
        // 这样，字符o和字符w就被释放了
        // 现在position的位置是6
        // 调用reset方法恢复之前被标记的位置
        buffer.reset();
        // 那么再次将剩余的字符释放，根据分析，输出的字符应该依次是llow
        System.out.println();
        System.out.print("second output: ");
        while (buffer.hasRemaining()){
            System.out.print(buffer.get() + " ");
        }
    }
}
