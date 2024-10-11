package fr.thomas.car_management.service;

import fr.thomas.car_management.entity.Car;
import fr.thomas.car_management.entity.Services;
import fr.thomas.car_management.repository.CarRepository;
import fr.thomas.car_management.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ServicesRepository servicesRepository;

    @Autowired
    public CarService(CarRepository carRepository, ServicesRepository servicesRepository) {
        this.carRepository = carRepository;
        this.servicesRepository = servicesRepository;
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

    public void addServiceToCar(Long carId, Services services) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        services.setCar_id(car);
        servicesRepository.save(services);
    }

    public void deleteService(Long service_id) {
        if (servicesRepository.existsById(service_id)) {
            servicesRepository.deleteById(service_id);
        } else {
            throw new RuntimeException("Car not found with the id : " + service_id);
        }
    }

}
