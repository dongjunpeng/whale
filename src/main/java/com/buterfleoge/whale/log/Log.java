package com.buterfleoge.whale.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log
 *
 * @author xiezhenzong
 *
 */
public abstract class Log {

    private static final Logger LOG = LoggerFactory.getLogger(Log.class);

    public static void info(String info) {
        LOG.info(info);
    }

    public static void error(String message, Exception exception) {
        LOG.error(message, exception);
    }

}
