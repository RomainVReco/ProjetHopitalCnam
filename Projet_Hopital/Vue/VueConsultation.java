package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Vue d'affichage des consultations, accessible par un médecin
 * Permettra à terme de :
 * - renseigner le détail clinique
 * - Choisir une ou plusieurs pathologies et visualiser le traitement recommandé
 * - Choisir le matériel nécessaire au soin
 * - Visualiser les consultations précédentes du patient et en afficher une sélectionnée
 * 
 * En cours de développement, non disponible dans la version actuelle du logiciel 
 * 
 * @author Romain
 *
 */
@SuppressWarnings("serial")
public class VueConsultation extends JFrame {
                    
    private JPanel creationConsultation;
    private JButton deconnexion;
    private JPanel detailClinique;
    private JLabel detailCliniqueLabel;
    private JTextArea detailCliniqueTextArea;
    private JPanel informationsPatient;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JLabel nomPatient;
    private JLabel nomPatientLabel;
    private JLabel nomSalarieUtilisateur;
    private JLabel nomVue;
    private JLabel numSS;
    private JLabel numSSLabel;
    private JLabel prenomPatient;
    private JLabel prenomPatientLabel;
    private JPanel resultatRechercheConsultation;
    private JScrollPane scrollDetailClinique;
    private JScrollPane scrollRechercheConsultation;
    private JScrollPane scrollTableauMaladie;
    private JTable tableauMaladie;
    private JTable tableauRechercheConsultation;
    private JPanel vueSalarie;
    
    
    public VueConsultation() {
        initComponents();
    }

    @SuppressWarnings({ "unchecked", "serial" })                     
    private void initComponents() {

        vueSalarie = new JPanel();
        nomVue = new JLabel();
        nomSalarieUtilisateur = new JLabel();
        deconnexion = new JButton();
        creationConsultation = new JPanel();
        informationsPatient = new JPanel();
        nomPatient = new JLabel();
        prenomPatientLabel = new JLabel();
        nomPatientLabel = new JLabel();
        prenomPatient = new JLabel();
        numSS = new JLabel();
        numSSLabel = new JLabel();
        detailClinique = new JPanel();
        scrollDetailClinique = new JScrollPane();
        detailCliniqueTextArea = new JTextArea();
        detailCliniqueLabel = new JLabel();
        scrollTableauMaladie = new JScrollPane();
        tableauMaladie = new JTable();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        resultatRechercheConsultation = new JPanel();
        scrollRechercheConsultation = new JScrollPane();
        tableauRechercheConsultation = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 300);
        
        // Panel d'affichage des informations du médecin créant la consultation, contient le bouton déconnexion
        vueSalarie.setPreferredSize(new Dimension(900, 40));
        nomVue.setText("Médecin :");

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
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 580, Short.MAX_VALUE)
                .addComponent(deconnexion, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        vueSalarieLayout.setVerticalGroup(
            vueSalarieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vueSalarieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vueSalarieLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nomSalarieUtilisateur)
                    .addComponent(nomVue)
                    .addComponent(deconnexion))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        getContentPane().add(vueSalarie, BorderLayout.NORTH);

        // Panel d'information du patient pour lequel la consultation est créée
        creationConsultation.setPreferredSize(new Dimension(900, 189));

        nomPatient.setText("Nom :");

        prenomPatientLabel.setText("{prenomPatient}");

        nomPatientLabel.setText("{nomPatient}");

        prenomPatient.setText("Prénom :");

        numSS.setText("Numéro SS :");

        numSSLabel.setText("{numSS}");

        GroupLayout informationsPatientLayout = new GroupLayout(informationsPatient);
        informationsPatient.setLayout(informationsPatientLayout);
        informationsPatientLayout.setHorizontalGroup(
            informationsPatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(informationsPatientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nomPatient)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomPatientLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(prenomPatient)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prenomPatientLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numSS)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numSSLabel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );
        informationsPatientLayout.setVerticalGroup(
            informationsPatientLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(informationsPatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(informationsPatientLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nomPatient)
                    .addComponent(nomPatientLabel)
                    .addComponent(prenomPatient)
                    .addComponent(prenomPatientLabel)
                    .addComponent(numSS)
                    .addComponent(numSSLabel))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // Panel du détail clinique
        GroupLayout detailCliniqueLayout = new GroupLayout(detailClinique);
        detailClinique.setLayout(detailCliniqueLayout);
        detailCliniqueLayout.setHorizontalGroup(
            detailCliniqueLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        detailCliniqueLayout.setVerticalGroup(
            detailCliniqueLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        detailCliniqueTextArea.setColumns(20);
        detailCliniqueTextArea.setRows(5);
        scrollDetailClinique.setViewportView(detailCliniqueTextArea);

        detailCliniqueLabel.setText("Détail clinique de la consultation :");

        // Panel d'affichage des maladies connues
        tableauMaladie.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nom maladie", "Traitement"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollTableauMaladie.setViewportView(tableauMaladie);

        // panel d'affichage des matériels ainsi que du statut de leur disponibilité  
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nom materiel", "Disponible"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
    
        jScrollPane1.setViewportView(jTable1);

        // Panel regroupant le détail clinique, les maladies et les matériels
        GroupLayout creationConsultationLayout = new GroupLayout(creationConsultation);
        creationConsultation.setLayout(creationConsultationLayout);
        creationConsultationLayout.setHorizontalGroup(
            creationConsultationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(creationConsultationLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(creationConsultationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(creationConsultationLayout.createSequentialGroup()
                        .addComponent(detailCliniqueLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(detailClinique, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(creationConsultationLayout.createSequentialGroup()
                        .addComponent(scrollDetailClinique, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scrollTableauMaladie, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(52, Short.MAX_VALUE))
                    .addGroup(creationConsultationLayout.createSequentialGroup()
                        .addComponent(informationsPatient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE))))
        );
        creationConsultationLayout.setVerticalGroup(
            creationConsultationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(creationConsultationLayout.createSequentialGroup()
                .addComponent(informationsPatient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(detailCliniqueLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(creationConsultationLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addGroup(creationConsultationLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(scrollTableauMaladie, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(scrollDetailClinique, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(detailClinique, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        getContentPane().add(creationConsultation, BorderLayout.CENTER);

        // Panel d'affichage des précédentes consultations du patient
        // Utile pour le médecin s'il souhaite recherche dans l'historique
        resultatRechercheConsultation.setPreferredSize(new Dimension(900, 100));

        tableauRechercheConsultation.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "idConsultation", "Nom Patient", "Prénom Patient", "Numéro SS", "Pathologie(s)", "Matériel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableauRechercheConsultation.getTableHeader().setReorderingAllowed(false);
        scrollRechercheConsultation.setViewportView(tableauRechercheConsultation);

        GroupLayout resultatRechercheConsultationLayout = new GroupLayout(resultatRechercheConsultation);
        resultatRechercheConsultation.setLayout(resultatRechercheConsultationLayout);
        resultatRechercheConsultationLayout.setHorizontalGroup(
            resultatRechercheConsultationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(resultatRechercheConsultationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollRechercheConsultation, GroupLayout.PREFERRED_SIZE, 788, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        resultatRechercheConsultationLayout.setVerticalGroup(
            resultatRechercheConsultationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(resultatRechercheConsultationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollRechercheConsultation, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(resultatRechercheConsultation, BorderLayout.SOUTH);

        pack();
    }                      

/**
 *  Permet de lancer la vue indépendamment du logiciel 
 */
    public static void main(String args[]) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueConsultation().setVisible(true);
            }
        });
    }

                 
}
