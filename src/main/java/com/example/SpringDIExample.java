package com.example;

import java.sql.SQLException;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.example.dao.CountryDao;
import com.example.model.Dept;

public class SpringDIExample {

	public static void main(String[] args) throws SQLException {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:/spring/beans.xml");
		
		Dept dept = ctx.getBean(Dept.class);
		System.out.println(dept);
		
		/*
		 * context:component-scan 설정하고 CountryDao(@Component) 출력 잘 되는지 확인
		 */
		
		CountryDao dao = ctx.getBean(CountryDao.class);
		System.out.println(dao);
		
		/*
		 * @Autowired로 DI실습
		 */
		System.out.println(dao.getDs().getConnection());
		
		/*
		 * selectAll() 쿼리 테스트
		 */
		System.out.println(dao.selectAll());
		
		/*
		 * AOP 어떻게 호출되는지 테스트 --> 두 번 나오는가?
		 * 
		 */
		System.out.println(dao.selectAll());
		
		ctx.close();
	}

}
