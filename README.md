##Build protoBuf
Download and install protocol Buffer app from https://developers.google.com/protocol-buffers/docs/downloads.
Execute the command: protoc -I=SRC_DIR --java_out=DST_DIR SRC_DIR/HelloService.proto. This will generate required java files required to communicate with rpc server or client
For generating typescript(experimental) files
Install protoc-gen-grpc-web pluginh
Execute command:  protoc -I=DIR echo.proto --js_out=import_style=typescript:generated --grpc-web_out=import_style=typescript,mode=grpcwebtext:generated. The ts codes will be available in generated folder.

##JAVA
mvn install
mvn exec:java -Dexec.mainClass="org.baeldung.grpc.server.GrpcServer" -Dexec.classpathScope=runtime

##Envoy
cd envoy
docker build -t grpc-web-envoy .
docker run -d -p 9090:9090 grpc-web-envoy .

##Angular
ng serve. Open browser. Then check console
