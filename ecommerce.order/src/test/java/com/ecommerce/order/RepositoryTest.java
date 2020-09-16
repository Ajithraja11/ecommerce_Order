package com.ecommerce.order;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.order.entity.Order;
import com.ecommerce.order.repository.OrderDaoImplementation;

@SpringBootTest
@DisplayName("Order Repository Layer UnitTest")
@RunWith(MockitoJUnitRunner.class)
class RepositoryTest {

	@Mock
	OrderDaoImplementation newOrderDao;
	
	@Test
	void CancelOrderSuccess() 
	{
		try {
			Mockito.doReturn(true).
			when(newOrderDao).deleteByID(1);
			
			assertEquals(true,
					(newOrderDao.deleteByID(1)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test
	void CancelOrderfailure() 
	{
		try {
			Mockito.doReturn(true).
			when(newOrderDao).deleteByID(2);
			
			assertEquals(false,
					(newOrderDao.deleteByID(1)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void getOrder() 
	{
	
		try {
			Mockito.doReturn(Optional.of("test")).
			when(newOrderDao).getOrderByID(1);
			
			assertEquals(Optional.of("test"),
					(newOrderDao.getOrderByID(1)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void createOrderSuccess() 
	{
	Order newOrder=new Order();
		try {
			Mockito.doReturn(true).
			when(newOrderDao).createNewOrder(newOrder);
			
			assertEquals(true,
					(newOrderDao.createNewOrder(newOrder)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	void createOrderFailure() 
	{
	Order newOrder=new Order();
		try {
			Mockito.doReturn(false).
			when(newOrderDao).createNewOrder(newOrder);
			
			assertEquals(false,
					(newOrderDao.createNewOrder(newOrder)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
