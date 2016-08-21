package com.rhwayfun.examples.guava.base;

import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author ZhongCB
 * @date 2016年08月21日 14:58
 * @description
 */
public class OptionalTest {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // Optional.of(T)：创建指定引用的Optional实例，若引用为null则快速失败
        ArrayList<Integer> reference = new ArrayList<>();
        reference.add(12);
        Optional<ArrayList<Integer>> optional = Optional.of(reference);
        // 返回Optional所包含的引用，若引用缺失，则抛出IlegalStateException
        ArrayList<Integer> list = optional.get();
        // 返回Optional所包含的引用，若引用缺失，返回指定的值
        ArrayList<Integer> or = optional.or(new ArrayList<Integer>());
        // 返回Optional所包含的引用，若引用缺失，返回null
        ArrayList<Integer> orNull = optional.orNull();
        // 返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合
        Set<ArrayList<Integer>> asSet = optional.asSet();
    }

}
