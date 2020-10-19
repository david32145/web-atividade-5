package br.ufc.crateus.web.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;
  private String name;
  private String brand;
  private long fabricationYear;
  private String saleDate;

  protected Car() {
  }

  public Car(String name, String brand, long fabricationYear, String saleDate) {
    this.name = name;
    this.brand = brand;
    this.fabricationYear = fabricationYear;
    this.saleDate = saleDate;
  }

  public Car(long id, String name, String brand, long fabricationYear, String saleDate) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.fabricationYear = fabricationYear;
    this.saleDate = saleDate;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBrand() {
    return brand;
  }

  public long getFabricationYear() {
    return fabricationYear;
  }

  public String getSaleDate() {
    return saleDate;
  }
}
