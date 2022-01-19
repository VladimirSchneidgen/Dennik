package dennik;

import java.awt.Image;
import java.awt.SystemColor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends Udaje implements ActionListener {

	private JFrame obraz;
	private JLabel lblPozadia;
	private JPasswordField poleHeslo;
	private JTextField txtCisloKarty;
	private JButton btnPrihlasit;
	private ImageIcon ikona, ikonaAp;
	
	public Login() {
		
		obraz = new JFrame();
		obraz.setBounds(100, 100, getVyskaObrazu(), getSirkaObrazu());
		obraz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obraz.setTitle("Prihlasenie");
		obraz.setLocationRelativeTo(null);
		obraz.setResizable(false);
		obraz.getContentPane().setLayout(null); 
		
		ikonaAp = new ImageIcon(getClass().getResource("/diar.jpg"));
		obraz.setIconImage(ikonaAp.getImage());
		
		ikona = new ImageIcon(this.getClass().getResource("/men-alone-bench-lonely-wallpaper-preview.jpg"));
		lblPozadia = new JLabel(ikona);
		lblPozadia.setLocation(0, 0);
		lblPozadia.setSize(708, 398);
		
		poleHeslo = new JPasswordField();
		poleHeslo.setToolTipText("Sem zadajte heslo");
		poleHeslo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		poleHeslo.setBounds(196, 162, 253, 26);
		obraz.getContentPane().add(poleHeslo);

		txtCisloKarty = new JTextField();
		txtCisloKarty.setToolTipText("Sem zadajte meno");
		txtCisloKarty.setBackground(SystemColor.window);
		txtCisloKarty.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCisloKarty.setBounds(194, 124, 253, 28);
		obraz.getContentPane().add(txtCisloKarty);
		txtCisloKarty.setColumns(10);

		btnPrihlasit = new JButton("Prihlásiť");
		btnPrihlasit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPrihlasit.setBounds(196, 198, 116, 33);
		btnPrihlasit.setBorderPainted(false); 
		obraz.getContentPane().add(btnPrihlasit);
		
		obraz.getContentPane().add(lblPozadia);
		obraz.setVisible(true);
		
		url = getUrl();
		menoMySql = getMenoMySql();
		hesloMySql = getHesloMySql();
		btnPrihlasit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(btnPrihlasit == (JButton) e.getSource()) {
			
			String vlozeneMeno = txtCisloKarty.getText();
			String vlozeneHeslo = new String(poleHeslo.getPassword());
			String dopyt = "SELECT * from myslienky_prihlasenie WHERE meno = '" + vlozeneMeno + "' AND heslo = '" + vlozeneHeslo + "'";
			
			try {
				
				Connection spojenie = DriverManager.getConnection(getUrl(), getMenoMySql(), getHesloMySql());
				Statement prikaz = spojenie.createStatement();
				ResultSet vysledok = prikaz.executeQuery(dopyt);
				
				if(vysledok.next()) {
					obraz.dispose();
					Menu menu = new Menu();
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Nesprávne meno lebo heslo!", "Chyba v prihlásení", JOptionPane.WARNING_MESSAGE);
				}
			}
			 catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
		}
	}
}
