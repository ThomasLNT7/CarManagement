package fr.thomas.car_management.entity;

public enum CarColorEnum {
    RED("Rouge"),
    YELLOW("Jaune"),
    BLUE("Bleu"),
    GREEN("Vert"),
    WHITE("Blanc"),
    BLACK("Noir");


    private final String displayName;

    // Constructeur de l'ENUM pour associer un libellé
    CarColorEnum(String displayName) {
        this.displayName = displayName;
    }

    // Méthode pour récupérer le libellé
    public String getDisplayName() {
        return displayName;
    }

    public String getSkinUrl() {
        return "/images/" + this.name().toLowerCase() + ".png";
    }
}
