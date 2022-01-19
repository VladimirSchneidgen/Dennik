package dennik;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Choice;
import javax.swing.JButton;

public class Historia extends Udaje {

	private JFrame obraz;
	private JLabel lblPozadia;
	private ImageIcon obrazokPozadia, ikonaAp;
	
	public Historia() {
		obraz = new JFrame();
		obraz.setBounds(100, 100, getVyskaObrazu(), getSirkaObrazu());
		obraz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obraz.setTitle("Historia poznamok");
		obraz.setLocationRelativeTo(null);
		obraz.setResizable(false);
		obraz.getContentPane().setLayout(null);
		ikonaAp = new ImageIcon(getClass().getResource("/diar.jpg"));
		obraz.setIconImage(ikonaAp.getImage());
		
		obrazokPozadia = new ImageIcon(this.getClass().getResource("/obloha.jpg"));
		lblPozadia = new JLabel(obrazokPozadia);
		lblPozadia.setLocation(0, 0);
		lblPozadia.setSize(708, 398);
		
		JLabel lblSpat = new JLabel("         Späť");
		lblSpat.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblSpat.setBounds(598, 344, 98, 54);
		lblSpat.setForeground(Color.white);
		obraz.getContentPane().add(lblSpat);
		
		Choice moznostRok = new Choice();
		moznostRok.setFont(new Font("Dialog", Font.PLAIN, 14));
		moznostRok.setBounds(341, 23, 70, 24);
		moznostRok.add("");
		LocalDate datum = LocalDate.now();
		moznostRok.add(datum.getYear() + "");
		moznostRok.add(datum.getYear()-1 + "");
		moznostRok.add(datum.getYear()-2 + "");
		obraz.getContentPane().add(moznostRok);
		
		Choice moznostMesiac = new Choice();
		moznostMesiac.setFont(new Font("Dialog", Font.PLAIN, 14));
		moznostMesiac.setBounds(265, 23, 70, 24);
		moznostMesiac.add("");
		for(int i = 1; i <= 12; i++) {
			moznostMesiac.add("" + i);
		}
		obraz.getContentPane().add(moznostMesiac);
		
		Choice moznostDen = new Choice();
		moznostDen.setFont(new Font("Dialog", Font.PLAIN, 14));
		moznostDen.add("");
		for(int i = 1; i <= 31; i++) {
			moznostDen.add(i+"");
		}
		moznostDen.setBounds(189, 23, 70, 24);
		obraz.getContentPane().add(moznostDen);
		
		JTextArea textArea = new JTextArea("");
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(42, 68, 586, 298);
		textArea.setOpaque(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		obraz.getContentPane().add(textArea);
		
		JButton btnHladaj = new JButton("Hľadaj");
		btnHladaj.setBounds(417, 23, 85, 24);
		obraz.getContentPane().add(btnHladaj);
		obraz.getContentPane().add(lblPozadia);
		obraz.setVisible(true);
		
		btnHladaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnHladaj == (JButton) e.getSource()) {
					try {
						String text = null;
						String dotaz_1 = "SELECT * from myslienky WHERE datum = '" + moznostRok.getSelectedItem() +"-" + moznostMesiac.getSelectedItem() + "-" + moznostDen.getSelectedItem() +"'";
						Connection spojenie = DriverManager.getConnection(url, menoMySql, hesloMySql);
						Statement prikaz = spojenie.createStatement();
						ResultSet vysledok = prikaz.executeQuery(dotaz_1);
						
					
						while(vysledok.next()) {
							text = vysledok.getString("myslienka");
							textArea.setText(text);
						}
						
						
						if(text == null) {
							JOptionPane.showMessageDialog(null, "Z tohoto datumu nie su ziadne poznamky.", null, JOptionPane.WARNING_MESSAGE);
						}
						
				}
					 catch (HeadlessException | SQLException e1) {
							e1.printStackTrace();
						}
				}
			}
		});
		
		lblSpat.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				  obraz.dispose();
				  Menu hlavneMenu = new Menu();
            }
		});
	}
}
