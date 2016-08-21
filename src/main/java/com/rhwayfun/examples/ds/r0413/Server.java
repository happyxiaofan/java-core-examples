package com.rhwayfun.examples.ds.r0413;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rhwayfun on 16-4-13.
 */
public class Server {

    private Map<String,Object> services;

    {
        services = new HashMap<>();
        services.put(SayHelloInterface.class.getName(),new SayHelloService());
    }

    public void start(){
        try {
            ServerSocket server = new ServerSocket(1234);
            while (true){
                Socket socket = server.accept();
                System.out.println("accept a client!");
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                //读取接口名称
                String interfaceName = is.readUTF();
                //方法名称
                String methodName = is.readUTF();
                //参数类型
                Class<?>[] parameterTypes = (Class<?>[]) is.readObject();
                //参数值
                Object[] args = (Object[]) is.readObject();

                //执行调用
                Class service = Class.forName(interfaceName);
                //调用的对象
                Object obj = services.get(interfaceName);
                //调用的方法
                Method method = service.getMethod(methodName,parameterTypes);
                //使用obj调用服务端的方法
                Object res = method.invoke(obj, args);

                //得到输出流
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                os.writeObject(res);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Server().start();
    }
}
