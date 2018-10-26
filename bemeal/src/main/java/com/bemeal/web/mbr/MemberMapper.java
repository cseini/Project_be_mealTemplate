package com.bemeal.web.mbr;

import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {
	public void post(Map<String, Object> r);
	public List<?> list(Map<?,?>p);
	public Member get(Member p); // 로그인 !!
	public Integer count(Map<?,?>p);
	public void put(Member p);
	public void delete(Member p);
}