package com.foodbridge.controller;

import com.foodbridge.dto.ReceivedRequestResponse;
import com.foodbridge.dto.RequestDetailsResponse;
import com.foodbridge.entity.Request;
import com.foodbridge.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
@CrossOrigin(origins = "http://localhost:5173")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRequest(
            @RequestParam Long foodId,
            @RequestParam String receiverEmail) {

        String response = requestService.createRequest(foodId, receiverEmail);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-requests")
    public ResponseEntity<List<RequestDetailsResponse>> getMyRequests(
            @RequestParam String email) {

        return ResponseEntity.ok(requestService.getMyRequests(email));
    }

    // NEW ENDPOINT
    @GetMapping("/received")
    public ResponseEntity<List<ReceivedRequestResponse>> getReceivedRequests(
            @RequestParam String email) {

        return ResponseEntity.ok(requestService.getReceivedRequests(email));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Request>> getPendingRequests() {

        return ResponseEntity.ok(requestService.getPendingRequests());
    }
}