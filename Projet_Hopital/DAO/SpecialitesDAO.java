package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Exceptions.ErreurInterrogationBDD;
import Hopital.Specialites;

public class SpecialitesDAO extends AbstractDAO<Specialites> {

	public SpecialitesDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Specialites objet) {
		if (findById(objet.getIdSpecialite()).isPresent()==true) return false;
		String sql = "INSERT INTO specialites VALUES (?,?);";
		int createStatus = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, objet.getIdSpecialite());
			pstmt.setString(2, objet.getNomSpecialite());
			createStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (createStatus==1) return true;
		else return false;
	}

	@Override
	public boolean update(Specialites objet) {
		if (findById(objet.getIdSpecialite()).isEmpty()==true) return false;
		int updateStatus = -1;
		String sql = "UPDATE specialites SET nomSpecialite = ? WHERE idSpecialite = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, objet.getNomSpecialite());
			pstmt.setInt(2, objet.getIdSpecialite());
			updateStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (updateStatus==1) return true;
		else return false;
	}

	@Override
	public Optional<Specialites> findById(int idSpecialite) {
		String sql = "SELECT * FROM specialites WHERE idSpecialite = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idSpecialite);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Specialites specialiteRecherche=extraireSpecialite(rs);
				return Optional.of(specialiteRecherche);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	public Optional<List<Specialites>> findByName(String nomService) {
		String sql = "";
		if (nomService.contains("%")) sql = "SELECT * FROM specialites WHERE nomSpecialite LIKE ?;";
		else sql = "SELECT * FROM specialites WHERE nomSpecialite = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, nomService);
			ResultSet rs = pstmt.executeQuery();
			if (rs.isBeforeFirst()) {
				List<Specialites> listeSpecialitesByNom = new ArrayList<>();
				while (rs.next()) {
					listeSpecialitesByNom.add(extraireSpecialite(rs));
				}
				return Optional.of(listeSpecialitesByNom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

	@Override
	public boolean delete(Specialites objet) {
		if (findById(objet.getIdSpecialite()).isEmpty()==true) return false;
		String sql = "DELETE FROM specialites WHERE idSpecialite = ?;";
		int deleteStatus = -1;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, objet.getIdSpecialite());
			deleteStatus = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (deleteStatus==1) return true;
		else return false;
	}
	
	private Specialites extraireSpecialite(ResultSet rs) {
		try {
			int idSpecialite = rs.getInt("idSpecialite");
			String nomSpecialite = rs.getString("nomSpecialite");
			return new Specialites(idSpecialite,nomSpecialite);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
