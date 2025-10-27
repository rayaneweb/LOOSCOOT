package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.ParcScooter;
import model.Scooter;
import controlleur.RetourMenuControl;

public class AffichageParcView extends Standard {
    private JTable table;
    private ParcScooter parc;

    public AffichageParcView(ParcScooter parc) {
        super();
        this.parc = parc;

        // configurer le bouton retour herite
        getRetourButton().setText("Retour au menu");
        getRetourButton().addActionListener(new RetourMenuControl(this));

        // creer le modele de tableau avec les colonnes demande
        String[] cols = {"N° Identification", "Modèle", "Kilométrage", "État"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        
        for (Scooter scooter : parc.getScooters()) {
            Object[] rowData = {
                scooter.getNumIdentification(),
                scooter.getModele(),
                scooter.getKilometrage(),
                scooter.getStatut()
            };
            model.addRow(rowData);
        }

        
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(800, 400));

        // Configuration des colonnes
        table.getColumnModel().getColumn(0).setPreferredWidth(150);  
        table.getColumnModel().getColumn(1).setPreferredWidth(250);  
        table.getColumnModel().getColumn(2).setPreferredWidth(100);  
        table.getColumnModel().getColumn(3).setPreferredWidth(150);  

        // Ajouter le tableau dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Ajouter au panel principal
        mainPanel.add(tablePanel, BorderLayout.CENTER);
    }

    public JTable getTable() {
        return table;
    }
}