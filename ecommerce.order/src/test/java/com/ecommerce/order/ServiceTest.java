package com.ecommerce.order;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.order.entity.Order;
import com.ecommerce.order.service.OrderService;

@SpringBootTest
@DisplayName("Order Service Layer UnitTest")
@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
	
	@Mock
	OrderService newOrderService;	

	@Test
	void CancelOrderSuccess() 
	{
		Mockito.doReturn(new ResponseEntity<Object>("Order cancelled",HttpStatus.ACCEPTED)).
		when(newOrderService).deletebyID(1);
	
		assertEquals((new ResponseEntity<Object>("Order cancelled",HttpStatus.ACCEPTED)),
				(newOrderService.deletebyID(1)));
	}
	
	@Test
	void CancelOrderFailure()
	{
		Mockito.doReturn(new ResponseEntity<Object>("Exception thrown",HttpStatus.BAD_REQUEST)).
		when(newOrderService).deletebyID(1);
	
		assertEquals((new ResponseEntity<Object>("Exception thrown",HttpStatus.BAD_REQUEST)),
				(newOrderService.deletebyID(1)));
	}
	
	@Test
	void CreateOrderSuccess() 
	{
		Order newOrder=new Order();
		Mockito.doReturn(new ResponseEntity<Object>("Order Created",HttpStatus.CREATED)).
		when(newOrderService).createOrder(newOrder);
	
		assertEquals((new ResponseEntity<Object>("Order Created",HttpStatus.CREATED)),
				(newOrderService.createOrder(newOrder)));
	}
	
	@Test
	void CreateOrderFailure() 
	{
		Order newOrder=new Order();
		Mockito.doReturn(new ResponseEntity<Object>("Exception thrown",HttpStatus.BAD_REQUEST)).
		when(newOrderService).createOrder(newOrder);
	
		assertEquals((new ResponseEntity<Object>("Exception thrown",HttpStatus.BAD_REQUEST)),
				(newOrderService.createOrder(newOrder)));
	}
	
	@Test
	void getOrderSuccess() 
	{
		
		Mockito.doReturn(new ResponseEntity<Object>("Order exists for the given ID",HttpStatus.ACCEPTED)).
		when(newOrderService).getOrderbyID(1);
	
		assertEquals((new ResponseEntity<Object>("Order exists for the given ID",HttpStatus.ACCEPTED)),
				(newOrderService.getOrderbyID(1)));
	}
	
	@Test
	void getOrderfailure() 
	{
		
		Mockito.doReturn(new ResponseEntity<Object>("Order Not found for the given ID",HttpStatus.BAD_REQUEST)).
		when(newOrderService).getOrderbyID(1);
	
		assertEquals((new ResponseEntity<Object>("Order Not found for the given ID",HttpStatus.BAD_REQUEST)),
				(newOrderService.getOrderbyID(1)));
	}
	
}
