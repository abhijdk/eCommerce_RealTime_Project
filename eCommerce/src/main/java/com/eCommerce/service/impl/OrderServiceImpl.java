package com.eCommerce.service.impl;

import com.eCommerce.exception.OrderException;
import com.eCommerce.exception.UserException;
import com.eCommerce.model.*;
import com.eCommerce.model.DTO.OrderDTO;
import com.eCommerce.repository.CartItemRepository;
import com.eCommerce.repository.CartRepository;
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

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;



    @Override
    public Orders getOrderDetails(Integer orderId) throws OrderException {
        return  orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("OrderId not Found"));;
    }

    @Override
    public OrderDTO placeOrder(Integer userId) throws OrderException {
//        Fetch User form Db
        User exUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
//        Fetch user cart
        Cart exCart = exUser.getCart();

//        Validate cart is empty or not
        if(exCart.getTotal_Amount()==0){
            throw new OrderException("Add Items to the Cart First");
        }

//        Get cart id

        Integer exCartId = exCart.getCart_Id();

//        Create new order obj and stoer date and status
        Orders newOrders = new Orders();
        newOrders.setOrder_Date(LocalDateTime.now());
        newOrders.setStatus(OrderStatus.PENDING);

//        Bidirectional Mapping
//        [

//      Attaching newOrd Object to User
        exUser.getOrders().add(newOrders);

//      Adding user obj to order obj
        newOrders.setUser(exUser);

//        ]

//        Save new Oredr ot repo
        orderRepository.save(newOrders);

//        Convort Cart Item to order item

        List<Order_Item> listOfItems=new ArrayList<>();

        for(Cart_Item cartItem:exCart.getCart_items()){
            if(cartItem.getCart().getCart_Id()==exCartId){
                Order_Item orderItem = new Order_Item();
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setOrder_Id(cartItem.getCart_Item_Id());
                orderItem.setQuantity(cartItem.getQuantity());
                listOfItems.add(orderItem);
            }
        }

        newOrders.setOrder_items(listOfItems);
        newOrders.setTotal_Amount(exCart.getTotal_Amount());

        orderRepository.save(newOrders);

        exCart.setTotal_Amount(exCart.getTotal_Amount()-newOrders.getTotal_Amount());

        cartItemRepository.removeAllProductFromCart(exCartId);
        cartRepository.save(exCart);

//Need to Return Order DTO hence the below code
        OrderDTO uiObj=new OrderDTO();
        uiObj.setOrderId(newOrders.getOrder_Id());
        uiObj.setOrderAmount(newOrders.getTotal_Amount());
        uiObj.setOrderStatus(newOrders.getStatus().toString());
        uiObj.setPaymentStatus("PENDING");
        uiObj.setOrderDate(newOrders.getOrder_Date().toString());
        return uiObj;


    }

    @Override
    public OrderDTO updateOrder(Integer orderId, OrderDTO orderdto) throws OrderException {
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
