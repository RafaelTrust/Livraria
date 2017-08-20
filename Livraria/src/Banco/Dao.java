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
	
	public static String status = "Não conectou...";
	
	protected void open()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL,USER,PASS);
		
		//testando a conexão
	    if(con != null){
	    	status = ("Status --> conectado com sucesso!");
	    }else{
	    	status = ("Status--> não foi possível realziar a conexão!");
	    }
	}
	
	//Metódo que retorna o status da conexão
	public static String statusConnection(){
		return status;
	}
	
	protected void close()throws Exception{
		con.close();
		System.out.println("Conexão encerrada!");
	}

}

