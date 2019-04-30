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

import io.netty.util.concurrent.FastThreadLocal;
import lombok.Getter;
import lombok.Setter;

/**
 * Bprc tcp request implementation, used for tcp protocols.
 *
 * @author wangjiayin@baidu.com
 * @since 2018-12-26
 */
@Setter
@Getter
public class RpcRequest extends AbstractRequest {

  private static final FastThreadLocal<RpcRequest> CURRENT_RPC_REQUEST = new FastThreadLocal<RpcRequest>() {
    @Override
    protected RpcRequest initialValue() {
      return new RpcRequest();
    }
  };

  public static RpcRequest getRpcRequest() {
    return CURRENT_RPC_REQUEST.get();
  }

}
