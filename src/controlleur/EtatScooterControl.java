package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vue.EtatScooterView;
import vue.MenuPrincipal;
import model.ParcScooter;
import model.Scooter;

public class EtatScooterControl implements ActionListener {
    private EtatScooterView vue;
    private ParcScooter parc;

    public EtatScooterControl(EtatScooterView vue, ParcScooter parc) {
        this.vue = vue;
        this.parc = parc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getSubmit()) {
            afficherEtatScooter();
        }
    }

    private void afficherEtatScooter() {
        String idScooter = vue.getIdField().getText().trim();

        if (idScooter.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Veuillez saisir un numéro de scooter.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Scooter scooter = parc.trouverScooter(idScooter);
        if (scooter == null) {
            JOptionPane.showMessageDialog(vue, "Scooter introuvable pour l'ID : " + idScooter, "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            String etat = "État du scooter :\n" +
                          "Numéro : " + scooter.getNumIdentification() + "\n" +
                          "Modèle : " + scooter.getModele() + "\n" +
                          "Statut : " + scooter.getStatut() + "\n" +
                          "Kilométrage : " + scooter.getKilometrage() + " km\n";

            JOptionPane.showMessageDialog(vue, etat, "État du scooter", JOptionPane.INFORMATION_MESSAGE);
        }

        vue.getIdField().setText("");
    }

}