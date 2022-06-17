package servlet;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.Sale;
import dao.SaleDAO;

public class ShowSalesByMonthServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String error = "";
		String cmd = "";

		String year = request.getParameter("year");
		String month = request.getParameter("month");
		//		YYYY年DD月
		//		String dispDate = year + "年" + month + "月";

		SaleDAO saleDao = new SaleDAO();

		ArrayList<Sale> sales = null;

		try {

			sales = saleDao.selectBySales(year, month);





		} catch (Exception e) {
			error = "DB接続エラーの為、売り上げ情報の確認は出来ません";
			cmd = "logout";


			// TODO: handle exception
		}finally{
			if(error.equals("")){
				request.setAttribute("sale_list", sales);
				request.getRequestDispatcher("/view/showSalesByMonth.jsp").forward(request,response);
			}else{
				request.setAttribute("error", error);
				request.setAttribute("cmd", "logout");
				request.getRequestDispatcher("/view/error.jsp").forward(request,response);
			}

		}


	}

}

