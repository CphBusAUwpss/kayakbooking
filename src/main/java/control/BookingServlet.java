package control;

import model.domain.entity.Booking;
import model.domain.entity.User;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dataaccess.BookingMapper;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Nov 30, 2016
 */
@WebServlet(name="BookingServlet", urlPatterns={"/BookingServlet"})
public class BookingServlet extends HttpServlet {
   private BookingMapper bm = new BookingMapper();
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            List<Booking> bookings = bm.getBookingsFromUser(user.getId());
            request.getSession().setAttribute("bookings", bookings);
        } else {
            List<Booking> bookings = bm.getAllBookings();
            sortByDate(bookings);
            request.getSession().setAttribute("bookings", bookings);
        }
        response.sendRedirect("showbookings.jsp");
    } 

    private void sortByDate(List<Booking> bookings) {
        bookings.sort(new Comparator<Booking>() {
            @Override
            public int compare(Booking b1, Booking b2) {
                return b1.getDate().compareTo(b2.getDate());
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
