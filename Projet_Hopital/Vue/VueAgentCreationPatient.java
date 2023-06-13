package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

/**
 *
 * @author Romain
 */
@SuppressWarnings("serial")
public class VueAgentCreationPatient extends JFrame {

    private JLabel adresse1;
    private JLabel adresse2;
    private JButton afficherPatientBouton;
    private JPanel barreRecherche;
    private JTextField champCodePostal;
    private JTextField champComplementAdresse;
    private JTextField champDateCreationPatient;
    private JTextField champIdPatient;
    private JTextField champNomPatient;
    private JTextField champNumeroAdresse;
    private JTextField champNumeroSSPatient;
    private JTextField champPays;
    private JTextField champPrenomPatient;
    private JTextField champRecherche;
    private JTextField champRueAdresse;
    private JTextField champVille;
    private JComboBox<String> choixCritereRecherche;
    private JLabel codePostal;
    private JButton creationPatientBouton;
    private JButton deconnexion;
    private JLabel idPatientLabel;
    private JLabel adressePatientLabel;
    private JLabel patientIdLabel;
    private JLabel informationLabel;
    private JToggleButton lancerRechercheBouton;
    private JLabel nomPatient;
    private JLabel nomSalarieUtilisateur;
    private JLabel nomVue;
    private JLabel numero;
    private JLabel numeroSSPatient;
    private JLabel paysLabel;
    private JLabel prenomPatient;
    private JLabel recherchePatient;
    private JButton supprimerPatientBouton;
    private JPanel validationPatient;
    private JLabel villeLabel;
    private JPanel vuePatient;
    private JPanel vueSalarie;
    
    public VueAgentCreationPatient() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")                       
    private void initComponents() {

        vuePatient = new JPanel();
        nomPatient = new JLabel();
        prenomPatient = new JLabel();
        numeroSSPatient = new JLabel();
        idPatientLabel = new JLabel();
        champNomPatient = new JTextField();
        champPrenomPatient = new JTextField();
        champNumeroSSPatient = new JTextField();
        champDateCreationPatient = new JTextField();
        numero = new JLabel();
        champNumeroAdresse = new JTextField();
        champRueAdresse = new JTextField();
        adresse1 = new JLabel();
        informationLabel = new JLabel();
        adresse2 = new JLabel();
        champComplementAdresse = new JTextField();
        champCodePostal = new JTextField();
        codePostal = new JLabel();
        villeLabel = new JLabel();
        champVille = new JTextField();
        champPays = new JTextField();
        paysLabel = new JLabel();
        adressePatientLabel = new JLabel();
        patientIdLabel = new JLabel();
        champIdPatient = new JTextField();
        barreRecherche = new JPanel();
        vueSalarie = new JPanel();
        nomVue = new JLabel();
        nomSalarieUtilisateur = new JLabel();
        deconnexion = new JButton();
        recherchePatient = new JLabel();
        choixCritereRecherche = new JComboBox<>();
        champRecherche = new JTextField();
        lancerRechercheBouton = new JToggleButton();
        validationPatient = new JPanel();
        afficherPatientBouton = new JButton();
        creationPatientBouton = new JButton();
        supprimerPatientBouton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        nomPatient.setText("Nom");
        nomPatient.setPreferredSize(new Dimension(40, 24));

        prenomPatient.setText("Prénom :");
        prenomPatient.setPreferredSize(new Dimension(40, 24));

        numeroSSPatient.setText("Numéro SS :");
        numeroSSPatient.setPreferredSize(new Dimension(40, 24));

        idPatientLabel.setText("Date création :");
        idPatientLabel.setPreferredSize(new Dimension(40, 24));

        numero.setText("Numéro :");
        numero.setPreferredSize(new Dimension(120, 24));

        champNumeroAdresse.setForeground(new Color(204, 204, 204));
        champNumeroAdresse.setText("35, 1bis, 2ter");

        adresse1.setText("Rue/voie :");
        adresse1.setPreferredSize(new Dimension(120, 24));

        informationLabel.setText("Information patient :");
        informationLabel.setMaximumSize(new Dimension(50, 30));
        informationLabel.setPreferredSize(new Dimension(50, 30));

        adresse2.setText("Complément adresse :");
        adresse2.setPreferredSize(new Dimension(120, 24));

        champComplementAdresse.setForeground(new Color(204, 204, 204));
        champComplementAdresse.setText("Lieu-dit, Chemin, etc.");

        codePostal.setText("Code postal :");
        codePostal.setPreferredSize(new Dimension(120, 24));

        villeLabel.setText("Ville :");
        villeLabel.setPreferredSize(new Dimension(120, 24));

        champPays.setText("FRANCE");

        paysLabel.setText("Pays :");
        paysLabel.setPreferredSize(new Dimension(120, 24));

        adressePatientLabel.setText("Adresse patient :");
        adressePatientLabel.setMaximumSize(new Dimension(50, 30));
        adressePatientLabel.setPreferredSize(new Dimension(50, 30));

        patientIdLabel.setText("Patient_Id :");
        patientIdLabel.setPreferredSize(new Dimension(40, 24));

        GroupLayout vuePatientLayout = new GroupLayout(vuePatient);
        vuePatient.setLayout(vuePatientLayout);
        vuePatientLayout.setHorizontalGroup(
            vuePatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vuePatientLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(vuePatientLayout.createSequentialGroup()
                        .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(prenomPatient, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addComponent(nomPatient, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addComponent(idPatientLabel, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addComponent(numeroSSPatient, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(champDateCreationPatient, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                            .addComponent(champNumeroSSPatient, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                            .addComponent(champPrenomPatient, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                            .addComponent(champNomPatient, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                        .addGap(94, 94, 94))
                    .addGroup(vuePatientLayout.createSequentialGroup()
                        .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(informationLabel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                            .addGroup(vuePatientLayout.createSequentialGroup()
                                .addComponent(patientIdLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(champIdPatient, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(adressePatientLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                    .addGroup(vuePatientLayout.createSequentialGroup()
                        .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING, vuePatientLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(codePostal, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(paysLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(villeLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.TRAILING, vuePatientLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(adresse1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numero, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addComponent(adresse2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, vuePatientLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(champCodePostal, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addComponent(champComplementAdresse, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                .addComponent(champPays, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                .addComponent(champVille, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.TRAILING, vuePatientLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(champRueAdresse, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                .addComponent(champNumeroAdresse, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        vuePatientLayout.setVerticalGroup(
            vuePatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vuePatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(informationLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(adressePatientLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(vuePatientLayout.createSequentialGroup()
                        .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(nomPatient, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                            .addComponent(champNomPatient, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(prenomPatient, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                            .addComponent(champPrenomPatient, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(numeroSSPatient, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                            .addComponent(champNumeroSSPatient, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(idPatientLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                            .addComponent(champDateCreationPatient, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(vuePatientLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(patientIdLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                            .addComponent(champIdPatient, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(vuePatientLayout.createSequentialGroup()
                        .addComponent(numero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adresse1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adresse2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codePostal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(villeLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paysLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(vuePatientLayout.createSequentialGroup()
                        .addComponent(champNumeroAdresse, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(champRueAdresse, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(champComplementAdresse, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(champCodePostal, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(champVille, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(champPays, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(vuePatient, BorderLayout.CENTER);

        barreRecherche.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        barreRecherche.setPreferredSize(new Dimension(550, 120));

        nomVue.setText("Agent d'accueil :");
        nomSalarieUtilisateur.setText("{nomSalarie}");
        deconnexion.setText("Déconnexion");

        GroupLayout vueSalarieLayout = new GroupLayout(vueSalarie);
        vueSalarie.setLayout(vueSalarieLayout);
        vueSalarieLayout.setHorizontalGroup(
            vueSalarieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vueSalarieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nomVue)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomSalarieUtilisateur)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deconnexion, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
        );
        vueSalarieLayout.setVerticalGroup(
            vueSalarieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vueSalarieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vueSalarieLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nomSalarieUtilisateur)
                    .addComponent(nomVue))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, vueSalarieLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(deconnexion))
        );

        recherchePatient.setText("Effectuer une recherche de patient par : ");

        choixCritereRecherche.setModel(new DefaultComboBoxModel<>(new String[] { "Nom", "Numéro SS", "idPatient" }));
      
        champRecherche.setForeground(new Color(204, 204, 204));
        champRecherche.setText("Indiquer votre critère de recherche");
        champRecherche.setPreferredSize(new Dimension(180, 24));

        lancerRechercheBouton.setText("Rechercher");
        lancerRechercheBouton.setPreferredSize(new Dimension(90, 24));
       
        GroupLayout barreRechercheLayout = new GroupLayout(barreRecherche);
        barreRecherche.setLayout(barreRechercheLayout);
        barreRechercheLayout.setHorizontalGroup(
            barreRechercheLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(barreRechercheLayout.createSequentialGroup()
                .addGroup(barreRechercheLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(vueSalarie, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, barreRechercheLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(recherchePatient)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(choixCritereRecherche, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(champRecherche, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lancerRechercheBouton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(282, Short.MAX_VALUE))
        );
        barreRechercheLayout.setVerticalGroup(
            barreRechercheLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(barreRechercheLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vueSalarie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(barreRechercheLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(recherchePatient)
                    .addComponent(choixCritereRecherche, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(champRecherche, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lancerRechercheBouton, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(barreRecherche, BorderLayout.NORTH);

        validationPatient.setMaximumSize(new Dimension(32767, 250));
        validationPatient.setPreferredSize(new Dimension(137, 250));

        afficherPatientBouton.setText("Modifier Patient");
        afficherPatientBouton.setPreferredSize(new Dimension(90, 24));

        creationPatientBouton.setText("Création patient");
        creationPatientBouton.setPreferredSize(new Dimension(90, 24));
      
        supprimerPatientBouton.setText("Supprimer patient");
        supprimerPatientBouton.setPreferredSize(new Dimension(90, 24));
       
        GroupLayout validationPatientLayout = new GroupLayout(validationPatient);
        validationPatient.setLayout(validationPatientLayout);
        validationPatientLayout.setHorizontalGroup(
            validationPatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(validationPatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(validationPatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(creationPatientBouton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(validationPatientLayout.createSequentialGroup()
                        .addGroup(validationPatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(afficherPatientBouton, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(supprimerPatientBouton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        
        validationPatientLayout.setVerticalGroup(
            validationPatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(validationPatientLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(creationPatientBouton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(afficherPatientBouton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(supprimerPatientBouton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        getContentPane().add(validationPatient, BorderLayout.LINE_END);
        pack();
    }                   
  
    public JTextField getChampRecherche() {
		return champRecherche;
	}

	public void setChampRecherche(JTextField champRecherche) {
		this.champRecherche = champRecherche;
	}

	public JComboBox<String> getChoixCritereRecherche() {
		return choixCritereRecherche;
	}

	public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueAgentCreationPatient().setVisible(true);
            }
        });
    }
           
}
