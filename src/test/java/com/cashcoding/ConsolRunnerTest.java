package com.cashcoding;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

/**
 * Created on 1/25/2019.
 */
public class ConsolRunnerTest {
    /**
     * act as a runner for the test Units
     * @param args
     */
    public static void main(String[] args) {
        JUnitCore jUnitCore = new JUnitCore();
        jUnitCore.addListener(new TextListener(System.out));
        jUnitCore.run(AppTest.class);
    }
}

