package Model;
import java.awt.Component;
import java.sql.*;
import java.util.ArrayList;

import Helper.DBConnection;
public class Clinic {
	private int id;
	private String name;

	
	
	public Clinic(){}
	
	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Clinic> poliklinikgetir() {
		
		DBConnection c1 =  new DBConnection();
		Connection conn = c1.connDb();
		ArrayList<Clinic> arraylist = new ArrayList<>();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM clinic");
			
			while(rs.next()) {
				Clinic poliklinik = new Clinic(rs.getInt("id"),rs.getString("name"));
				arraylist.add(poliklinik);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arraylist;
		
	}

	
}
