var proto_file = "./Student.proto";
var myGrpc = require('grpc');
var myGrpcService = myGrpc.load(proto_file).com.yanqun.grpc.proto;

var server = new myGrpc.Server();
//注册服务方法
server.addService(myGrpcService.StudentService.service,{
    queryStudentNameById:findStudentNameById
});
server.bind("127.0.0.1:8888",myGrpc.ServerCredentials.createInsecure()) ;
server.start() ;
//实现服务方法
function findStudentNameById(call,callback){
    console.log("接收到的请求：id="+call.request.id) ;
    console.log("向客户端做出响应：name=zs") ;
    callback(null,{name:"zs"});
}