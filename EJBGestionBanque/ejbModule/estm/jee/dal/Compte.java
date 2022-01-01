package estm.jee.dal;

public class Compte {

	private int CompteID;
	private Double Sold;
	private int AgentID;
	
	
	public int getAgentID() {
		return AgentID;
	}
	public void setAgentID(int agentID) {
		AgentID = agentID;
	}
	public int getCompteID() {
		return CompteID;
	}
	public void setCompteID(int compteID) {
		CompteID = compteID;
	}
	public Double getSold() {
		return Sold;
	}
	public void setSold(Double sold) {
		Sold = sold;
	}
	
}
