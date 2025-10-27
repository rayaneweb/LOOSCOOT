package vue;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import controlleur.LouerControl;
import model.ParcScooter;
import controlleur.RetourMenuControl;

public class LouerView extends Standard {
    private JTextField idField, dateField, dateField2;
    private JButton submitButton;
    private ParcScooter parcScooter;

    public LouerView(ParcScooter parcScooter) {
        super();
        this.parcScooter = parcScooter;

        // Panel principal contenant les champs
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 0, 20)); // 4 lignes, espacées verticalement
        formPanel.setOpaque(true);
        formPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200)); // marges pour centrer

        // Ligne 1 : ID
        JPanel idPanel = new JPanel(new BorderLayout());
        idPanel.setOpaque(false);
        JLabel label = new JLabel("N° d'identification :");
        label.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        label.setForeground(new Color(0xF39C50));
        idField = new JTextField(12);
        idField.setFont(new Font("Arial", Font.PLAIN, 16));
        idPanel.add(label, BorderLayout.NORTH);
        idPanel.add(idField, BorderLayout.CENTER);
        formPanel.add(idPanel);

        // Ligne 2 : Date début
        JPanel dateDebutPanel = new JPanel(new BorderLayout());
        dateDebutPanel.setOpaque(false);
        JLabel label2 = new JLabel("Date début (JJ/MM/AAAA) :");
        label2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        label2.setForeground(new Color(0xF39C50));
        dateField = new JTextField(12);
        dateField.setFont(new Font("Arial", Font.PLAIN, 16));
        dateDebutPanel.add(label2, BorderLayout.NORTH);
        dateDebutPanel.add(dateField, BorderLayout.CENTER);
        formPanel.add(dateDebutPanel);

        // Ligne 3 : Date fin
        JPanel dateFinPanel = new JPanel(new BorderLayout());
        dateFinPanel.setOpaque(false);
        JLabel label3 = new JLabel("Date fin (JJ/MM/AAAA) :");
        label3.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        label3.setForeground(new Color(0xF39C50));
        dateField2 = new JTextField(12);
        dateField2.setFont(new Font("Arial", Font.PLAIN, 16));
        dateFinPanel.add(label3, BorderLayout.NORTH);
        dateFinPanel.add(dateField2, BorderLayout.CENTER);
        formPanel.add(dateFinPanel);

        // Ligne 4 : Bouton
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        submitButton = new JButton("Louer");
        submitButton.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        submitButton.setBackground(new Color(0xF39C50));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setPreferredSize(new Dimension(120, 40));
        buttonPanel.add(submitButton);
        formPanel.add(buttonPanel);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Créer le contrôleur avec l'instance de la vue et du ParcScooter
        LouerControl controller = new LouerControl(this, parcScooter);
        submitButton.addActionListener(controller);
        getRetourButton().addActionListener(new RetourMenuControl(this));
    }

    // Accesseurs
    public String getIdScooter() { return idField.getText().trim(); }
    public JTextField getIdField() { return idField; }
    public void setIdField(JTextField idField) { this.idField = idField; }
    public JTextField getDateField() { return dateField; }
    public void setDateField(JTextField dateField) { this.dateField = dateField; }
    public JTextField getDateField2() { return dateField2; }
    public void setDateField2(JTextField dateField2) { this.dateField2 = dateField2; }
    public JButton getSubmitButton() { return submitButton; }
    public void setSubmitButton(JButton submitButton) { this.submitButton = submitButton; }
    public void setIdScooter(String id) { idField.setText(id); }

    // Méthode pour afficher les messages
    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    // Méthode pour réinitialiser le formulaire
    public void clearForm() {
        idField.setText("");
        dateField.setText("");
        dateField2.setText("");
    }
}
