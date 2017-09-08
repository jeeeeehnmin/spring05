package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.apachecommons.CommonsLog;

@Component
@Aspect			//횡단적 관심사를 끌어모은 코드
@CommonsLog
public class LogAspect {

	
	/*
	 * AOP를 사용하기 위해서 pom.xml에 org.aspectj.aspectjweaver 디펜던시 필요 & beans.xml NameSpace에서도 선택 및 수정
	 * 로그, 보안 등의 정보들을 모아둔 것 = Aspect
	 * 
	 * 실행시에 해당되는 메소드에 끼워진다고 해서 weaving
	 * 모든 메소드에 적용 가능하지만 적용할 메소드를 정할 수 있음 --> 표현식 ("")에 작성하면 됨
	 */

	@Before("within(com.example.dao.CountryDao)")				//dao패키지에 잇는 모든 클래스의 모든 메소드에 적용
	public void beforeAdvice(JoinPoint jp){
		//--> 실제 객체가 호출되기 전에 가짜 객체(object) 호출 = proxy 거쳐서 호출
		log.info("#############################");
		log.info("@Before		" + jp.getSignature());			//어떤 메소드(=Signatrue)가 호출되었는지 확인
		log.info("#############################");
	}
	
	@After("within(com.example.dao.CountryDao)")				//dao패키지에 잇는 모든 클래스의 모든 메소드에 적용
	public void afterAdvice(JoinPoint jp){
		log.info("#############################");
		log.info("@After		" + jp.getSignature());			//어떤 메소드가 호출되었는지 확인
		log.info("#############################");
	}
}
