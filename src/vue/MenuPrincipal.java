package vue;

import javax.swing.*;
import java.awt.*;
import controlleur.MenuPrincipaleControl;
import controlleur.SupprimerScooterControl;
import model.ParcScooter;
import java.io.IOException;
import controlleur.AjouterScooterControl;

public class MenuPrincipal extends Standard {
    private JPanel menu;
    private JButton louer, retourner, etatscooter, etatparc, saisieparc, ajouterScooter, supprimerScooter, quitter;
    private JLabel adresse;
    private JLabel tel;
    private ParcScooter parcScooter;
    private MenuPrincipaleControl controller;

    public MenuPrincipal() throws IOException {
        super();

        // Charger le parc de scooters depuis la source
        this.parcScooter = ParcScooter.charger();
        getRetourButton().setVisible(false);

        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);

        // Initialisation du panel du menu
        menu = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 100));
        menu.setOpaque(false);

     
        JPanel verticalButtons = new JPanel(new GridLayout(8, 1, 0, 25)); // 8 lignes maintenant
        verticalButtons.setOpaque(false);

        // Initialisation des boutons
        ajouterScooter = makeButton("Ajouter un scooter"); 
        supprimerScooter = makeButton("Supprimer un scooter"); // Nouveau bouton ajouté ici
        louer = makeButton("Louer un scooter");
        retourner = makeButton("Retour d'un scooter");
        etatscooter = makeButton("État d'un scooter");
        etatparc = makeButton("Affichage du parc de scooters");
        saisieparc = makeButton("Saisie du parc des scooters");
        quitter = makeButton("Quitter le programme");

        // Ajouter les boutons au panel vertical dans l'ordre voulu
        verticalButtons.add(ajouterScooter);
        verticalButtons.add(supprimerScooter); 
        verticalButtons.add(louer);
        verticalButtons.add(retourner);
        verticalButtons.add(etatscooter);
        verticalButtons.add(etatparc);
        verticalButtons.add(saisieparc);
        verticalButtons.add(quitter);

        // Ajouter les boutons au menu principal
        menu.add(verticalButtons);
        container.add(menu, BorderLayout.CENTER);

       
        // Ajouter le container au panel principal
        mainPanel.add(container, BorderLayout.CENTER);

        // Initialiser le contrôleur et ajouter les écouteurs
        controller = new MenuPrincipaleControl(this, parcScooter);
        
        louer.addActionListener(controller);
        retourner.addActionListener(controller);
        etatscooter.addActionListener(controller);
        etatparc.addActionListener(controller);
        saisieparc.addActionListener(controller);
        ajouterScooter.addActionListener(new AjouterScooterControl(this, parcScooter));
        supprimerScooter.addActionListener(new SupprimerScooterControl(this,parcScooter));
        quitter.addActionListener(controller);

        
    }

    // Méthode pour créer un bouton avec un texte personnalisé
    private JButton makeButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    // Méthode pour créer un label avec un texte donne
    private JLabel makeLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setForeground(Color.BLACK);
        return label;
    }

    // Getters pour accéder aux boutons dans le contrôleur
    public JButton getAjouterScooter() { return ajouterScooter; }
    public JButton getSupprimerScooter() { return supprimerScooter; } // nouveau getter
    public JButton getLouer() { return louer; }
    public JButton getRetourner() { return retourner; }
    public JButton getEtatscooter() { return etatscooter; }
    public JButton getEtatparc() { return etatparc; }
    public JButton getSaisieparc() { return saisieparc; }
    public JButton getQuitter() { return quitter; }

    // Getter pour accéder au parc de scooters
    public ParcScooter getParcScooter() {
        return this.parcScooter;
    }
}
