package org.example.server.service;

import lombok.RequiredArgsConstructor;
import org.example.server.model.Car;
import org.example.server.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    @Autowired
    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<Car> findAll() {
        return repository.findAll();
    }

    public Optional<Car> findById(Long id) {
        return repository.findById(id);
    }

    public Car save(Car car) {
        return repository.save(car);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Car update(Long id, Car newCar) {
        return repository.findById(id)
                .map(car -> {
                    car.setBrand(newCar.getBrand());
                    car.setModel(newCar.getModel());
                    car.setColor(newCar.getColor());
                    car.setType(newCar.getType());
                    car.setNumberCar(newCar.getNumberCar());
                    return repository.save(car);
                }).orElse(null);
    }

}
