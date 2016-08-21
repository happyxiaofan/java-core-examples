package com.rhwayfun.examples.guava.base;

import static com.google.common.base.Preconditions.*;

/**
 * @author ZhongCB
 * @date 2016年08月21日 15:42
 * @description
 */
public class PreconditionTest {
    public static void main(String[] args) {
        // 检查参数是否符合条件
        int i = 6;
        checkArgument(i > 0, "arg is %s but expected nonnegative", i);
        // 检查indx作为索引值对某个列表、字符串或者数组是否有效。检查通过的条件是index>=0 && index < size
        checkElementIndex(2,3);
        // 检查非空
        checkNotNull(1);
        // 检查对象的状态
        checkState(i > 0);
    }
}
