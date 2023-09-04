package com.games.gamesHouse.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


@Entity
@Table(name = "categories")
public class Categories {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "The attribute name is Mandatory")
  @Column(unique = true)
  private String name;

  private String description;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties("category")
  private List<Products> products;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Products> getProducts() {
    return products;
  }

  public void setProducts(List<Products> products) {
    this.products = products;
  }


}
