package fr.thomas.car_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String purchase_date;
    private String technical_control_date;
    private String emptying_date;

    @Enumerated(EnumType.STRING)
    private CarStateEnum state;

    public Car() {}

    public Car(String brand, String model, String purchase_date, String technical_control_date, String emptying_date, String state) {
        this.brand = brand;
        this.model = model;
        this.purchase_date = purchase_date;
        this.technical_control_date = technical_control_date;
        this.emptying_date = emptying_date;
        this.state = CarStateEnum.valueOf(state);
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

    public String getEmptyingDate() {
        return emptying_date;
    }

    public void setEmptyingDate(String emptying_date) {
        this.emptying_date = emptying_date;
    }

    public CarStateEnum getState() {
        return state;
    }

    public void setState(String state) {
        this.state = CarStateEnum.valueOf(state);
    }


}
