package com.pms.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponseDTO 
{

    private long id;
    
    private String productName;
    
    private String createdBy;
    
    private LocalDateTime createdOn;
}

