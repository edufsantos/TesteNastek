/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectaBanco {
        //criando essa classe para a manipulação do  model.dao
        private static String DRIVER = "com.mysql.jdbc.Driver";
 	private static String URL = "jdbc:mysql://localhost:3306/nastek", USUARIO = "root", SENHA = "";
	private static Connection con;
	
	public static Connection  ConectaBanco(){

            try {
                Class.forName(DRIVER);
                return DriverManager.getConnection(URL,USUARIO,SENHA);
            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException("ERRO NA CONEXÂO",ex); 
            }
	}
        
        //fechando todas as conexões
        public static void EncerraBanco(Connection conexao){
            if(conexao!=null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }
	
        public static void EncerraBanco(Connection conexao, PreparedStatement pstm){
            EncerraBanco(conexao);
            
            
                try {
                    if(pstm!=null){
                        pstm.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        
        }
        public static void EncerraBanco(Connection conexao, PreparedStatement pstm, ResultSet rs){
                EncerraBanco(conexao, pstm);
                try {
                    if(rs!=null){
                        rs.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        
        }
        
}

