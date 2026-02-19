package com.pms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponseDTO 
{

    private Long id;
    
    private Long quantity;
}

