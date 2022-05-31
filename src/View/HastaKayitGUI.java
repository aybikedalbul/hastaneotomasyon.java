package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class HastaKayitGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaKayitGUI frame = new HastaKayitGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public HastaKayitGUI() {
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ad Soyad:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(75, 104, 75, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblTcNo = new JLabel("Tc No:");
		lblTcNo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblTcNo.setBounds(75, 171, 75, 22);
		contentPane.add(lblTcNo);
		
		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblifre.setBounds(75, 248, 75, 22);
		contentPane.add(lblifre);
		
		textField = new JTextField();
		textField.setBounds(195, 104, 300, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(195, 171, 300, 30);
		contentPane.add(textField_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(195, 248, 300, 30);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Kayıt Ol");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConnection con = new DBConnection();
				Connection c = con.connDb();
				String name = textField.getText();
				String tcno = textField_1.getText();
				String password = passwordField.getText();
				
				String sql_cumlesi = "INSERT INTO user (name,tcno,password,type) VALUES ('"+name+"','"+tcno+"','"+password+"','hasta')";
				
				try {
					Statement st = c.createStatement();
					st.executeUpdate(sql_cumlesi);
				}catch(SQLException b) {
					System.out.println(b);
				}
				
				textField.setText("");
				textField_1.setText("");
				passwordField.setText("");

				
				LoginGUI lGUI = new LoginGUI();
				lGUI.setVisible(true);
				dispose();			
				
				
			}
		});
		btnNewButton.setBounds(366, 327, 129, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Kayıt olmak için aşağıdaki boşlukları doldurun!");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(75, 65, 318, 13);
		contentPane.add(lblNewLabel_1);
	}
}
