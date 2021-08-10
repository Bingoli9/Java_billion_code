var thrift = require('thrift');
var StudentService = require('./StudentService.js');
var ttypes = require('./Student_types');

var transport = thrift.TFramedTransport;
var protocol = thrift.TBinaryProtocol;
var options = {transport: transport, protocol: protocol};
var connection = thrift.createConnection("127.0.0.1", 8888, options);

//创建客户端
client = thrift.createClient(StudentService, connection);

//处理异常
connection.on('error', function(err) {
    console.error(err);
});

client.addStudent("ww",25,function(err,result){
    console.log(result?"增加成功":"增加失败");
});

client.queryStudents(function(err,result){
    result.forEach(function(student,index){
        console.log("查询结果如下：")
        console.log(student.name + "," +student.age)
    });
});



