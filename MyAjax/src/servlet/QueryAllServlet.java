package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.impl.LineinforDaoImpl;
import entity.Lineinfor;

/**
 * Servlet implementation class QueryAllServlet
 */
@WebServlet("/QueryAllServlet")
public class QueryAllServlet extends HttpServlet {
	LineinforDaoImpl lineinfor = new LineinforDaoImpl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");    
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int page=0;
		int every =0;
		String page1 = request.getParameter("page");
		if(page1 == ""){
			page = 1;
		}else{
			page = Integer.parseInt(page1);
		}
		
		String every1 = request.getParameter("every");
		if(every1 == ""){
			every = 3;
		}else{
			every = Integer.parseInt(every1);
		}
		List<Lineinfor> list = null;
		LineinforDaoImpl ldi = new LineinforDaoImpl();
		list = lineinfor.pageFind((page-1)*every, every); 
		Gson gson = new Gson();
		String goal =gson.toJson(list);
		out.write(goal);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
