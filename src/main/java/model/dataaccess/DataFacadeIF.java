package model.dataaccess;

import java.util.Date;
import java.util.List;
import model.domain.entity.Booking;
import model.domain.entity.Kayak;
import model.domain.entity.User;
import model.domain.entity.exceptions.BookingNotPossibleException;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on May 2, 2017
 */
public interface DataFacadeIF {

    boolean authenticate(String username, String password);

    List<Booking> getAllBookings();

    List<Kayak> getAllKayaks();

    List<User> getAllUsers();

    Kayak getKayak(int id);

    User getUser(int id);

    User getUserFromName(String username);

    void makeBooking(Kayak kayak, User user, Date date) throws BookingNotPossibleException;

}
