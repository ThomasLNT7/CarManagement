package fr.thomas.car_management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @JsonIgnore
    private Car car;

    private ServicesNomEnum nomService;

    private String zlText;

    private String commentaire;

    // Remplacer String par LocalDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateDebut;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFin;

    public Services(){}

    public Services(Long id, Car car, ServicesNomEnum nomService, String commentaire, LocalDate dateDebut, LocalDate dateFin, String zlText) {
        this.id = id;
        this.car = car;
        this.nomService = nomService;
        this.zlText = zlText;
        this.commentaire = commentaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("car_id")
    public Long getCar_id() {
        return car != null ? car.getId() : null;
    }

    public void setCar_id(Car car_id) {
        this.car = car_id;
    }

    public ServicesNomEnum getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = ServicesNomEnum.valueOf(nomService);
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getZlText() {
        return zlText;
    }

    public void setZlText(String zlText) {
        this.zlText = zlText;
    }
}
