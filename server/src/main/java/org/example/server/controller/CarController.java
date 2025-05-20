package org.example.server.controller;

import lombok.RequiredArgsConstructor;
import org.example.server.model.Car;
import org.example.server.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
//@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CarController {

    private final CarService service;

    @Autowired // Esta anotación le dice a Spring que use este constructor para inyectar CarService
    public CarController(CarService service) {
        this.service = service;
    }
    @GetMapping
    public List<Car> getAllCars() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return service.save(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        Car updated = service.update(id, car);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
