/*
 * Copyright (c) 2018 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baidu.brpc.thread;

import com.baidu.brpc.utils.ThreadPool;
import io.netty.channel.EventLoopGroup;
import io.netty.util.Timer;
import java.util.concurrent.ExecutorService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShutDownManager {

  private static volatile ShutDownManager clientShutDownManager;

  static {
    // do clean work when jvm shut down

    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      @Override
      public void run() {
        log.info("Brpc do clean work...");

        EventLoopGroup ioThread = BrpcIoThreadPoolInstance.getInstance();
        ThreadPool workThread = BrpcWorkThreadPoolInstance.getInstance();
        ExecutorService clientCallBackThread = ClientCallBackThreadPoolInstance.getInstance();
        Timer clientHealthCheckerTimer = ClientHealthCheckTimerInstance.getInstance();
        Timer clientTimeOutTimer = ClientTimeoutTimerInstance.getInstance();

        if (clientCallBackThread != null) {
          clientCallBackThread.shutdownNow();
        }
        if (ioThread != null) {
          ioThread.shutdownGracefully();
        }
        if (workThread != null) {
          workThread.stop();
        }
        if (clientHealthCheckerTimer != null) {
          clientHealthCheckerTimer.stop();
        }
        if (clientTimeOutTimer != null) {
          clientTimeOutTimer.stop();
        }
      }
    }));
  }

  private ShutDownManager() {

  }

  public static ShutDownManager getInstance() {

    if (clientShutDownManager == null) {
      synchronized (ShutDownManager.class) {
        if (clientShutDownManager == null) {
          clientShutDownManager = new ShutDownManager();
        }
      }
    }
    return clientShutDownManager;
  }
}
