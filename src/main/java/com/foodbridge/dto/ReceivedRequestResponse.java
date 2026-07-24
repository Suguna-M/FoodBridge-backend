package com.foodbridge.dto;

public class ReceivedRequestResponse {

    private Long requestId;
    private Long foodId;

    private String foodName;
    private String quantity;

    private String receiverEmail;

    private String city;

    private String expiryTime;

    private String requestTime;

    private String status;

    public ReceivedRequestResponse() {
    }

    public ReceivedRequestResponse(Long requestId,
                                   Long foodId,
                                   String foodName,
                                   String quantity,
                                   String receiverEmail,
                                   String city,
                                   String expiryTime,
                                   String requestTime,
                                   String status) {
        this.requestId = requestId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.receiverEmail = receiverEmail;
        this.city = city;
        this.expiryTime = expiryTime;
        this.requestTime = requestTime;
        this.status = status;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}