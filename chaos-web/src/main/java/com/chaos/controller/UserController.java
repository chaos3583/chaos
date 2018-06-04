package com.chaos.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.chaos.model.User;
import com.chaos.service.IUserService;
import com.chaos.util.TokenClient;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;
	
	@CreateCache(expire = 100)
	private Cache<Long, User> userCache;
	
	@RequestMapping("/")
    public ModelAndView findMemberByMemberId() throws Exception{
    	ModelAndView model = new ModelAndView();
	  	model.setViewName("login");
	  	return model;
    }
	
	@RequestMapping("/login")
//	@Cached(name="login", expire=360,key="user.userName", cacheType=CacheType.LOCAL)
	public Integer login(HttpSession session,User user) {
			userCache.PUT(12345L, user);
			User loginUser = userService.login(user);
			String token = TokenClient.getToken(loginUser.getId());
			session.setAttribute("user_token", token);
			session.setMaxInactiveInterval(30 * 60);//设置session失效时间，单位为秒
			Integer result = 0;
			if(loginUser!=null) {
				result=1;
			}
			User user2 = userCache.get(12345L);
			return result;
	}

}
