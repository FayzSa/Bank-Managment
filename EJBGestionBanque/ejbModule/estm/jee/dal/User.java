package estm.jee.dal;

public class User {
	private int ID;
	private String UserName;
	private String FullName;
	private String Password;
    public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	private String TypeUser;
    private int CompteID;
    
	public int getCompteID() {
		return CompteID;
	}
	public void setCompteID(int compteID) {
		CompteID = compteID;
	}
	public String getTypeUser() {
		return TypeUser;
	}
	public void setTypeUser(String typeUser) {
		TypeUser = typeUser;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
}
