package estm.jee.metiers;
import javax.ejb.Remote;

@Remote

public interface IHello {
public String sayHello();	
public String UserName(int id);


}
