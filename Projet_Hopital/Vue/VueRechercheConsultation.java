package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class VueRechercheConsultation extends JFrame {
	
    private JButton afficherPatientBouton;
    private JPanel barreRecherche;
    private JTextField champRecherche;
    private JComboBox<String> choixCritereRecherche;
    private JButton creationPatientBouton;
    private JButton deconnexion;
    private JPanel extractionConsultation;
    private JToggleButton lancerRechercheBouton;
    private JLabel nomSalarieUtilisateur;
    private JLabel nomVue;
    private JLabel rechercheConsultation;
    private JPanel resultatRechercheConsultation;
    private JScrollPane scrollRechercheConsultation;
    private JButton supprimerPatientBouton;
    private JTable tableauRechercheConsultation;
    private JPanel vueSalarie;
    
    public VueRechercheConsultation() {
        initComponents();
    }

    @SuppressWarnings({ "unchecked", "serial" })                       
    private void initComponents() {

        barreRecherche = new JPanel();
        vueSalarie = new JPanel();
        nomVue = new JLabel();
        nomSalarieUtilisateur = new JLabel();
        deconnexion = new JButton();
        rechercheConsultation = new JLabel();
        choixCritereRecherche = new JComboBox<>();
        champRecherche = new JTextField();
        lancerRechercheBouton = new JToggleButton();
        resultatRechercheConsultation = new JPanel();
        scrollRechercheConsultation = new JScrollPane();
        tableauRechercheConsultation = new JTable();
        extractionConsultation = new JPanel();
        afficherPatientBouton = new JButton();
        creationPatientBouton = new JButton();
        supprimerPatientBouton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recherche consultation");
        setPreferredSize(new Dimension(900, 300));

        barreRecherche.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        barreRecherche.setPreferredSize(new Dimension(550, 120));

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
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 446, Short.MAX_VALUE)
                .addComponent(deconnexion, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                .addGap(405, 405, 405))	
        );
        vueSalarieLayout.setVerticalGroup(
            vueSalarieLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(vueSalarieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vueSalarieLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nomSalarieUtilisateur)
                    .addComponent(nomVue)
                    .addComponent(deconnexion))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        rechercheConsultation.setText("Rechercher une consultation par : ");

        choixCritereRecherche.setModel(new DefaultComboBoxModel<>(new String[] { "Nom patient", "Pathologie", "Numéro SS" }));
     
        champRecherche.setForeground(new Color(204, 204, 204));
        champRecherche.setText("Indiquer votre critère de recherche");
        champRecherche.setPreferredSize(new Dimension(180, 24));
        champRecherche.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                champRechercheFocusGained(evt);
            }
            public void focusLost(FocusEvent evt) {
                champRechercheFocusLost(evt);
            }
        });

        lancerRechercheBouton.setText("Rechercher");
        lancerRechercheBouton.setPreferredSize(new Dimension(90, 24));

        GroupLayout barreRechercheLayout = new GroupLayout(barreRecherche);
        barreRecherche.setLayout(barreRechercheLayout);
        barreRechercheLayout.setHorizontalGroup(
            barreRechercheLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(barreRechercheLayout.createSequentialGroup()
                .addGroup(barreRechercheLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(vueSalarie, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(GroupLayout.Alignment.LEADING, barreRechercheLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rechercheConsultation)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(choixCritereRecherche, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(champRecherche, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lancerRechercheBouton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        barreRechercheLayout.setVerticalGroup(
            barreRechercheLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(barreRechercheLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vueSalarie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(barreRechercheLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(rechercheConsultation)
                    .addComponent(choixCritereRecherche, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(champRecherche, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lancerRechercheBouton, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(barreRecherche, BorderLayout.NORTH);

        resultatRechercheConsultation.setPreferredSize(new Dimension(500, 250));

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
                .addContainerGap(143, Short.MAX_VALUE))
        );
        resultatRechercheConsultationLayout.setVerticalGroup(
            resultatRechercheConsultationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(resultatRechercheConsultationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollRechercheConsultation, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(resultatRechercheConsultation, BorderLayout.CENTER);

        extractionConsultation.setMaximumSize(new Dimension(32767, 250));
        extractionConsultation.setPreferredSize(new Dimension(150, 250));

        afficherPatientBouton.setText("Afficher consultation");
        afficherPatientBouton.setPreferredSize(new Dimension(90, 24));

        creationPatientBouton.setText("Créer consultation");
        creationPatientBouton.setPreferredSize(new Dimension(90, 24));
     
        supprimerPatientBouton.setText("Supprimer consultation");
        supprimerPatientBouton.setPreferredSize(new Dimension(90, 24));

        GroupLayout extractionConsultationLayout = new GroupLayout(extractionConsultation);
        extractionConsultation.setLayout(extractionConsultationLayout);
        extractionConsultationLayout.setHorizontalGroup(
            extractionConsultationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(extractionConsultationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(extractionConsultationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(creationPatientBouton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(extractionConsultationLayout.createSequentialGroup()
                        .addGroup(extractionConsultationLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(afficherPatientBouton, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(supprimerPatientBouton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        extractionConsultationLayout.setVerticalGroup(
            extractionConsultationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(extractionConsultationLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(creationPatientBouton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(afficherPatientBouton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(supprimerPatientBouton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        getContentPane().add(extractionConsultation, BorderLayout.EAST);
        pack();
    }                   

  

    private void champRechercheFocusLost(FocusEvent evt) {                                         
        champRecherche.setText("Indiquer votre critère de recherche");
    }                                        
    private void champRechercheFocusGained(FocusEvent evt) {                                           
        champRecherche.setText("");
    }                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueRechercheConsultation().setVisible(true);
            }
        });
    }
}
