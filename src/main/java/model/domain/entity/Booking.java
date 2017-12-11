package model.domain.entity;

import java.util.Date;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Nov 10, 2016 
 */
public class Booking {
    
    private Kayak kayak;
    private User user;
    private Date date;



    public Booking(Kayak kayak, User user, Date date) {
        this.kayak = kayak;
        this.user = user;
        this.date = date;
    }

    
    

    public Booking() {
    }

    public Kayak getKayak() {
        return kayak;
    }

    public void setKayak(Kayak kayak) {
        this.kayak = kayak;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
