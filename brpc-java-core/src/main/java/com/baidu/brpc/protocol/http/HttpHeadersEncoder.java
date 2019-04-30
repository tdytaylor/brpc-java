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
package com.baidu.brpc.protocol.http;

import static com.baidu.brpc.protocol.http.BrpcHttpObjectEncoder.CRLF_SHORT;
import static io.netty.handler.codec.http.HttpConstants.COLON;
import static io.netty.handler.codec.http.HttpConstants.SP;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;

public class HttpHeadersEncoder {

  private static final int COLON_AND_SPACE_SHORT = (COLON << 8) | SP;

  private HttpHeadersEncoder() {
  }

  static void encoderHeader(CharSequence name, CharSequence value, ByteBuf buf) {
    final int nameLen = name.length();
    final int valueLen = value.length();
    final int entryLen = nameLen + valueLen + 4;
    buf.ensureWritable(entryLen);
    int offset = buf.writerIndex();
    writeAscii(buf, offset, name);
    offset += nameLen;
    ByteBufUtil.setShortBE(buf, offset, COLON_AND_SPACE_SHORT);
    offset += 2;
    writeAscii(buf, offset, value);
    offset += valueLen;
    ByteBufUtil.setShortBE(buf, offset, CRLF_SHORT);
    offset += 2;
    buf.writerIndex(offset);
  }

  private static void writeAscii(ByteBuf buf, int offset, CharSequence value) {
    if (value instanceof AsciiString) {
      ByteBufUtil.copy((AsciiString) value, 0, buf, offset, value.length());
    } else {
      buf.setCharSequence(offset, value, CharsetUtil.US_ASCII);
    }
  }
}