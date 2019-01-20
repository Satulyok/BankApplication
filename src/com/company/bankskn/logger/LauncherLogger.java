package com.company.bankskn.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LauncherLogger implements Runnable{

    private static final Logger LOGGER = Logger.getLogger(LauncherLogger.class.getName());

    public static void launcherLogMessage(){
    }

    @Override
    public void run() {
        try {
            LOGGER.log(Level.INFO, "Please wait! Generating random card...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(Thread.class.getName() + " is interrupted.");
        }
    }
}