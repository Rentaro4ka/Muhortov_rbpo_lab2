package com.example.lab2.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemClaim {
    private Long id;
    private Long lossReportId;
    private Long foundItemId;
    private String evidence;
    private String status;
    private LocalDateTime createdAt;
}