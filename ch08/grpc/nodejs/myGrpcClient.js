var proto_file = "./Student.proto";
var myGrpc = require('grpc');
var myGrpcService = myGrpc.load(proto_file).com.yanqun.grpc.proto;
//创建客户端对象
var client = new myGrpcService.StudentService("127.0.0.1:8888",myGrpc.credentials.createInsecure());
//调用服务端提供的queryStudentNameById()方法，并接收返回值
client.queryStudentNameById({id:1},function(error,result) {
    console.log(result.name) ;
});