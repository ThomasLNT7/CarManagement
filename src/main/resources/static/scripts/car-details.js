document.addEventListener("DOMContentLoaded", function() {
    var openPopupBtn = document.getElementById("openPopupBtn");
    var closePopupBtn = document.getElementById("closePopupBtn");
    var popup = document.getElementById("popup");

    // Ouvrir la popup
    openPopupBtn.addEventListener("click", function() {
        console.log("click");
        popup.style.display = "block";
    });

    // Fermer la popup en cliquant sur la croix
    closePopupBtn.addEventListener("click", function() {
        popup.style.display = "none";
    });

    // Fermer la popup en cliquant en dehors de son contenu
    window.addEventListener("click", function(event) {
        if (event.target === popup) {
            popup.style.display = "none";
        }
    });

    const buttons = document.querySelectorAll('.button-group button');
        buttons.forEach(button => {
            button.addEventListener('click', () => {
                button.style.transform = 'scale(0.95)';
                setTimeout(() => {
                    button.style.transform = 'scale(1)';
                }, 150);
            });
        });
});

// EXTRA INFO
document.addEventListener("DOMContentLoaded", function() {
    var toggleExtraInfoBtn = document.getElementById("toggleExtraInfo");
    var extraInfoSection = document.getElementById("extraInfo");

    // Toggle pour afficher/masquer la section des informations supplémentaires
    toggleExtraInfoBtn.addEventListener("click", function() {
        if (extraInfoSection.style.display === "none") {
            extraInfoSection.style.display = "block";
            toggleExtraInfoBtn.textContent = "Masquer les informations supplémentaires";
        } else {
            extraInfoSection.style.display = "none";
            toggleExtraInfoBtn.textContent = "Afficher les informations supplémentaires";
        }
    });
});

// Fonction pour afficher/masquer le champ zlText
    function toggleZlTextField() {
        const nomService = document.getElementById('nomService').value;
        const zlTextContainer = document.getElementById('zlTextContainer');

        // Affiche le champ zlText uniquement pour REPARATION et REMPLACEMENT
        if (nomService === 'REPARATION' || nomService === 'REMPLACEMENT') {
            zlTextContainer.style.display = 'block';
        } else {
            zlTextContainer.style.display = 'none';
        }
    }

    // Appel initial pour cacher le champ si un service est déjà sélectionné
    document.addEventListener('DOMContentLoaded', function() {
        toggleZlTextField();
    });
