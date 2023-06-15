package Vue;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import idGenerator.IdGenPatients;


/**
 * Ecran d'affichage des informations du patient.
 * 
 * Cette vue sert aussi bien pour l'affichage des informations d'un patient préalablement recherché avec le vue VueRecherchePatient ou pour la création d'un nouveau patient
 * 
 * @author Romain
 */
@SuppressWarnings("serial")
public class VuePatient extends JFrame {

    private JPanel BorderPanel;
    private JPanel InfoPatientPanel;
    private JLabel adresse1Label;
    private JLabel adresse2Label;
    private JLabel adressePatientLabel;
    private JPanel adressePatientPanel;
    private JPanel boutonPanel;
    private JTextField champAdresse1;
    private JTextField champAdresse2;
    private JTextField champCodePostal;
    private JTextField champDateCreationPatient;
    private JTextField champIdPatient;
    private JTextField champNomPatient;
    private JTextField champNumAdresse;
    private JTextField champNumeroSSPatient;
    private JTextField champPays;
    private JTextField champPrenomPatient;
    private JTextField champVille;
    private JLabel codePostalLabel;
    private JButton creationPatientBouton;
    private JLabel dateCreationLabel;
    private JLabel idPatientLabel;
    private JLabel infoPatientLabel;
    private JButton modifierPatientBouton;
    private JButton annulerModifierBouton;
    private JLabel nomPatientLabel;
    private JLabel numeroAdresseLabel;
    private JLabel numeroSSPatientLabel;
    private JLabel paysLabel;
    private JLabel prenomPatientLabel;
    private JButton validerModifBouton;
    private JLabel villeLabel;

    /**
     * Constructeur de la vue
     * Les élements de visibilité sont définis par la méthode créant la vue
     */
    public VuePatient() {
        initComponents();
    }
    
    /**
     * Méthode d'initialisation des composants de la vue
     */              
    private void initComponents() {

        BorderPanel = new JPanel();
        adressePatientPanel = new JPanel();
        adressePatientLabel = new JLabel();
        numeroAdresseLabel = new JLabel();
        champNumAdresse = new JTextField();
        adresse1Label = new JLabel();
        champAdresse1 = new JTextField();
        adresse2Label = new JLabel();
        champAdresse2 = new JTextField();
        codePostalLabel = new JLabel();
        champCodePostal = new JTextField();
        villeLabel = new JLabel();
        champVille = new JTextField();
        paysLabel = new JLabel();
        champPays = new JTextField();
        InfoPatientPanel = new JPanel();
        infoPatientLabel = new JLabel();
        nomPatientLabel = new JLabel();
        champNomPatient = new JTextField();
        prenomPatientLabel = new JLabel();
        champPrenomPatient = new JTextField();
        numeroSSPatientLabel = new JLabel();
        champNumeroSSPatient = new JTextField();
        dateCreationLabel = new JLabel();
        champDateCreationPatient = new JTextField();
        idPatientLabel = new JLabel();
        champIdPatient = new JTextField();
        boutonPanel = new JPanel();
        creationPatientBouton = new JButton();
        modifierPatientBouton = new JButton();
        annulerModifierBouton = new JButton();
        validerModifBouton = new JButton();
        
        // La vue venant par dessus une autre vue, la croix de fermeture ne doit fermer que cet écran spécifique,
        // sans mettre fin au programme
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Panel contenant l'ensemble des autres panels, pour une meilleure insertion dans le JFrame
        BorderPanel.setPreferredSize(new Dimension(1000, 800));
        
        GroupLayout BorderPanelLayout = new GroupLayout(BorderPanel);
        BorderPanel.setLayout(BorderPanelLayout);
        BorderPanelLayout.setHorizontalGroup(
            BorderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(BorderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InfoPatientPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adressePatientPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(BorderPanelLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(boutonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        BorderPanelLayout.setVerticalGroup(
            BorderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, BorderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BorderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(InfoPatientPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(adressePatientPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addComponent(boutonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        // Panel regroupant les informations d'adresse du patient
        adressePatientLabel.setText("Adresse patient :");
        numeroAdresseLabel.setText("Numéro :");
        numeroAdresseLabel.setPreferredSize(new Dimension(150, 16));
        adresse1Label.setText("Rue/voie :");
        adresse2Label.setText("Complément adresse :");
        codePostalLabel.setText("Code postal :");
        villeLabel.setText("Ville :");
        paysLabel.setText("Pays :");
        champPays.setText("France");

        GroupLayout adressePatientPanelLayout = new GroupLayout(adressePatientPanel);
        adressePatientPanel.setLayout(adressePatientPanelLayout);
        adressePatientPanelLayout.setHorizontalGroup(
            adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(adressePatientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(adressePatientPanelLayout.createSequentialGroup()
                        .addGroup(adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(adressePatientPanelLayout.createSequentialGroup()
                                .addComponent(numeroAdresseLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(champNumAdresse, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                            .addGroup(adressePatientPanelLayout.createSequentialGroup()
                                .addGroup(adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(adresse1Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(adresse2Label, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                        .addComponent(paysLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(villeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(codePostalLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(champAdresse2, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(champAdresse1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(champCodePostal, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(champVille, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(champPays, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(adressePatientPanelLayout.createSequentialGroup()
                        .addComponent(adressePatientLabel)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        adressePatientPanelLayout.setVerticalGroup(
            adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(adressePatientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adressePatientLabel)
                .addGap(12, 12, 12)
                .addGroup(adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroAdresseLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(champNumAdresse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(adresse1Label)
                    .addComponent(champAdresse1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(adresse2Label)
                    .addComponent(champAdresse2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(champCodePostal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(codePostalLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(champVille, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(villeLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adressePatientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(champPays, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(paysLabel))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        // Panel regroupant les informations d'identité du patient 
        infoPatientLabel.setText("Informations patient :");

        nomPatientLabel.setText("Nom :");
        prenomPatientLabel.setText("Prénom :");
        numeroSSPatientLabel.setText("Numéro SS :");
        dateCreationLabel.setText("Date création :");
        idPatientLabel.setText("idPatient :");
        
        // L'identifiant du patient, ainsi que sa date de création ne doivent pas être éditables
        champDateCreationPatient.setEditable(false);
        champIdPatient.setEditable(false);        
        
        GroupLayout InfoPatientPanelLayout = new GroupLayout(InfoPatientPanel);
        InfoPatientPanel.setLayout(InfoPatientPanelLayout);
        InfoPatientPanelLayout.setHorizontalGroup(
            InfoPatientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(InfoPatientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InfoPatientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(infoPatientLabel)
                    .addGroup(InfoPatientPanelLayout.createSequentialGroup()
                        .addGroup(InfoPatientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(nomPatientLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(prenomPatientLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(numeroSSPatientLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateCreationLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idPatientLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InfoPatientPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(GroupLayout.Alignment.LEADING, InfoPatientPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(champIdPatient, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addComponent(champDateCreationPatient, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addComponent(champNumeroSSPatient, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addComponent(champPrenomPatient, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                            .addComponent(champNomPatient, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        InfoPatientPanelLayout.setVerticalGroup(
            InfoPatientPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(InfoPatientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoPatientLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(InfoPatientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nomPatientLabel)
                    .addComponent(champNomPatient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoPatientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(prenomPatientLabel)
                    .addComponent(champPrenomPatient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoPatientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(champNumeroSSPatient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroSSPatientLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoPatientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(champDateCreationPatient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateCreationLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoPatientPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(champIdPatient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(idPatientLabel))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // Panel regroupant les boutons permettant la gestio des informations du patient
        creationPatientBouton.setText("Créer patient");
        modifierPatientBouton.setText("Modifier patient");
        validerModifBouton.setText("Valider modification");
        
        // Bouton utilisé pour annuler la modification des informations du patient et verrouiller de nouveau la vue
        annulerModifierBouton.setText("Annuler modification");
        annulerModifierBouton.setVisible(false);

        GroupLayout boutonPanelLayout = new GroupLayout(boutonPanel);
        boutonPanel.setLayout(boutonPanelLayout);
        boutonPanelLayout.setHorizontalGroup(
            boutonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(boutonPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(creationPatientBouton, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifierPatientBouton, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(annulerModifierBouton, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(validerModifBouton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        boutonPanelLayout.setVerticalGroup(
            boutonPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, boutonPanelLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(boutonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(creationPatientBouton)
                    .addComponent(modifierPatientBouton)
                    .addComponent(annulerModifierBouton)
                    .addComponent(validerModifBouton))
                .addGap(17, 17, 17))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(BorderPanel, GroupLayout.PREFERRED_SIZE, 832, GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(BorderPanel, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * Getters et setters des variables de la vue d'affichage d'un patient
     */
	public JTextField getChampAdresse1() {
		return champAdresse1;
	}

	public void setChampAdresse1(String champAdresse1) {
		this.champAdresse1.setText(champAdresse1);
	}

	public JTextField getChampAdresse2() {
		return champAdresse2;
	}

	public void setChampAdresse2(String champAdresse2) {
		this.champAdresse2.setText(champAdresse2); 
	}

	public JTextField getChampCodePostal() {
		return champCodePostal;
	}

	public void setChampCodePostal(String champCodePostal) {
		this.champCodePostal.setText(champCodePostal);
	}

	public JTextField getChampDateCreationPatient() {
		return champDateCreationPatient;
	}
	
	public void setChampDateCreationPatient(String dateCreation) {
		this.champDateCreationPatient.setText(dateCreation);
	}

	public JTextField getChampIdPatient() {
		return champIdPatient;
	}

	public void setChampIdPatient(String champIdPatient) {
		this.champIdPatient.setText(champIdPatient);
	}
	
	public void setNewIdPatient() {
		this.champIdPatient.setText(String.valueOf(IdGenPatients.genId()));
	}

	public JTextField getChampNomPatient() {
		return champNomPatient;
	}

	public void setChampNomPatient(String champNomPatient) {
		this.champNomPatient.setText(champNomPatient);
	}

	public JTextField getChampNumAdresse() {
		return champNumAdresse;
	}

	public void setChampNumAdresse(String champNumAdresse) {
		this.champNumAdresse.setText(champNumAdresse); 
		}

	public JTextField getChampNumeroSSPatient() {
		return champNumeroSSPatient;
	}

	public void setChampNumeroSSPatient(String champNumeroSSPatient) {
		this.champNumeroSSPatient.setText(champNumeroSSPatient);
	}

	public JTextField getChampPays() {
		return champPays;
	}

	public void setChampPays(String champPays) {
		this.champPays.setText(champPays);
	}

	public JTextField getChampPrenomPatient() {
		return champPrenomPatient;
	}

	public void setChampPrenomPatient(String champPrenomPatient) {
		this.champPrenomPatient.setText(champPrenomPatient);
	}

	public JTextField getChampVille() {
		return champVille;
	}

	public void setChampVille(String champVille) {
		this.champVille.setText(champVille);
	}

	public JButton getCreationPatientBouton() {
		return creationPatientBouton;
	}

	public JButton getModifierPatientBouton() {
		return modifierPatientBouton;
	}

	public void setModifierPatientBouton(JButton modifierPatientBouton) {
		this.modifierPatientBouton = modifierPatientBouton;
	}

	public JButton getValiderModifBouton() {
		return validerModifBouton;
	}

	public JButton getAnnulerModifierPatient() {
		return annulerModifierBouton;
	}

	public void setAnnulerModifierPatient(JButton annulerModifierPatient) {
		this.annulerModifierBouton = annulerModifierPatient;
	}
	
	
	public JTextField[] getJTextField() {
		JTextField[] tableauJTextfield = {champAdresse1,champAdresse2,champCodePostal,
				champDateCreationPatient,champIdPatient,champNomPatient,champNumAdresse,
				champNumeroSSPatient,champPays,champPrenomPatient,champVille};
		return tableauJTextfield;
	}

	/**
	 * Gestion des messages d'erreur pour informer l'utilisateur des erreurs fonctionnelles
	 */
	public void sameInfoPatientError() {
		JOptionPane.showMessageDialog(this, "Aucune information n'a été modifiée");
	}

	public void updatePatientFailed() {
		JOptionPane.showMessageDialog(this, "La mise à jour des informations du patient a échoué");
	}

	public void updatePatientSuccess() {
		JOptionPane.showMessageDialog(this, "La mise à jour des informations du patient a été réalisée avec succès !");
	}

	public void creationPatientSuccess() {
		JOptionPane.showMessageDialog(this, "Le nouveau patient a été créé avec succès !");
	}

	public void creationPatientFailed() {
		JOptionPane.showMessageDialog(this, "Ce patient existe déjà");
	}
	public void codePostalFormatError() {
		JOptionPane.showMessageDialog(this, "Le code postal doit être inférieur à 10 caractères !");
	}
                                                               
}
