package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import model.ParcScooter;
import model.Scooter;
import vue.MenuPrincipal;

public class SupprimerScooterControl implements ActionListener {
    private MenuPrincipal vue;
    private ParcScooter parc;

    public SupprimerScooterControl(MenuPrincipal vue, ParcScooter parc) {
        this.vue = vue;
        this.parc = parc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = JOptionPane.showInputDialog(vue, "Numéro d'identification du scooter à supprimer :", "Suppression de scooter", JOptionPane.QUESTION_MESSAGE);
        if (id == null || id.trim().isEmpty()) {
            return; 
        }

        id = id.trim();

        Scooter scooter = parc.trouverScooter(id);
        if (scooter == null) {
            JOptionPane.showMessageDialog(vue, "Aucun scooter trouvé avec l'identifiant \"" + id + "\".", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(vue, 
                "Confirmez-vous la suppression du scooter " + id + " (Modèle : " + scooter.getModele() + ") ?", 
                "Confirmation suppression", 
                JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            boolean supprimé = parc.supprimerLocationEtScooter(id);
            if (supprimé) {
                try {
                    parc.sauvegarder();
                    JOptionPane.showMessageDialog(vue, "Scooter supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    
                    vue.dispose();
                    new MenuPrincipal();

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(vue, "Erreur lors de la sauvegarde.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(vue, "Échec de la suppression du scooter.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
