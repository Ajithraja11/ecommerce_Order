package com.ecommerce.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.order.controller.OrderController;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@DisplayName("Controller layer Integration Testing")
@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class ControllerTest {

	@MockBean
	OrderService orderService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void getOrderByIDTest() throws Exception
	{
		Mockito.doReturn(new ResponseEntity<Object>("Order Id found",HttpStatus.ACCEPTED)).
		when(orderService).getOrderbyID(42);
		
	 mockMvc.perform(get("/order/{orderID}",42)
			    .contentType("application/json"))
			    .andExpect(status().isAccepted()).andReturn();
		
	}
	
	@Test
	public void cancelByIDTest() throws Exception
	{
		Mockito.doReturn(new ResponseEntity<Object>("Order Cancelled",HttpStatus.ACCEPTED)).
		when(orderService).deletebyID(12);
		
	 mockMvc.perform(delete("/order/{orderID}",12)
			    .contentType("application/json"))
			    .andExpect(status().isAccepted()).andReturn();
		
	}
	
	@Test
	public void createOrderTest() throws Exception
	{
		Order newOrder=new Order();
		Mockito.doReturn(new ResponseEntity<Object>("Order Cancelled",HttpStatus.OK)).
		when(orderService).createOrder(newOrder);
		
	 mockMvc.perform(post("/order")
			 .content(asJsonString(newOrder))
			    .contentType("application/json"))
			    .andExpect(status().isOk()).andReturn();
		
	}

	private String asJsonString(Order newOrder) {
		 try {
		        return new ObjectMapper().writeValueAsString(newOrder);
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
	}
}
