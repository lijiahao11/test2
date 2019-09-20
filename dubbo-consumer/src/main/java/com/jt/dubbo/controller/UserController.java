package com.jt.dubbo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.dubbo.pojo.User;
import com.jt.dubbo.service.UserService;

@RestController
public class UserController {
	
	/**
	 * check:true,检查是否有服务的提供者
	 * 显启动服务提供者，之后启动消费者
	 * random 随机
	 * roundrobin 轮训
	 * consistentHash 哈希
	 * leastActive 最小活跃，挑选服务器负载小的
	 */
	@Reference(timeout=3000,check=true,loadbalance ="random" )
	private UserService userService;
	
	@RequestMapping("/findAll")
	public List<User> findAll(){
		
		return userService.findAll();
	}
	
	@RequestMapping("/saveUser/{name}/{age}/{sex}")
	public String saveUser(User user) {
		
		userService.saveUser(user);
		return "用户入库成功!!!";
	}
}
