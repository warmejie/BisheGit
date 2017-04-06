package cn.wj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 上传商品
 */


@Controller
public class AddItemController {

	@RequestMapping("/post-ad")
	public String additemPage(){
		return "post-ad";
	}
}
