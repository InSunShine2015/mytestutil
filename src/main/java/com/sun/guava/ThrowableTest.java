package com.sun.guava;

import com.google.common.base.Throwables;

public class ThrowableTest {
	public static void main(String[] args) throws Throwable {
        try {
            throw new Exception();
        } catch (Throwable t) {
            String ss = Throwables.getStackTraceAsString(t);
            System.out.println("ss:"+ss);
            throw t;
        }
	}
}
