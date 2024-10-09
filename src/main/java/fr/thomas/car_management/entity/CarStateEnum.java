package fr.thomas.car_management.entity;

public enum CarStateEnum {
    RESERVE("Réservée"),
    GARAGE("Au garage"),
    PARKING("Au parking");

    private final String displayName;

    // Constructeur de l'ENUM pour associer un libellé
    CarStateEnum(String displayName) {
        this.displayName = displayName;
    }

    // Méthode pour récupérer le libellé
    public String getDisplayName() {
        return displayName;
    }
}
