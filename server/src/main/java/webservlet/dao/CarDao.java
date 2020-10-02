package webservlet.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import webservlet.models.Car;

public class CarDao {
	private static CarDao instance = new CarDao();
	private static int nextId = 1;
	
	private Map<Integer, Car> carsMap;
	
	private CarDao() {
		this.carsMap = new HashMap<>();
	}
	
	public static CarDao getInstance() {
		return instance;
	}
	
	public Car addCar(Car car) {
		Car newCar = new Car(CarDao.nextId++, car.getName(), car.getBrand(), car.getFabricationYear(), car.getSaleDate());
		this.carsMap.put(newCar.getId(), newCar);
		return newCar;
	}
	
	public Collection<Car> getCars(Integer limit, String brand) {
		if(limit == null || limit < 0 || limit > this.carsMap.size()) {
			limit = this.carsMap.size();
		}
		return this.carsMap.values()
				.stream()
				.filter(car -> {
					return brand == null || car.getBrand().equals(brand);
				})
				.limit(limit)
				.collect(Collectors.toList());
	}
	
	public Car updateById(Car car, int id) {
		Car updatedCar = new Car(id, car.getName(), car.getBrand(), car.getFabricationYear(), car.getSaleDate());
		this.carsMap.put(id, updatedCar);
		return updatedCar;
	}
	
	public void deleteById(int id) {
		this.carsMap.remove(id);
	}
	
	public Car getCarById(int id) {
		return this.carsMap.get(id);
	}
	
	public boolean carExistsById(int id) {
		return getCarById(id) != null;
	}
}
