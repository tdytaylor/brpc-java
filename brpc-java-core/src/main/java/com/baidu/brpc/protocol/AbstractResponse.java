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
package com.baidu.brpc.protocol;

import com.baidu.brpc.RpcMethodInfo;
import com.baidu.brpc.client.RpcFuture;
import com.baidu.brpc.protocol.nshead.NSHead;
import io.netty.buffer.ByteBuf;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractResponse implements Response {

  private long logId;
  private Object result;
  private Throwable exception;
  private RpcMethodInfo rpcMethodInfo;
  private RpcFuture rpcFuture;
  private Map<String, String> kvAttachment;
  private ByteBuf binaryAttachment;
  private int compressType;
  private NSHead nsHead;

  public void reset() {
    logId = -1;
    result = null;
    exception = null;
    rpcMethodInfo = null;
    rpcFuture = null;
    nsHead = null;
    kvAttachment = null;
    binaryAttachment = null;
    compressType = 0;
  }
}
