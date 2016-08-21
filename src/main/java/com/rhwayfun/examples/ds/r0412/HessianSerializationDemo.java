package com.rhwayfun.examples.ds.r0412;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by rhwayfun on 16-4-12.
 */
public class HessianSerializationDemo {

    public static void main(String[] agrs) throws IOException {
        // 字节数组，存放对象二进制字节流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Person对象
        Person person = new Person(1,"Tom");
        // 得到HessianOutput
        HessianOutput output = new HessianOutput(out);
        // 序列化对象
        output.writeObject(person);
        // 得到二进制字节流
        byte[] bytes = out.toByteArray();

        // 反序列化
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        // 创建HessianInput
        HessianInput input = new HessianInput(in);
        // 执行反序列化，读取对象
        Person person1 = (Person) input.readObject();
        System.out.println(person1.getId() + "-->" + person1.getName() + "-->" + Person.class.getName());
    }
}
