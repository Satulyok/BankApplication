package com.company.bankskn.logger;


/**
 * AccountLogger class for printing account balance information
 * after transaction.
 */

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountLogger {

    private static final Logger LOGGER = Logger.getLogger(AccountLogger.class.getName());

    public static void accountLogMessage () throws IOException {
        LOGGER.log(Level.INFO, "Transaction was successful!");
        FileHandler fileHandler = new FileHandler(AccountLogger.class.getName() + ".log");
        LOGGER.addHandler(fileHandler);
    }
}