package com.eCommerce.repository;

import com.eCommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {

    @Query("select a from Address a  where a.user.user_id= :user_id")
    public List<Address> getUserAddressList(@Param("user_Id") Integer userId);
}
