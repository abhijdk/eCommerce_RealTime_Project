package com.eCommerce.service;


import com.eCommerce.exception.OrderException;
import com.eCommerce.model.DTO.OrderDTO;
import com.eCommerce.model.Orders;

import java.util.Date;
import java.util.List;

public interface OrderService {

    public OrderDTO placeOrder(Integer userId) throws OrderException;

    public OrderDTO updateOrder(Integer orderId,OrderDTO orderdto) throws OrderException;

    public Orders getOrderDetails(Integer orderId) throws OrderException;

    public List<Orders> getAllUserOrders(Integer userId) throws OrderException;

    public List<Orders> getAllOrderinSystem() throws OrderException;

    public List<Orders> getAllOrdersByDate(Date orderDate) throws OrderException;

    public void deleteOrder(Integer orderId,Integer userId) throws OrderException;
}
