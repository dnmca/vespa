// Copyright 2017 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.container.logging;

import com.yahoo.container.core.AccessLogConfig;

import java.util.logging.Level;

/**
 * Log a message in Vespa JSON access log format.
 *
 * @author frodelu
 * @author Tony Vaagenes
 */
public final class JSONAccessLog implements RequestLogHandler {

    private final AccessLogHandler logHandler;
    private final JSONFormatter formatter;

    public JSONAccessLog(AccessLogConfig config) {
        logHandler = new AccessLogHandler(config.fileHandler());
        formatter = new JSONFormatter();
    }

    @Override
    public void log(RequestLogEntry entry) {
        logHandler.access.log(Level.INFO, formatter.format(entry) + '\n');
    }

    // TODO: This is never called. We should have a DI provider and call this method from its deconstruct.
    public void shutdown() {
        logHandler.shutdown();
    }

    void rotateNow() {
        logHandler.rotateNow();
    }

}
