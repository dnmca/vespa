// Copyright Verizon Media. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package ai.vespa.feed.client;

import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;

import java.io.Closeable;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Allows dispatch to a Vespa cluster.
 */
interface Cluster extends Closeable {

    /** Dispatch the request to the cluster, causing the response vessel to complete at a later time. */
    void dispatch(SimpleHttpRequest request, CompletableFuture<SimpleHttpResponse> vessel);

    @Override
    default void close() { }

    default Stats stats() { return new Stats(0, Collections.emptyMap(), 0, 0, 0, 0, 0, 0, 0); }

    class Stats {

        private final long requests;
        private final Map<Integer, Long> responsesByCode;
        private final long inflight;
        private final long exceptions;
        private final long averageLatencyMillis;
        private final long minLatencyMillis;
        private final long maxLatencyMillis;
        private final long bytesSent;
        private final long bytesReceived;

        public Stats(long requests, Map<Integer, Long> responsesByCode, long exceptions, long inflight,
                     long averageLatencyMillis, long minLatencyMillis, long maxLatencyMillis,
                     long bytesSent, long bytesReceived) {
            this.requests = requests;
            this.responsesByCode = responsesByCode;
            this.exceptions = exceptions;
            this.inflight = inflight;
            this.averageLatencyMillis = averageLatencyMillis;
            this.minLatencyMillis = minLatencyMillis;
            this.maxLatencyMillis = maxLatencyMillis;
            this.bytesSent = bytesSent;
            this.bytesReceived = bytesReceived;
        }

        public long requests() {
            return requests;
        }

        public long responses() {
            return requests - inflight;
        }

        public Map<Integer, Long> responsesByCode() {
            return responsesByCode;
        }

        public long exceptions() {
            return exceptions;
        }

        public long inflight() {
            return inflight;
        }

        public long averageLatencyMillis() {
            return averageLatencyMillis;
        }

        public long minLatencyMillis() {
            return minLatencyMillis;
        }

        public long maxLatencyMillis() {
            return maxLatencyMillis;
        }

        public long bytesSent() {
            return bytesSent;
        }

        public long bytesReceived() {
            return bytesReceived;
        }

        @Override
        public String toString() {
            return "Stats{" +
                   "requests=" + requests +
                   ", responsesByCode=" + responsesByCode +
                   ", exceptions=" + exceptions +
                   ", inflight=" + inflight +
                   ", averageLatencyMillis=" + averageLatencyMillis +
                   ", minLatencyMillis=" + minLatencyMillis +
                   ", maxLatencyMillis=" + maxLatencyMillis +
                   ", bytesSent=" + bytesSent +
                   ", bytesReceived=" + bytesReceived +
                   '}';
        }

    }

}
