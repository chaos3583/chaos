package com.chaos.service;

import java.util.List;

import com.chaos.model.Member;

/**
 * @author Administrator
 *@date 2017年9月28日
 */
public interface IMemberService {

    Member findMemberByUid(Long uid) throws Exception;

    Member findMemberByMemberId(Integer memberId) throws Exception;

    int insertMember(Member member) throws Exception;
    
    List<Member> find() throws Exception;
}
