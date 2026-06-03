package com.example.courseworkitfu.model;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private User sender;
    private User receiver;
    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;
    private FoodOrder relatedOrder;
}
