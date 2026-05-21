package com.fooddonation.system.service;

import com.fooddonation.system.entity.Request;

import java.util.List;

public interface RequestService {

    Request createRequest(Long foodId, String email);

    List<Request> getMyRequests(String email);

    Request acceptRequest(Long requestId);
}