package com.baidu.brpc.example.http.proto;

import com.baidu.brpc.example.standard.EchoServiceImpl;
import com.baidu.brpc.server.RpcServer;
import com.baidu.brpc.server.RpcServerOptions;

public class RpcServerTest {

  public static void main(String[] args) {
    int port = 8080;
    if (args.length == 1) {
      port = Integer.valueOf(args[0]);
    }

    RpcServerOptions options = new RpcServerOptions();
    RpcServer rpcServer = new RpcServer(port, options);
    rpcServer.registerService(new EchoServiceImpl());
    rpcServer.start();

    // make server keep running
    synchronized (RpcServerTest.class) {
      try {
        RpcServerTest.class.wait();
      } catch (Throwable e) {
        // ignore
      }
    }
  }
}
