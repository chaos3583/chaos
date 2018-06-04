package com.chaos.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaos.dao.mapper.MemberMapper;
import com.chaos.model.Member;



/**
 * @author Administrator
 *@date 2017年9月28日
 */
@Service
public class MemberService implements IMemberService{

    @Autowired
    private MemberMapper memberDao;

    @Override
    public Member findMemberByUid(Long uid) throws Exception {
        return memberDao.findMemberByUid(uid);
    }

    @Override
    public Member findMemberByMemberId(Integer memberId) throws Exception {
        return memberDao.findMemberByMemberId(memberId);
    }

	@Override
	public int insertMember(Member member) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.insertMember(member);
	}

	@Override
	public List<Member> find() throws Exception {
		// TODO Auto-generated method stub
		return memberDao.find();
	}
}
