package com;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.doctorService;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/doctorServiceAPI")
public class doctorServiveAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	doctorService itemObj = new doctorService ();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doctorServiveAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String output =itemObj.insertDoctor(request.getParameter("name"),
				request.getParameter("specialization"),
				
				request.getParameter("mobile"),
				
				request.getParameter("doctorFee"));
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Map paras = getParasMap(request); 
		String output = itemObj.updateDoctor(paras.get("DID").toString(), 
				paras.get("name").toString(), 
				paras.get("specialization").toString(),       
				paras.get("mobile").toString(),       
				paras.get("doctorFee").toString()); 
		
		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		Map paras = getParasMap(request); 
		String output = itemObj.deleteDoctor(paras.get("DID").toString()); 
		response.getWriter().write(output); 
	}

	
	
	// Convert request parameters to a Map 
	private static Map getParasMap(HttpServletRequest request)
	{  
		Map<String, String> map = new HashMap<String, String>();  
		try 
		{   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";   
			scanner.close(); 
		
		  String[] params = queryString.split("&");   
		  for (String param : params)   {
			  
			  String[] p = param.split("=");    
			  map.put(p[0], p[1]); 
		  }
	}
	
		catch (Exception e)
		{  
			}  return map;
		}
}
