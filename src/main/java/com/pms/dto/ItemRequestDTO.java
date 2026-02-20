package com.pms.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemRequestDTO 
{

    @NotNull
    @Min(1)
    private Long quantity;
    
    @NotBlank
    private String name;
}
