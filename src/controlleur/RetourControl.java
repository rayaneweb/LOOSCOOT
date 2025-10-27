package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import vue.RetourView;
import vue.MenuPrincipal;
import model.Location;
import model.ParcScooter;

public class RetourControl implements ActionListener {
    private RetourView vue;
    private ParcScooter parc;

    public RetourControl(RetourView vue, ParcScooter parc) {
        this.vue = vue;
        this.parc = parc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getSubmit()) {
            traiterRetour();
        }
    }

    private void traiterRetour() {
        String idScooter = vue.getIdField().getText().trim();

        if (idScooter.isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Veuillez entrer un identifiant de scooter.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (parc.trouverScooter(idScooter) == null) {
            JOptionPane.showMessageDialog(vue, "Erreur : Aucun scooter avec cet identifiant.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Location locationActive = parc.trouverLocationActive(idScooter);

        if (locationActive == null) {
            JOptionPane.showMessageDialog(vue, "Erreur : Ce scooter n'est pas en location actuellement.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String kmStr = JOptionPane.showInputDialog(vue, "Kilométrage ajouté :", "Retour de scooter", JOptionPane.QUESTION_MESSAGE);

        if (kmStr == null || kmStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vue, "Opération annulée ou champ vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int kmAjoutes = Integer.parseInt(kmStr.trim());
            boolean success = parc.retournerScooter(idScooter, kmAjoutes);

            if (success) {
                try {
                    parc.sauvegarder();  
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(vue, "Scooter retourné avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                retourAuMenu();
            } else {
                JOptionPane.showMessageDialog(vue, "Erreur lors du retour du scooter.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vue, "Veuillez entrer un nombre valide pour le kilométrage.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void retourAuMenu() {
        try {
            new MenuPrincipal();
            vue.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
