package fr.thomas.car_management.controller;

import fr.thomas.car_management.entity.*;
import fr.thomas.car_management.repository.ServicesRepository;
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

    @Autowired
    private ServicesRepository servicesRepository;

    @GetMapping("/cars")
    private String getCars(Model model) {
        List<Car> cars =carService.getAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("carStates", CarStateEnum.values());
        model.addAttribute("carColors", CarColorEnum.values());
        return "index";
    }

    @GetMapping("/cars/{id}")
    private String getCarById(@PathVariable Long id, Model model) {
        Optional<Car> car = carService.getCarById(id);
        if (car.isPresent()) {
            model.addAttribute("car", car.get());
            model.addAttribute("serviceNames", ServicesNomEnum.values());

            // Récupération des services triés
            List<Services> services = servicesRepository.findAllByOrderByDateDebutAsc();
            model.addAttribute("services", services);  // Ajout de la liste des services triés au modèle

            return "car-details";  // Retourne la vue 'car-details'
        } else {
            return "error";  // En cas d'erreur, retourne la vue 'error'
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

    @GetMapping("/services/delete/{id}/{carId}")
    public String deleteService(@PathVariable Long id, @PathVariable Long carId) {
        carService.deleteService(id); // Suppression du service
        return "redirect:/cars/" + carId; // Redirige vers la voiture spécifique
    }

    @GetMapping("/services/add/{carId}")
    public String showAddServiceForm(@PathVariable Long carId, Model model) {
        // Trouve la voiture à laquelle le service sera ajouté
        Optional<Car> car = carService.getCarById(carId);

        if (!car.isPresent()) {
            // Gérer le cas où la voiture n'existe pas
            return "redirect:/cars"; // Rediriger vers la liste des voitures si la voiture n'est pas trouvée
        }

        // Ajouter la voiture et la liste des services possibles au modèle
        model.addAttribute("car", car);
        model.addAttribute("serviceNames", ServicesNomEnum.values());
        model.addAttribute("service", new Services()); // Initialiser un nouvel objet service


        return "redirect:/cars" + carId; // Vue contenant le formulaire pour ajouter un service
    }



}
