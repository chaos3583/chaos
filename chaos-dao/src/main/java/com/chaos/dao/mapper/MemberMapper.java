package com.chaos.dao.mapper;

import java.util.List;

import com.chaos.model.Member;
<<<<<<< HEAD
import org.mapstruct.Mapper;


//@DataSourceRouting(dataSource = "chaos", table = "member",routeRule=FwScmRouteRule.class,isReadWriteSplitting = true)
@Mapper
=======


>>>>>>> chaos
public interface MemberMapper {
	
//	@ShardingExtensionMethod
    Member findMemberByUid( Long id) throws Exception;
	
//	@ShardingExtensionMethod
    Member findMemberByMemberId( Integer memberId) throws Exception;
	
//	@ShardingExtensionMethod
	int insertMember( Member member);

	List<Member> find();
	
	
}
