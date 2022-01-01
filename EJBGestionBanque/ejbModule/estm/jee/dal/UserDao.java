package estm.jee.dal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
@Singleton(mappedName="EJB") 
@Local
public class UserDao {
	
	
	private  Statement stm;
	private ResultSet rs;
	Connection Cn = Dao.getCnx();
	

	public User login(String login,String password)
	{
		User user=new User();
		try {
			stm=Dao.getCnx().createStatement();
			
			String quer = "select * from user where UserName='"+login+"' and Password='"+password+"';";
			rs=stm.executeQuery(quer);
			
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
	
	public boolean editUser(int UserID,String UserName,String FullName, String Password) {
	 	int i = 0 ;
	 	if(UserName.trim() == "" || FullName == null || Password == null)
	 		return false;
			try {
				stm=Dao.getCnx().createStatement();
				String quer = "UPDATE user SET UserName = '" +UserName+"' , FullName = '"+FullName+"', Password = '"+Password+"'  WHERE UserID = '"+UserID+"';";
				i =stm.executeUpdate(quer);	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (i>0)? true:false;
	}
	

	
	public Compte getCompte(int code) {
		Compte Compte=new Compte();
		try {
			stm=Dao.getCnx().createStatement();
			
			String quer = "select * from compte where ComptID='"+code+"' ;";
			rs=stm.executeQuery(quer);
			
			if(rs.next()) {
				Compte.setCompteID(rs.getInt("ComptID"));
				Compte.setSold(rs.getDouble("Sold"));
				Compte.setAgentID(rs.getInt("AgentID"));
				return Compte;
			}
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		return null;
		}
		return Compte;
	 }
	
	

}
