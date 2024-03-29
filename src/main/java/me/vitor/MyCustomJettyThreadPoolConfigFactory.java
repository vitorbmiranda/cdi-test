/*
 * Copyright 2019 Giacomo Lacava
 * Modified from Per Wendel's original JettyServerFactory.java.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package me.vitor;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import spark.embeddedserver.jetty.JettyServerFactory;

/**
 * where the actual Server() is returned, although it's mostly used for thread pool stuff
 */
public class MyCustomJettyThreadPoolConfigFactory implements JettyServerFactory {

    public Server create(int maxThreads, int minThreads, int threadTimeoutMillis) {
        Server server;
        /* ********* CUSTOMIZATION POINT ***************
        This is where you can tweak threadpool settings.
        Strictly speaking, it's also where Server is created,
        so you can add any extra parameter to that too.
        */
        if (maxThreads > 0) {
            int max = maxThreads;
            int min = (minThreads > 0) ? minThreads : 8;
            int idleTimeout = (threadTimeoutMillis > 0) ? threadTimeoutMillis : 60000;

            server = new Server(new QueuedThreadPool(max, min, idleTimeout));
        } else {
            server = new Server();
        }

        return server;
    }

    public Server create(ThreadPool threadPool) {
        return threadPool != null ? new Server(threadPool) : new Server();
    }

}


