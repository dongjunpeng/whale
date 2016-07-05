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
public class InvokeLogger {
    
    private static final Logger LOG = LoggerFactory.getLogger(InvokeLogger.class);
    
    public static final void log(String service, String tag, Object request, Object response, long start, long timecost, int status) {
        StringBuilder sb = new StringBuilder("[service=").append(service)
                .append("][tag=").append(tag)
                .append("][request=").append(request)
                .append("][response=").append(response)
                .append("][start=").append(start)
                .append("][timecost=").append(timecost)
                .append("][status=").append(status).append("]");
        LOG.info(sb.toString());
    }

}
