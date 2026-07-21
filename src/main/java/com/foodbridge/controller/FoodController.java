package com.foodbridge.controller;

import com.foodbridge.dto.FoodRequest;
import com.foodbridge.entity.Food;
import com.foodbridge.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "http://localhost:5173")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/donate")
    public ResponseEntity<String> donateFood(@RequestBody FoodRequest request) {

        String response = foodService.donateFood(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Food>> getAllFood() {

        return ResponseEntity.ok(foodService.getAllFood());
    }
}