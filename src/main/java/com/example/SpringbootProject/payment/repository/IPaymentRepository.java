package com.example.SpringbootProject.payment.repository;

import com.example.SpringbootProject.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
}
