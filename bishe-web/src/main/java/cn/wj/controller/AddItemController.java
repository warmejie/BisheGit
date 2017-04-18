package cn.wj.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.wj.pojo.Item;
import cn.wj.pojo.User;
import cn.wj.service.AddItem;
import cn.wj.utils.BiSheResult;
import cn.wj.utils.IDUtils;
import cn.wj.utils.JsonUtils;

/*
 * 上传商品
 */


@Controller
public class AddItemController {
	
	@Autowired
	private AddItem addItem;

	@RequestMapping("/post-ad")
	public String additemPage(HttpServletRequest request){
		BiSheResult result = new BiSheResult();
		result =addItem.getCategory();
		request.setAttribute("data", result.getData());
		return "post-ad";
	}
	
	
	    //上传图片
		@RequestMapping(value = "/uploadaa")    
		 @ResponseBody  
		 public String ajaxUpload(@RequestParam(value="basicInfoHead",required=false) MultipartFile file,HttpServletRequest request) throws IllegalStateException,  
		   IOException {  
			String path1 = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
			
			String realName= file.getOriginalFilename();
			
			String prefix=realName.substring(realName.lastIndexOf(".")+1,realName.length()).toLowerCase();
			String newFilename = IDUtils.genImageName()+"."+prefix;
			File targetfile = new File(path1,newFilename);
			if (!targetfile.exists()) {
				targetfile.mkdirs();
			}
			
			try {
				file.transferTo(targetfile);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			String url1 = "/upload/"+newFilename;
			System.out.println(url1);
			request.setAttribute("imagePath", "url1");
			
			BiSheResult result = new BiSheResult();
			result.setStatus(200);
			result.setData(url1);
			String json = JsonUtils.objectToJson(url1);
			return url1;
			
		}
		
		/**
		 * 添加商品
		 */
		@RequestMapping("/addItem")
		public String addItem(HttpServletRequest request,
				@RequestParam("cid") String cid,
				@RequestParam("title") String title,
				@RequestParam("description") String description,
				@RequestParam("image") String image,
				@RequestParam("price") BigDecimal price,
				@RequestParam("address") String address){
			//补充实体类
			Item item = new Item();
			User user = (User) request.getSession().getAttribute("user");
			item.setUid(user.getUid());
			item.setCid(cid);
			item.setTitle(title);
			item.setDescription(description);
			item.setImage(image);
			item.setPrice(price);
			item.setAddress(address);
			item.setStatus(1);
			item.setCreateTime(new Date());
			item.setUpdateTime(new Date());
			BiSheResult result = addItem.addItem(item);
			if(Integer.parseInt(result.getStatus().toString())==200){
				return "";
			}else{
				return "";
			}
        }
	
}
