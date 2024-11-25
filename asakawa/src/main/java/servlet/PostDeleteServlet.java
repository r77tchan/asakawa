package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Data;
import model.DataLogic;


@WebServlet("/delete")
public class PostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 配置設定画面からの削除リクエスト(非同期でJSONのみが送られてくる。js側で何かしらレスが返ったらページリロードするのでjava側は削除だけでいい)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // 日本語扱うので
	  request.setCharacterEncoding("UTF-8");

	  // JSONが送られてくるのでbodyをMapへ格納
	  BufferedReader reader = request.getReader(); // body読み取り
	  Gson gson = new Gson();
	  Map<String, String> requestData = gson.fromJson(reader, Map.class); // クラス情報を型に指定

	  // 変数へ
	  String name = requestData.get("name");
	  String action = requestData.get("action");

	  switch (action) {
	    case "0": // データベース
  	    {
  	      DataLogic dataLogic = new DataLogic();
          dataLogic.deleteData(name); // データ削除ロジック
  	    }
	      break;

	    case "1": // アプリケーションスコープ
  	    {
  	      ServletContext application = this.getServletContext();
  	      // 読み込んで、削除して、再設定
          LinkedHashMap<String, List<Data>> map =
              (LinkedHashMap<String, List<Data>>)application.getAttribute("map");
          map.remove(name);
          application.setAttribute("map", map);
  	    }
	      break;

	    case "2": // セッションスコープ
  	    {
          HttpSession session = request.getSession();
          LinkedHashMap<String, List<Data>> map =
              (LinkedHashMap<String, List<Data>>)session.getAttribute("map");
          map.remove(name);
          session.setAttribute("map", map);
  	    }
	      break;
	  }

	  // 返すものは特にない
	  response.setStatus(HttpServletResponse.SC_OK); // エラーが発生しなければデフォルトでステータスコード200(成功)が送られるのでなくてもいい

	}

}
