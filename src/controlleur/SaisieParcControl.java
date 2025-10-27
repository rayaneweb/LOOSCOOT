package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.MenuPrincipal;
import vue.SaisieParcView;

public class SaisieParcControl implements ActionListener {
    private SaisieParcView vue;

    public SaisieParcControl(SaisieParcView vue) {
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