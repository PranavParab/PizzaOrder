import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;

class JConnection
{
	public static Connection ConnecrDb()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/pizza","root","");
			System.out.println("Connected!");
			String query="insert into table1 values(?,?,?,?,?,?,?,?,?,?,?)";
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
	}
}