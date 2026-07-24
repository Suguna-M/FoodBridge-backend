package com.foodbridge.service;

import com.foodbridge.dto.FoodRequest;
import com.foodbridge.entity.Food;
import com.foodbridge.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public String donateFood(FoodRequest request) {

        Food food = new Food();

        food.setFoodName(request.getFoodName());
        food.setQuantity(request.getQuantity());
        food.setExpiryTime(request.getExpiryTime());
        food.setAddress(request.getAddress());
        food.setCity(request.getCity());
        food.setDonorEmail(request.getDonorEmail());

        food.setStatus("AVAILABLE");

        foodRepository.save(food);

        return "Food Donation Added Successfully";
    }

    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    public List<Food> getMyDonations(String donorEmail) {
        return foodRepository.findByDonorEmail(donorEmail);
    }

}