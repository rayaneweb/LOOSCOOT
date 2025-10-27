package model;



import java.time.LocalDate;

public class Location {
	 private Scooter scooter;
	    private Client client;
	    private LocalDate dateDebut;
	    private LocalDate dateFin;
	    private int kilometrage;
		public Location(Scooter scooter, Client client, LocalDate dateDebut, LocalDate dateFin, int kilometrage) {
			
			this.scooter = scooter;
			this.client = client;
			this.dateDebut = dateDebut;
			this.dateFin = dateFin;
			this.kilometrage = kilometrage;
		}
		public Scooter getScooter() {
			return scooter;
		}
		public void setScooter(Scooter scooter) {
			this.scooter = scooter;
		}
		public Client getClient() {
			return client;
		}
		public void setClient(Client client) {
			this.client = client;
		}
		public LocalDate getDateDebut() {
			return dateDebut;
		}
		public void setDateDebut(LocalDate dateDebut) {
			this.dateDebut = dateDebut;
		}
		public LocalDate getDateFin() {
			return dateFin;
		}
		public void setDateFin(LocalDate dateFin) {
			this.dateFin = dateFin;
		}
		public int getKilometrage() {
			return kilometrage;
		}
		public void setKilometrage(int kilometrage) {
			this.kilometrage = kilometrage;
		}
	    
		
		
		@Override
		public String toString() {
			return "Location [scooter=" + scooter + ", client=" + client + ", dateDebut=" + dateDebut + ", dateFin="
					+ dateFin + ", kilometrage=" + kilometrage + "]";
		}
		
	    
}

