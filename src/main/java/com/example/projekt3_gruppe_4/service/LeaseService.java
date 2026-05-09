package com.example.projekt3_gruppe_4.service;

import com.example.projekt3_gruppe_4.model.Customer;
import com.example.projekt3_gruppe_4.model.Lease;
import com.example.projekt3_gruppe_4.repository.CustomerRepository;
import com.example.projekt3_gruppe_4.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaseService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LeaseRepository leaseRepository;

    public void createLeaseWithCustomer(Customer customer, Lease lease) {

        Customer createdCustomer = customerRepository.createCustomer(customer);

        lease.setCustomerId(createdCustomer.getId());

        leaseRepository.createLease(lease);
    }

    public int findLeaseIdByVehicleNo(int vehicleNo) {
        return leaseRepository.findLeaseIdByVehicleNo(vehicleNo);
    }
}
