package Vue;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EcranConnexion extends JFrame {
	private JLabel loginLabel ;
	protected JTextField loginField;
	private JLabel passwordLabel;
	protected  JTextField passwordField;
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
		this.setTitle("Connexion");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void initComposants() {
		Box boxVertical = Box.createVerticalBox();
		
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(flowLayout);
		loginPanel.setSize(new Dimension(250,30));
		loginLabel = new JLabel ("Login : ");
		loginLabel.setPreferredSize(dimLabel);
		loginField = new JTextField();
		loginField.setPreferredSize(dimTextField);
		loginField.setText("jpeuplus");
		loginPanel.add(loginLabel);
		loginPanel.add(loginField);
		
		JPanel passwordPanel = new JPanel ();
		passwordPanel.setLayout(new FlowLayout());
		passwordPanel.setSize(new Dimension(250,30));
		passwordLabel = new JLabel ("Mot de passe : ");
		passwordLabel.setPreferredSize(dimLabel);
		passwordField = new JTextField();
		passwordField.setPreferredSize(dimTextField);
		passwordField.setText("admin");
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		
		JPanel boutonPanel = new JPanel ();	
		boutonPanel.setSize(new Dimension(105,35));
		boutonConnexion = new JButton ("Connexion");
		boutonConnexion.setPreferredSize(dimBouton);
//		boutonConnexion.addActionListener(null);
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
	
	public void initVueCreationPatient() {
		VueAgentCreationPatient vueAgentCreationPatient = new VueAgentCreationPatient();
		//this.
	}
	
	public void connexionError() {
		JOptionPane.showMessageDialog(this, "Login ou mot de passe érroné");
	}
	
	public void connexionGranted(String str) {
		JOptionPane.showMessageDialog(this, "Connexion : "+str+".");
	}

	public JTextField getLoginField() {
		return loginField;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public JButton getBoutonConnexion() {
		return boutonConnexion;
	}

	


}
