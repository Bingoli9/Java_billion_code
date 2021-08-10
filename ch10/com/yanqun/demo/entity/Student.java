package com.yanqun.demo.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
//通过@Component将此类的对象，纳入到Spring IOC容器中
@Component
//绑定配置文件中属性名的前缀（在yaml中，就是顶级的属性名）
@ConfigurationProperties(prefix = "student")
//开启数据校验
@Validated
//@PropertySource(value={"classpath:conf.properties"})
public class Student {
//	@Value("${student.someone2:颜群}")
	private String name;
	@Value("${random.int(100)}")
	private int age;
	// true:男 false:女
	@Value("false")
	private boolean sex;
	@Value("2019/09/19")
	private Date birthday;
//	@Value("{province: 陕\n西,city: 西安,zone: 莲湖区}")
	private Map<String, Object> location;
	
	private String[] hobbies;
	private List<String> skills;
	
	private Pet pet;
	//对邮箱值进行校验
	@Email
	private String email;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Map<String, Object> getLocation() {
		return location;
	}

	public void setLocation(Map<String, Object> location) {
		this.location = location;
	}

	public String[] getHobbies() {
		return hobbies;
	}

	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}

	public List<String> getSkills() {
		return skills;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ",age=" + age +
				", pet=" + pet
				+ ", email=" + email + "]";
//		return "Student [name=" + name + ", location="
//		+ location+ ", age=" + age + ", sex=" + sex + ", birthday=" + birthday  + ", hobbies=" + Arrays.toString(hobbies) + ", skills=" + skills + ", pet=" + pet
//		+ ", email=" + email + "]";
	}

}
