package br.ufc.crateus.web.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.crateus.web.models.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long>{ }
