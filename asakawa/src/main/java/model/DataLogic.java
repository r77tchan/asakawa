package model;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.DataDAO;

public class DataLogic { // DAOを利用するクラス(p394辺り参考)

  public void selectAll(HttpServletRequest request, ServletContext application) {
    // DBと全スコープからデータを取得して、リクエストスコープに移すロジック

    // データベースから全取得
    DataDAO dao = new DataDAO();
    String dbData = dao.selectAll();
    if (dbData != null) {
      request.setAttribute("dbData", dbData);
    }

    // アプリケーションスコープ
    LinkedHashMap<String, List<Data>> appMap =
        (LinkedHashMap<String, List<Data>>)application.getAttribute("map");
    if (appMap != null) { // JSONでリクエストスコープに渡す
      Gson gson = new Gson();
      String appData = gson.toJson(appMap);
      request.setAttribute("appData", appData);
    }

    // セッションスコープからのデータ取得
    HttpSession session = request.getSession();
    LinkedHashMap<String, List<Data>> sesMap =
        (LinkedHashMap<String, List<Data>>)session.getAttribute("map");
    if (sesMap != null) {
      Gson gson = new Gson();
      String sesData = gson.toJson(sesMap);
      request.setAttribute("sesData", sesData);
    }
  }

  public String selectNames() {
    // 重複なしで全てのnameを取得
    // set画面も全てのデータを取得してjsのオブジェクトに格納しておく事になったので未使用
    DataDAO dao = new DataDAO();
    return dao.selectNames();
  }

  public boolean insertData(String name, List<Data> monstersData) {
    // 挿入
    DataDAO dao = new DataDAO();
    return dao.insertData(name, monstersData);
  }

  public boolean deleteData(String name) {
    // 削除
    DataDAO dao = new DataDAO();
    return dao.deleteData(name);
  }

  public String selectDataByName(String name) {
    // 指定のnameを持つ全てのデータを取得
    // 上の理由で未使用。名前だけ取得して、読み込みボタンが押されたときに、押された名前のデータだけを取得するよりも、初めから全データ取得してページ上(jsのオブジェクト)に置くのがシンプル
    DataDAO dao = new DataDAO();
    return dao.selectDataByName(name);
  }

}
