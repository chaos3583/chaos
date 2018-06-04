package com.chaos.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chaos.model.Member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value="member")
@Api(tags={"测试swagger"})
public class MemberApi {
	
	@RequestMapping(value="/testSwagger1",method=RequestMethod.POST)
	@ApiOperation(value="测试swagger-传对象 ",notes="注意问题点")
	public void testSwagger1(@RequestBody Member member) {
		
	}
	
	@RequestMapping(value="/testSwagger2",method=RequestMethod.POST)
	@ApiOperation(value="测试swagger--传单个参数",notes="注意问题点")
	public void testSwagger2(@ApiParam(name="id",value="用户id",required=true) Long id,@ApiParam(name="userName",value="用户名",required=true) String userName) {
		
	}
	
	
}
