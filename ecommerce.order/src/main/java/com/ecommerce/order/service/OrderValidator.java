package com.ecommerce.order.service;

import java.util.regex.Pattern;

import com.ecommerce.order.entity.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderValidator {

	public boolean validateOrder(Order neworder) {
		
		if(checkForNull(neworder) && checkStatus(neworder.getOrder_status()) && checkplaceName(neworder.getOrder_billing_city())
				&& checkplaceName(neworder.getOrder_billing_state()) && checkplaceName(neworder.getOrder_shipping_city())
				&& checkplaceName(neworder.getOrder_shipping_state()))
			return true;
		
		return false;
	}

	private boolean checkplaceName(String location) {
		if(Pattern.matches("[a-zA-Z]+",location))
			return true;
		
		log.error("The Location (city or state) provided in the order "+location+" is invalid");
		return false;
	}

	private boolean checkStatus(String order_status) {
		if(order_status.equalsIgnoreCase("order received") || order_status.equalsIgnoreCase("order in transit") 
				|| order_status.equalsIgnoreCase("Out for delivery"))
		return true;
		
		log.error("The Order_Status provided in the order "+order_status+" is invalid");
		return false;
	}

	private boolean checkForNull(Order neworder) {
		if(neworder.getOrder_status()!=(null) && neworder.getOrder_billing_addressline1()!=null && neworder.getOrder_billing_city()!=null
				&& neworder.getOrder_billing_state()!=null && neworder.getOrder_billing_zip()!=0 && neworder.getOrder_customer_id()!=0
				&& neworder.getOrder_item_name()!=null && neworder.getOrder_item_qty()!=null && neworder.getOrder_payment_confirmation_number()!=null
				&& neworder.getOrder_payment_date()!=null && neworder.getOrder_payment_method()!=null && neworder.getOrder_shipping_addressline1()!=null
				&& neworder.getOrder_shipping_city()!=null && neworder.getOrder_shipping_state()!=null && neworder.getOrder_shipping_zip()!=0
				&& neworder.getOrder_subtotal()!=0 && neworder.getOrder_total()!=0)
			return true;
		
		log.error("Null value provided in the order "+neworder.toString());
		return false;
		
	}

}
