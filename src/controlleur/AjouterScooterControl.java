package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import model.ParcScooter;
import model.Scooter;
import vue.MenuPrincipal;

public class AjouterScooterControl implements ActionListener {
    private MenuPrincipal vue;
    private ParcScooter parc;

    public AjouterScooterControl(MenuPrincipal vue, ParcScooter parc) {
        this.vue = vue;
        this.parc = parc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = JOptionPane.showInputDialog(vue, "Numéro d'identification du scooter :", "Ajout de scooter", JOptionPane.QUESTION_MESSAGE);
        if (id == null || id.trim().isEmpty()) {
            return; 
        }

        id = id.trim();

        // Verification d'existence
        if (parc.trouverScooter(id) != null) {
            JOptionPane.showMessageDialog(vue, "Un scooter avec l'identifiant \"" + id + "\" existe déjà dans le parc.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String modele = JOptionPane.showInputDialog(vue, "Modèle du scooter :", "Ajout de scooter", JOptionPane.QUESTION_MESSAGE);
        if (modele == null || modele.trim().isEmpty()) {
            return; 
        }

        modele = modele.trim();

       
        Scooter nouveauScooter = new Scooter(modele, id);
        parc.ajouterScooter(nouveauScooter);

        try {
            parc.sauvegarder();
            JOptionPane.showMessageDialog(vue, "Scooter ajouté avec succès !\nID : " + id + "\nModèle : " + modele, "Succès", JOptionPane.INFORMATION_MESSAGE);
           
            vue.dispose(); 
            new MenuPrincipal();
            
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(vue, 
                "Erreur de sauvegarde", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }

       
    }
}
