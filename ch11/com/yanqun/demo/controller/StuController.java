package com.yanqun.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yanqun.demo.mapper.StuMapper;
import com.yanqun.demo.service.StudentService;
import com.yanqun.entity.Student;

//@CacheConfig(cacheNames = { "stu" })
@RestController
public class StuController {
	@Autowired
	StuMapper stuMapper;
	@Autowired
	StudentService studentService ;
	
	@Resource
	RedisTemplate<Integer,Student> redisTemplate ;
	@Autowired
	StringRedisTemplate stringRedisTemplate ;
//	@Autowired
//	RedisTemplate myRedisTemplate ;
	@RequestMapping("/addStu")
	public boolean addStu(Student stu) {
		return stuMapper.addStu(stu);
	}

//	@CacheEvict(cacheNames = { "stu" },allEntries=true,beforeInvocation=false)
	@CacheEvict(cacheNames = { "stu" },key="#stuno",beforeInvocation=false)
	@RequestMapping("/deleteStuByStuno/{stuno}")
	public boolean deleteStuByStuno(@PathVariable("stuno") Integer stuno) {
		return stuMapper.deleteStuByStuno(stuno);
	}

	@RequestMapping("/updateStuByStuno")
	public boolean updateStuByStuno(Student student) {
		return stuMapper.updateStuByStuno(student);
	}
	
	@RequestMapping("/testAsync")
	public void testAsync() throws InterruptedException {
		studentService.showStudents();
		for(int i=0;i<10;i++){
			Thread.sleep(100);
			System.out.println("testAsync");
		}
		
	}


	@RequestMapping("/queryStus")
	public List<Student> queryStus() {
		return stuMapper.queryStus();
	}

	// 通过Cacheable声明queryStuByStuno()方法是支持缓存的
	@Cacheable(cacheNames = { "stu" }, key = "#stuno")
	@RequestMapping("/queryStuByStuno/{stuno}")
	public Student queryStuByStuno(@PathVariable("stuno") Integer stuno) {
		return stuMapper.queryStuByStuno(stuno);
	}

	@Cacheable(cacheNames = { "stu" }, key = "#p0")
	@RequestMapping("/queryStuByStuno2")
	public Student queryStuByStuno2(@PathVariable("stuno") Integer stuno) {
		return stuMapper.queryStuByStuno(stuno);
	}

	@Cacheable(cacheNames = { "stu" }, key = "#stu.stuno")
	@RequestMapping("/queryStuByStuno3")
	public Student queryStuByStuno3(Student stu) {
		return stuMapper.queryStuByStuno(stu.getStuno());
	}

	@Cacheable(cacheNames = { "stu" }, key = "#p0.stuno")
	@RequestMapping("/queryStuByStuno4")
	public Student queryStuByStuno4(Student stu) {
		return stuMapper.queryStuByStuno(stu.getStuno());
	}

	@Cacheable(cacheNames = { "stu,abc" }, key = "#root.caches[0].name")
	@RequestMapping("/queryStuByStuno5")
	public Student queryStuByStuno5(Student stu) {
		return stuMapper.queryStuByStuno(stu.getStuno());
	}

	@Cacheable(cacheNames = { "stu" }, key = "args[0]")
	@RequestMapping("/queryStuByStuno6")
	public Student queryStuByStuno6(@PathVariable("stuno") Integer stuno) {
		return stuMapper.queryStuByStuno(stuno);
	}

	@Cacheable(cacheNames = { "stu" }, keyGenerator = "myKeyGenerator")
	@RequestMapping("/queryStuByStuno7/{stuno}")
	public Student queryStuByStuno7(@PathVariable("stuno") Integer stuno) {
		return stuMapper.queryStuByStuno(stuno);
	}

	@Cacheable(cacheNames = { "stu" }, key = "#student.stuno", condition = "#student.stuno%2==1")
	@RequestMapping("/queryStuByStuno8")
	public Student queryStuByStuno8(Student student) {
		return stuMapper.queryStuByStuno(student.getStuno());
	}

	@Cacheable(cacheNames = { "stu" }, unless = "#result == null")
	@RequestMapping("/queryStuByStuno9/{stuno}")
	public Student queryStuByStuno9(@PathVariable("stuno") Integer stuno) {
		return stuMapper.queryStuByStuno(stuno);
	}

	@CachePut(cacheNames = { "stu" }, key = "#result.stuno")
	@RequestMapping("/updateStuBystuno")
	public Student updateStuBystuno(Student student) {
		boolean result = stuMapper.updateStuByStuno(student);
		return result ? stuMapper.queryStuByStuno(student.getStuno()) : null;
	}
	
	@Caching(
		cacheable={
				@Cacheable(cacheNames = { "stu" }, key = "#stuno", condition = "stuno < 10")
		},
		put={
				@CachePut(cacheNames = { "stu" }, key = "#stuno", condition = "stuno > 10")
		},
		evict={
				@CacheEvict(value = { "stu" },key="#stuno", condition = "stuno == 10")
		}
	)
	@RequestMapping("/queryStuByStuno10/{stuno}")
	public Student queryStuByStuno10(@PathVariable("stuno") Integer stuno) {
		return stuMapper.queryStuByStuno(stuno);
	}

	@RequestMapping("/testStringRedisTemplate")
	public String testStringRedisTemplate() {
		stringRedisTemplate.opsForValue().set("kr1", "vr1"); ;
		return stringRedisTemplate.opsForValue().get("kr1");
	}
	
	@RequestMapping("/testRedisTemplate")
	public Object testRedisTemplate() {
		redisTemplate.opsForValue().set(3, queryStuByStuno(3)); ;
		return redisTemplate.opsForValue().get(3);
	}
	


}
