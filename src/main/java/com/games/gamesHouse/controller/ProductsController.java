package com.games.gamesHouse.controller;

import com.games.gamesHouse.model.Products;
import com.games.gamesHouse.repository.CategoriesRepository;
import com.games.gamesHouse.repository.ProductsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductsController {

  @Autowired
  private ProductsRepository productsRepository;

  @Autowired
  private CategoriesRepository categoriesRepository;

  @GetMapping
  ResponseEntity<List<Products>> getAll() {
    return ResponseEntity.ok(productsRepository.findAll());
  }

  @GetMapping("/name")
  ResponseEntity<List<Products>> getAllByName(@RequestParam(name = "q") String q) {
    return ResponseEntity.ok(productsRepository.findAllByNameContainingIgnoreCase(q));
  }

  @GetMapping("/brand")
  ResponseEntity<List<Products>> getAllByBrand(@RequestParam(name = "q") String q) {
    return ResponseEntity.ok(productsRepository.findAllByBrandContainingIgnoreCase(q));
  }

  @PostMapping
  ResponseEntity<Products> create(@Valid @RequestBody Products products) {
    if (categoriesRepository.existsById(products.getCategory().getId())) {
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(productsRepository.save(products));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Theme not Exists", null);
  }

  @PutMapping
  ResponseEntity<Products> update(@Valid @RequestBody Products products) {
    if (productsRepository.existsById(products.getId())) {
      if (categoriesRepository.existsById(products.getCategory().getId())) {
        return ResponseEntity.status(HttpStatus.OK).body(productsRepository.save(products));
      }

      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not Exists", null);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @DeleteMapping("/{id}")
  void delete(@PathVariable Long id) {
    Optional<Products> product = productsRepository.findById(id);
    if (product.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    productsRepository.deleteById(id);
  }

}
