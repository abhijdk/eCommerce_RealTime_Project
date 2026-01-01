package com.eCommerce.service.impl;

import com.eCommerce.exception.AddressException;
import com.eCommerce.model.Address;
import com.eCommerce.model.User;
import com.eCommerce.repository.AddressRepository;
import com.eCommerce.repository.UserRepository;
import com.eCommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address addAddressToUser(Integer user_Id, Address addressObj) throws AddressException {
        User existingUser = userRepository.findById(user_Id)
                .orElseThrow(()->new RuntimeException("User Not Found"));

        Address newAddressObj = addressRepository.save(addressObj);
        newAddressObj.setUser(existingUser);

        existingUser.getAddress().add(newAddressObj);
        userRepository.save(existingUser);

        return newAddressObj;
    }

    @Override
    public Address updateAddressToUser(Integer address_Id, Address addressObj) throws AddressException {
        Address existingAddress=addressRepository.findById(address_Id)
                .orElseThrow(()->new RuntimeException("Address Not FOund"));

        existingAddress.setFlatNo(addressObj.getFlatNo());
        existingAddress.setStreet(addressObj.getStreet());
        existingAddress.setCity(addressObj.getCity());
        existingAddress.setState(addressObj.getState());
        existingAddress.setZip_Code(addressObj.getZip_Code());


        return addressRepository.save(existingAddress);
    }

    @Override
    public void removeAddress(Integer address_Id) throws AddressException {
        Address existingAddress = addressRepository.findById(address_Id)
                .orElseThrow(() -> new RuntimeException("Address Not FOund"));
        addressRepository.delete(existingAddress);

    }

    @Override
    public List<Address> getAllUserAddress(Integer user_Id) throws AddressException {
        User singleUser = userRepository.findById(user_Id).orElseThrow(() -> new RuntimeException("User Not Found"));
        return singleUser.getAddress();
    }
}
