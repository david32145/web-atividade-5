package webservlet.models;

public class Car {
	private int id;
	private String name;
	private String brand;
	private long fabricationYear;
	private String saleDate;
	
	public Car(int id, String name, String brand, long fabricationYear, String saleDate) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.fabricationYear = fabricationYear;
		this.saleDate = saleDate;
	}
	
	public int getId() {
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
