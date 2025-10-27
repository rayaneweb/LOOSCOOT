package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controlleur.EtatScooterControl;
import controlleur.RetourMenuControl;
import model.ParcScooter;

public class EtatScooterView extends Standard {
    private JTextField idField;
    private JButton submit;

   
    public EtatScooterView(ParcScooter parcScooter) {
        super();

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setOpaque(false);

        JLabel label = new JLabel("N° d'identification :");
        label.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        label.setForeground(new Color(0xF39C50));
        panel.add(label);

        idField = new JTextField(12);
        idField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(idField);

        submit = new JButton("Vérifier");
        submit.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        submit.setBackground(new Color(0xF39C50));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.setPreferredSize(new Dimension(120, 40));
        panel.add(submit);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);

        this.add(mainPanel);

       
        EtatScooterControl control = new EtatScooterControl(this, parcScooter);
        submit.addActionListener(control);
        getRetourButton().addActionListener(new RetourMenuControl(this));
    }

    public JTextField getIdField() {
        return idField;
    }

    public void setIdField(JTextField idField) {
        this.idField = idField;
    }

    public JButton getSubmit() {
        return submit;
    }

    public void setSubmit(JButton submit) {
        this.submit = submit;
    }

   
}
