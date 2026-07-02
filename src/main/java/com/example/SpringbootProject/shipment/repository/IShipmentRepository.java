package com.example.SpringbootProject.shipment.repository;

import com.example.SpringbootProject.shipment.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShipmentRepository extends JpaRepository<Shipment, Long> {
}
