package com.company.bankskn.logger;


/**
 * Logger class for printing account balance information
 * after transaction.
 */

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class Logger {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Logger.class.getName());

    public static synchronized void logMessage(String str) throws IOException {
        LOGGER.log(Level.INFO, str);
//        FileHandler fileHandler = new FileHandler(Logger.class.getName() + ".log");
//        LOGGER.addHandler(fileHandler);
    }
}