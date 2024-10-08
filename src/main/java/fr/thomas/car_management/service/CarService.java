package fr.thomas.car_management.service;

import fr.thomas.car_management.entity.Car;
import fr.thomas.car_management.entity.CarStateEnum;
import fr.thomas.car_management.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {return carRepository.findAll();}

    public Optional<Car> getCarById(Long id){
        return carRepository.findById(id);
    }

    public Car createCar(Car car){
        return carRepository.save(car);
    }

    public void deleteCar(Long id){
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new RuntimeException("Car not found with the id : " + id);
        }
    }

    // Méthode pour mettre à jour l'état d'une voiture
    public void updateCarState(Long id, String state) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            car.setState(state); // Convertit la chaîne en ENUM
            carRepository.save(car);  // Sauvegarde la modification
        }
    }
}
