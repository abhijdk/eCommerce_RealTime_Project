package com.eCommerce.service;

import com.eCommerce.exception.AddressException;
import com.eCommerce.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    public Address addAddressToUser(Integer user_Id,Address addressObj) throws AddressException;
    public Address updateAddressToUser(Integer address_Id,Address addressObj) throws AddressException;
    public void removeAddress(Integer address_Id) throws AddressException;
    public List<Address> getAllUserAddress(Integer user_Id) throws AddressException;
}
