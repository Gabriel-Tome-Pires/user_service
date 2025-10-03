package com.example.user_service.controller;

import com.example.user_service.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("address")
public class AddressController {

    private AddressService addressService;


}
