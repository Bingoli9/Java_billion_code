var thrift = require("thrift");
var StudentService = require('./StudentService');
//启动服务器，默认只支持TBufferedTransport和TBinaryProtocol
var server;
var transport = thrift.TFramedTransport;
var protocol = thrift.TBinaryProtocol;
var options = {transport: transport, protocol: protocol};

server = thrift.createServer(StudentService, {
    addStudent:function(name,age){
        console.log("--NodeJs服务端，模拟增加操作--") ;
        console.log(name+","+age+"增加成功！") ;
        return true ;
    },
    queryStudents:function () {
        console.log("--NodeJs服务端，模拟查询操作--") ;
        var students=[{name:"zs",age:23},{name:"ls",age:24}];
        console.log("--查询完毕--") ;
        return students ;
    }
},options);
server.listen(8888);