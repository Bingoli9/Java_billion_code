public class Test
{
	//根据学号查询学生
	private static Student queryAStudent(int stuno) throws Exception {
		Student student = new Student();
		String value = jedis.hget("student", String.valueOf(stuno));
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		if(value != null){
			student = JsonUtils.jsonStrToObject(value,Student.class) ;
		}else{
			//如果redis中不存在，则先从mysql中读取
			//connection是数据库连接对象
			pstmt = connection.prepareStatement("select * from student where stuno = ?") ;
			pstmt.setInt(1,stuno );
			rs = pstmt.executeQuery();
			if(rs.next()){
				stuno = rs.getInt("stuno");
				String stuname = rs.getString("stuname");
				int gradeId = rs.getInt("gradeId");
				student = new Student(stuno,stuname,gradeId);
				//然后将读取的数据放入redis
				jedis.hset("student",String.valueOf(stuno),
				JsonUtils.objectToJsonStr(student));
			}
		}
		//关闭rs、pstmt、connection
		return student ;
	}
	
	
	//增加
private static void addAStudent(Student student) throws Exception {

     PreparedStatement pstmt = connection.prepareStatement("insert into 
        student values(?,?,?)");
    pstmt.setInt(1,student.getStuno());
    pstmt.setString(2,student.getStuname());
    pstmt.setInt(3,student.getGradeId());
    int result = pstmt.executeUpdate();
    if(result >0){
    //向redis中写入数据
    jedis.hset("student", String.valueOf(student.getStuno()),
        JsonUtils.objectToJsonStr(student));  
    }
    //关闭rs、pstmt、connection
}
//删除
private static void deleteAStudent(int stuno) throws SQLException {

    PreparedStatement pstmt = connection.prepareStatement("delete from student 
    where stuno = ?");
    pstmt.setInt(1,stuno);
    int result = pstmt.executeUpdate();
    if(result >0){
        //从redis中删除
        jedis.hdel("student",String.valueOf(stuno));
    }
    System.out.println("删除成功！");
    //关闭rs、pstmt、connection
}

//修改，根据stuno修改其他内容
private static void updateAStudent(Student student) throws Exception {
   
    PreparedStatement pstmt = connection.prepareStatement("update student set 
	stuname = ? ,gradeId = ? where stuno = ? ");
    pstmt.setString(1,student.getStuname());
    pstmt.setInt(2,student.getGradeId());
    pstmt.setInt(3,student.getStuno());
    int result = pstmt.executeUpdate();
    if(result >0){
        //覆盖redis中的值
        jedis.hset("student",String.valueOf(student.getStuno()),
               JsonUtils.objectToJsonStr(student)); 
    }
    //关闭rs、pstmt、connection
}
}