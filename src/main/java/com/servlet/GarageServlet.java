package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import com.model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import java.util.List;
import com.dao.GarageDao;
import com.dao.StockDao;


@WebServlet("/")
public class GarageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GarageDao GarageDAO;
    private StockDao StockDAO;
    
    public GarageServlet() {
    	 this. GarageDAO = new GarageDao();
    	 this.StockDAO=new StockDao();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String action = request.getServletPath();
	        try {
	            switch (action) {
	            case "/buy":
	            	RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
	    		    dispatcher.forward(request, response);
	    		    break;
                    case "/stock":
                    veiwstock(request, response);
                    break;
	                case "/new":
	                    showNewForm(request, response);
	                    break;
	                case "/insert":
	                    insertProduct(request, response);
	                    break;
	                case "/store":
	                    store(request, response);
	                    break;
	                case "/delete":
	                    deleteUser(request, response);
	                    break;
	                case "/user":
	                    user(request, response);
	                    break;
	                default:
	                    listUser(request, response);
	                    break;
	            }
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
	}
	
	private void user(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List < Product > listUser =  StockDAO.selectAllProduct();
        request.setAttribute("a",listUser);
        System.out.print(listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user.jsp");
        dispatcher.forward(request, response);
    }

	private void veiwstock(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List < Product > listUser =  StockDAO.selectAllProduct();
        request.setAttribute("a",listUser);
        System.out.print(listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("stocks.jsp");
        dispatcher.forward(request, response);
    }

	private void store(HttpServletRequest request, HttpServletResponse response) 
		    throws SQLException, IOException, ServletException {
		    int q = Integer.parseInt(request.getParameter("quantity"));
		    int id = Integer.parseInt(request.getParameter("id"));
		    
		    // Retrieve the product from the database using GarageDAO
		    Product t = GarageDAO.getp(id);
		    
		    // Check if the product exists
		    if (t != null) {
		        // Set the quantity of the product
		        t.setQuantity(q);
		        
		        // Store the updated product in the database using GarageDAO
		        GarageDAO.store(t);
		        
		        System.out.println("Added successfully");
		    } else {
		        System.out.println("Product not found");
		    }
		    
		    // Forward the request to the list.jsp page
		    RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		    dispatcher.forward(request, response);
		}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException, ServletException {
			        List < Product > listUser =  GarageDAO.selectAllProduct();
			        request.setAttribute("list",listUser);
			        System.out.println(listUser);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
			        dispatcher.forward(request, response);
			    }
	  private void insertProduct(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
			        String name = request.getParameter("name");
			        int rs = Integer.parseInt(request.getParameter("rs"));
			        Product newp = new Product(name,rs);
			        GarageDAO.insertProduct(newp);
			        response.sendRedirect("list");
			    }
	  private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
			        int id = Integer.parseInt(request.getParameter("id"));
			        GarageDAO.deleteUser(id);
			        response.sendRedirect("list");

			    }
	  private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
		  			
			        RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
			        dispatcher.forward(request, response);
			    }
}