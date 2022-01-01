package estm.jee.dal;

public class Operation {

	private int OperationID;
	private String OperationType;
	private double Sold;
	private int CompteID;
	
	
	public int getCompteID() {
		return CompteID;
	}
	public void setCompteID(int compteID) {
		CompteID = compteID;
	}
	public int getOperationID() {
		return OperationID;
	}
	public void setOperationID(int operationID) {
		OperationID = operationID;
	}
	public String getOperationType() {
		return OperationType;
	}
	public void setOperationType(String operationType) {
		OperationType = operationType;
	}
	public double getSold() {
		return Sold;
	}
	public void setSold(double sold) {
		Sold = sold;
	}
	
	
}