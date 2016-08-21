package com.rhwayfun.examples.guava.base;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhongCB
 * @date 2016年08月21日 16:15
 * @description
 */
public class ComparisonTest {
    public static void main(String[] args) {

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Slf4j
    class Person implements Comparable<Person>{

        private String lastName;
        private String firstName;
        private int zipCode;

        @Override
        public int compareTo(Person o) {

            /***
             * 原来的比较代码
             */
//            int cmp = lastName.compareTo(o.getLastName());
//            if (cmp != 0)
//                return cmp;
//            cmp = firstName.compareTo(o.getFirstName());
//            if (cmp != 0)
//                return cmp;
//            return Integer.compare(zipCode, o.getZipCode());

            return ComparisonChain.start()
                    .compare(this.lastName, o.getLastName())
                    .compare(this.firstName, o.getFirstName())
                    .compare(this.zipCode, o.getZipCode(), Ordering.<Integer>natural())
                    .result();
        }
    }
}
