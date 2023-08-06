/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOImplement.Implement_Tiket;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.koneksi;
import model.ModelTiket;

/**
 *
 * @author irsya
 */
public class DAOTiket implements Implement_Tiket{
    
    Connection connection;
    final String insert = "INSERT INTO film (nama,judul,jam,kursi) VALUES (?,?,?,?);";
    final String update = "UPDATE film set nama=?, judul=?, jam=?, kursi=? where id=? ;";
    final String delete = "DELETE FROM film where id=? ;";
    final String select = "SELECT * FROM film ;";
    final String carinama = "SELECT * FROM film where nama like ?";
    
    public DAOTiket(){
        connection = koneksi.connection();
    }
    
    @Override
    public void insert(ModelTiket data){
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert);
            statement.setString(1, data.getNama());
            statement.setString(2, data.getJudul());
            statement.setString(3, data.getJam());
            statement.setString(4, data.getKursi());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void update(ModelTiket data){
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, data.getNama());
            statement.setString(2, data.getJudul());
            statement.setString(3, data.getJam());
            statement.setString(4, data.getKursi());
            statement.setInt(5, data.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void delete(int id){
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public List<ModelTiket> getALL(){
        List<ModelTiket> lt = null;
        try{
            lt = new ArrayList<ModelTiket>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()){
                ModelTiket data = new ModelTiket();
                data.setId(rs.getInt("id"));
                data.setNama(rs.getString("nama"));
                data.setJudul(rs.getString("judul"));
                data.setJam(rs.getString("jam"));
                data.setKursi(rs.getString("kursi"));
                lt.add(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTiket.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return lt;
    }
    
    @Override
    public List<ModelTiket> getCariNama(String nama){
        List<ModelTiket> lt = null;
        try{
            lt = new ArrayList<ModelTiket>();
            PreparedStatement st = connection.prepareStatement(carinama);
            st.setString(1, "%" + nama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                ModelTiket data = new ModelTiket();
                data.setId(rs.getInt("id"));
                data.setNama(rs.getString("nama"));
                data.setJudul(rs.getString("judul"));
                data.setJam(rs.getString("jam"));
                data.setKursi(rs.getString("kursi"));
                lt.add(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTiket.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return lt;
    }
}
