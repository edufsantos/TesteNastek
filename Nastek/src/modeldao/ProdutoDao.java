
package modeldao;

import connection.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Produto;

/**
 *
 * @author eduardo
 */
public class ProdutoDao {
    public void create( Produto prod){
        //criando a conexao
        Connection con = ConectaBanco.ConectaBanco();
        
        PreparedStatement pstm = null;
       
        try {
            pstm = con.prepareStatement("insert into produto (nome,quantidade,fk_categoria,valor) values (?,?,?,?)");
            pstm.setString(1, prod.getNome());
            pstm.setInt(2, prod.getQtd());
            pstm.setInt(3, prod.getFk_cat());
            pstm.setDouble(4, prod.getValor());
            
            pstm.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo com sucesso");
        } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"Erro ao salvarno banco");
        }finally{
            ConectaBanco.EncerraBanco(con, pstm);
        }
    }
    
    
    //esse metodo espera o prod da view
    public void update( Produto prod){
        //criando a conexao 
        Connection con = ConectaBanco.ConectaBanco();
        
        PreparedStatement pstm = null;
        //execultando sql
        try {
            pstm = con.prepareStatement("UPDATE produto SET nome = ? ,quantidade = ?,"
                    + " fk_categoria = ?, valor = ? where id_produto = ?");
            pstm.setString(1, prod.getNome());
            pstm.setInt(2, prod.getQtd());
            pstm.setInt(3, prod.getFk_cat());
            pstm.setDouble(4, prod.getValor());
            pstm.setInt(5, prod.getId());
            pstm.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
        } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"Erro ao Atualizar banco");
        }finally{
            //fechando todas as conexoes abertas 
            ConectaBanco.EncerraBanco(con, pstm);
        }
    }
    
    //criando um metodo list 
    public List<Produto> lista() throws SQLException{
        
        Connection con = ConectaBanco.ConectaBanco();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        List<Produto> listaprodutos = new ArrayList<>(); 
        try {
            //execultando sql
            pstm = con.prepareStatement("Select * from produto order by nome asc ");
            rs = pstm.executeQuery();
            
            //laço de repetição e adicionando em nosso objeto
            while(rs.next()){
                Produto listprod = new Produto();
                listprod.setId(rs.getInt("id_produto"));
                listprod.setNome(rs.getString("nome"));
                listprod.setQtd(rs.getInt("quantidade"));
                listprod.setFk_cat(rs.getInt("fk_categoria"));
                listprod.setValor(rs.getDouble("valor"));
                
                listaprodutos.add(listprod);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConectaBanco.EncerraBanco(con, pstm, rs);
        }
        return listaprodutos;
    }
    public List<Produto> listaquant() throws SQLException{
        
        Connection con = ConectaBanco.ConectaBanco();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        List<Produto> listaprodutos = new ArrayList<>(); 
        try {
            pstm = con.prepareStatement("Select * from produto order by quantidade desc ");
            rs = pstm.executeQuery();
            
            //laço de repetição e adicionando em nosso objeto
            while(rs.next()){
                Produto listprod = new Produto();
                listprod.setId(rs.getInt("id_produto"));
                listprod.setNome(rs.getString("nome"));
                listprod.setQtd(rs.getInt("quantidade"));
                listprod.setFk_cat(rs.getInt("fk_categoria"));
                listprod.setValor(rs.getDouble("valor"));
                
                listaprodutos.add(listprod);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConectaBanco.EncerraBanco(con, pstm, rs);
        }
        return listaprodutos;
    }
    public List<Produto> listaquantmn() throws SQLException{
        
        Connection con = ConectaBanco.ConectaBanco();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        List<Produto> listaprodutos = new ArrayList<>(); 
        
        try {
            //cirando um sql em que busca todos os campos da tabrla onde as quantidades sejam menores e manores 
            pstm = con.prepareStatement("SELECT * FROM `produto`as `a`, (SELECT MIN(quantidade) as mini, MAX(quantidade)as maxi from `produto` )AS m "
                    + "WHERE m.maxi = a.quantidade or m.mini = a.quantidade "
                    + "GROUP BY quantidade");
            rs = pstm.executeQuery();
            
            //laço de repetição e adicionando em nosso objeto
            while(rs.next()){
                Produto listprod = new Produto();
                listprod.setId(rs.getInt("id_produto"));
                listprod.setNome(rs.getString("nome"));
                listprod.setQtd(rs.getInt("quantidade"));
                listprod.setFk_cat(rs.getInt("fk_categoria"));
                listprod.setValor(rs.getDouble("valor"));
                
                listaprodutos.add(listprod);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConectaBanco.EncerraBanco(con, pstm, rs);
        }
        return listaprodutos;
    }
    public List<Produto> listaporcat(Integer idcat) throws SQLException{
        Connection con = ConectaBanco.ConectaBanco();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        List<Produto> listaprodutos = new ArrayList<>(); 
        try {
            pstm = con.prepareStatement("Select * from produto where fk_categoria like ?");
            pstm.setInt(1, idcat);
            
            rs = pstm.executeQuery();
            
            //laço de repetição e adicionando em nosso objeto
            while(rs.next()){
                Produto listprod = new Produto();
                listprod.setId(rs.getInt("id_produto"));
                listprod.setNome(rs.getString("nome"));
                listprod.setQtd(rs.getInt("quantidade"));
                listprod.setFk_cat(rs.getInt("fk_categoria"));
                listprod.setValor(rs.getDouble("valor"));
                
                listaprodutos.add(listprod);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConectaBanco.EncerraBanco(con, pstm, rs);
        }
        return listaprodutos;
    }
}

