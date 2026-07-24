package com.foodbridge.service;

import com.foodbridge.dto.RequestDetailsResponse;
import com.foodbridge.entity.Food;
import com.foodbridge.entity.Request;
import com.foodbridge.repository.FoodRepository;
import com.foodbridge.repository.RequestRepository;
import com.foodbridge.dto.ReceivedRequestResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final FoodRepository foodRepository;

    public RequestService(RequestRepository requestRepository,
                          FoodRepository foodRepository) {
        this.requestRepository = requestRepository;
        this.foodRepository = foodRepository;
    }

    // Create a new food request
    public String createRequest(Long foodId, String receiverEmail) {

        if (requestRepository.existsByFoodIdAndReceiverEmail(foodId, receiverEmail)) {
            return "You have already requested this food.";
        }

        Request request = new Request();

        request.setFoodId(foodId);
        request.setReceiverEmail(receiverEmail);
        request.setRequestTime(LocalDateTime.now().toString());
        request.setStatus("PENDING");

        requestRepository.save(request);

        return "Food Request Sent Successfully";
    }

    // Get requests made by receiver with food details
    public List<RequestDetailsResponse> getMyRequests(String receiverEmail) {

        List<Request> requests = requestRepository.findByReceiverEmail(receiverEmail);

        List<RequestDetailsResponse> responseList = new ArrayList<>();

        for (Request request : requests) {

            Food food = foodRepository.findById(request.getFoodId()).orElse(null);

            if (food != null) {

                RequestDetailsResponse response = new RequestDetailsResponse();

                response.setRequestId(request.getId());
                response.setFoodId(food.getId());
                response.setFoodName(food.getFoodName());
                response.setQuantity(food.getQuantity());
                response.setCity(food.getCity());
                response.setExpiryTime(food.getExpiryTime());
                response.setRequestTime(request.getRequestTime());
                response.setStatus(request.getStatus());

                responseList.add(response);
            }
        }

        return responseList;
    }

    // Get all requests received for donor's food
    public List<ReceivedRequestResponse> getReceivedRequests(String donorEmail) {

        List<Food> donatedFoods = foodRepository.findByDonorEmail(donorEmail);

        List<ReceivedRequestResponse> responseList = new ArrayList<>();

        for (Food food : donatedFoods) {

            List<Request> requests = requestRepository.findByFoodId(food.getId());

            for (Request request : requests) {

                ReceivedRequestResponse response = new ReceivedRequestResponse();

                response.setRequestId(request.getId());
                response.setFoodId(food.getId());

                response.setFoodName(food.getFoodName());
                response.setQuantity(food.getQuantity());

                response.setReceiverEmail(request.getReceiverEmail());

                response.setCity(food.getCity());

                response.setExpiryTime(food.getExpiryTime());
                response.setRequestTime(request.getRequestTime());

                response.setStatus(request.getStatus());

                responseList.add(response);
            }
        }

        return responseList;
    }

    // Get all pending requests
    public List<Request> getPendingRequests() {
        return requestRepository.findByStatus("PENDING");
    }
}