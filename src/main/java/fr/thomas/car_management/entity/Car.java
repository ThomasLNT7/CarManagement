package fr.thomas.car_management.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String immatriculation;
    private String purchase_date;
    private String technical_control_date;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Services> services = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private CarStateEnum state;

    @Enumerated(EnumType.STRING)
    private CarColorEnum color;

    public Car() {}

    public Car(String brand, String model, String purchase_date, String technical_control_date, String state, String color, String immatriculation, List<Services> services) {
        this.brand = brand;
        this.model = model;
        this.purchase_date = purchase_date;
        this.technical_control_date = technical_control_date;
        this.state = CarStateEnum.valueOf(state);
        this.color = CarColorEnum.valueOf(String.valueOf(color));
        this.immatriculation = immatriculation;
        this.services = services;;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPurchaseDate() {
        return purchase_date;
    }

    public void setPurchaseDate(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getTechnicalControlDate() {
        return technical_control_date;
    }

    public void setTechnicalControlDate(String technical_control_date) {
        this.technical_control_date = technical_control_date;
    }

    public CarStateEnum getState() {
        return state;
    }

    public void setState(String state) {
        this.state = CarStateEnum.valueOf(state);
    }

    public CarColorEnum getColor() {
        return color;
    }

    public void setColor(CarColorEnum color) {
        this.color = color;
    }

    public String getSkinUrl() {
        return this.color.getSkinUrl();
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }
}
