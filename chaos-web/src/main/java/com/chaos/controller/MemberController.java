package com.chaos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaos.date.DateUtils;
import com.chaos.excel.ExportExcel;
import com.chaos.model.Member;
import com.chaos.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/export")
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Member> memberList = memberService.find();
		String fileName = "导出数据"+DateUtils.getDate("yyyy-MM-dd") + ".xlsx";
		new ExportExcel("member", Member.class).setDataList(memberList).write(response, fileName);
	}
}
