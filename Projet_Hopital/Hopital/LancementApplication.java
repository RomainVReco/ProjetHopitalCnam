package Hopital;

import java.sql.Connection;

import Vue.EcranConnexion;
import connexion.SingleConnection;
import controleur.ConnexionControleur;
import hopital_modeles.ModelConnexion;

/**
 * Classe de lancement de l'application
 *  
 * @author Romain
 *
 */
public class LancementApplication {

	public static void main(String[] args) {
			Connection conn = SingleConnection.getInstance();
			
			EcranConnexion fen = new EcranConnexion();
			ModelConnexion mc = new ModelConnexion();
			ConnexionControleur cc = new ConnexionControleur(fen, mc);

			
}}
