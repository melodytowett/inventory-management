package com.example.inventorymanagement.dtos;

import lombok.Data;
@Data
public class ItemsDto {
    private Long id;
    private String name;
    private Integer quantity;
    private Integer price;
    private Boolean orders;
    private Boolean sales;
}
