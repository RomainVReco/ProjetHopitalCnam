package Hopital;

import java.math.BigDecimal;
import java.sql.Connection;

import Vue.EcranConnexion;
import Vue.VuePatient;
import Vue.VueRechercheAgentPatient;
import connexion.SingleConnection;
import controleur.ConnexionControleur;
import controleur.GestionPatientControleur;
import hopital_modeles.ModelConnexion;
import hopital_modeles.ModelGestionPatient;

public class testFonctionnement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = SingleConnection.getInstance();
		
			float fl = 984856615.5f;
			System.out.println(BigDecimal.valueOf(fl).getClass());
	
			
			EcranConnexion fen = new EcranConnexion();
			ModelConnexion mc = new ModelConnexion();
			ConnexionControleur cc = new ConnexionControleur(fen, mc);
			
//			VueRechercheAgentPatient rp = new VueRechercheAgentPatient();
//			ModelGestionPatient gp = new ModelGestionPatient();
//			GestionPatientControleur pc = new GestionPatientControleur(rp, gp, fen);

			//VueAgentCreationPatient fen = new VueAgentCreationPatient();
			
			//VuePatient vue = new VuePatient();
			
			
			
}}
