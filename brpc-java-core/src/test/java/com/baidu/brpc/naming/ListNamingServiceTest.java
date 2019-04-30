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

package com.baidu.brpc.naming;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.baidu.brpc.client.instance.Endpoint;
import com.baidu.brpc.test.BaseMockitoTest;
import java.util.List;
import org.junit.Test;

public class ListNamingServiceTest extends BaseMockitoTest {

  @Test
  public void testUpdateServerList() {
    String serverList = "list://1.1.1.1:1111,2.2.2.2:2222";
    ListNamingService namingService = new ListNamingService(new BrpcURL(serverList));
    List<Endpoint> endPoints = namingService.lookup(null);
    assertThat(endPoints, hasItems(
        new Endpoint("1.1.1.1", 1111),
        new Endpoint("2.2.2.2", 2222)
    ));
    assertThat(endPoints.size(), is(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateServerListInvalid1() {
    ListNamingService namingService = new ListNamingService(new BrpcURL(""));
    namingService.lookup(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateServerListInvalid2() {
    ListNamingService namingService = new ListNamingService(new BrpcURL("1.1.1.1,2.2.2.2"));
    namingService.lookup(null);
  }

}