package model.dataaccess;

import model.domain.entity.Booking;
import model.domain.entity.Kayak;
import model.domain.entity.User;
import model.domain.entity.exceptions.BookingNotPossibleException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Nov 10, 2016
 */
public class DataFacade implements DataFacadeIF {

    BookingMapper bm = new BookingMapper();
    KayakMapper km = new KayakMapper();
    UserMapper um = new UserMapper();
    
    public static void main(String[] args) {
        
        DataFacade df = new DataFacade();
        System.out.println("testing getAllBookings()");
        for(Booking booking : df.getAllBookings()) {
            System.out.println("booking date: "+booking.getDate()+" of kayak: "+booking.getKayak().getName());
        }
        System.out.println("testing makeBooking()");
        System.out.println("user: "+df.getUser(1).getId());
        try {
            df.makeBooking(df.getKayak(1), df.getUser(1), new Date());
        } catch (BookingNotPossibleException ex) {
            ex.printStackTrace();
        }
        System.out.println("testing getAllKayaks()");
        for (Kayak kayak : df.getAllKayaks()) {
            System.out.println("kayak: "+kayak.getName());
        }
        System.out.println("testing getKayak() med id: 1");
        System.out.println(df.getKayak(1).getName());
        
        System.out.println("testing getAllUsers()");
        for (User user : df.getAllUsers()) {
            System.out.println(user.getName());
        }
        System.out.println("testing getUser() with id: 1");
        System.out.println(df.getUser(1));
    }
    @Override
    public List<Booking> getAllBookings(){
        return bm.getAllBookings();
    }
    @Override
    public void makeBooking(Kayak kayak, User user, Date date) throws BookingNotPossibleException{
        bm.makeBooking(kayak, user, date);
    }
    @Override
    public List<Kayak> getAllKayaks(){
        return km.getAllKayaks();
    }
    @Override
    public Kayak getKayak(int id){
        return km.getKayak(id);
    }
    @Override
    public List<User> getAllUsers(){
        return um.getAllUsers();
    }
    @Override
    public User getUser(int id){
       return um.getUser(id);
    }
    @Override
    public boolean authenticate(String username, String password){
        return um.authenticate(username, password);
    }
    @Override
    public User getUserFromName(String username){
        return um.getUserByName(username);
    }
}
