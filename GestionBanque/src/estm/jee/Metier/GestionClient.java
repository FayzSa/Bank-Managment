package estm.jee.presentation;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import estm.jee.dal.Compte;
import estm.jee.dal.CompteDao;
import estm.jee.dal.Operation;
import estm.jee.dal.User;
import estm.jee.dal.*;
import javax.servlet.http.HttpSession;

@ManagedBean(name="ClGest")
@SessionScoped
public class GestionClient {
	
	
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	User U = (User) session.getAttribute("loggedIn");
	private String MsgError = null;
	public String getMsgError() {
		return MsgError;
	}
	public void setMsgError(String msgError) {
		MsgError = msgError;
	}


	ArrayList<Operation> Op = new ArrayList<Operation>();
	
	UserDao UD = new UserDao();
	

	
	
	private String UserName = U.getUserName();
	private String Password = U.getPassword();
	private String FullName = U.getFullName();
	private int UID = U.getID();
	
	public int getUID() {
		return UID;
	}
	public void setUID(int uID) {
		UID = uID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public ArrayList<Operation> getOp() {
		return Op;
	}
	public void setOp(ArrayList<Operation> op) {
		Op = op;
	}
	CompteDao CD = new CompteDao();
	private double Montant;
	
	
	private int CompteID = U.getCompteID();
	
	public double getMontant() {
		return Montant;
	}
	public void setMontant(double montant) {
		Montant = montant;
	}
	Compte C  = (Compte) session.getAttribute("Compte");
	
	private Agent Agent = CD.getAgent(C.getCompteID());
	
	private String AgentName = Agent.getAgentName();
	
	public String getAgentName() {
		return AgentName;
	}
	public void setAgentName(String agentName) {
		AgentName = agentName;
	}
	public Agent getAgent() {
		return Agent;
	}
	public void setAgent(Agent agent) {
		Agent = agent;
	}
	public int getUserID() {
		return CompteID;
	}
	public void setUserID(int userID) {
		CompteID = userID;
	}
	public User getU() {
		return U;
	}
	public void setU(User u) {
		U = u;
	}
	public UserDao getUD() {
		return UD;
	}
	public void setUD(UserDao uD) {
		UD = uD;
	}
	public CompteDao getCD() {
		return CD;
	}
	public void setCD(CompteDao cD) {
		CD = cD;
	}
	public Compte getC() {
		return C;
	}
	public void setC(Compte c) {
		C = c;
	}
	
	
	public void crediterS(){
		this.MsgError = "";
		if(this.Montant == 0.0) {
			this.MsgError = "Amount Can Not Be 0";
		}
		else {
		CD.crediter(this.CompteID,C.getSold(),this.Montant);
		C = CD.getCompte(this.CompteID);
		this.MsgError = null;
		}
	}

	public void debiterS(){
		this.MsgError = "";
		if(this.Montant == 0.0)
			this.MsgError = "Amount Can Not Be 0";
		if(this.Montant > C.getSold())
			this.MsgError = "Invalid Operation ";
		
		else {
		CD.debiter(this.CompteID,C.getSold(),this.Montant);
		C = CD.getCompte(this.CompteID);
		this.MsgError = null;}
	}
	public String getAllOperas(){
		this.Op = CD.getOperations(this.CompteID);
		return "Operations";
	}
	
	public boolean editProfile(){
		this.MsgError="";
		if(this.FullName.trim() == "" || this.FullName.equals("") || this.FullName.equals(null)) {
			this.MsgError = "Information are not Correct";
			return false;
			}
		if(this.Password.trim() == "" || this.Password.equals("") || this.Password.equals(null)) {
			this.MsgError = "Information are not Correct";
			return false;
			}
		if(this.UserName.trim() == "" || this.UserName.equals("") || this.UserName.equals(null)) {
			this.MsgError = "Information are not Correct";
			return false;
			}
		 boolean Test = UD.editUser(this.UID, this.UserName, this.FullName, this.Password);
		 if(!Test)
			 this.MsgError = "Information are not Correct";
		 else
			 this.MsgError = "";
		 return Test;
		}
	

	
}
