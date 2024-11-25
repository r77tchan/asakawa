package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DataLogic;

@WebServlet("/set")
public class GetSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 配置設定画面へのGETリクエスト
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // DBと全スコープからデータを取得して、リクエストスコープに移すロジックを実行
    DataLogic dataLogic = new DataLogic();
    dataLogic.selectAll(request, this.getServletContext());

    // フォワード
    RequestDispatcher dispatcher =
        request.getRequestDispatcher("WEB-INF/jsp/set.jsp");
    dispatcher.forward(request, response);
  }

}