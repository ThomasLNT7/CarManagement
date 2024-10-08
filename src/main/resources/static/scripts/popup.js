console.log("Début du script");

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
});

/**
 * Permettre le drop d'un élément dans une zone de dépôt.
 * @param {Event} ev - L'événement dragover
 */
function allowDrop(ev) {
    ev.preventDefault(); // Empêche l'action par défaut pour autoriser le drop
    ev.target.classList.add('drop-highlight'); // Ajouter un effet visuel de surlignage
}

/**
 * Gérer le drag d'une voiture. Enregistrer l'ID de la voiture et indiquer le drag en cours.
 * @param {Event} ev - L'événement dragstart
 */
function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.getAttribute("data-id")); // Enregistrer l'ID de la voiture
    document.body.classList.add('dragging'); // Appliquer un style global pour le drag
}

/**
 * Réinitialiser l'effet visuel lorsque l'utilisateur quitte une zone de dépôt.
 * @param {Event} ev - L'événement dragleave
 */
function resetDropHighlight(ev) {
    ev.target.classList.remove('drop-highlight'); // Enlever l'effet de surlignage
}

/**
 * Gérer le drop d'une voiture dans une nouvelle colonne. Mettre à jour son état.
 * @param {Event} ev - L'événement drop
 */
function drop(ev) {
    ev.preventDefault(); // Empêche le comportement par défaut
    var carId = ev.dataTransfer.getData("text"); // Obtenir l'ID de la voiture
    var newState = ev.target.getAttribute("data-state"); // Nouvel état de la colonne cible

    // Appel AJAX pour mettre à jour l'état de la voiture
    fetch('/cars/update-state/' + carId, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            'state': newState
        })
    }).then(response => {
        if (response.ok) {
            console.log('Voiture déplacée et état mis à jour avec succès');
            location.reload(); // Recharger la page pour voir les modifications
        } else {
            console.error('Échec de la mise à jour de l\'état');
        }
    }).catch(error => console.error('Erreur lors du drop:', error));

    // Réinitialiser les styles après le drop
    document.body.classList.remove('dragging');
}

/**
 * Réinitialiser les styles si l'utilisateur abandonne le drag sans déposer.
 */
document.addEventListener('dragend', function() {
    document.body.classList.remove('dragging'); // Réinitialiser les styles globaux
});

console.log("Fin du script");
