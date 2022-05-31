package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import Helper.DBConnection;
import Helper.Helper;
import Helper.Helper.*;
import Model.Bashekim;
import Model.Hasta;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_hastaPass;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private DBConnection conn = new DBConnection();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 402);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 102, 466, 236);
		w_pane.add(w_tabpane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Girişi", null, panel, null);
		panel.setLayout(null);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setLayout(null);
		w_hastaLogin.setBackground(Color.WHITE);
		w_hastaLogin.setBounds(0, -14, 461, 223);
		panel.add(w_hastaLogin);
		
		JLabel lblTcNumaranz = new JLabel("Tc numaran\u0131z: ");
		lblTcNumaranz.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblTcNumaranz.setBounds(42, 38, 93, 18);
		w_hastaLogin.add(lblTcNumaranz);
		
		JLabel lblifre = new JLabel("\u015Eifre:");
		lblifre.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblifre.setBounds(42, 90, 93, 18);
		w_hastaLogin.add(lblifre);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setBounds(145, 33, 269, 25);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		fld_hastaPass = new JTextField();
		fld_hastaPass.setColumns(10);
		fld_hastaPass.setBounds(145, 89, 269, 23);
		w_hastaLogin.add(fld_hastaPass);
		
		JButton btn_register = new JButton("Kay\u0131t Ol");
		btn_register.setBackground(Color.GRAY);
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HastaKayitGUI hGUI = new HastaKayitGUI();
				hGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setBounds(63, 156, 148, 43);
		w_hastaLogin.add(btn_register);
		
		JButton btn_hastaLogin = new JButton("Giri\u015F Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConnection con = new DBConnection();
				Connection c = con.connDb();
				String tc = fld_hastaTc.getText();
				String password = fld_hastaPass.getText();
				
				String sql_cumlesi = "SELECT * FROM user WHERE tcno='"+tc+"' AND password='"+password+"' AND type='hasta'";
				

				try {
					Statement st = c.createStatement();
					ResultSet rs = st.executeQuery(sql_cumlesi);
					boolean basarilimi = false;
					while(rs.next()) {
						basarilimi = true;
						Hasta h1 = new Hasta();
						h1.setName(rs.getString("name"));
						h1.setId(Integer.valueOf(rs.getString("id")));
						h1.setTcno(rs.getString("tcno"));
						h1.setPassword(rs.getString("password"));
						h1.setType("hasta");
						HastaGUI hGUI = new HastaGUI(h1);
						hGUI.setVisible(true);
						dispose();
						
					}
					 if(basarilimi == false) JOptionPane.showMessageDialog(null, "Hatalı Giriş!");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				fld_hastaTc.setText("");
				fld_hastaPass.setText("");
				
				
			
			}
		});
		btn_hastaLogin.setBackground(Color.GRAY);
		btn_hastaLogin.setBounds(278, 157, 136, 43);
		w_hastaLogin.add(btn_hastaLogin);
		
		JPanel w_doktorLogin = new JPanel();
		w_doktorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor G", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);
		
		JLabel lblTcNumaranz_1 = new JLabel("Tc numaran\u0131z: ");
		lblTcNumaranz_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblTcNumaranz_1.setBounds(39, 22, 93, 18);
		w_doktorLogin.add(lblTcNumaranz_1);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(142, 20, 269, 25);
		w_doktorLogin.add(fld_doctorTc);
		
		JLabel lblifre_1 = new JLabel("\u015Eifre:");
		lblifre_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblifre_1.setBounds(39, 76, 93, 18);
		w_doktorLogin.add(lblifre_1);
		
		JButton btn_doktorLogin = new JButton("Giri\u015F Yap");
		btn_doktorLogin.setBackground(Color.GRAY);
		btn_doktorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						
						while(rs.next()) {
							if(fld_doctorTc.getText().equals(rs.getString("tcno")) && fld_doctorPass.getText().equals(rs.getNString("password"))){
							
								switch(rs.getString("type")) {
								case "doktor":
									DoktorGUI dGUI = new DoktorGUI(rs.getInt("id"));
									dGUI.setVisible(true);
									dispose();
									
									break;
								case "bashekim":
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword(rs.getString("password"));
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
									break;
								default:
									System.out.println("Hastalar bu paneli kullanamaz.");
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_doktorLogin.setBounds(39, 143, 373, 36);
		w_doktorLogin.add(btn_doktorLogin);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(142, 77, 269, 25);
		w_doktorLogin.add(fld_doctorPass);
		
		JLabel lblNewLabel = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz.");
		lblNewLabel.setBounds(119, 48, 289, 21);
		w_pane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
	}
}
