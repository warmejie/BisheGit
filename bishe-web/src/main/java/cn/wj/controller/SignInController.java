package cn.wj.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wj.pojo.User;
import cn.wj.service.CheckLoginService;
import cn.wj.utils.BiSheResult;

@Controller
public class SignInController {
	
	@Autowired
	private CheckLoginService checkLoginService;
	
	@RequestMapping("/signin")
	public String singin(){
		return "signin";
	}
	
	@RequestMapping("/checkLogin")
	public String checkSignin(@RequestParam("email") String email,@RequestParam("password") String password,HttpServletRequest request){
		User user = new User();
		user.setEmail(email.substring(0, email.length()-1));
		user.setPassword(password);
		BiSheResult result = checkLoginService.checkLogin(user);
		if(result.getStatus()!=200){
			request.setAttribute("message", result.getMsg());
			return "signin";
		}else
		    request.getSession().setAttribute("user", result.getData());
			return "index";
	}
	
	

}
