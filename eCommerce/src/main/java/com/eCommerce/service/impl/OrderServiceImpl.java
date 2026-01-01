package com.eCommerce.service.impl;

import com.eCommerce.exception.OrderException;
import com.eCommerce.exception.UserException;
import com.eCommerce.model.*;
import com.eCommerce.model.DTO.OrderDTO;
import com.eCommerce.repository.OrderRepository;
import com.eCommerce.repository.UserRepository;
import com.eCommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Order_Item orderItem;

    @Autowired
    private UserRepository userRepository;


    @Override
    public OrderDTO placeOrder(Integer userId) throws OrderException {
        User exUser = userRepository.findById(userId).orElseThrow(() -> new UserException("User Not Found"));

        Cart exCart = exUser.getCart();

        if (exCart.getTotal_Amount()==0){
            throw new OrderException("Need to add Some Item in Cart");
        }

        Integer cartId = exCart.getCart_Id();

        Orders newOrd = new Orders();

        newOrd.setOrder_Date(LocalDateTime.now());
        newOrd.setStatus(OrderStatus.PENDING);

        //Attaching newOrd Object to User
        exUser.getOrders().add(newOrd);
        //Adding User Object to the Order Object
        newOrd.setUser(exUser);

        orderRepository.save(newOrd);

        //Populating the data into OrderItem Table (Cart Item we have Data)
        List<Order_Item> orderItems=new ArrayList<>();

        for (Cart_Item cartItem: exCart.getCart_items()){
            if(cartItem.getCart().equals(cartId)){
                Order_Item orderItem1 = new Order_Item();
                orderItem1.setProduct(cartItem.getProduct());
                orderItem1.setOrder_Id(newOrd.getOrder_Id());
                orderItem1.setQuantity(cartItem.getQuantity());
            }
        }

        return null;
    }

    @Override
    public OrderDTO updateOrder(Integer orderId, OrderDTO orderdto) throws OrderException {
        return null;
    }

    @Override
    public Orders getOrderDetails(Integer orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Orders> getAllUserOrders(Integer userId) throws OrderException {
        return List.of();
    }

    @Override
    public List<Orders> getAllOrderinSystem() throws OrderException {
        return List.of();
    }

    @Override
    public List<Orders> getAllOrdersByDate(Date orderDate) throws OrderException {
        return List.of();
    }

    @Override
    public void deleteOrder(Integer orderId, Integer userId) throws OrderException {

    }
}
