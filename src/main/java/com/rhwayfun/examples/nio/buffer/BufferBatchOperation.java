package com.rhwayfun.examples.nio.buffer;

import java.nio.CharBuffer;

/**
 * Created with IntelliJ IDEA.
 *
 * @description: 批量操作缓冲区
 * @author: rhwayfuns
 * @since: 2016-04-18 上午9:12
 */
public class BufferBatchOperation {
    public static void main(String[] args) {
        copyToBigArray();
        copyToSmallArray();
        System.out.println();
        putStringToBuffer();
    }

    private static void copyToBigArray() {
        // 将一个缓冲区释放到一个大数组中
        String s = "hello,rhwayfun";
        CharBuffer buffer = CharBuffer.allocate(20);
        for (int i = 0; i < s.length(); i++){
            buffer.put(s.charAt(i));
        }
        buffer.flip();
        // 创建一个大数组
        char[] array = new char[100];
        // 如果直接使用get方法将元素释放到array数组中
        // 则实际上将数组的0位置到最后一个位置，这里是
        // 99,依次将缓冲区的元素释放到数组中。由于缓冲
        // 区的元素根本不够，所以会抛出BufferUnderflowException异常
        // buffer.get(array);
        int remaining = buffer.remaining();
        buffer.get(array,0,remaining);

        // 测试array数组
        for (char c : array){
            System.out.print(c + " ");
        }
    }

    private static void copyToSmallArray(){
        // 将一个缓冲区释放到一个大数组中
        String s = "hello,rhwayfun";
        CharBuffer buffer = CharBuffer.allocate(20);
        for (int i = 0; i < s.length(); i++){
            buffer.put(s.charAt(i));
        }
        buffer.flip();
        char[] array = new char[10];
        while (buffer.hasRemaining()){
            int length = Math.min(array.length,buffer.remaining());
            buffer.get(array,0,length);
        }
    }

    private static  void putStringToBuffer(){
        String s = "Good morning";
        CharBuffer buffer = CharBuffer.allocate(20);
        buffer.put(s);
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get() + " ");
        }
    }
}
