package control;

import model.entity.exceptions.BookingNotPossibleException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DataFacade;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Nov 11, 2016
 */
@WebServlet(name="BookKayak", urlPatterns={"/BookKayak"})
public class BookKayak extends HttpServlet {
   DataFacade cf = new DataFacade();
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        int kayakid = Integer.parseInt(request.getParameter("kayakid"));
        int userid = Integer.parseInt(request.getParameter("userid"));
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateformat.parse(request.getParameter("date"));
            cf.makeBooking(cf.getKayak(kayakid), cf.getUser(userid), date);
        } catch (ParseException ex) {
            out.print("Date could not be parsed");
        } catch (BookingNotPossibleException ex) {
            request.getSession().setAttribute("msg", "The booking could not be made. Try another kayak or another day.");
            request.getRequestDispatcher("BookingServlet").forward(request, response);
       }
        out.println("SUCCES you have booked "+cf.getKayak(kayakid).getName()+" on "+date.toString());
        out.println("<a href=\"showkayaks.jsp\">Go back to booking page</a>");
        out.close();
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
