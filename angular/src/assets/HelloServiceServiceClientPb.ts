/**
 * @fileoverview gRPC-Web generated client stub for org.baeldung.grpc
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!


import * as grpcWeb from 'grpc-web';

import {
  HelloRequest,
  HelloResponse} from './HelloService_pb';

export class HelloServiceClient {
  client_: grpcWeb.AbstractClientBase;
  hostname_: string;
  credentials_: null | { [index: string]: string; };
  options_: null | { [index: string]: string; };

  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; }) {
    if (!options) options = {};
    if (!credentials) credentials = {};
    options['format'] = 'text';

    this.client_ = new grpcWeb.GrpcWebClientBase(options);
    this.hostname_ = hostname;
    this.credentials_ = credentials;
    this.options_ = options;
  }

  methodInfohello = new grpcWeb.AbstractClientBase.MethodInfo(
    HelloResponse,
    (request: HelloRequest) => {
      return request.serializeBinary();
    },
    HelloResponse.deserializeBinary
  );

  hello(
    request: HelloRequest,
    metadata: grpcWeb.Metadata | null,
    callback: (err: grpcWeb.Error,
               response: HelloResponse) => void) {
    return this.client_.rpcCall(
      this.hostname_ +
        '/org.baeldung.grpc.HelloService/hello',
      request,
      metadata || {},
      this.methodInfohello,
      callback);
  }

}

