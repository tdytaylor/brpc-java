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

import com.baidu.brpc.Controller;
import com.baidu.brpc.RpcMethodInfo;
import com.baidu.brpc.client.RpcCallback;
import com.baidu.brpc.exceptions.RpcException;
import com.baidu.brpc.protocol.nshead.NSHead;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import java.lang.reflect.Method;
import java.util.Map;

public interface Request {

  /**
   * The msg param is the real request content to sent by netty. For http protocols, the msg is an
   * instance of {@link FullHttpRequest}. For tcp protocols, the msg may be an instance of byte[].
   *
   * @see HttpRequest
   * @see RpcRequest
   */
  Object getMsg();

  void setMsg(Object o);

  long getLogId();

  void setLogId(long logId);

  Object getTarget();

  void setTarget(Object obj);

  Method getTargetMethod();

  void setTargetMethod(Method method);

  RpcMethodInfo getRpcMethodInfo();

  void setRpcMethodInfo(RpcMethodInfo rpcMethodInfo);

  String getServiceName();

  void setServiceName(String serviceName);

  String getMethodName();

  void setMethodName(String methodName);

  Object[] getArgs();

  void setArgs(Object[] newArgs);

  Map<String, String> getKvAttachment();

  void setKvAttachment(Map<String, String> requestKvAttachment);

  ByteBuf getBinaryAttachment();

  void setBinaryAttachment(ByteBuf requestBinaryAttachment);

  int getCompressType();

  void setCompressType(int number);

  RpcException getException();

  void setException(RpcException e);

  Channel getChannel();

  void setChannel(Channel channel);

  NSHead getNsHead();

  void setNsHead(NSHead nsHead);

  Request retain();

  void release();

  void reset();

  String getAuth();

  void setAuth(String auth);

  Long getTraceId();

  void setTraceId(Long traceId);

  Long getSpanId();

  void setSpanId(Long spanId);

  Long getParentSpanId();

  void setParentSpanId(Long parentSpanId);

  Controller getController();

  void setController(Controller controller);

  RpcCallback getCallback();

  void setCallback(RpcCallback callback);
}
