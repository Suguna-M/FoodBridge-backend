package com.foodbridge.repository;

import com.foodbridge.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findByReceiverEmail(String receiverEmail);

    List<Request> findByStatus(String status);

    boolean existsByFoodIdAndReceiverEmail(Long foodId, String receiverEmail);

    // NEW
    List<Request> findByFoodId(Long foodId);

}