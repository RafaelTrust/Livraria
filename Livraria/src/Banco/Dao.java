package Banco;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
	
	protected Connection con;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	
    private final String URL = "jdbc:mysql://127.0.0.1:3306/livraria";
	private final String USER = "root";
	private final String PASS = "";
	
	public static String status = "N�o conectou...";
	
	protected void open()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,USER,PASS);
		
		//testando a conex�o
	    if(con != null){
	    	status = ("Status --> conectado com sucesso!");
	    }else{
	    	status = ("Status--> n�o foi poss�vel realziar a conex�o!");
	    }
	}
	
	//Met�do que retorna o status da conex�o
	public static String statusConnection(){
		return status;
	}
	
	protected void close()throws Exception{
		con.close();
		System.out.println("Conex�o encerrada!");
	}

}

