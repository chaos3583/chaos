package com.chaos.controller;

import javax.servlet.http.HttpSession;

import com.chaos.util.ControllerUtil;
import com.chaos.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.chaos.model.User;
import com.chaos.service.IUserService;
import com.chaos.util.TokenClient;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;
	
//	@CreateCache(expire = 100)
//	private Cache<Long, User> userCache;
	
	@RequestMapping("/")
	@ResponseBody
    public ModelAndView findMemberByMemberId(@RequestBody String jsonStr) throws Exception{
    	ModelAndView model = new ModelAndView();
	  	model.setViewName("login");
	  	return model;
    }
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
//	@Cached(name="login", expire=360,key="user.userName", cacheType=CacheType.LOCAL)
	public String login(HttpSession session,@RequestBody String jsonStr) {
		User user = ControllerUtil.strJsonToObject(jsonStr, User.class);
		if (null==user){
			return "";
		}
		if (StringUtils.isEmpty(user.getUserName())){
			return  "用户名为空";
		}
		if (StringUtils.isEmpty(user.getPassword())){
			return  "密码为空";
		}
//			userCache.PUT(12345L, user);
			User loginUser = userService.login(user);
			String token = TokenClient.getToken(loginUser.getId());
			session.setAttribute("user_token", token);
			session.setMaxInactiveInterval(30 * 60);//设置session失效时间，单位为秒
			String result = "";
			if(loginUser!=null) {
				result="1";
			}
//			User user2 = userCache.get(12345L);
			return result;
	}

}
