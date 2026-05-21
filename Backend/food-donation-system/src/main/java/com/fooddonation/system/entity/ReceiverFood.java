//package com.fooddonation.system.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
//@Setter
//@Getter
//@Entity
//@Table(name = "receiver_food")
//public class ReceiverFood {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String title;
//
//    @Column(length = 500)
//    private String description;
//
//    @Column(nullable = false)
//    private Integer quantity;
//
//    @Column(nullable = false)
//    private String location;
//
//    @Column(nullable = false)
//    private String status;
//
//    @Column(name = "expiry_time")
//    private LocalDateTime expiryTime;
//
//    // DEFAULT CONSTRUCTOR
//    public ReceiverFood() {
//    }
//}