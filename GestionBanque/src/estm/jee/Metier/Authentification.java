package estm.jee.presentation;

import java.io.IOException;
import java.util.Locale;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import estm.jee.dal.Compte;
import estm.jee.dal.CompteDao;
import estm.jee.dal.User;
import estm.jee.dal.UserDao;

@ManagedBean(name="Auth")
@SessionScoped

public class Authentification {
	User U = new User();
	UserDao UD = new UserDao();
	CompteDao CD = new CompteDao();

	private String UserName;
	private String Password;
	
	
	private String Msg;
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
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
	
    String loggedIn = null;
	public String login() {
		if(UD.login(this.UserName,this.Password) != null){
			U = UD.login(this.UserName,this.Password);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute("loggedIn",U);
			loggedIn = (String)((User) session.getAttribute("loggedIn")).getTypeUser();
			Compte C = CD
					.getCompte(U.getCompteID());
			session.setAttribute("Compte",C);
			 
		} 
		if(U.getTypeUser() == null)
			this.setMsg("Login Information are not correct");
		
		return U.getTypeUser();
	}
	
	public void logout() throws IOException {   
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();  
	ec.invalidateSession(); 
	ec.redirect(ec.getRequestContextPath() + "/faces/Hello.xhtml");
	
	}
	public User getU() {
		return U;
	}
	public void setU(User u) {
		U = u;
	}
private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}


	
	private String Language ;
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	
	public void langCheck(String lang)
	{
		this.Language = lang;
	       locale = new Locale(Language);
	        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}
	
	public void langChanger(){
		if(this.Language!=null)
	        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(Language));
	}
  public String getLanguage2() {
	        return locale.getLanguage();
	    }
  
  
	public void isLoggedIn() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
	
		
	
		ConfigurableNavigationHandler nav
		   = (ConfigurableNavigationHandler)
				   facesContext.getApplication().getNavigationHandler();
		if(loggedIn == null) {
			

			nav.performNavigation("Hello");
		} else {
			if(loggedIn.equals("Client")) {
				nav.performNavigation("Home");
				
			}
	        if(loggedIn.equals("Operateur")) {
	        	nav.performNavigation("HomeOperateur");
	    		
			}
	        
	
		}
		
	}

}
