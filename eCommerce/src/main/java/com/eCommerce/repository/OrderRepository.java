package com.eCommerce.repository;

import com.eCommerce.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    @Query("SELECT o from ORDERS o where  o.user.user_Id=:user_Id")
    public List<Orders> getAllOrdersByuserId(@Param("user_Id") Integer user_Id);

    @Query("SELECT o from ORDERS o where o.orderId=:orderId and o.user.user_Id=:user_Id")
    public Orders getOrderDetailsByCustomer(@Param("orderId") Integer orderId,@Param("user_Id") Integer user_Id);

    @Query("SELECT o FROM ORDERS o where o.orderDate >= :date")
    public List<Orders> findByOrderDateGreaterThanEqual(@Param("date") Date date);
}
