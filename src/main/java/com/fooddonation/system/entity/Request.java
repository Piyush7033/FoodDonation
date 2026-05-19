package com.fooddonation.system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Food food;

    @ManyToOne
    private User receiver;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime requestTime;
}