package Vue;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Génère la vue de connexion comportant :
 * - un champ pour le login 
 * - un champ pour le mot de passe 
 * - un bouton pour envoyer les deux précédents informations au contrôleur
 * @author Romain
 *
 */
@SuppressWarnings("serial")
public class EcranConnexion extends JFrame {
	private JLabel loginLabel ;
	protected JTextField loginField;
	private JLabel passwordLabel;
	protected  JPasswordField passwordField;
	private JButton boutonConnexion;
	FlowLayout flowLayout = new FlowLayout ();
	private Dimension dimLabel = new Dimension (90,24);
	private Dimension dimTextField = new Dimension (100,24);
	private Dimension dimBouton = new Dimension (100,30);
	int tailleX = 1000;
	int tailleY = 500;
	
	public EcranConnexion () {
		initComposants();
		this.setSize(new Dimension(1000,500));
		this.setTitle("Connexion Hopital");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// initialisation des composants de la vue
	private void initComposants() {
		Box boxVertical = Box.createVerticalBox();
		
		// Panel du login
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(flowLayout);
		loginPanel.setSize(new Dimension(250,30));
		loginLabel = new JLabel ("Login : ");
		loginLabel.setPreferredSize(dimLabel);
		loginField = new JTextField();
		loginField.setPreferredSize(dimTextField);
//		loginField.setText("jpeuplus");
		loginPanel.add(loginLabel);
		loginPanel.add(loginField);
		
		// Panel du mot de passe
		JPanel passwordPanel = new JPanel ();
		passwordPanel.setLayout(new FlowLayout());
		passwordPanel.setSize(new Dimension(250,30));
		passwordLabel = new JLabel ("Mot de passe : ");
		passwordLabel.setPreferredSize(dimLabel);
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(dimTextField);
//		passwordField.setText("admin");
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		
		// Panel du bouton de connexion
		JPanel boutonPanel = new JPanel ();	
		boutonPanel.setSize(new Dimension(105,35));
		boutonConnexion = new JButton ("Connexion");
		boutonConnexion.setPreferredSize(dimBouton);
		boutonPanel.add(boutonConnexion);	
		
		boxVertical.add(Box.createRigidArea(new Dimension(0,150)));
		boxVertical.add(loginPanel);
		boxVertical.add(passwordPanel);
		boxVertical.add(boutonPanel);

		JPanel panelConteneur = new JPanel();
		panelConteneur.setSize(300, 150);
		panelConteneur.add(boxVertical);
		this.add(panelConteneur);
	}
	
	// Getters de la vue
	public JTextField getLoginField() {
		return loginField;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public JButton getBoutonConnexion() {
		return boutonConnexion;
	}	

	// Gestion des messages affichés au client 
	public void connexionError() {
		JOptionPane.showMessageDialog(this, "Login ou mot de passe érroné");
	}
	
	public void connexionGranted(String str) {
		JOptionPane.showMessageDialog(this, "Connexion : "+str+".");
	}
}
