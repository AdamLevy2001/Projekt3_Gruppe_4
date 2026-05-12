package com.example.projekt3_gruppe_4.service;

import com.example.projekt3_gruppe_4.model.Customer;
import com.example.projekt3_gruppe_4.model.Lease;
import com.example.projekt3_gruppe_4.repository.CustomerRepository;
import com.example.projekt3_gruppe_4.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LeaseService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LeaseRepository leaseRepository;

    public void createLeaseWithCustomer(String firstName, String lastName, String email, String phoneNumber,
                                        int carVehicleNo, int deliveryLocationId, LocalDate startDate, int leaseLength,
                                        double downPayment, double monthlyPayment, int kmPerMonth) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("Fornavn må ikke være tomt");
        }

        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Efternavn må ikke være tomt");
        }

        if (email == null || email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email er ikke gyldig");
        }

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Telefonnummer må ikke være tomt");
        }

        if (downPayment < 0) {
            throw new IllegalArgumentException("Førstegangsydelse må ikke være negativ");
        }

        if (monthlyPayment <= 0) {
            throw new IllegalArgumentException("Månedlig ydelse skal være positiv");
        }

        if (startDate == null) {
            throw new IllegalArgumentException("Startdato må ikke være tom");
        }

        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Startdato må ikke være i fortiden");
        }

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phoneNumber);

        Lease lease = new Lease();
        lease.setCarVehicleNo(carVehicleNo);
        lease.setDeliveryLocationId(deliveryLocationId);
        lease.setStartDate(startDate);
        lease.setEndDate(startDate.plusMonths(leaseLength));
        lease.setDownPayment(downPayment);
        lease.setMonthlyPayment(monthlyPayment);
        lease.setKmPerMonth(kmPerMonth);
        lease.setStatus("active");

        Customer existingCustomer = customerRepository.findCustomerByEmail(email);
        if (existingCustomer != null) {
            lease.setCustomerId(existingCustomer.getId());
        } else {
            Customer createdCustomer = customerRepository.createCustomer(customer);
            lease.setCustomerId(createdCustomer.getId());
        }

        leaseRepository.createLease(lease);
    }

    public int findLeaseIdByVehicleNo(int vehicleNo) {
        return leaseRepository.findLeaseIdByVehicleNo(vehicleNo);
    }
}
