package com.chaos.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.chaos.util.DataResult;
import com.chaos.util.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaos.date.DateUtils;
import com.chaos.excel.ExportExcel;
import com.chaos.model.Member;
import com.chaos.service.IMemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private IMemberService memberService;
	
	@RequestMapping("/export")
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Member> memberList = memberService.find();
		String fileName = "导出数据"+DateUtils.getDate("yyyy-MM-dd") + ".xlsx";
		new ExportExcel("member", Member.class).setDataList(memberList).write(response, fileName);
	}

	@RequestMapping("/findMember")
	public Map<String,?> findMember(HttpServletRequest request, HttpServletResponse response, String jsonstr) throws Exception {
		List<Member> memberList = memberService.find();
		System.out.println(JSONObject.toJSONString(memberList));
		return new DataResult("0","0",JSONObject.toJSONString(memberList)).toMap();
	}
}
