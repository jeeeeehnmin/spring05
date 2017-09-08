package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;

@Component
@Getter
@CommonsLog			//commonslog 선언해줌
public class CountryDao {
	
	/*
	 * CountryDao는 데이터 소스를 의존한다.
	 * --> annotation으로 자동으로 dataSource를 주입한다(DI) ; 필드 주입
	 * 
	 * 주입 방법 3가지: 생성자, 게더세터, 필드(어노테이션)
	 */

	@Autowired
	DataSource ds;
	//어제 사용한 BasicDataSource는 DataSource 인터페이스 사용한 녀석;
	
	
	@PostConstruct
	public void postConstruct(){
		log.info("##############");
		log.info("@postConstruct");
		log.info("##############");
		
	}
	
	@PreDestroy
	public void preDestroy(){
		
		log.info("##############");
		log.info("@preDestroy");
		log.info("##############");
	}
	
	public List<String> selectAll() throws SQLException{
		
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select name from country");
		ResultSet rs = pstmt.executeQuery();
		
		List<String> list = new ArrayList<>();
		while(rs.next()){
			list.add(rs.getString("name")); 
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
	}

	public List<String> repeatedSelectAll() throws SQLException{
		/*
		 * 코드의 중복! 이것도 자동화를 할 수 있다.
		 */
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("");
		ResultSet rs = pstmt.executeQuery();
		
		List<String> list = new ArrayList<>();
		while(rs.next()){
			list.add(rs.getString(""));
		}
		
		return list;
	}

}
