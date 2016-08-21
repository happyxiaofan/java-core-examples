package com.rhwayfun.examples.ds.r0413;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by rhwayfun on 16-4-13.
 */
public class Client {

    public void start() {
        try {
            //接口名称
            String interfaceName = SayHelloInterface.class.getName();
            //需要执行的远程方法
            Method method = SayHelloInterface.class.getMethod("sayHello",String.class);
            //传递的参数
            Object[] args = new Object[]{"hello"};

            //打开socket连接
            Socket client = new Socket("127.0.0.1",1234);

            //得到输出流
            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            //写入要传输到服务端的接口名称
            os.writeUTF(interfaceName);
            //写入方法
            os.writeUTF(method.getName());
            //方法需要的参数类型
            os.writeObject(method.getParameterTypes());
            //方法的参数
            os.writeObject(args);
            System.out.println("client sent!");

            //得到输入流，用于接收服务端返回的信息
            ObjectInputStream is = new ObjectInputStream(client.getInputStream());
            Object o = is.readObject();
            if (o instanceof String){
                System.out.println("client received " + o);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Client client = new Client();
        client.start();
    }
}
