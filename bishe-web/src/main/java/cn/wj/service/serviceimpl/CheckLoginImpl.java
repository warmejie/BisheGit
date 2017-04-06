package cn.wj.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wj.mapper.UserMapper;
import cn.wj.pojo.User;
import cn.wj.pojo.UserExample;
import cn.wj.pojo.UserExample.Criteria;
import cn.wj.service.CheckLoginService;
import cn.wj.utils.BiSheResult;

@Service
public class CheckLoginImpl implements CheckLoginService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public BiSheResult checkLogin(User user) {
       String email = user.getEmail();
       String passerword = user.getPassword();
       UserExample example = new UserExample();
       Criteria criteria =  example.createCriteria();
       criteria.andEmailEqualTo(email);
       List<User> realUser = userMapper.selectByExample(example);
       BiSheResult result = new BiSheResult();
       if(realUser.size()==0){
    	   result.setStatus(400);
    	   result.setMsg("此账号不存在");
       }else if(realUser.get(0).getPassword()!=passerword){
    	   result.setStatus(500);
    	   result.setMsg("账号或密码错误");
       }else{
    	   result.setStatus(200);
       }
		return result;
	}

}
