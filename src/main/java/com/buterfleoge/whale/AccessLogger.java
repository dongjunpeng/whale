/**
 *
 */
package com.buterfleoge.whale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiezhenzong
 *
 */
public class AccessLogger {

    private static final Logger LOG = LoggerFactory.getLogger(AccessLogger.class);

    public static void log(String log) {
        LOG.info(log);
    }

}
