package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vue.AffichageParcView;
import vue.EtatScooterView;
import vue.LouerView;
import vue.MenuPrincipal;
import vue.RetourView;
import vue.SaisieParcView;
import model.ParcScooter;

public class MenuPrincipaleControl implements ActionListener {
    private MenuPrincipal vue;
    private ParcScooter parc;

    public MenuPrincipaleControl(MenuPrincipal vue, ParcScooter parc) {
        this.vue = vue;
        this.parc = parc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getLouer()) {
            ouvrirLouerView();
        } else if (e.getSource() == vue.getRetourner()) {
            ouvrirRetourView();
        } else if (e.getSource() == vue.getEtatscooter()) {
            ouvrirEtatScooterView();
        } else if (e.getSource() == vue.getEtatparc()) {
            ouvrirAffichageParcView();
        } else if (e.getSource() == vue.getSaisieparc()) {
            ouvrirSaisieParcView();
        } else if (e.getSource() == vue.getQuitter()) {
            quitterApplication();
        }
    }

    private void ouvrirLouerView() {
        LouerView louerView = new LouerView(parc);
        vue.dispose();
    }

    private void ouvrirRetourView() {
        RetourView retourView = new RetourView(parc);
        vue.dispose();
    }

    private void ouvrirEtatScooterView() {
        EtatScooterView etatView = new EtatScooterView(parc);
        vue.dispose();
    }

    private void ouvrirAffichageParcView() {
        AffichageParcView parcView = new AffichageParcView(parc);
        vue.dispose();
    }

    private void ouvrirSaisieParcView() {
        SaisieParcView saisieView = new SaisieParcView(parc);
        vue.dispose();
    }

    private void quitterApplication() {
    	  try {
              parc.sauvegarder(); 
              JOptionPane.showMessageDialog(vue, "Sauvegarde réussie");
              System.exit(0); 
          } catch (IOException ex) {
              JOptionPane.showMessageDialog(vue,
                  "Erreur de sauvegarde: " + ex.getMessage(),
                  "Erreur",
                  JOptionPane.ERROR_MESSAGE);
              System.exit(1); 
          }
      }
}