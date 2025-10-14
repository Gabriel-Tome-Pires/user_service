package com.example.user_service.repository;

import com.example.user_service.model.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "/application-test.properties")
public class TestAddressRepository {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void TestCreatingAddress_WithValidData_ReturnsAddress() {
        Address address = new Address("state","city","steet");

        Address newAddress=addressRepository.save(address);

        Assertions.assertNotNull(newAddress);
        Assertions.assertEquals(address.getState(),newAddress.getState());
        Assertions.assertEquals(address.getCity(),newAddress.getCity());
        Assertions.assertEquals(address.getStreet(),newAddress.getStreet());
    }

    @Test
    public void TestDeletingAddress_WithValidData() {
        Address address = new Address("state","city","steet");
        Address newAddress=addressRepository.save(address);

        addressRepository.deleteById(newAddress.getId());
    }

    @Test
    public void TestGetById_WithValidData_ReturnsAddress() {
        Address address = new Address("state","city","steet");
        Address newAddress=addressRepository.save(address);

        Address gettedAddress=addressRepository.findById(newAddress.getId()).get();

        Assertions.assertNotNull(newAddress);
        Assertions.assertEquals(address.getState(),gettedAddress.getState());
        Assertions.assertEquals(address.getCity(),gettedAddress.getCity());
        Assertions.assertEquals(address.getStreet(),gettedAddress.getStreet());
    }

    @Test
    public void TestGetAllAddresses_WithValidData_ReturnsAddress() {

        List<Address> addresses=addressRepository.findAll();

        Assertions.assertNotNull(addresses);
    }
}
