package com.spring.test;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//실행시 클래스 로딩
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DataSourceTest {
	//스프링이 생성해저 주입하므로 객체생성하지 않아도 됨
	@Inject
	private DataSource ds;
	
	@Test
	public void testConection()throws Exception{
		
		try(Connection con = ds.getConnection()){
			
			System.out.println(con);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}


