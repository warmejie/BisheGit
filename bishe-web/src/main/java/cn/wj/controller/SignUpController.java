package cn.wj.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wj.pojo.User;
import cn.wj.service.RegistService;
import cn.wj.utils.BiSheResult;

@Controller
public class SignUpController {
	
	@Autowired
	private RegistService registService;
	
	@RequestMapping("/signup")
	public String singup(){
		return "signup";
	}
	
	@RequestMapping("/regist")
	public String regist(@RequestParam("username") String name,
			@RequestParam("email") String email,
			@RequestParam("mobile") String mobile,
			@RequestParam("password") String password,
			HttpServletRequest request
			             ){
		User user =new User();
		user.setUsername(name);
		user.setEmail(email);
		user.setMobile(Integer.valueOf(mobile));
		user.setPassword(password);
		BiSheResult result = registService.regist(user);
		System.out.println(2);
		if(result.getStatus()==500){
			 request.setAttribute("message", result.getMsg());
			 return "signup";
		}
			
		return "signin";
	}

}
