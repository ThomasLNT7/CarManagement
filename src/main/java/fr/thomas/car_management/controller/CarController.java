package fr.thomas.car_management.controller;

import fr.thomas.car_management.entity.Car;
import fr.thomas.car_management.entity.Services;
import fr.thomas.car_management.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    @PostMapping
    public Car createCar(@RequestBody Car car){
        return carService.createCar(car);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{carId}/items")
    public ResponseEntity<Void> addItemToProduct(@PathVariable Long carId, @RequestBody Services services) {
        carService.addServiceToCar(carId, services);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
