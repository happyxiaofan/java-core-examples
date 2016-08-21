package com.rhwayfun.examples.guava.base;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhongCB
 * @date 2016年08月21日 15:52
 * @description
 */
public class ObjectTest {
    public static void main(String[] args) {
        Objects.equal("a","a");
        Objects.equal(null, "a");
        Objects.equal("a", null);
        Objects.equal(null, null);
        T t = new T();
        t.setAge(12);
        t.setName("zh");
        int hashCode = Objects.hashCode(t);
        System.out.println(hashCode);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Slf4j
    static class T{
        private int age;
        private String name;
    }
}

