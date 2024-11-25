package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Data;
import model.DataLogic;

@WebServlet("/game")
public class GetGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ゲーム画面へのGETリクエスト
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // tmpMapが存在する場合は取得してリクエストスコープへ(PostAddServletからのリダイレクト時)
	  HttpSession session = request.getSession();
	  LinkedHashMap<String, List<Data>> reqMap =
        (LinkedHashMap<String, List<Data>>)session.getAttribute("tmpMap");
    if (reqMap != null) {
      Gson gson = new Gson();
      String reqData = gson.toJson(reqMap);
      request.setAttribute("reqData", reqData);
      // 一時的なものなので不要
      session.removeAttribute("tmpMap");
    }

	  // 全データ取得してリクエストスコープに移すロジック実行
    DataLogic dataLogic = new DataLogic();

    // フォワード
	  dataLogic.selectAll(request, this.getServletContext());
	  RequestDispatcher dispatcher =
	      request.getRequestDispatcher("WEB-INF/jsp/game.jsp");
	  dispatcher.forward(request, response);
	}

}
