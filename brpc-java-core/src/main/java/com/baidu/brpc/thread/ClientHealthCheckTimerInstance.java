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

import com.baidu.brpc.utils.CustomThreadFactory;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;

public class ClientHealthCheckTimerInstance {

  private static volatile Timer healthCheckTimer;

  private ClientHealthCheckTimerInstance() {

  }

  public static Timer getOrCreateInstance() {

    if (healthCheckTimer == null) {
      synchronized (ClientHealthCheckTimerInstance.class) {
        if (healthCheckTimer == null) {
          healthCheckTimer =
              new HashedWheelTimer(new CustomThreadFactory("health-check-timer-thread"));
        }
      }
    }

    return healthCheckTimer;
  }

  public static Timer getInstance() {

    return healthCheckTimer;
  }
}
