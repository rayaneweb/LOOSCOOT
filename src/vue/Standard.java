package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;


public class Standard extends JFrame {
	protected JButton retourButton;
    
    ImageIcon icon       = new ImageIcon(getClass().getResource("/vue/LOOSCOOT.png"));
    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/vue/LOOSCOOT.png"));
    Image scaledImage    = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    ImageIcon smallIcon  = new ImageIcon(scaledImage);

    // Panel central pour les sous-classes
    protected JPanel mainPanel;

    JLabel textlogo;
    JLabel logo;     
    JLabel logo2;

    
   
    public Standard() {
        // Configuration de la fenêtre
        this.setSize(800,700);
        this.setTitle("LOOSCOOT");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(true);
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(0xFFB280));

        // Titre
        textlogo = new JLabel("LOOSCOOT");
        textlogo.setFont(new Font("Century Gothic", Font.BOLD, 60));
        textlogo.setForeground(Color.white);
        textlogo.setHorizontalAlignment(JLabel.CENTER);

        // Logo
        logo = new JLabel(smallIcon);
        logo.setHorizontalAlignment(JLabel.LEFT);
        
        logo2=new JLabel(smallIcon);
        logo.setHorizontalAlignment(JLabel.RIGHT);

        

        // Panel du haut logo + titre
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(true);
        topPanel.add(logo, BorderLayout.WEST);
        topPanel.add(textlogo, BorderLayout.CENTER);
        topPanel.add(logo2,BorderLayout.EAST);
        topPanel.setBackground(new Color(0xff8ba3));
        this.add(topPanel, BorderLayout.NORTH);

        // Panel central pour contenu dynamique
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        this.add(mainPanel, BorderLayout.CENTER);
        retourButton = new JButton("Retour au menu");
        retourButton.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        retourButton.setBackground(new Color(0xF39C50));
        retourButton.setForeground(Color.WHITE);
        retourButton.setFocusPainted(false);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(retourButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        this.setVisible(true);
        
        
        
       
    }
    public JButton getRetourButton() {
        return retourButton;
    }
        
    }
   
