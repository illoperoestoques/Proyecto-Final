package Utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "conexion")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatosConexion {
	private String server;
	private String database;
	private String username;
	private String password;
	
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "DatosConexion [server=" + server + ", database=" + database + ", username=" + username + ", password="
				+ password + "]";
	}
	public DatosConexion(String server, String database, String username, String password) {
		super();
		this.server = server;
		this.database = database;
		this.username = username;
		this.password = password;
	}
	public DatosConexion() {
		super();
		this.server = "";
		this.database = "";
		this.username = "";
		this.password = "";
	}
	
	
	
}
