package Hopital;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Exceptions.ErreurTempsException;
import Exceptions.FormatIncorrectException;
import idGenerator.IdGenConsultation;

public class Consultations {

	private int idConsultation;
	private LocalDateTime horodatageDebut;
	private LocalDateTime horodatageFin;
	private String detailClinique;
	private String listePrescription;
	private Patients patient;
	private Medecins medecin;
	private Set<String> listePathologie;
	private MaterielMedical materiel; 

	public Consultations(int id) {
		this.idConsultation=id;
	}
	
	public Consultations() {
		setAutoGenIdConsultation ();
		setHorodatageDebut(LocalDateTime.now());
	}

	public Consultations(int ID, LocalDateTime horodatageDebut, Patients patient, Medecins medecin) {
		this.idConsultation=ID;
		this.horodatageDebut = horodatageDebut;
		this.patient = patient;
		this.medecin = medecin;
	}
	
	public Consultations(int ID, Patients patient, Medecins medecin) {
		this.idConsultation=ID;
		setHorodatageDebut(LocalDateTime.now());
		this.patient = patient;
		this.medecin = medecin;
	}

	public Consultations(int idConsultation, LocalDateTime horodatageDebut, LocalDateTime horodatageFin,
			String detailClinique, String listePrescription, Patients patient, Medecins medecin,
			Set<String> listePathologie) {
		super();
		this.idConsultation = idConsultation;
		this.horodatageDebut = horodatageDebut;
		this.horodatageFin = horodatageFin;
		this.detailClinique = detailClinique;
		this.listePrescription = listePrescription;
		this.patient = patient;
		this.medecin = medecin;
		this.listePathologie = listePathologie;
	}
	
	public Consultations(LocalDateTime horodatageFin, String detailClinique, 
			String listePrescription, Patients patient, Medecins medecin,
			Set<String> listePathologie) {
		super();
		setAutoGenIdConsultation ();
		setHorodatageDebut(LocalDateTime.now());
		this.horodatageFin = horodatageFin;
		this.detailClinique = detailClinique;
		this.listePrescription = listePrescription;
		this.patient = patient;
		this.medecin = medecin;
		this.listePathologie = listePathologie;
	}

	public Consultations(int id, LocalDateTime horodatageDebut, String detailClinique2, String listePrescription2, Patients nouveauPatient,
			Medecins nouveauMedecin, Set<String> listePathologie2, MaterielMedical object2) {
		super();
		this.idConsultation = id;
		setHorodatageDebut(LocalDateTime.now());
		this.horodatageDebut = horodatageDebut;
		this.detailClinique = detailClinique2;
		this.listePrescription = listePrescription2;
		this.patient = nouveauPatient;
		this.medecin = nouveauMedecin;
		this.listePathologie = listePathologie2;
		this.materiel=object2;
	}

	/**
	 * @return the idConsultation
	 */
	public int getIdConsultation() {
		return idConsultation;
	}

	/**
	 * @param idConsultation the idConsultation to set
	 */
	public void setIdConsultation(int idConsultation) {
		this.idConsultation = idConsultation;
	}
	
	private void setAutoGenIdConsultation () {
		this.idConsultation = IdGenConsultation.getIdGenConsultation();
	}
	
	/**
	 * @return the horodatageDebut
	 */
	public LocalDateTime getHorodatageDebut() {
		return horodatageDebut;
	}
	
	/**
	 * @param horodatageDebut the horodatageDebut to set
	 */
	public void setHorodatageDebut(LocalDateTime horodatageDebut) {
		this.horodatageDebut = horodatageDebut;
	}

	/**
	 * @return the horodatageFin
	 */
	public LocalDateTime getHorodatageFin() {
		return horodatageFin;
	}

	/**
	 * @param horodatageFin the horodatageFin to set
	 * @throws ErreurTempsException 
	 */
	public void setHorodatageFin(LocalDateTime horodatageFin) throws ErreurTempsException {
		 this.horodatageFin = horodatageFin;
	}

	/**
	 * @return the detailClinique
	 */
	public String getDetailClinique() {
		return detailClinique;
	}

	/**
	 * @param detailClinique the detailClinique to set
	 */
	public void setDetailClinique(String detailClinique) throws FormatIncorrectException{
		try {
			if (detailClinique.length()<20) {
				throw new FormatIncorrectException("Le détail clinique doit être plus long que 20 caractères");
				} 
		} catch (FormatIncorrectException e) {
			System.out.println("Le détail clinique doit être plus long que 20 caractères");
			return ;
		} 
			
		this.detailClinique = detailClinique;
	}

	/**
	 * @return the prescription
	 */
	public String getPrescription() {
		return listePrescription;
	}

	/**
	 * @param prescription the prescription to set
	 */
	public void setPrescription(String nouvelleListePrescription) {
		this.listePrescription = nouvelleListePrescription;
	}

	/**
	 * @return the patient
	 */
	public Patients getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patients patient) {
		this.patient = patient;
	}

	/**
	 * @return the medecin
	 */
	public Medecins getMedecin() {
		return medecin;
	}

	/**
	 * @param medecin the medecin to set
	 */
	public void setMedecin(Medecins medecin) {
		this.medecin = medecin;
	}

	/**
	 * @return the pathologie
	 */
	public Set<String> getPathologie() {
		return listePathologie;
	}

	/**
	 * @param pathologie the pathologie to set
	 */
	public void setPathologie(Set<String> pathologie) {
		this.listePathologie = pathologie;
	}

	public void addPathologie(String nouvellePathologie) {
		this.listePathologie.add(nouvellePathologie);
	}
	
	public Optional<List<String>> getPathologieFromSet(String maladie) {
		if (this.listePathologie.isEmpty() || this.listePathologie==null) return Optional.empty();
		HashSet<String> setStream = new HashSet<>(this.listePathologie);
		
		Stream<String> sp = setStream.stream();
		List<String> pathoRecherchee = sp.filter(x-> x.contains(maladie))
										.collect(Collectors.toList());
		return Optional.ofNullable(pathoRecherchee);

	}
	
	public String convertirSetPathologie () {
		String str ="";
		for (String s : this.listePathologie) {
			str = str+s+", ";
		}
		return str.substring(0, str.length()-2);
	}

	public MaterielMedical getMateriel() {
		return materiel;
	}

	public void setMateriel(MaterielMedical materiel) {
		this.materiel = materiel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(detailClinique, horodatageDebut, horodatageFin, idConsultation, listePathologie,
				listePrescription, patient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consultations other = (Consultations) obj;
		return Objects.equals(detailClinique, other.detailClinique)
				&& Objects.equals(horodatageDebut, other.horodatageDebut)
				&& Objects.equals(horodatageFin, other.horodatageFin) && idConsultation == other.idConsultation
				&& Objects.equals(listePathologie, other.listePathologie)
				&& Objects.equals(listePrescription, other.listePrescription) && Objects.equals(patient, other.patient);
	}

	
	
}