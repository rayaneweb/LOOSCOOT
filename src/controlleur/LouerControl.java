package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import vue.LouerView;
import vue.MenuPrincipal;
import model.ParcScooter;
import model.Client;
import model.Scooter;

public class LouerControl implements ActionListener {
    private LouerView vue;
    private ParcScooter parc;

    public LouerControl(LouerView vue, ParcScooter parc) {
        this.vue = vue;
        this.parc = parc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getSubmitButton()) {
            traiterLocation();
        }
    }

    private void traiterLocation() {
        String idScooter = vue.getIdScooter();
        String dateDebutStr = vue.getDateField().getText();
        String dateFinStr = vue.getDateField2().getText();

       
        if (idScooter == null || idScooter.isBlank() ||
            dateDebutStr == null || dateDebutStr.isBlank() ||
            dateFinStr == null || dateFinStr.isBlank()) {
            vue.showMessage("Veuillez remplir toutes les cases.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateDebut = LocalDate.parse(dateDebutStr, formatter);
            LocalDate dateFin = LocalDate.parse(dateFinStr, formatter);

           
            if (dateFin.isBefore(dateDebut)) {
                vue.showMessage("La date de fin doit être postérieure ou égale à la date de début.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Scooter scooter = parc.trouverScooter(idScooter);
            if (scooter == null) {
                vue.showMessage("Scooter introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!"DISPONIBLE".equals(scooter.getStatut()) || parc.trouverLocationActive(idScooter) != null) {
                vue.showMessage("Ce scooter est déjà en location actuellement.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!parc.estDisponible(idScooter, dateDebut, dateFin)) {
                vue.showMessage("Ce scooter n'est pas disponible aux dates indiquées.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

           
            String nom = JOptionPane.showInputDialog(vue, "Nom du client :");
            String prenom = JOptionPane.showInputDialog(vue, "Prénom du client :");

            if (nom == null || prenom == null || nom.isBlank() || prenom.isBlank()) {
                vue.showMessage("Le nom et le prénom doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            nom = nom.trim();
            prenom = prenom.trim();


            Client client = parc.trouverClient(nom, prenom);

            String tel = null;
            String permis = null;

          
            if (client == null) {
                tel = JOptionPane.showInputDialog(vue, "Téléphone du client :");
                permis = JOptionPane.showInputDialog(vue, "Catégorie du permis :");

                if (tel == null || permis == null || tel.isBlank() || permis.isBlank()) {
                    vue.showMessage("Le téléphone et le permis doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tel = tel.trim();
                permis = permis.trim().toUpperCase();

                
                if (!permis.equalsIgnoreCase(scooter.getPermis())) {
                    vue.showMessage("Permis invalide. Permis requis : " + scooter.getPermis(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

             
                client = new Client(nom, prenom, tel, permis);
                parc.ajouterClient(client);

            } else {
             
                if (!client.getCategoriePermis().equalsIgnoreCase(scooter.getPermis())) {
                    vue.showMessage("Le client ne possède pas le permis requis. Permis requis : " + scooter.getPermis(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            boolean succes = parc.louerScooter(idScooter, client, dateDebut, dateFin);
            if (succes) {
                try {
                    parc.sauvegarder();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                vue.showMessage("Location réussie !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                vue.clearForm();
                retourAuMenu();
            } else {
                vue.showMessage("Échec de la location.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            vue.showMessage("Format de date invalide. Utilisez JJ/MM/AAAA.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
