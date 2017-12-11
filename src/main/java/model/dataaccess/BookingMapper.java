package model.dataaccess;

import model.domain.entity.Booking;
import model.domain.entity.Kayak;
import model.domain.entity.User;
import model.domain.entity.exceptions.BookingNotPossibleException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Nov 10, 2016 
 */
public class BookingMapper {
    
    private static UserMapper um = new UserMapper();
    private static KayakMapper km = new KayakMapper();
    
//    public Booking getBooking(int id){}
    
    public List<Booking> getAllBookings(){
        List<Booking> bookings = new ArrayList();
        try {
            String sql = "select userid, kayakid, bookingdate from booking";
            PreparedStatement pstmt = DB.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                
                int userid = rs.getInt("userid");
                int kayakid = rs.getInt("kayakid");
                Date date = new Date(rs.getDate("bookingdate").getTime()); //convert from java.sql.date to java.util.date
                Booking booking = new Booking(km.getKayak(kayakid),um.getUser(userid), date);
                bookings.add(booking);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookings;
    }
//    
//    public List<Booking> getBookingsFromDate(Date date){
//        
//    }
//    
    public List<Booking> getBookingsFromUser(int userid){
                List<Booking> bookings = new ArrayList();
        try {
            String sql = "SELECT userid, kayakid, bookingdate FROM booking WHERE userid = ?";
            PreparedStatement pstmt = DB.getConnection().prepareStatement(sql);
            pstmt.setInt(1, userid);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){

                int kayakid = rs.getInt("kayakid");
                Date date = new Date(rs.getDate("bookingdate").getTime()); //convert from java.sql.date to java.util.date
                Booking booking = new Booking(km.getKayak(kayakid),um.getUser(userid), date);
                bookings.add(booking);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookings;
    }
//    
    public void makeBooking(Kayak kayak, User user, Date date) throws BookingNotPossibleException{
        Booking booking = null;
        if(!isBooked(kayak, date)){ 
            try {
                String sql = "INSERT INTO booking(userid, kayakid, bookingdate) VALUES (?, ?, ?)";
                PreparedStatement pstmt = DB.getConnection().prepareStatement(sql);
                pstmt.setInt(1, user.getId());
                pstmt.setInt(2, kayak.getId());
                pstmt.setDate(3, new java.sql.Date(date.getTime()));
                int result = pstmt.executeUpdate();
            } catch (SQLException ex) {
                throw new BookingNotPossibleException(ex.getMessage());
            }

        } else {
            throw new BookingNotPossibleException();
        }
    }
//    
    public boolean isBooked(Kayak kayak, Date date){
        
        try {
            String sql = "SELECT userid, kayakid, bookingdate FROM booking WHERE kayakid = ? AND bookingdate = ?";
            PreparedStatement pstmt = DB.getConnection().prepareStatement(sql);
            pstmt.setInt(1, kayak.getId());
            pstmt.setDate(2, new java.sql.Date(date.getTime()));
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        BookingMapper bm = new BookingMapper();      
        Kayak kayak = km.getKayak(1);
        User user = um.getUser(1);
        
        try {
        System.out.println("kayak:"+kayak.getName()+" and user: "+user.getName());
        
        Date bookingDate = new SimpleDateFormat("dd-MM-yyyy").parse("25-12-2016");
            bm.makeBooking(kayak, user, bookingDate);
        } catch (BookingNotPossibleException | ParseException ex) {
            System.out.println(ex.getMessage());
        }
        
        List<Booking> bookings = bm.getBookingsFromUser(2);
        for (Booking booking : bookings) {
            System.out.println("booking: "+booking.getDate()+" of: "+booking.getKayak().getName()+" by: "+booking.getUser().getName());
        }
    }
}
