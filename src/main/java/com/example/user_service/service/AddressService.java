package com.example.user_service.service;

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

    public Address CreateAddress(Address address){
        return addressRepository.save(address);
    }

    public Address UpdateAddress(Address address, Long id) {
        Address newAddress = getById(id);

        if(newAddress.getState()!=null){newAddress.setState(address.getState());}
        if(newAddress.getCity()!=null){newAddress.setCity(address.getCity());}
        if(newAddress.getStreet()!=null){newAddress.setStreet(address.getStreet());}

        return addressRepository.save(newAddress);
    }

    public void DeleteAddress(Address address){
        addressRepository.delete(address);
    }

    public Address getById(Long id){
        return addressRepository.findById(id).orElseThrow();
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }
}
