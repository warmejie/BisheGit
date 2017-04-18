package cn.wj.service.serviceimpl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.wj.mapper.ItemCatMapper;
import cn.wj.mapper.ItemMapper;
import cn.wj.pojo.Item;
import cn.wj.pojo.ItemCat;
import cn.wj.pojo.ItemCatExample;
import cn.wj.pojo.User;
import cn.wj.service.AddItem;
import cn.wj.utils.BiSheResult;
import cn.wj.utils.FtpUtil;
import cn.wj.utils.IDUtils;

@Service
public class AddItemImpl implements AddItem {
	
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	BiSheResult result = new BiSheResult();

	
	public String additemPage(HttpServletRequest request,HttpSession session){
		ItemCatExample example = new ItemCatExample();
		List<ItemCat> category = itemCatMapper.selectByExample(example);
		request.setAttribute("data", category);
		return "post-ad";
	}
	
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public BiSheResult getCategory() {
		ItemCatExample example = new ItemCatExample();
		List<ItemCat> category = itemCatMapper.selectByExample(example);
		if(category.size()>0){
		result.setStatus(200);
		}
		result.setData(category);
		return result;
	}
	
	
	@Override
	public Map uplocadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		//取旧名字
		String oldName= uploadFile.getOriginalFilename();
		//生成新名字
		String newName=IDUtils.genImageName();
		newName = newName + oldName.substring(oldName.lastIndexOf("."));
		String imagePath =new DateTime().toString("/yyyy/MM/dd");
		//图片上传
        try {
	    	boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, 
	    			FTP_BASE_PATH, imagePath, newName, uploadFile.getInputStream());
		
	    	if(!result){
	    		resultMap.put("error", 1);
	    		resultMap.put("message", "上传失败");
	    		return resultMap;
	    	}
	    	resultMap.put("error", 0);
	    	resultMap.put("url", IMAGE_BASE_URL+imagePath+"/"+newName);
	    	return resultMap;
        } catch (IOException e) {
        	resultMap.put("error", 1);
    		resultMap.put("message", "文件上传发生异常");
    		return resultMap;
		}
		
	}

	/**
	 * 添加商品Service
	 */
	@Override
	public BiSheResult addItem(Item item) {
		
		System.out.println(item.getCid());
		System.out.println(item.getTitle());
		System.out.println(item.getDescription());
		System.out.println(item.getImage());
		System.out.println(item.getPrice());
		System.out.println(item.getAddress());
		item.setStatus(1);
	
		int i = itemMapper.insert(item);
		System.out.println(i);
		return BiSheResult.ok();
	}


}
