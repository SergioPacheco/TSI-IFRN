package ifrn.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final Conexao instancia = new Conexao();

    public static Conexao getInstancia() {
        return instancia;
    }

    private Conexao() {
    }

    public Connection criaConexao() {
   	
    	String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    	String DATABASE_URL = "jdbc:mysql://localhost:3306/cinema";
        String USERNAME = "root";
    	String PASSWORD = "1234";
    	
    	Connection conn = null;
    	 
    	try {
            Class.forName(DATABASE_DRIVER);
            System.out.println("Driver registrado.");
            conn = DriverManager.getConnection(
                    DATABASE_URL, 
                    USERNAME, 
                    PASSWORD);
            System.out.println("Conexão efetuada.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco nao encontrado.");
        } catch (SQLException ex) {
            System.out.print("Erro ao obter conexao: " + ex.getMessage());
        }
	return conn;
    }
}

