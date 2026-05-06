package com.example.projekt3_gruppe_4.service;

import com.example.projekt3_gruppe_4.model.DeliveryLocation;
import com.example.projekt3_gruppe_4.repository.DeliveryLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryLocationService {
    @Autowired
    DeliveryLocationRepository deliveryLocationRepository;

    public List<DeliveryLocation> getAllDeliveryLocations(){
        return deliveryLocationRepository.getAllDeliveryLocations();
    }
}
