package org.madridjs.logopoll.daos;

import static org.junit.Assert.*;

import java.util.List;


import javax.inject.Inject;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.madridjs.logopoll.dto.UserDto;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@ContextConfiguration("classpath:spring/test-context.xml") 
@RunWith(SpringJUnit4ClassRunner.class)
public class UserPersistenceTests {
	@Inject
	private UserRepository userRepository;
	
//	@Test
//	public void listAllOrders(){
//		Iterable<Order> orders = orderRepository.findAll();
//		
//		System.out.println(orders);
//		assertNotNull(orders);
//		assertTrue(orders.iterator().hasNext());
//	}
	

	
	@Test
	public void getUserByEmailWhenCustomerExists(){

		List<UserDto> users = userRepository.findByEmail("israel@gmail.com");
		
		assertNotNull(users);
		assertTrue(users.size()==1);
	}
	
	@Test
	public void getCustomerByIdWhenCustomerDoesntExists(){
		List<UserDto> users = userRepository.findByEmail("israe@gmail.com");
		
		assertNotNull(users);
		assertTrue(users.size()==0);
	}
	


	
}
