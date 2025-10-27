package model;



public class Client {
private String nom;
private String prenom;
private String numTelClient;
private String categoriePermis;


public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getNumTelClient() {
	return numTelClient;
}
public void setNumTelClient(String numTelClient) {
	this.numTelClient = numTelClient;
}
public String getCategoriePermis() {
	return categoriePermis;
}
public void setCategoriePermis(String categoriePermis) {
	this.categoriePermis = categoriePermis;
}
public Client(String nom, String prenom, String numTelClient, String categoriePermis) {
	
	this.nom = nom;
	this.prenom = prenom;
	this.numTelClient = numTelClient;
	this.categoriePermis = categoriePermis;
}


@Override
public String toString() {
	return "Client [nom=" + nom + ", prenom=" + prenom + ", numTelClient=" + numTelClient + ", categoriePermis="
			+ categoriePermis + "]";
}



}

