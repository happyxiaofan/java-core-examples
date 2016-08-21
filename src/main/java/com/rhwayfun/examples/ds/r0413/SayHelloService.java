package com.rhwayfun.examples.ds.r0413;

/**
 * Created by rhwayfun on 16-4-13.
 */
public class SayHelloService implements SayHelloInterface {

    @Override
    public String sayHello(String args) {
        if (args.equals("hello")){
            return "hello";
        }else {
            return "bye";
        }
    }

}
