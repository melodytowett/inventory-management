package com.example.inventorymanagement.controllers;

import com.example.inventorymanagement.dtos.ItemsDto;
import com.example.inventorymanagement.entities.Items;
import com.example.inventorymanagement.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemControllers {
    private final ModelMapper modelMapper;

    private final ItemService itemService;

    public ItemControllers(ModelMapper modelMapper, ItemService itemService) {
        this.modelMapper = modelMapper;
        this.itemService = itemService;
    }
    @PostMapping()
    public ResponseEntity<ItemsDto>createItems(@RequestBody ItemsDto itemsDto){
        return new ResponseEntity<>(itemService.createItems(itemsDto),HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<ItemsDto>>getAllItems(){
      return ResponseEntity.ok(itemService.getAllItems().stream()
              .map(newItem->modelMapper.map(newItem,ItemsDto.class))
              .collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ItemsDto>getItemsById(@PathVariable Long id){
        return ResponseEntity.ok(itemService.getItemsById(id));
    }
    @GetMapping("/ordered")
    public ResponseEntity<List<ItemsDto>>findOrderedItems(){
        return new ResponseEntity<>(itemService.getItemsWithOrders(true),HttpStatus.OK);
    }
    @GetMapping("/sales")
    public ResponseEntity<List<ItemsDto>>findItemsWithSales(){
        return new ResponseEntity<>(itemService.getItemsWithSales(true),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ItemsDto>updateItems(@RequestBody ItemsDto itemsDto,@PathVariable Long id){
        ItemsDto response = itemService.updateItemsById(itemsDto,id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteItems(@PathVariable Long id){
        itemService.deleteItems(id);
        return new ResponseEntity<>("item deleted successfully",HttpStatus.OK);
    }

}

