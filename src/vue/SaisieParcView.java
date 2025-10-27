package vue;

import javax.swing.*;
import java.awt.*;
import model.ParcScooter;
import model.Scooter;
import javax.swing.table.DefaultTableModel;
import controlleur.RetourMenuControl;

public class SaisieParcView extends Standard {
    private JTable table;
    private ParcScooter parc;
    private JTextArea summaryArea;

    public SaisieParcView(ParcScooter parc) {
        super();
        this.parc = parc;

        JPanel mainContent = new JPanel(new BorderLayout());

        // Calculs
        int total = parc.getScooters().size();
        int enLocation = 0;
        int disponibles = 0;
        double kmTotal = 0;
        String locIds = "";
        String dispIds = "";

        for (Scooter s : parc.getScooters()) {
            kmTotal += s.getKilometrage();
            if ("EN LOCATION".equals(s.getStatut())) {
                enLocation++;
                locIds += s.getNumIdentification() + ", ";
            } else {
                disponibles++;
                dispIds += s.getNumIdentification() + ", ";
            }
        }

        
        if (!locIds.isEmpty()) locIds = locIds.substring(0, locIds.length() - 2);
        if (!dispIds.isEmpty()) dispIds = dispIds.substring(0, dispIds.length() - 2);

        double kmMoyen = total > 0 ? kmTotal / total : 0;

        String[] cols = {
            "Nombre total de scooters",
            "Scooters en location (N°)",
            "Kilométrage moyen",
            "Scooters disponibles (N°)"
        };

        Object[][] data = {{
            total,
            enLocation + " (" + locIds + ")",
            String.format("%.1f km", kmMoyen),
            disponibles + " (" + dispIds + ")"
        }};

        table = new JTable(data, cols);
        table.setPreferredScrollableViewportSize(new Dimension(800, 100));
        JScrollPane tableScrollPane = new JScrollPane(table);
        mainContent.add(tableScrollPane, BorderLayout.NORTH);

        summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        summaryArea.setFont(new Font("Arial", Font.PLAIN, 14));
        updateSummary(); 

        JScrollPane summaryScrollPane = new JScrollPane(summaryArea);
        summaryScrollPane.setPreferredSize(new Dimension(800, 150));
        mainContent.add(summaryScrollPane, BorderLayout.CENTER);

        getRetourButton().setText("Retour au menu");
        getRetourButton().addActionListener(new RetourMenuControl(this));

        mainPanel.add(mainContent, BorderLayout.CENTER);
    }

    private void updateSummary() {
        int total = parc.getScooters().size();
        int enLocation = 0;
        int disponibles = 0;
        double kmTotal = 0;
        String locIds = "";
        String dispIds = "";

        for (Scooter s : parc.getScooters()) {
            kmTotal += s.getKilometrage();
            if ("EN LOCATION".equals(s.getStatut())) {
                enLocation++;
                locIds += s.getNumIdentification() + ", ";
            } else {
                disponibles++;
                dispIds += s.getNumIdentification() + ", ";
            }
        }

        if (!locIds.isEmpty()) locIds = locIds.substring(0, locIds.length() - 2);
        if (!dispIds.isEmpty()) dispIds = dispIds.substring(0, dispIds.length() - 2);

        double kmMoyen = total > 0 ? kmTotal / total : 0;

        String summary = "=== RÉSUMÉ DU PARC ===\n" +
                         "Nombre total: " + total + " scooters\n" +
                         "En location: " + enLocation + " (" + locIds + ")\n" +
                         "Disponibles: " + disponibles + " (" + dispIds + ")\n" +
                         String.format("Kilométrage moyen: %.1f km", kmMoyen);

        summaryArea.setText(summary);
    }

    public JTable getTable() {
        return table;
    }
}
