package View;

import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JTable;

import Helper.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {
	
	static Bashekim bashekim = new Bashekim();

	private JPanel w_pane;
	private JTable table_doctor;
	private JTable table_clinic;
	private JTable table_eslesme;
	private DefaultTableModel doctorModel = null;
	private DefaultTableModel eslesmeModel = new DefaultTableModel();
	private DefaultTableModel clinicModel = null;
	
	

	private Object[] doctorData = null;
	private Object[] clinicData = null;
	private Object[] eslesmeData = null;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public BashekimGUI(Bashekim bashekim) {
		setTitle("Hastane Yönetim Sistemi - Başhekim Paneli");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın " + bashekim.getName());
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 52, 410, 22);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginGUI().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 10));
		btnNewButton.setBounds(594, 80, 89, 31);
		w_pane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 121, 716, 332);
		w_pane.add(tabbedPane);
		
		JTabbedPane tabbedPane2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 121, 716, 332);
		w_pane.add(tabbedPane);
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Şifre";
		
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		
		ArrayList<User> doktorlar = doktorlarigetir();
		
		
		for(int i=0; i<doktorlar.size();i++) {
			doctorData[0] = doktorlar.get(i).getId();
			doctorData[1] = doktorlar.get(i).getName();
			doctorData[2] = doktorlar.get(i).getTcno();
			doctorData[3] = doktorlar.get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Yönetimi", null, panel_1, null);
		tabbedPane.setEnabledAt(0, true);
		panel_1.setLayout(null);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 10, 535, 273);
		panel_1.add(w_scrollDoctor);
	
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(555, 10, 62, 20);
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(555, 40, 144, 20);
		panel_1.add(textField);
		
		JLabel lblNewLabel_2 = new JLabel("Tc no");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(555, 69, 45, 13);
		panel_1.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(555, 92, 144, 20);
		panel_1.add(textField_1);
		
		JLabel lblNewLabel_3 = new JLabel("Şifre");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(555, 120, 45, 13);
		panel_1.add(lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(555, 138, 144, 20);
		panel_1.add(passwordField);
		
		JButton btnNewButton_1 = new JButton("Ekle");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setBounds(555, 166, 144, 31);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("Kullanıcı ID");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(555, 209, 74, 13);
		panel_1.add(lblNewLabel_4);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		passwordField_1.setBackground(SystemColor.controlHighlight);
		passwordField_1.setBounds(555, 227, 144, 20);
		panel_1.add(passwordField_1);
		
		JButton btnNewButton_1_1 = new JButton("Sil");
		btnNewButton_1_1.setForeground(Color.BLACK);
		btnNewButton_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnNewButton_1_1.setBackground(Color.GRAY);
		btnNewButton_1_1.setBounds(555, 252, 144, 31);
		panel_1.add(btnNewButton_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Poliklinikler", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 233, 285);
		panel.add(scrollPane);
		
		clinicModel = new DefaultTableModel();
		Object[] colClinicName = new Object[2];
		colClinicName[0] = "ID";
		colClinicName[1] = "Klinik İsmi";
		
		clinicModel.setColumnIdentifiers(colClinicName);
		clinicData = new Object[2];
		clinicData = new Object[2];
		
		ArrayList<Clinic> clinicler = clinicgetir();
		
		
		for(int i=0; i<clinicler.size();i++) {
			clinicData[0] = clinicler.get(i).getId();
			clinicData[1] = clinicler.get(i).getName();
	
			clinicModel.addRow(clinicData);
		}
		
		
		table_clinic = new JTable(clinicModel);
		scrollPane.setViewportView(table_clinic);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(468, 10, 233, 285);
		panel.add(scrollPane_1);
		
		DefaultTableModel eslesme = null;
		
		DBConnection con = new DBConnection();
		Connection c = con.connDb();
		
		String sql_cumlesi = "SELECT * FROM eslesme";
		
		try {
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql_cumlesi);
			
			clinicModel = new DefaultTableModel();
			Object[] coleslesmeName = new Object[2];
			coleslesmeName[0] = "Doktor Adı";
			coleslesmeName[1] = "Klinik Adı";
			
			eslesmeModel.setColumnIdentifiers(coleslesmeName);
			eslesmeData = new Object[2];
			eslesmeData = new Object[2];
			
			while(rs.next()) {
				int doctor_id = rs.getInt("doctor_id");
				int clinic_id = rs.getInt("clinic_id");
				String sql_cumlesi1 = "SELECT * FROM user WHERE id="+doctor_id;
				String sql_cumlesi2 = "SELECT * FROM clinic WHERE id="+clinic_id;
				
				Statement st1 = c.createStatement();
				ResultSet rs1 = st1.executeQuery(sql_cumlesi1);
				
				Statement st2 = c.createStatement();
				ResultSet rs2 = st2.executeQuery(sql_cumlesi2);
				String doctor_adi = "";
				String clinic_adi = "";
				
				while(rs1.next()) {
					 doctor_adi = rs1.getString("name");
					
				}
				while(rs2.next()) {
					clinic_adi = rs2.getString("name");
					
				}
				
				eslesmeData[0] = doctor_adi;
				eslesmeData[1] = clinic_adi;
		
				eslesmeModel.addRow(eslesmeData);
				
				eslesme = eslesmeModel;
				
				
				
			
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}

		
		table_eslesme = new JTable(eslesme);
		scrollPane_1.setViewportView(table_eslesme);

		JLabel lblNewLabel_5 = new JLabel("Poliklinik Adı:");
		lblNewLabel_5.setBounds(253, 10, 85, 13);
		panel.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(253, 26, 205, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Ekle");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DBConnection con = new DBConnection();
				Connection connection = con.connDb();
				String clinic_adi = textField_2.getText();
				
				String sql_cumlesi = "INSERT INTO clinic (name) VALUES('"+clinic_adi+"')";
				
				Statement statement;
				try {
					statement = connection.createStatement();
					statement.executeUpdate(sql_cumlesi);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				BashekimGUI bGUI = new BashekimGUI(bashekim);
				bGUI.setVisible(true);
				dispose();
				

				
				
			}
		});
		btnNewButton_2.setBounds(253, 48, 205, 33);
		panel.add(btnNewButton_2);
		
		JComboBox comboBox = new JComboBox();
		ArrayList<User> veri = doktorlarigetir();
		for(int i = 0; i < veri.size(); i++) {
			comboBox.addItem(veri.get(i).getName());
		}
		comboBox.setBounds(253, 245, 205, 19);
		panel.add(comboBox);
		
		JButton btnNewButton_3 = new JButton("Poliklniğe Ekle");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DBConnection cn = new DBConnection();
				Connection c = cn.connDb();
				
				Object doctor = comboBox.getSelectedItem();
				String doctor_name = doctor.toString();
				int selected = table_clinic.getSelectedRow();
				String value = table_clinic.getModel().getValueAt(selected, 0).toString();
				int clinic_id = Integer.valueOf(value);
				String sql_cumle = "SELECT * FROM user WHERE name='"+doctor_name+"' AND type='doktor'"; 
				
				Statement st;
				try {
					st = c.createStatement();
					ResultSet rs = st.executeQuery(sql_cumle);
					int doctor_id = 0;
					while(rs.next()){
						doctor_id= rs.getInt("id");
					}
					
					String sql_cumle1 = "SELECT * FROM eslesme WHERE doctor_id='"+doctor_id+"' AND clinic_id='"+clinic_id+"'";
					String sql_cumle2 = "INSERT INTO eslesme (doctor_id, clinic_id) VALUES ('"+doctor_id+"','"+clinic_id+"')";
					
					Statement statement;
					
					
					try {
						statement = c.createStatement();
						
						ResultSet rs2 = statement.executeQuery(sql_cumle1);
						boolean varmi = false;
						while(rs2.next()) {
							varmi = true;
						}
						
						
						if(!varmi) {
							statement.executeUpdate(sql_cumle2);
											

					}} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println("Hata 2");
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Hata");
					
				}
				
				
				
				BashekimGUI bGUI = new BashekimGUI(bashekim);
				bGUI.setVisible(true);
				dispose();
				
				
				
			}
		});
		btnNewButton_3.setBounds(253, 267, 205, 28);
		panel.add(btnNewButton_3);
		
		
		
	}
	
	public ArrayList<User> doktorlarigetir() {
	
		DBConnection c1 =  new DBConnection();
		Connection conn = c1.connDb();
		ArrayList<User> arraylist = new ArrayList<>();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE type='doktor'");
			
			while(rs.next()) {
				User doktor = new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
				arraylist.add(doktor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arraylist;
		
	}
	public JLabel getLblNewLabel_4() {
		return getLblNewLabel_4();
	}
	
	public ArrayList<Clinic> clinicgetir() {
		
		DBConnection c1 =  new DBConnection();
		Connection conn = c1.connDb();
		ArrayList<Clinic> arraylist = new ArrayList<>();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM clinic");
			
			while(rs.next()) {
				Clinic clinic = new Clinic(rs.getInt("id"),rs.getString("name"));
				arraylist.add(clinic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arraylist;
		
	}
}
