/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author eduardo
 */
public class conexao2 {
        //cirando essa classe para a manipulação do banco diretamente nos jframe
	private String url, usuario, senha;
	private Connection con;
	
	public conexao2(){
		url = "jdbc:mysql://localhost:3306/nastek";
		usuario = "root";
		senha = "";
		try {	
                    con = DriverManager.getConnection(url, usuario, senha);	
                    System.out.println("Conexão realizada com sucesso!");
		} catch (Exception e) {
                    e.printStackTrace();
		}
	}
        
        public void executaSql(String sql){
            try{
                Statement s = con.createStatement();
                s.executeUpdate(sql);			
            }
            catch(Exception e){
                 e.printStackTrace();
            }		
	}
        
        public ResultSet buscaDados(String sql){ 
            try{
                    Statement s = con.createStatement();
                    ResultSet rs = s.executeQuery(sql);
                    return rs;
            }
            catch(Exception e){
                    e.printStackTrace();
                    return null;
            }
	}
        
        public void finalizaConexao(){
           try{
               con.close();
           }
           catch(Exception e){
               e.printStackTrace();
           }
       }
}



