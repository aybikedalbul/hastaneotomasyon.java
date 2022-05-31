package Helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.User;

public class Helper {
	
	public static void showMsg(String str){
		String msg;
		
		switch(str) {
		case "fill":
			msg = "Lütfen tüm alanları doldurunuz.";
			break;
			default:
				msg = str;
		}
		
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}
	public boolean kullanici_kontrol(String tcno) {
		DBConnection conn = new DBConnection();
		Connection c = conn.connDb();
		
		String sql_cumle = "SELECT * FROM user WHERE tcno='"+tcno+"'";

			
			try {
				Statement st = c.createStatement();
				ResultSet rs = st.executeQuery(sql_cumle);
				
				return rs.next();
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return true;
			}
		
	}

}
