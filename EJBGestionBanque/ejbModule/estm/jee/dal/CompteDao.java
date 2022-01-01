package estm.jee.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Singleton;
@Singleton(mappedName="EJB") 
@Local
public class CompteDao {
	Connection conn = Dao.getCnx();
	private Statement st;
	private ResultSet rs;
	public Compte getCompte(int code) {
		Compte Compte=new Compte();
		try {
			st=Dao.getCnx().createStatement();
			
			String quer = "select * from compte where ComptID='"+code+"' ;";
			rs=st.executeQuery(quer);
			
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
	public boolean updateSolde(int code,double solde) {
		int i = 0 ;
		try {
			st=Dao.getCnx().createStatement();
			String quer = "UPDATE compte SET Sold  = '" +solde+"'  WHERE ComptID  = '"+code+"';";
			i =st.executeUpdate(quer);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (i>0)? true:false;
		
		
	
	}
	
	
public Agent getAgentOpera(int Operateur){
		
		Agent Agent=new Agent();
		try {
			st=Dao.getCnx().createStatement();
			String Req= "SELECT * FROM agent WHERE AgentID IN (SELECT AgentID FROM compte WHERE operateur = '"+Operateur+"')";		
			rs=st.executeQuery(Req);
			
			if(rs.next()) {
				Agent.setAgentID(rs.getInt("AgentID"));
				Agent.setAgentName(rs.getString("AgentName"));
				return Agent;
			}
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		return null;
		}
		return Agent;
	
	}
	
	
	
	public Agent getAgent(int AgentID){
		
		Agent Agent=new Agent();
		try {
			st=Dao.getCnx().createStatement();
			
			String quer = "SELECT * FROM agent WHERE AgentID IN (SELECT AgentID FROM compte WHERE AgentID = '"+AgentID+"') ; ";
				
			rs=st.executeQuery(quer);
			
			if(rs.next()) {
				Agent.setAgentID(rs.getInt("AgentID"));
				Agent.setAgentName(rs.getString("AgentName"));
				return Agent;
			}
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		return null;
		}
		return Agent;
	
	}
	
	
	
	public boolean debiter(int code,double OldSold,double solde) {
		double soldedb  = OldSold;
		boolean T = false;
		if(soldedb>solde && solde>0 ) {
			T = updateSolde(code, soldedb-solde);
			if(T) {
			// Operation 
				Operation Op = new Operation();
				Op.setSold(solde);
				Op.setOperationType("Debiter");
				Op.setCompteID(code);
				insertOperation(Op);
			
			}}	
	return T;
	}

	
	
	public boolean crediter(int code,double OldSold,double montant) {
		double soldedb = OldSold;
		boolean T = false;
		if( montant>0) {
			T = updateSolde(code, soldedb+montant);
			if(T) {
				// Operation 
					Operation Op = new Operation();
					Op.setSold(montant);
					Op.setOperationType("Crediter");
					Op.setCompteID(code);
					insertOperation(Op);
				
				}
		 }
		return T;
	}
	public ArrayList<Operation> getOperations(int compteid){
		ResultSet Rs ;
		ArrayList<Operation> Opers = new ArrayList<Operation>();
		try {
			st = Dao.getCnx().createStatement();
			Rs = st.executeQuery("SELECT * FROM operation WHERE ComptID = '"+compteid+"';");
			while(Rs.next()){
				Operation Op = new Operation();
				Op.setSold(Rs.getDouble("Sold"));
				Op.setOperationType(Rs.getString("TypeOperation"));
				Op.setCompteID(Rs.getInt("ComptID"));
				Op.setOperationID(Rs.getInt("OperationID"));
				Opers.add(Op);
			}
			return Opers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	private boolean insertOperation(Operation oper) {
		int i = 0 ;
		try {
			st=Dao.getCnx().createStatement();
			String quer = "INSERT INTO operation(TypeOperation,Sold,ComptID) VALUES('"+oper.getOperationType()+"','"+ oper.getSold()+"','"+oper.getCompteID()+"')";
			i =st.executeUpdate(quer);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (i>0)? true:false;
	}
	
}
