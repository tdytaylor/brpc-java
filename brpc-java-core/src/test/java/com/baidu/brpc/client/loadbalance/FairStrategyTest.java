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
package com.baidu.brpc.client.loadbalance;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.Assert;
import org.junit.Test;

public class FairStrategyTest extends FairStrategy {

  @Test
  public void test1() {
    Queue<Node> leafNodes = new LinkedList<Node>();
    Node node1 = new Node(1, 8, true);
    Node node2 = new Node(1, 12, true);
    Node node3 = new Node(1, 10, true);
    leafNodes.add(node1);
    leafNodes.add(node2);
    leafNodes.add(node3);
    Node root = generateWeightTreeByLeafNodes(leafNodes);
    Node selected = searchNode(root, 6);
    Assert.assertSame(node1, selected);
    selected = searchNode(root, 18);
    Assert.assertSame(node2, selected);
    selected = searchNode(root, 22);
    Assert.assertSame(node3, selected);
  }

  @Test
  public void test2() {
    Queue<Node> leafNodes = new LinkedList<Node>();
    Node node1 = new Node(1, 8, true);
    Node node2 = new Node(1, 12, true);
    Node node3 = new Node(1, 8, true);
    Node node4 = new Node(1, 2, true);
    leafNodes.add(node1);
    leafNodes.add(node2);
    leafNodes.add(node3);
    leafNodes.add(node4);
    Node root = generateWeightTreeByLeafNodes(leafNodes);
    Node selected = searchNode(root, 22);
    Assert.assertSame(node3, selected);
    selected = searchNode(root, 30);
    Assert.assertSame(node4, selected);
  }
}
