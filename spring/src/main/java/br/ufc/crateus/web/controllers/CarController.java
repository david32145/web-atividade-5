package br.ufc.crateus.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.crateus.web.dtos.CarDTO;
import br.ufc.crateus.web.models.Car;
import br.ufc.crateus.web.services.CarService;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin()
public class CarController {

  private CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  @GetMapping()
  public ResponseEntity<Iterable<Car>> getCars(
      @RequestParam(name = "limit", required = false) Integer limit,
      @RequestParam(name = "brand", required = false) String brand) {
    return new ResponseEntity<Iterable<Car>>(carService.findAllCars(limit, brand), HttpStatus.OK);
  }

  @GetMapping("/{carId}")
  public ResponseEntity<?> getCarById(@PathVariable long carId) {
    Car car = carService.findCarById(carId);
    if (car == null) {
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Car>(car, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<Car> postCar(@RequestBody CarDTO request) {
    Car car = new Car(request.getName(), request.getBrand(), request.getFabricationYear(), request.getSaleDate());
    car = carService.createCar(car);
    return new ResponseEntity<Car>(car, HttpStatus.CREATED);
  }

  @PutMapping("/{carId}")
  public ResponseEntity<?> putCar(@RequestBody CarDTO request, @PathVariable long carId) {
    Car car = new Car(carId, request.getName(), request.getBrand(), request.getFabricationYear(),
        request.getSaleDate());
    car = carService.updateCar(car);
    if (car == null) {
      Map<String, String> responseError = new HashMap<>();
      responseError.put("error", "car not found");
      return new ResponseEntity<Map<String, String>>(responseError, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<Car>(car, HttpStatus.OK);
  }

  @DeleteMapping("/{carId}")
  public ResponseEntity<?> deleteCar(@PathVariable long carId) {
    boolean deleteCarSuccess = carService.deleteCarById(carId);
    if (deleteCarSuccess) {
      return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    Map<String, String> responseError = new HashMap<>();
    responseError.put("error", "car not found");
    return new ResponseEntity<Map<String, String>>(responseError, HttpStatus.BAD_REQUEST);
  }
}
