package com.foodbridge.repository;

import com.foodbridge.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByDonorEmail(String donorEmail);

    Optional<Food> findById(Long id);

}