package main;

import java.io.IOException;
import vue.MenuPrincipal;
import model.ParcScooter;

public class Main {
		
        public static void main(String[] args) throws IOException {
        	
    	ParcScooter parc = ParcScooter.charger();
    	
        new MenuPrincipal();
      
        }
        
}
