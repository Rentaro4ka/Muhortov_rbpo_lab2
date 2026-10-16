package com.example.lab2.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LossReport {
    private Long id;
    private Long applicantId;
    private String description;
    private String category;
    private String status;
    private LocalDateTime createdAt;
}