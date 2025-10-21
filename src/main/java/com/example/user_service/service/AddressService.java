package com.example.user_service.service;

import com.example.user_service.exception.ObjectNotFound;
import com.example.user_service.model.Address;
import com.example.user_service.repository.AddressRepository;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {
    AddressRepository addressRepository;

    public Address createAddress(Address address){
        return addressRepository.save(address);
    }

    public Address updateAddress(Address address, Long id) {
        Address existingAddress = getById(id);

        if(existingAddress.getState()!=null){existingAddress.setState(address.getState());}
        if(existingAddress.getCity()!=null){existingAddress.setCity(address.getCity());}
        if(existingAddress.getStreet()!=null){existingAddress.setStreet(address.getStreet());}

        return addressRepository.save(existingAddress);
    }

    public void deleteAddress(Address address){
        addressRepository.delete(address);
    }

    public Address getById(Long id){
        return addressRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFound("Address not found with id " + id));
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }
}
