
package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;

import vue.MenuPrincipal;

public class RetourMenuControl implements ActionListener {
    private JFrame currentFrame;

    public RetourMenuControl(JFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new MenuPrincipal();
            currentFrame.dispose();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}