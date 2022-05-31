package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;
import Model.Doktor;
import Model.Hasta;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DoktorGUI extends JFrame {
	

	private JPanel contentPane;
	private JTextField textField;

	private static Doktor doktor = new Doktor();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoktorGUI frame = new DoktorGUI(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public DoktorGUI(int doktorid) {
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 65, 644, 338);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Çalışma Saatleri", null, panel, null);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 29, 143, 31);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tarih Saat Giriniz:");
		lblNewLabel_1.setBounds(10, 10, 96, 13);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Ekle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConnection con = new DBConnection();
				Connection c  = con.connDb();
				
				String date= textField.getText();
				
				
				String sql_cumle = "INSERT INTO worker (doctor_id,date,status) VALUES('"+doktorid+"','"+date+"',0)";
				
				Statement st;
				try {
					st = c.createStatement();
					st.executeUpdate(sql_cumle);
					textField.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(10, 64, 143, 21);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(182, 10, 244, 291);
		panel.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Hoş Geldiniz Sayın " + doktor.getName());
		
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 26, 208, 16);
		contentPane.add(lblNewLabel);
		
		
		
		
		
		
	}
}
