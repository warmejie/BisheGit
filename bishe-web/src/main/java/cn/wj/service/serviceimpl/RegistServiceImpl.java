package cn.wj.service.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wj.mapper.UserMapper;
import cn.wj.pojo.User;
import cn.wj.pojo.UserExample;
import cn.wj.pojo.UserExample.Criteria;
import cn.wj.service.RegistService;
import cn.wj.utils.BiSheResult;

@Service
public class RegistServiceImpl implements RegistService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public BiSheResult regist(User user) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(user.getEmail());
		List list = userMapper.selectByExample(example);
		if ( list.size()>=1) {
			BiSheResult result =new BiSheResult();
			result.setMsg("该邮箱已注册");
			result.setStatus(500);
			return result;
		}
		user.setCreatetime(new Date());
		userMapper.insert(user);

		return BiSheResult.ok(user);
	}

}
