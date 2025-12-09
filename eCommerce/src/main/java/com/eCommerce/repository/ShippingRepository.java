package com.eCommerce.repository;

import com.eCommerce.model.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<ShippingDetails,Integer> {
}
