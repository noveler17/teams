package com.sds.teams.msg.controller;

import com.sds.teams.msg.domain.Customer;
import com.sds.teams.msg.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Api(tags = {"Customer"}, value = "Customers information", produces = "application/json")
@RestController
public class CustomerController {


    @Autowired
    CustomerService customerService;


    @ApiOperation(value = "Save customers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved customer", response = Customer.class),
            @ApiResponse(code = 500, message = "Customer service fail!", response = Customer.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer) {
        if("james".equals(customer.getFirstName())){
            throw new RuntimeException();
        }
        return customerService.save(customer);
    }


    @ApiOperation(value = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieve all customer", response = Customer.class),
            @ApiResponse(code = 500, message = "Customer service fail!", response = Customer.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(@RequestParam(value = "lastName", required = false) String lastName) {
        if(lastName != null){
            return customerService.getCustomerByLastName(lastName);
        }else{
            return customerService.getAllCustomers();
        }
    }


    @ApiOperation(value = "Get customer by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieve customer", response = Customer.class),
            @ApiResponse(code = 500, message = "Customer service fail!", response = Customer.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }


}