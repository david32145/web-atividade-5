package br.ufc.crateus.web.dtos;

public class CarDTO {
  private String name;
  private String brand;
  private long fabricationYear;
  private String saleDate;

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
