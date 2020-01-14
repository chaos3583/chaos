package com.chaos.controller;

import javax.servlet.http.HttpSession;

import com.chaos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chaos.model.User;
import com.chaos.util.TokenClient;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
//	@CreateCache(expire = 100)
//	private Cache<Long, User> userCache;
	
	@RequestMapping(value="/")
    public ModelAndView findMemberByMemberId() throws Exception{
    	ModelAndView model = new ModelAndView();
	  	model.setViewName("login");
	  	return model;
    }

	@RequestMapping("/ftlIndex")
	public String ftlIdex(){
		return "hello";
	}
	
	@RequestMapping("/login")
//	@Cached(name="login", expire=360,key="user.userName", cacheType= CacheType.LOCAL)
	public Integer login(HttpSession session,User user) {
//			userCache.PUT(12345L, user);
<<<<<<< HEAD
			User loginUser = userService.selectByUser(user);
			if (loginUser==null){
				return 0;
			}
=======
			User loginUser = userService.login(user);
>>>>>>> chaos
			String token = TokenClient.getToken(loginUser.getId());
			session.setAttribute("user_token", token);
			session.setMaxInactiveInterval(30 * 60);//设置session失效时间，单位为秒
			Integer result = 0;
			if(loginUser!=null) {
				result=1;
			}
//			User user2 = userCache.get(12345L);
			return result;
	}

}
