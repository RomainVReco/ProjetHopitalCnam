package Hopital;

import java.util.List;
import java.util.Objects;

/**
 * Représente les services de l'hopital où exerce le personnel
 * 
 * Un service a un nom, un budget annuel alloué, un budget consommé, une liste de personnels, et un nombre de personnels
 * 
 * Un service est géré par un Administrateur
 * 
 * @author Romain
 *
 */
public class Services {

	private int idService;
	private String nomService;
	private float budget;
	private float budgetConsomme;
	List<Personnels> listePersonnels;
	int nombrePersonnel;

	public Services() {
	}

	public Services(int idService) {
		this.idService=idService;
	}

	public Services(int id, String nomService, float budget, float budgetConsomme, int nbrPersonnel) {
		this.idService=id;
		this.nomService=nomService;
		this.budget=budget;
		this.budgetConsomme=budgetConsomme;
		this.nombrePersonnel=nbrPersonnel;
	}

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}
	
	public String getNomService() {
		return nomService;
	}

	public void setNomService(String nomService) {
		this.nomService = nomService;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public float getBudgetConsomme() {
		return budgetConsomme;
	}

	public void setBudgetConsomme(float budgetConsomme) {
		this.budgetConsomme = budgetConsomme;
	}
	
	public float calculBudgetRestant () {
		return this.budget-this.budgetConsomme;
	}

	public List<Personnels> getListeMedecins() {
		return listePersonnels;
	}

	public void setListeMedecins(List<Personnels> listePersonnels) {
		this.listePersonnels = listePersonnels;
	}

	public int getNombrePersonnel() {
		return nombrePersonnel;
	}

	public void setNombrePersonnel(int nombrePersonnel) {
		this.nombrePersonnel = nombrePersonnel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(budget, idService, nomService, nombrePersonnel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Services other = (Services) obj;
		return Float.floatToIntBits(budget) == Float.floatToIntBits(other.budget) && idService == other.idService
				&& Objects.equals(nomService, other.nomService) && nombrePersonnel == other.nombrePersonnel;
	}
		
	
}