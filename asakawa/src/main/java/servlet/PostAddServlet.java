package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Data;
import model.DataLogic;


@WebServlet("/add")
public class PostAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 配置設定画面からの追加リクエスト(フォーム送信)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // リクエストパラメーター取得
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");
    String[] type = request.getParameterValues("type");
    String[] x = request.getParameterValues("x");
    String[] y = request.getParameterValues("y");
    String[] hp = request.getParameterValues("hp");

    // 配列(List)[Data, Data, ...]を作成
    List<Data> monstersData = new ArrayList<>();

    for (int i = 0; i < type.length; i++) {
      Data monster = new Data(type[i], Integer.parseInt(x[i]),
          Integer.parseInt(y[i]), Integer.parseInt(hp[i]));
      monstersData.add(monster);
    }

    // 送信されたボタンのvalue(リクエストパラメータ)を取得
    String action = request.getParameter("action");

    // 押されたボタンに応じて処理を分岐
    switch (action) {
      case "0": // データベース
        {
          DataLogic dataLogic = new DataLogic();
          dataLogic.insertData(name, monstersData); // データ追加ロジック
        }
        break;

      case "1": // アプリケーションスコープ
        {
          // スコープ読み込みとデータ追加
          ServletContext application = this.getServletContext();
          LinkedHashMap<String, List<Data>> map =
              (LinkedHashMap<String, List<Data>>)application.getAttribute("map");
          if (map == null) {
            map = new LinkedHashMap<>();
          }
          map.put(name, monstersData);
          application.setAttribute("map", map);
        }
        break;

      case "2": // セッションスコープ
        {
          HttpSession session = request.getSession();
          LinkedHashMap<String, List<Data>> map =
              (LinkedHashMap<String, List<Data>>)session.getAttribute("map");
          if (map == null) {
            map = new LinkedHashMap<>();
          }
          map.put(name, monstersData);
          session.setAttribute("map", map);
        }
        break;

      case "3": // リクエストスコープ
        {
          // URLを変える為に/gameへリダイレクトする(POSTリクエストはPOSTしたURLになる)
          // 一旦sessionに保存して、/gameで取り出して、リクエストスコープに移したら、消す
          HttpSession session = request.getSession();
          LinkedHashMap<String, List<Data>> map = new LinkedHashMap<>();
          map.put(name, monstersData);
          session.setAttribute("tmpMap", map);
          response.sendRedirect("game");
          return; // 戻ってこないので意味ないけどここで終了を分かりやすく
        }
    }

    // POST後はリダイレクトしないと、ページのリロードで再送信されてしまう。分かりやすいようにファイルをGETとPOSTで分けることになったのでURLを変える為でもある。
    response.sendRedirect("set");
  }

}
