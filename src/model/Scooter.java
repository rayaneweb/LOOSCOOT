package model;





public class Scooter {
private String modele;
private String numIdentification;
private static String permis="A";
private String statut="DISPONIBLE";
private double kilometrage;
public String getModele() {
	return modele;
}
public void setModele(String modele) {
	this.modele = modele;
}
public String getNumIdentification() {
	return numIdentification;
}
public void setNumIdentification(String numIdentification) {
	this.numIdentification = numIdentification;
}
public static String getPermis() {
	return permis;
}
public static void setPermis(String permis) {
	Scooter.permis = permis;
}
public String getStatut() {
	return statut;
}
public void setStatut(String statut) {
	this.statut = statut;
}
public double getKilometrage() {
	return kilometrage;
}
public void setKilometrage(double kilometrage) {
	this.kilometrage = kilometrage;
}
public Scooter(String modele, String numIdentification) {
	
	this.modele = modele;
	this.numIdentification = numIdentification;
		this.kilometrage = 0;
}


public void ajouterKilometrage(double km) {
    this.kilometrage += km;
}
@Override
public String toString() {
	return "Scooter [modele=" + modele + ", numIdentification=" + numIdentification + ", statut=" + statut
			+ ", kilometrage=" + kilometrage + "]";
}





}

