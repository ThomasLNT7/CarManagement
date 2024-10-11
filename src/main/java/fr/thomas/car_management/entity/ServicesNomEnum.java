package fr.thomas.car_management.entity;

public enum ServicesNomEnum {
    REPARATION("Réparation"),
    REMPLACEMENT("Remplacement"),
    VIDANGE("Vidange"),
    CONTROLE_TECHNIQUE("Contrôle technique")

    ;


    ServicesNomEnum(String displayName) { this.displayName = displayName;
    }

    private final String displayName;

    // Méthode pour récupérer le libellé
    public String getDisplayName() {
        return displayName;
    }
}
