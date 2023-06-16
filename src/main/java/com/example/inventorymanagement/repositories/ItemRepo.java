package com.example.inventorymanagement.repositories;
import com.example.inventorymanagement.entities.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Items, Long> {
List<Items>findByOrders(boolean orders);
List<Items>findBySales(boolean sales);
}
