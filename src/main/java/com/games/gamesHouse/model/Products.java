package com.games.gamesHouse.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class Products {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "The attribute name is Mandatory")
  private String name;

  @NotBlank(message = "The attribute brand is Mandatory")
  private String brand;

  private String sinopse;
  @ManyToOne
  @JsonIgnoreProperties("products")
  private Categories category;


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

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getSinopse() {
    return sinopse;
  }

  public void setSinopse(String sinopse) {
    this.sinopse = sinopse;
  }

  public Categories getCategory() {
    return category;
  }

  public void setCategory(Categories category) {
    this.category = category;
  }




}
