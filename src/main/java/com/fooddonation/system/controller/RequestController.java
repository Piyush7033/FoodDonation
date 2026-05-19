package com.fooddonation.system.controller;

import com.fooddonation.system.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/api/request")
public class RequestController {

    @Autowired
    private RequestService requestService;
  
    @PostMapping("/{foodId}")
    public ResponseEntity<?> requestFood(@PathVariable Long foodId, Principal principal) {
        return ResponseEntity.ok(requestService.createRequest(foodId, principal.getName()));
    }

    @GetMapping("/my")
    public ResponseEntity<?> myRequests(Principal principal) {
        return ResponseEntity.ok(requestService.getMyRequests(principal.getName()));
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<?> accept(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.acceptRequest(id));
    }
}
