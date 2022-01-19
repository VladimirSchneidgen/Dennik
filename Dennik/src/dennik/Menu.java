package dennik;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu {

	private JFrame obraz;
	private ImageIcon ikona, ikonaAp;
	private JLabel lblPozadia, lblOdhlasitSa;
	private JButton btnPozriet, btnNapisat;

	public Menu () {
		obraz = new JFrame();
		obraz.setBounds(100, 100, 700, 424);
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
		
		btnNapisat = new JButton("Napísať");
		btnNapisat.setFont(new Font("Open Sans", Font.PLAIN, 14));
		btnNapisat.setBounds(115, 135, 173, 68);
		btnNapisat.setBorderPainted(false);
		obraz.getContentPane().add(btnNapisat);
		
		btnPozriet = new JButton("Pozrieť si");
		btnPozriet.setFont(new Font("Open Sans", Font.PLAIN, 14));
		btnPozriet.setBounds(418, 135, 173, 68);
		btnPozriet.setBorderPainted(false);
		obraz.getContentPane().add(btnPozriet);
		
		lblOdhlasitSa = new JLabel("Odhlásiť sa");
		lblOdhlasitSa.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblOdhlasitSa.setBounds(598, 344, 98, 54);
		lblOdhlasitSa.setForeground(Color.white);
		obraz.getContentPane().add(lblOdhlasitSa);
		
		obraz.getContentPane().add(lblPozadia);
		obraz.setVisible(true);
		
		lblOdhlasitSa.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				  obraz.dispose();
				  Login l = new Login();
              }
		});
		
		btnNapisat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnNapisat == (JButton) e.getSource()) {
					obraz.dispose();
					Poznamka p = new Poznamka();
				}
			}
		});
		
		btnPozriet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent f) {
				if(btnPozriet == (JButton) f.getSource()) {
					obraz.dispose();
					Historia historia = new Historia();
				}
			}
		});
	}
}
