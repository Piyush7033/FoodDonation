package com.fooddonation.system.dto;

import com.fooddonation.system.entity.RequestStatus;

import java.time.LocalDateTime;

public class RequestResponseDto {

    private Long id;
    private String foodTitle;
    private String receiverName;
    private RequestStatus status;
    private LocalDateTime requestTime;

    public RequestResponseDto(Long id, String foodTitle, String receiverName,
                              RequestStatus status, LocalDateTime requestTime) {
        this.id = id;
        this.foodTitle = foodTitle;
        this.receiverName = receiverName;
        this.status = status;
        this.requestTime = requestTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
// getters
}