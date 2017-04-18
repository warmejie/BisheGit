package cn.wj.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import cn.wj.pojo.Item;
import cn.wj.utils.BiSheResult;

/**
 * 添加商品
 * @author ShuiDaJiao
 *
 */
public interface AddItem {
	
	BiSheResult getCategory();
	Map uplocadPicture( MultipartFile file);
	BiSheResult addItem(Item item);
}
