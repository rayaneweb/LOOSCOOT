package model;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParcScooter {
    private String nom ;
    private String adresse;
    private String numTel;
    private List<Scooter> scooters = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Location> locations = new ArrayList<>();
    private static final String FICHIER_SAUVEGARDE = "parc_scooters.txt";
    
    
    public ParcScooter() {
    	this.nom="LOUSCOOT";
        this.adresse = "45 rue lounes matoub 75019";
        this.numTel = "06 05 04 03 02";
    }
    
    public void ajouterClient(Client client) {
        clients.add(client);
    }
    
    public void ajouterScooter(Scooter scooter) {
        scooters.add(scooter);
    }
    
    public Scooter trouverScooter(String id) {
        for (Scooter scooter : scooters) {
            if (scooter.getNumIdentification().equals(id)) { 
                return scooter;
            }
        }
        return null; 
    }

    
    public Client trouverClient(String nom, String prenom) {
        for(Client c : clients) {
            if(c.getNom().equals(nom) && c.getPrenom().equals(prenom)){
                return c;
            }
        }
        return null;
    }
    
    public Location trouverLocationActive(String scooterId) {
       
        for (Location loc : locations) {
            if (loc.getScooter().getNumIdentification().equals(scooterId)) {
                

               
                        return loc;
                    }
                }
        
        return null;
    }





    public boolean estDisponible(String numeroScooter, LocalDate dateDebut, LocalDate dateFin) {
        Scooter scooter = trouverScooter(numeroScooter);
        if (scooter == null) return false;

        

        for (Location loc : locations) {
            if (loc.getScooter().equals(scooter)) {
                System.out.println("Location existante: " + loc.getDateDebut() + " à " + loc.getDateFin());
                if (!(dateFin.isBefore(loc.getDateDebut()) || dateDebut.isAfter(loc.getDateFin()))) {
                    
                    return false;
                }
            }
        }
     
        return true;
    }

    public boolean louerScooter(String numeroScooter, Client client, LocalDate dateDebut, LocalDate dateFin) {
        // Nettoyer les locations terminées avant de commencer
        

        Scooter scooter = trouverScooter(numeroScooter);
        if (scooter == null) {
            System.out.println("Scooter introuvable.");
            return false;
        }

        if (!estDisponible(numeroScooter, dateDebut, dateFin)) {
            System.out.println("Scooter déjà en location sur cette période.");
            return false;
        }

        if (!client.getCategoriePermis().equals(Scooter.getPermis())) {
            System.out.println("Le permis du client n'est pas adapté.");
            return false;
        }

        Location location = new Location(scooter, client, dateDebut, dateFin, 0);
        locations.add(location);
        scooter.setStatut("EN LOCATION");

        try {
            sauvegarder();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean supprimerLocationEtScooter(String scooterId) {
        // Trouver la location active associée au scooter
        Location locationActive = trouverLocationActive(scooterId);
        if (locationActive != null) {
            locations.remove(locationActive);
        }

        // Trouver le scooter dans la liste
        Scooter scooter = trouverScooter(scooterId);
        if (scooter != null) {
            scooters.remove(scooter);
            try {
                sauvegarder();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        return false; // scooter non trouvé
    }


    public void nettoyerLocationTermineePourScooter(String scooterId) {
        LocalDate today = LocalDate.now();
        locations.removeIf(loc -> loc.getScooter().getNumIdentification().equals(scooterId)
                && loc.getDateFin() != null
                && (loc.getDateFin().isBefore(today) || loc.getDateFin().isEqual(today)));
    }




    
    public boolean retournerScooter(String scooterId, int kmAjoutes) {
        Scooter scooter = trouverScooter(scooterId);
        if (scooter != null && "EN LOCATION".equals(scooter.getStatut())) {
            Location location = trouverLocationActive(scooterId);
            if (location != null) {
                location.setDateFin(LocalDate.now()); // Marque la fin de la location
                location.setKilometrage(kmAjoutes);   // Enregistre le km ajouté
                scooter.setKilometrage(scooter.getKilometrage() + kmAjoutes);
                scooter.setStatut("DISPONIBLE");
                nettoyerLocationTermineePourScooter(scooterId);
                return true;
            }
        }
        return false;
    }



   

    
    public String etatScooter(String numero) {
        Scooter scooter = trouverScooter(numero);
        if (scooter == null) return "Scooter non trouvé";
        return scooter.toString();
    } 
    
    public List<Scooter> getScootersDisponibles() {
        List<Scooter> disponibles = new ArrayList<>();
        for (Scooter s : scooters) {
            if ("DISPONIBLE".equals(s.getStatut())) {
                disponibles.add(s);
            }
        }
        return disponibles;
    }
    
    public List<Scooter> getScootersEnLocation() {
        List<Scooter> enLocation = new ArrayList<>();
        for (Scooter s : scooters) {
            if ("EN LOCATION".equals(s.getStatut())) {
                enLocation.add(s);
            }
        }
        return enLocation;
    }
    
    public double getKilometrageMoyen() {
        if (scooters.isEmpty()) return 0;
        double total = 0;
        for (Scooter s : scooters) {
            total += s.getKilometrage();
        }
        return total / scooters.size();
    }
    
    public void sauvegarder() throws IOException {
        File fichier = new File(FICHIER_SAUVEGARDE);
        FileWriter writer = new FileWriter(fichier);
        
        // Sauvegarde des scooters
        for (Scooter s : scooters) {
            writer.write("SCOOTER;" + 
                s.getNumIdentification() + ";" +
                s.getModele() + ";" +
                s.getKilometrage() + ";" +
                s.getStatut() + "\n");
        }
        
        // Sauvegarde des clients
        for (Client c : clients) {
            writer.write("CLIENT;" +
                c.getNom() + ";" +
                c.getPrenom() + ";" +
                c.getNumTelClient() + ";" +
                c.getCategoriePermis() + "\n");
        }
        
        // Sauvegarde des locations
        for (Location l : locations) {
            writer.write("LOCATION;" +
                l.getScooter().getNumIdentification() + ";" +
                l.getClient().getNom() + ";" +
                l.getClient().getPrenom() + ";" +
                l.getDateDebut() + ";" +
                (l.getDateFin() != null ? l.getDateFin() : "null") + ";" +
                l.getKilometrage() + "\n");
        }

        writer.close();
    }

    public static ParcScooter charger() throws IOException {
        ParcScooter parc = new ParcScooter();
        File fichier = new File(FICHIER_SAUVEGARDE);
        if (!fichier.exists()) return parc;

        FileReader fr = new FileReader(fichier);
        BufferedReader reader = new BufferedReader(fr);

        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            
            switch(data[0]) {
            case "SCOOTER":
                Scooter s = new Scooter(
                    data[2], // modèle
                    data[1]  // numIdentification
                );
                s.setKilometrage(Double.valueOf(data[3]));
                s.setStatut(data[4]); // Ajout de cette ligne pour le statut
                parc.ajouterScooter(s);
                break;
                    
                case "CLIENT":
                    Client c = new Client(
                        data[1], // nom
                        data[2], // prenom
                        data[3], // numTel
                        data[4]  // permis
                    );
                    parc.ajouterClient(c);
                    break;
                    
                case "LOCATION":
                    Scooter scooter = parc.trouverScooter(data[1]);
                    Client client = parc.trouverClient(data[2], data[3]);
                    if (scooter != null && client != null) {
                        LocalDate debut = LocalDate.parse(data[4]);
                        LocalDate fin = data[5].equals("null") ? null : LocalDate.parse(data[5]);
                        int km = Integer.valueOf(data[6]);
                        
                        Location loc = new Location(scooter, client, debut, fin, km);
                        parc.getLocations().add(loc);
                    }
                    break;
            }
        }
        reader.close();
        return parc;
    }
    
   

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public List<Scooter> getScooters() {
        return scooters;
    }
    
    public List<Client> getClients() {
        return clients;
    }

    public List<Location> getLocations() {
        return locations;
    }
}

