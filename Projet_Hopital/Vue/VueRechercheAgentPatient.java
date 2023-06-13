
package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;

/**
 * 
 * @author Romain
 * Interface de recherche de patients
 * Gère la recherche, la sélection de patient et les opérations CRD
 */
public class VueRechercheAgentPatient extends JFrame {

    private JScrollPane affichagePatientScroll;
    private JPanel affichagePatientsRecherche;
    private JButton afficherPatientBouton;
    private JPanel barreRecherche;
    private JTextField champRecherche;
    private JComboBox<String> choixCritereRecherche;
    private JButton creationPatientBouton;
    private JButton deconnexion;
    private JPanel extractionPatient;
    private JButton lancerRechercheBouton;
    private JTable matriceAffichagePatient;
    private JLabel nomSalarieUtilisateur;
    private JLabel nomVue;
    private JLabel recherchePatient;
    private JButton supprimerPatientBouton;
    private JPanel vueSalarie;
    private PatientModelTable patientModelTable;
    
    public VueRechercheAgentPatient() {
        initComponents();
        this.setSize(new Dimension(1000, 500));
        this. setTitle("Vue Agent Accueil");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

 // Méthode d'initialisation des composants utilisés pour l'affichage
    @SuppressWarnings({ "unchecked", "serial" })
    private void initComponents() {

        affichagePatientsRecherche = new JPanel();
        affichagePatientScroll = new JScrollPane();
        matriceAffichagePatient = new JTable();
        barreRecherche = new JPanel();
        vueSalarie = new JPanel();
        nomVue = new JLabel();
        nomSalarieUtilisateur = new JLabel();
        deconnexion = new JButton();
        recherchePatient = new JLabel();
        choixCritereRecherche = new JComboBox<>();
        champRecherche = new JTextField();
        lancerRechercheBouton = new JButton();
        extractionPatient = new JPanel();
        afficherPatientBouton = new JButton();
        creationPatientBouton = new JButton();
        supprimerPatientBouton = new JButton();

        // Panel d'affichage des patients issus de la recherche 
        affichagePatientsRecherche.setPreferredSize(new Dimension(350, 150));
        
        // Création du tableau d'affichage des patients, vierge par défaut
        patientModelTable = new PatientModelTable(
                new String [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "idPatient", "Nom", "Prénom", "Numéro SS"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                };
                boolean[] canEdit = new boolean [] {
                    false, false, false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
        
        matriceAffichagePatient.setModel(patientModelTable);
        
        // Impose la sélection d'un seul patient à la fois
        matriceAffichagePatient.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        affichagePatientScroll.setViewportView(matriceAffichagePatient);

        GroupLayout affichagePatientsRechercheLayout = new GroupLayout(affichagePatientsRecherche);
        affichagePatientsRecherche.setLayout(affichagePatientsRechercheLayout);
        affichagePatientsRechercheLayout.setHorizontalGroup(
            affichagePatientsRechercheLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(affichagePatientsRechercheLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(affichagePatientScroll, GroupLayout.PREFERRED_SIZE, 837, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        affichagePatientsRechercheLayout.setVerticalGroup(
            affichagePatientsRechercheLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, affichagePatientsRechercheLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(affichagePatientScroll, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        getContentPane().add(affichagePatientsRecherche, BorderLayout.CENTER);

        /**
         * Panel permettant l'affichage du login de l'utilisateur via une variable static et la déconnexion de l'application
         */
        barreRecherche.setPreferredSize(new Dimension(550, 80));

        nomVue.setText("Agent d'accueil :");

        nomSalarieUtilisateur.setText("{nomSalarie}");

        deconnexion.setText("Déconnexion");
        deconnexion.setPreferredSize(new Dimension(100, 24));
 

        GroupLayout vueSalarieLayout = new GroupLayout(vueSalarie);
        vueSalarie.setLayout(vueSalarieLayout);
        vueSalarieLayout.setHorizontalGroup(
            vueSalarieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vueSalarieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nomVue)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomSalarieUtilisateur)
                .addContainerGap(486, Short.MAX_VALUE))
            .addGroup(vueSalarieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(vueSalarieLayout.createSequentialGroup()
                    .addContainerGap(542, Short.MAX_VALUE)
                    .addComponent(deconnexion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        vueSalarieLayout.setVerticalGroup(
            vueSalarieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vueSalarieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vueSalarieLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nomSalarieUtilisateur)
                    .addComponent(nomVue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(vueSalarieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(vueSalarieLayout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(deconnexion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        /**
         *  Panel de recherche pour gérer les critères de recherhe des patients
         *  et lancer la recherche
         */
       
        recherchePatient.setText("Effectuer une recherche de patient par : ");

        choixCritereRecherche.setModel(new DefaultComboBoxModel<>(new String[] { "Nom", "Numéro SS", "idPatient" }));
        
        champRecherche.setForeground(new Color(204,204,204));
        champRecherche.setText("Indiquer le critère de recherche");
        champRecherche.setPreferredSize(new Dimension(180, 24));

        lancerRechercheBouton.setText("Rechercher");
        lancerRechercheBouton.setPreferredSize(new Dimension(100, 24));

        GroupLayout barreRechercheLayout = new GroupLayout(barreRecherche);
        barreRecherche.setLayout(barreRechercheLayout);
        barreRechercheLayout.setHorizontalGroup(
            barreRechercheLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(barreRechercheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(barreRechercheLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(vueSalarie, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(barreRechercheLayout.createSequentialGroup()
                        .addComponent(recherchePatient)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(choixCritereRecherche, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(champRecherche, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lancerRechercheBouton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(327, Short.MAX_VALUE))
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
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(barreRecherche, BorderLayout.PAGE_START);

        /**
         * Panel dédié aux boutons des opérations CRUD
         * la partie Update se fait dans la partie Retrieve "Affichage patient"
         */
        extractionPatient.setMaximumSize(new Dimension(32767, 250));
        extractionPatient.setPreferredSize(new Dimension(137, 250));

        afficherPatientBouton.setText("Afficher patient");
        afficherPatientBouton.setPreferredSize(new Dimension(90, 24));

        creationPatientBouton.setText("Créer patient");
        creationPatientBouton.setPreferredSize(new Dimension(90, 24));
      
        supprimerPatientBouton.setText("Supprimer patient");
        supprimerPatientBouton.setPreferredSize(new Dimension(90, 24));
      
        GroupLayout extractionPatientLayout = new GroupLayout(extractionPatient);
        extractionPatient.setLayout(extractionPatientLayout);
        extractionPatientLayout.setHorizontalGroup(
            extractionPatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(extractionPatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(extractionPatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(creationPatientBouton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(extractionPatientLayout.createSequentialGroup()
                        .addGroup(extractionPatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(afficherPatientBouton, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(supprimerPatientBouton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        extractionPatientLayout.setVerticalGroup(
            extractionPatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(extractionPatientLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(creationPatientBouton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(afficherPatientBouton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(supprimerPatientBouton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        getContentPane().add(extractionPatient, BorderLayout.LINE_END);
        pack();
    }           
    
    /**
     * Getters et setters des variables de la vue de recherche des Patients
     */
   
	public PatientModelTable getPatientModelTable() {
		return patientModelTable;
	}

	public JLabel getNomSalarieUtilisateur() {
		return nomSalarieUtilisateur;
	}

	public void setNomSalarieUtilisateur(String nomSalarieUtilisateur) {
		this.nomSalarieUtilisateur.setText(nomSalarieUtilisateur); 
	}

	public JTextField getChampRecherche() {
		return champRecherche;
	}

	public void setChampRecherche(String champRecherche) {
		this.champRecherche.setText(champRecherche);
	}

	public JButton getLancerRechercheBouton() {
		return lancerRechercheBouton;
	}

	public JTable getMatriceAffichagePatient() {
		return matriceAffichagePatient;
	}

	public void setMatriceAffichagePatient(JTable matriceAffichagePatient) {
		this.matriceAffichagePatient = matriceAffichagePatient;
	}

	public JComboBox<String> getChoixCritereRecherche() {
		return choixCritereRecherche;
	}

	public JButton getCreationPatientBouton() {
		return creationPatientBouton;
	}

	public JButton getAfficherPatientBouton() {
		return afficherPatientBouton;
	}

	public JButton getDeconnexion() {
		return deconnexion;
	}

	public JButton getSupprimerPatientBouton() {
		return supprimerPatientBouton;
	}


	/**
	 * Gestion des messages d'erreur pour informer l'utilisateur des erreurs de recherche fonctionnelles
	 */
	public void rechercheError(String str) {
		JOptionPane.showMessageDialog(this, "La recherche par "+str+" ne doit contenir que des chiffres !");
	}
	public void rechercheVide() {
		JOptionPane.showMessageDialog(this, "Aucun patient trouvé avec ces critères de recherche.");
	}
	public void suppressionPatientSuccess(String str) {
		JOptionPane.showMessageDialog(this, "Le patient "+str+" a bien été supprimé");
	}
	public void suppressionPatientFailed(String str) {
		JOptionPane.showMessageDialog(this, "Le patient "+str+" n'a pas été supprimé");
	}
		
 /**
  * Bloc permettant l'affichage seul de la vue RecherchePatient
  * A n'utiliser que pour vérifier le rendu
  */
	
//	public static void main(String args[]) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VueRechercheAgentPatient().setVisible(true);
//            }
//        });
//    }
              

               
}
