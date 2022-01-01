package estm.jee.dal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OperateurDao extends UserDao{
	

	private  Statement stm;
	private ResultSet rs;
	Connection Cn = Dao.getCnx();
	
	public OperateurDao(){
		super();
	}
	
	public ArrayList<User> getAllUsers(int UserID,int AgentID){
		ArrayList<User> UserArr = new ArrayList<>();
	//	String Req = "SELECT * FROM user WHERE CompteID IN (SELECT ComptID FROM compte WHERE Operateur = '"+UserID+"')";
		String Requ= "SELECT * FROM user WHERE CompteID IN ( SELECT ComptID FROM compte WHERE AgentID = '"+AgentID+"' AND Operateur = '"+UserID+"')";
		try {
			stm=Dao.getCnx().createStatement();
			
			rs=stm.executeQuery(Requ);
			
			while(rs.next()) {
				User user = new User();
				user.setID(rs.getInt("UserID"));
				user.setFullName(rs.getString("FullName"));
				user.setTypeUser(rs.getString("UserType"));
				user.setUserName(rs.getString("UserName"));
				user.setCompteID(rs.getInt("CompteID"));
				UserArr.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		return null;
		}
		return UserArr;
		
	}
	
	
	
	public User getUser(int UserID,int Operateur){
		User user = new User();
		String Requ= "SELECT * FROM user WHERE UserType LIKE 'Client' AND UserID='"+UserID+"' AND CompteID IN(SELECT ComptID FROM compte WHERE Operateur = '"+Operateur+"') ;";
		try {
			stm=Dao.getCnx().createStatement();
			
			rs=stm.executeQuery(Requ);
			if(rs.next()) {
				
				user.setID(rs.getInt("UserID"));
				user.setFullName(rs.getString("FullName"));
				user.setTypeUser(rs.getString("UserType"));
				user.setUserName(rs.getString("UserName"));
				user.setCompteID(rs.getInt("CompteID"));
				user.setPassword(rs.getString("Password"));
				
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		return null;
		}
		return user;
		
	}
	
	
			
	

}
