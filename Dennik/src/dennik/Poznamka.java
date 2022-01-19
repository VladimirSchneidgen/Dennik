package dennik;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Poznamka extends Udaje{
	private JFrame obraz;
	private ImageIcon ikona, ikonaAp;
	private JLabel lblPozadia;
	private int id = 0;
	
	public Poznamka() {
		obraz = new JFrame();
		obraz.setBounds(100, 100, getVyskaObrazu(), getSirkaObrazu());
		obraz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obraz.setTitle("Prihlasenie");
		obraz.setLocationRelativeTo(null);
		obraz.setResizable(false);
		obraz.getContentPane().setLayout(null);
		
		ikonaAp = new ImageIcon(getClass().getResource("/diar.jpg"));
		obraz.setIconImage(ikonaAp.getImage());
		
		ikona = new ImageIcon(this.getClass().getResource("/obloha.jpg"));
		lblPozadia = new JLabel(ikona);
		lblPozadia.setLocation(0, 0);
		lblPozadia.setSize(696, 398);
		
		JTextArea textovePole = new JTextArea();
		textovePole.setBounds(42, 32, 606, 304);
		textovePole.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		textovePole.setOpaque(false);
		textovePole.setForeground(Color.white);
		textovePole.setLineWrap(true);
		textovePole.setWrapStyleWord(true);
		obraz.getContentPane().add(textovePole);
		
		JLabel lblPridatText = new JLabel("Pridať text");
		lblPridatText.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblPridatText.setBounds(311, 344, 98, 42);
		lblPridatText.setForeground(Color.white);
		obraz.getContentPane().add(lblPridatText);
		
		JLabel lblSpat = new JLabel("           Späť");
		lblSpat.setForeground(Color.WHITE);
		lblSpat.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblSpat.setBounds(598, 346, 98, 42);
		obraz.getContentPane().add(lblSpat);
		obraz.getContentPane().add(lblPozadia);
		obraz.setVisible(true);
		
		lblPridatText.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				  try {
						Connection spojenie = DriverManager.getConnection(getUrl(), getMenoMySql(),getHesloMySql());
						Statement prikaz = spojenie.createStatement();
						ResultSet vysledok_vyberu = prikaz.executeQuery("SELECT * FROM myslienky");
						while(vysledok_vyberu.next()) {
							id = vysledok_vyberu.getInt("id");
						}
						id += 1;	
						String text = textovePole.getText();
						LocalDate date = LocalDate.now();
						
						PreparedStatement prikaz_vyberu;
						prikaz_vyberu = spojenie.prepareStatement("INSERT INTO `myslienky` (id, myslienka, datum) VALUE ('"+ id +"', '"+ text +"', '"+ date +"')");
						int vysledok = prikaz_vyberu.executeUpdate();
						
						if(vysledok > 0) {
							JOptionPane.showMessageDialog(null, "Text bol úspešne zapísaný!", "Potvrdenie", JOptionPane.INFORMATION_MESSAGE);
							obraz.dispose();
							Menu menu = new Menu();
						}
						else
							JOptionPane.showMessageDialog(null, "Text nebol zapísaný!", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (SQLException d) {
						d.printStackTrace();
					}
			  }
		});
	lblSpat.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			obraz.dispose();
			Menu menu = new Menu();
		}
	});	
	}
}
