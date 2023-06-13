package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Exceptions.ErreurInterrogationBDD;
import Hopital.Services;

public class ServicesDAO extends AbstractDAO<Services> {
	
	public ServicesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Services objet) throws ErreurInterrogationBDD {
		if (findById(objet.getIdService()).isPresent()) return false;
		int createStatus = -1 ;
		String sql = "INSERT INTO services VALUES (?,?,?,?,?);" ;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, objet.getIdService());
			pstmt.setString(2, objet.getNomService());
			pstmt.setFloat(3, objet.getBudget());
			pstmt.setFloat(4, objet.getBudgetConsomme());
			pstmt.setInt(5, objet.getNombrePersonnel());
			createStatus = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (createStatus==1) return true;
		else return false;
	}

	@Override
	public boolean update(Services objet) {
		if (findById(objet.getIdService()).isEmpty()) return false;
		String sql = "UPDATE services SET nomService = ?, budget = ?, budgetConsomme = ?,"
				+ " nombrePersonnel = ? WHERE idService = ?;";
		int updateStatus = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			int i = 1;
			pstmt.setString(i++, objet.getNomService());
			pstmt.setFloat(i++, objet.getBudget());
			pstmt.setFloat(i++, objet.getBudgetConsomme());
			pstmt.setInt(i++, objet.getNombrePersonnel());
			pstmt.setInt(i++, objet.getIdService());
			updateStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (updateStatus==1) return true;
		else return false;
	}

	@Override
	public Optional<Services> findById(int idService) {
		String sql = "SELECT * FROM services WHERE idService = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idService);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Services serviceRecherche = new Services (idService);
				String nomService = rs.getString("nomService");
				float budget = rs.getFloat("budget");
				float budgetConsomme = rs.getFloat("budgetConsomme");
				int nombrePersonnel = rs.getInt("nombrePersonnel");
				serviceRecherche.setNomService(nomService);
				serviceRecherche.setBudget(budget);
				serviceRecherche.setBudgetConsomme(budgetConsomme);
				serviceRecherche.setNombrePersonnel(nombrePersonnel);
				return Optional.of(serviceRecherche);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	public Optional<List<Services>> findByNom(String nomService) {
		String sql = "";
		if (nomService.contains("%")) sql ="SELECT * FROM services WHERE nomService LIKE ?;";
		else sql = "SELECT * FROM services WHERE nomService = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, nomService);
			ResultSet rs = pstmt.executeQuery();
			if (rs.isBeforeFirst()) {
				List<Services> listeServicesByNom = new ArrayList<>();
				while (rs.next()) {
					listeServicesByNom.add(extraireService(rs));
				}
				return Optional.of(listeServicesByNom);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

	@Override
	public boolean delete(Services objet) throws ErreurInterrogationBDD {
		if (findById(objet.getIdService()).isEmpty()) return false;
		String sql = "DELETE FROM services WHERE idService = ?;";
		int deleteStatus = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, objet.getIdService());
			deleteStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (deleteStatus==1) return true;
		else return false;
	}
	  
	  private Services extraireService(ResultSet rs) {
		  try {
			int idService = rs.getInt("idService");
			String nomService = rs.getString("nomService");
			float budget = rs.getFloat("budget");
			float budgetConsomme = rs.getFloat("budgetConsomme");
			int nbrePersonnel = rs.getInt("nombrePersonnel");
			return new Services (idService,nomService,budget,budgetConsomme,nbrePersonnel);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return null;
	  }
}
