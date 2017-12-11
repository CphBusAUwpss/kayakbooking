package model.dataaccess;

import model.domain.entity.Kayak;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Nov 10, 2016 
 */

public class KayakMapper {
    public Kayak getKayak(int id){
        Kayak kayak = null;
        try {
            String sql = "SELECT id, name, model, description, year, color, length, image from kayak WHERE id = ?";
            PreparedStatement pstmt = DB.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int kayakid = rs.getInt("id");
                String name = rs.getString("name");
                String model = rs.getString("model");
                String desc = rs.getString("description");
                int year = rs.getInt("year");
                String color = rs.getString("color");
                double length = rs.getDouble("length");
                InputStream in = rs.getBinaryStream("image");
                
                kayak = new Kayak(kayakid, name, model, desc, color, length, in);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return kayak;
    }
    
    public List<Kayak> getAllKayaks(){
        List<Kayak> kayaks = new ArrayList();
        try {
            String sql = "SELECT id, name, model, description, year, color, length, image FROM kayak";
            PreparedStatement pstmt = DB.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int kayakid = rs.getInt("id");
                String name = rs.getString("name");
                String model = rs.getString("model");
                String desc = rs.getString("description");
                int year = rs.getInt("year");
                String color = rs.getString("color");
                double length = rs.getDouble("length");
                InputStream in = rs.getBinaryStream("image");
//                Kayak kayak = new Kayak(kayakid, name, model, desc, color, length, in);
                Kayak kayak = new Kayak(kayakid, name, model, desc, color, length, null);
                kayaks.add(kayak);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return kayaks;
    }
    public void createKayak(Kayak kayak){
        try {
            String sql = "INSERT INTO kayak (name, model, description, color, length) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = DB.getConnection().prepareStatement(sql);
            pstmt.setString(1, kayak.getName());
            pstmt.setString(2, kayak.getModel());
            pstmt.setString(3, kayak.getDescription());
            pstmt.setString(4, kayak.getColor());
            pstmt.setDouble(5, kayak.getLength());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
//    private void inserImg2(Kayak kayak, String pathname){
//    String sql = "UPDATE kayak SET image = "+pathname+" WHERE id = "+kayak.getId();
//        try {
//            Statement stmt = DB.getConnection().createStatement();
//            stmt.execute(sql);
//            
//            
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
    private void insertImg(String pathname, Kayak kayak){
        String sql = "UPDATE kayak SET image = ? WHERE id = ?";
        try {
            PreparedStatement pstmt = DB.getConnection().prepareStatement(sql);
            InputStream in = new FileInputStream(pathname);
            pstmt.setBlob(1, in);
            pstmt.setInt(2, kayak.getId());
            if(pstmt.executeUpdate()==0)
                throw new IOException("Image was not persisted in db");
            in.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        KayakMapper km = new KayakMapper();
//        String path = "C:\\Users\\tha\\GitRepos\\AU\\WPSS\\kayakapp\\src\\main\\webapp\\img\\";
//        String[] images = {"k1.jpg", "k2.jpg", "k3.jpg", "k4.jpg", "k5.jpg", "k6.jpg", "k7.jpg"};
//        for (int i = 0; i < km.getAllKayaks().size(); i++) {
//            System.out.println(images[i]);
//            km.insertImg(path+images[i], km.getKayak(i+1));
//        }
        km.createKayak(new Kayak("Hansen", "Solo", "Low", "red", 4.5, null));
    }
}
