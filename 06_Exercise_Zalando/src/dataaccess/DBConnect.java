package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnect
{
	Connection conn = null;
	PreparedStatement prepareStatement = null;
	ResultSet result = null;
	ArrayList<String> dbList = new ArrayList<String>();
	
	
	public void dbSearch(String searchWord) throws SQLException
	{
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/zalando?user=root&password=");
			prepareStatement = conn.prepareStatement("SELECT * FROM products WHERE category = '" + searchWord + "' OR "
																				+ "brand = '" + searchWord + "' OR "
																				+ "color = '" + searchWord + "' OR "
																				+ "size = '" + searchWord + "' OR "
																				+ "price = '" + searchWord + "' OR "
																				+ "product_description = '" + searchWord + "';");
			result = prepareStatement.executeQuery();
			
			while (result.next())
			{
				dbList.add(result.getString("category"));
				dbList.add(result.getString("brand"));
				dbList.add(result.getString("color"));
				dbList.add(result.getString("size"));
				dbList.add(result.getString("price"));
				dbList.add(result.getString("product_description"));
			}
		} 
		catch (SQLException e)
		{
			System.err.println(e);
		}
		
		finally
		{
			if (conn != null)
			{
				conn.close();
			}
		}
	}
	public String alToString() throws SQLException
	{
		String s = dbList.toString();
		
		return s; 
	}
}
