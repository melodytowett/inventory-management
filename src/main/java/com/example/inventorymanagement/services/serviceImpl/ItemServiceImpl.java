package com.example.inventorymanagement.services.serviceImpl;

import com.example.inventorymanagement.dtos.ItemsDto;
import com.example.inventorymanagement.entities.Items;
import com.example.inventorymanagement.exception.ItemNotFoundException;
import com.example.inventorymanagement.repositories.ItemRepo;
import com.example.inventorymanagement.services.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepo itemRepo;

    public ItemServiceImpl(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @Override
    public ItemsDto createItems(ItemsDto itemsDto) {
        Items items = new Items();
        items.setId(itemsDto.getId());
        items.setName(itemsDto.getName());
        items.setQuantity(itemsDto.getQuantity());
        items.setPrice(itemsDto.getPrice());
        items.setOrders(itemsDto.getOrders());
        items.setSales(itemsDto.getSales());
        Items newItems = itemRepo.save(items);

        ItemsDto itemsDto1 = new ItemsDto();
        itemsDto1.setId(newItems.getId());
        itemsDto1.setName(newItems.getName());
        itemsDto1.setQuantity(newItems.getQuantity());
        itemsDto1.setPrice(newItems.getPrice());
        itemsDto1.setOrders(newItems.getOrders());
        itemsDto1.setSales(newItems.getSales());
        return itemsDto1;
    }

    @Override
    public List<Items> getAllItems() {
        return itemRepo.findAll();
    }

    @Override
    public ItemsDto getItemsById(Long id) {
        Items items  = itemRepo.findById(id).orElseThrow(()->new ItemNotFoundException("items with That is not found"));
        return mapToDto(items);
    }

    @Override
    public ItemsDto updateItemsById(ItemsDto itemsDto,Long id) {
        Items items  = itemRepo.findById(id).orElseThrow(()->new ItemNotFoundException("items with That is not found"));
        items.setName(itemsDto.getName());
        items.setQuantity(itemsDto.getQuantity());
        items.setPrice(itemsDto.getPrice());
        items.setSales(itemsDto.getSales());
        items.setOrders(itemsDto.getOrders());
        Items updatedItems = itemRepo.save(items);
        return mapToDto(updatedItems);
    }

    @Override
    public List<ItemsDto> getItemsWithOrders(boolean orders) {
        List<Items> items = itemRepo.findByOrders(orders);
        return items.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<ItemsDto> getItemsWithSales(boolean sales) {
        List<Items> items = itemRepo.findBySales(sales);
        return items.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteItems(Long id) {
        Items items = itemRepo.findById(id).orElseThrow(()->new ItemNotFoundException("item with that id not found"));
        itemRepo.delete(items);
    }

    private ItemsDto mapToDto(Items items){
        ItemsDto itemsDto = new ItemsDto();
        itemsDto.setId(items.getId());
        itemsDto.setName(items.getName());
        itemsDto.setPrice(items.getPrice());
        itemsDto.setQuantity(items.getQuantity());
        itemsDto.setOrders(items.getOrders());
        itemsDto.setSales(items.getSales());
        return itemsDto;
    }
    private Items matToEntity(ItemsDto itemsDto){
        Items items = new Items();
        items.setId(itemsDto.getId());
        items.setName(itemsDto.getName());
        items.setPrice(itemsDto.getPrice());
        items.setQuantity(itemsDto.getQuantity());
        items.setOrders(itemsDto.getOrders());
        items.setSales(itemsDto.getSales());
        return items;
    }

}

