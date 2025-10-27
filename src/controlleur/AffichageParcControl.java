package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.AffichageParcView;
import vue.MenuPrincipal;

public class AffichageParcControl implements ActionListener {
    private AffichageParcView vue;

    public AffichageParcControl(AffichageParcView vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vue.getRetourButton()) {
            retourAuMenu();
        }
    }

    private void retourAuMenu() {
        try {
            MenuPrincipal menu = new MenuPrincipal();
            vue.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}