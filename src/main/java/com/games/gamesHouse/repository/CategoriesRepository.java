package com.games.gamesHouse.repository;

import com.games.gamesHouse.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

  List<Categories> findAllByNameContainingIgnoreCase(@Param("name") String name);
  List<Categories> findAllByDescriptionContainingIgnoreCase(@Param("description") String description);

}
