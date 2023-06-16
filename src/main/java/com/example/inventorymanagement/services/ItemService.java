package com.example.inventorymanagement.services;

import com.example.inventorymanagement.dtos.ItemsDto;
import com.example.inventorymanagement.entities.Items;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemService  {
    ItemsDto createItems(ItemsDto itemsDto);
    List<Items> getAllItems();
    ItemsDto getItemsById(Long id);
    ItemsDto updateItemsById(ItemsDto itemsDto,Long id);
    List<ItemsDto> getItemsWithOrders(boolean order);
    List<ItemsDto> getItemsWithSales(boolean sales);
    void deleteItems(Long id);
}
