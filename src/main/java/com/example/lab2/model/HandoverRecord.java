package com.example.lab2.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandoverRecord {
    private Long id;
    private Long claimId;
    private LocalDateTime handoverDate;
    private Boolean confirmed;
}