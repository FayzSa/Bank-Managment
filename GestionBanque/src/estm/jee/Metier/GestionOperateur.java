package estm.jee.presentation;

import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import estm.jee.dal.*;

@ManagedBean(name="OperGest")
@SessionScoped
public class GestionOperateur {

	
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	User U = (User) session.getAttribute("loggedIn");
	UserDao UD = new UserDao();
	OperateurDao ODao = new OperateurDao();
	ArrayList<User> Users = new ArrayList<User>();
	private String UserName = U.getUserName();
	private String Password = U.getPassword();
	private String FullName = U.getFullName();
	private int UID = U.getID();
	private int CompteID = U.getCompteID();
	CompteDao CD = new CompteDao();
	private double Montant;
	private Agent Agent = CD.getAgentOpera(UID);
	private int AgentID = Agent.getAgentID();
	private String AgentName = Agent.getAgentName();
	User Client = new User();
	ArrayList<Operation> Operations= new ArrayList<Operation>();
	Compte C = new Compte();
	private int ClientID;
	private String MsgError;
	private String MsgError2;
	
	public String getMsgError2() {
		return MsgError2;
	}
	public void setMsgError2(String msgError2) {
		MsgError2 = msgError2;
	}
	public String getMsgError() {
		return MsgError;
	}
	public void setMsgError(String msgError) {
		MsgError = msgError;
	}
	public Compte getC() {
		return C;
	}
	public void setC(Compte c) {
		C = c;
	}
	public ArrayList<Operation> getOperations() {
		return Operations;
	}
	public void setOperations(ArrayList<Operation> operations) {
		Operations = operations;
	}
	public User getU() {
		return U;
	}
	public void setU(User u) {
		U = u;
	}
	public ArrayList<User> getUsers() {
		return Users;
	}
	public void setUsers(ArrayList<User> users) {
		Users = users;
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
	public int getUID() {
		return UID;
	}
	public void setUID(int uID) {
		UID = uID;
	}
	public int getCompteID() {
		return CompteID;
	}
	public void setCompteID(int compteID) {
		CompteID = compteID;
	}
	public double getMontant() {
		return Montant;
	}
	public void setMontant(double montant) {
		Montant = montant;
	}
	public Agent getAgent() {
		return Agent;
	}
	public void setAgent(Agent agent) {
		Agent = agent;
	}
	public String getAgentName() {
		return AgentName;
	}
	public void setAgentName(String agentName) {
		AgentName = agentName;
	}
	public void setClient(User client) {
		Client = client;
	}
	public User getClient() 
	{
		return this.Client;
	}
	public boolean editClient(){
		boolean Test = false;
		this.MsgError="";
		if(Client.getFullName().trim() == "" || Client.getFullName().equals("") || Client.getFullName().equals(null)) {
			this.MsgError = "Information are not Correct";
			return false;
			}
		if(Client.getPassword().trim() == "" || Client.getPassword().equals("") || Client.getPassword().equals(null)) {
			this.MsgError = "Information are not Correct";
			return false;
			}
		if(Client.getUserName().trim() == "" || Client.getUserName().equals("") || Client.getUserName().equals(null)) {
			this.MsgError = "Information are not Correct";
			return false;
			}
		Test = UD.editUser(Client.getID(),Client.getUserName(),Client.getFullName(),Client.getPassword());	
		 if(!Test)
			 this.MsgError = "Information are not Correct";
		 else
			 this.MsgError = "";
		 return Test;
		}
	
	public void getAllCLients(){
		this.Users = ODao.getAllUsers(this.UID,AgentID);
	}
	
	
	public String getThisClient(){	
		Client = ODao.getUser(this.ClientID,this.UID);
		C = CD.getCompte(Client.getCompteID());
		Operations = CD.getOperations(Client.getCompteID());
		if(Client.getUserName() != null) {
			return "Exist";
		}
		return "Not";
	}
	public void crediterS(){
		this.MsgError2 = "";
		if(this.Montant == 0.0)
			this.MsgError2 = "Amount Can Not Be 0";
		else {
		CD.crediter(Client.getCompteID(),C.getSold(),this.Montant);
		C = CD.getCompte(Client.getCompteID());
		getAllOperas();
		this.MsgError2 = "";
		}
	}

	public void debiterS(){
		this.MsgError2 = "";
		if(this.Montant == 0.0)
			this.MsgError2 = "Amount Can Not Be 0";
		if(this.Montant > C.getSold())
			this.MsgError2 = "Invalid Operation ";
		CD.debiter(Client.getCompteID(),C.getSold(),this.Montant);
		C = CD.getCompte(Client.getCompteID());
		getAllOperas();
		this.MsgError2 = "";
	}
	public String getAllOperas(){
		this.Operations = CD.getOperations(Client.getCompteID());
		return "Operations";
	}
	public int getClientID() {
		return ClientID;
	}
	public void setClientID(int clientID) {
		ClientID = clientID;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
