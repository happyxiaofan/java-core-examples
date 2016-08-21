package com.rhwayfun.examples.ds.r0413;

import com.alibaba.fastjson.JSON;

/**
 * Created by rhwayfun on 16-4-13.
 */
public class JSONDemo {



    public static void main(String[] args){
        Person person = new Person(12,"Jim");

        //序列化
        String jsonObject = JSON.toJSONString(person);
        System.out.println(jsonObject);

        //反序列化
        Person person1 = JSON.parseObject(jsonObject,Person.class);
        System.out.println(person1.getId() + "-->" + person1.getName());
    }
}
