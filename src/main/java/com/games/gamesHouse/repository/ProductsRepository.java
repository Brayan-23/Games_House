package com.games.gamesHouse.repository;

import com.games.gamesHouse.model.Categories;
import com.games.gamesHouse.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsRepository  extends JpaRepository<Products, Long> {

  List<Products> findAllByNameContainingIgnoreCase(@Param("name") String name);
  List<Products> findAllByBrandContainingIgnoreCase(@Param("brand") String brand);

}
