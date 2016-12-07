package control;


import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name="Login", urlPatterns={"/Login", "/index.html"})
public class Login extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");
        if(request.getParameter("username")==null)
            response.sendRedirect("login.jsp");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(cf.authenticate(username, password)){
            request.setAttribute("kayaks", cf.getAllKayaks());
            request.setAttribute("user", cf.getUserFromName(username));
            request.getRequestDispatcher("showkayaks.jsp").forward(request, response);
            
        } else {
            PrintWriter out = response.getWriter();
            out.println("Unfortunately you could not be authenticated");
            out.close();
        }
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
