package com.games.gamesHouse.controller;

import com.games.gamesHouse.model.Categories;
import com.games.gamesHouse.repository.CategoriesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriesController {

  @Autowired
  private CategoriesRepository categoriesRepository;

  @PutMapping
  public ResponseEntity<Categories> put(@Valid @RequestBody Categories category) {
    return categoriesRepository.findById(category.getId())
        .map(resposta -> ResponseEntity.status(HttpStatus.OK)
            .body(categoriesRepository.save(category)))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @PostMapping
  ResponseEntity<Categories> post(@Valid @RequestBody Categories category) {
    return ResponseEntity.status(HttpStatus.CREATED).body(categoriesRepository.save(category));
  }

  @GetMapping
  ResponseEntity<List<Categories>> getAll() {
    return ResponseEntity.ok(categoriesRepository.findAll());
  }

  @GetMapping("/name")
  ResponseEntity<List<Categories>> getAllByName(@RequestParam(name = "q") String q) {
    return ResponseEntity.ok(categoriesRepository.findAllByNameContainingIgnoreCase(q));
  }

  @GetMapping("/description")
  ResponseEntity<List<Categories>> getAllByDescription(@RequestParam(name = "q") String q) {
    return ResponseEntity.ok(categoriesRepository.findAllByDescriptionContainingIgnoreCase(q));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  void delete(@PathVariable Long id) {
    Optional<Categories> category = categoriesRepository.findById(id);

    if (category.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    categoriesRepository.deleteById(id);
  }

}
