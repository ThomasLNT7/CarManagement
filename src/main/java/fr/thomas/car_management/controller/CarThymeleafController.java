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

    @PostMapping("/{carId}/items")
    public String addItemToProduct(@PathVariable Long carId, @ModelAttribute Services services, Model model) {
        // Ajouter le service à la voiture
        carService.addServiceToCar(carId, services);

        // Optionnel : Ajout de la voiture dans le modèle pour la redirection si nécessaire
        Car car = carService.getCarById(carId).orElseThrow(() -> new IllegalArgumentException("Car not found"));
        model.addAttribute("car", car);

        // Rediriger vers la page de détails de la voiture après ajout du service
        return "redirect:/cars/" + carId;
    }

    @GetMapping("/cars/{carId}/items/add")
    public String showAddServiceForm(@PathVariable Long carId, Model model) {
        Optional<Car> carOptional = carService.getCarById(carId);

        if (!carOptional.isPresent()) {
            return "redirect:/cars"; // Rediriger si la voiture n'est pas trouvée
        }

        Car car = carOptional.get();
        model.addAttribute("car", car);
        model.addAttribute("serviceNames", ServicesNomEnum.values()); // Liste des noms de services
        model.addAttribute("service", new Services()); // Initialiser un nouvel objet service

        return "redirect:/cars/" + carId; // Vue contenant le formulaire pour ajouter un service
    }





}
