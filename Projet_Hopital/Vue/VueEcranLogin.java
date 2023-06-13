
package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class VueEcranLogin extends JFrame {

    private JTextField ChampMDP;
    private JPanel accueil;
    private JTextField champLogin;
    private JButton connexionBouton;
    private JLabel loginLabel;
    private JLabel mdpLabel;
    private JLabel titreLabel;
    
    public VueEcranLogin() {
        initComponents();
    }
                     
    private void initComponents() {

        accueil = new JPanel();
        loginLabel = new JLabel();
        champLogin = new JTextField();
        mdpLabel = new JLabel();
        ChampMDP = new JTextField();
        connexionBouton = new JButton();
        titreLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ecran de connexion");

        loginLabel.setText("Login :");
        mdpLabel.setText("Mot de passe :");
        connexionBouton.setText("Connexion");
        titreLabel.setText("PROJET_HOPITAL");

        GroupLayout accueilLayout = new GroupLayout(accueil);
        accueil.setLayout(accueilLayout);
        accueilLayout.setHorizontalGroup(
            accueilLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(accueilLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(accueilLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(connexionBouton, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                    .addGroup(accueilLayout.createSequentialGroup()
                        .addGroup(accueilLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(mdpLabel)
                            .addComponent(loginLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(accueilLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(champLogin, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(ChampMDP))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, accueilLayout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addComponent(titreLabel, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );
        
        accueilLayout.setVerticalGroup(
            accueilLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(accueilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titreLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGroup(accueilLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(champLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(accueilLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mdpLabel)
                    .addComponent(ChampMDP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(connexionBouton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        getContentPane().add(accueil, BorderLayout.PAGE_START);
        pack();
    }                    

   
    public static void main(String args[]) {
              EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueEcranLogin().setVisible(true);
            }
        });
    }
           

                
}
