package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.LineinforDaoImpl;
import entity.Lineinfor;
@WebServlet(name="PageServlet",urlPatterns="/find",initParams={@WebInitParam(name="every",value="3")})
public class PageServlet extends HttpServlet {

	  //����userdao
		private LineinforDaoImpl  lineinfor = new LineinforDaoImpl();
		//����������
		 int count = 0;
		 //user����
		 List<Lineinfor> list =null;
	/**
	 * Constructor of the object.
	 */
	public PageServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ȡ��ʼ����������Ϊҳ���С
		int every  = Integer.parseInt(this.getInitParameter("every"));
		
		//��ȡ����������
		int count = lineinfor.findCount();		
		//����ҳ��
		int pageCount = count/every;
		if(count % every >0){
			pageCount +=1;
		}
			//��ȡ��ǰҳ
			String page = request.getParameter("page");
			//����ǰҳ������������
			request.setAttribute("page", page);
			//����ҳ��������������
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("every", every);
			request.getRequestDispatcher("mytabel.jsp").forward(request, response);
				
			}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
