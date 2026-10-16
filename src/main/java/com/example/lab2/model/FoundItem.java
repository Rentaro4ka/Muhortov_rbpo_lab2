package com.example.lab2.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoundItem {
    private Long id;
    private String description;
    private String category;
    private String locationFound;
    private String status;
    private LocalDateTime foundAt;
}