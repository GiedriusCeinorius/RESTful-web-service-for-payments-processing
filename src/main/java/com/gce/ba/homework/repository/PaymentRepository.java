package com.gce.ba.homework.repository;

import com.gce.ba.homework.domain.Payment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Integer> {

    List<Payment> findByValidity(boolean validity, Pageable pageable);

    List<Payment> findAllByValidityAndAmount(boolean validity, double amoun, Pageable pageable);
}
