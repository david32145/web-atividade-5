package br.ufc.crateus.web.services;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import br.ufc.crateus.web.models.Car;
import br.ufc.crateus.web.repositories.CarRepository;

@Service
public class CarService {
  private CarRepository carRepository;
  private EntityManager entityManager;

  public CarService(CarRepository carRepository, EntityManager entityManager) {
    this.carRepository = carRepository;
    this.entityManager = entityManager;
  }

  public Car createCar(Car car) {
    car = carRepository.save(car);
    return car;
  }

  public Car updateCar(Car car) {
    boolean carExists = carRepository.existsById(car.getId());
    if (carExists) {
      car = carRepository.save(car);
      return car;
    }
    return null;
  }

  public Iterable<Car> findAllCars(Integer limit, String brand) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
    Root<Car> root = criteriaQuery.from(Car.class);
    criteriaQuery.select(root);
    if(isValidBrand(brand)) {
      criteriaQuery
        .where(
          criteriaBuilder
            .like(root.get("brand"), "%" + brand + "%")
        );
    }
    TypedQuery<Car> query = entityManager.createQuery(criteriaQuery);
      if(isValidLimit(limit)) {
        query.setMaxResults(limit);
      }
    return query.getResultList();
  }

  public Car findCarById(long carId) {
    return carRepository.findById(carId).orElse(null);
  }

  public boolean deleteCarById(long carId) {
    boolean carExists = carRepository.existsById(carId);
    if (carExists) {
      carRepository.deleteById(carId);
      return true;
    }
    return false;
  }

  public boolean isValidLimit(Integer limit) {
    return limit != null && limit > 0;
  }

  public boolean isValidBrand(String brand) {
    return brand != null && !brand.isBlank();
  }
}
