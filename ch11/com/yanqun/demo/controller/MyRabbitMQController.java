package com.yanqun.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yanqun.entity.Student;
@RestController
public class MyRabbitMQController {
	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	AmqpAdmin mqAdmin ; 
	@RequestMapping("/testCreateAmqp")
	public void testCreateAmqp(){
		//创建Exchange
		mqAdmin.declareExchange(new DirectExchange("myexchange.demo"));
		//创建MQ
		mqAdmin.declareQueue(new Queue("myqueue.demo",true));
		//创建Exchange和MQ的绑定规则
		mqAdmin.declareBinding(new Binding("myqueue.demo", Binding.DestinationType.QUEUE, "myexchange.demo", "mymq.routingKey", null) );
	}
	
	//发送MQ
	@RequestMapping("/testSendDirectMQ")
	public void testSendDirectMQ(){
		List<String> infos = Arrays.asList("developer","teacher","writer");
		/*
		 * 通过交换器myexchange.direct，向队列myqueues.infos发送一条infos消息；
		 * 此外，infos是一个对象，默认是以Java序列化的形式被保存在myqueues.infos中的
		 */
		rabbitTemplate.convertAndSend("myexchange.direct","myqueues.infos",infos);
	}
	//接收MQ
	@RequestMapping("/testReceiveDirectMQ")
	public List<String> testReceiveDirectMQ(){
		//接收myqueues.infos中的消息
		List<String> receives = (List<String>)rabbitTemplate.receiveAndConvert("myqueues.infos");
		return receives;
	}
	
	//发送MQ
	@RequestMapping("/testMQListener")
	public void testMQListener(){
		Student student = new Student(10,"颜群",1);
		rabbitTemplate.convertAndSend("myexchange.direct","yanqun.infos",student);
	}
}
