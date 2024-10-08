package fr.thomas.car_management.controller;

import fr.thomas.car_management.entity.Car;
import fr.thomas.car_management.entity.CarColorEnum;
import fr.thomas.car_management.entity.CarStateEnum;
import fr.thomas.car_management.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CarThymeleafController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    private String getCars(Model model) {
        List<Car> cars =carService.getAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("carStates", CarStateEnum.values());
        model.addAttribute("carColors", CarColorEnum.values());
        return "index";
    }

    @GetMapping("/cars/{id}")
    private String getBookById(@PathVariable Long id, Model model) {
        Optional<Car> car = carService.getCarById(id);
        if (car.isPresent()) {
            model.addAttribute("car", car.get());
            return "car-details";
        } else {
            return "error";
        }
    }

    @PostMapping("/cars/add")
    public String addCar(@ModelAttribute Car car) {
        carService.createCar(car);
        return "redirect:/cars"; // Redirige vers la page de la liste des voitures après ajout
    }

    @GetMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/cars"; // Redirige vers la liste des voitures après suppression
    }

    // Méthode pour mettre à jour l'état d'une voiture
    @PostMapping("/cars/update-state/{id}")
    @ResponseBody
    public String updateCarState(@PathVariable Long id, @RequestParam("state") String state) {
        carService.updateCarState(id, state);
        return "redirect:/cars";
    }


}
