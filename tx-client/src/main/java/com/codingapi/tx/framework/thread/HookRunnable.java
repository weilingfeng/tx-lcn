package com.codingapi.tx.framework.thread;

import com.codingapi.tx.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * create by lorne on 2017/8/9
 */
public abstract class HookRunnable implements Runnable {

    private volatile boolean hasOver;

    private Logger logger = LoggerFactory.getLogger(HookRunnable.class);
    @Override
    public void run() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Constants.hasExit = true;
                while (!hasOver) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        if (!Constants.hasExit) {
            Runtime.getRuntime().addShutdownHook(thread);
        } else {
            logger.info("jvm has exit..");
            return;
        }
        try {
            run0();
        } finally {

            hasOver = true;

            if (!thread.isAlive()) {
                Runtime.getRuntime().removeShutdownHook(thread);
            }
        }
    }

    public abstract void run0();

}
