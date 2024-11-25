package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import model.Data;

public class DataDAO { // DATAテーブルを担当するDAO(p423,p391参考)
  private final String JDBC_URL =
      "jdbc:h2:tcp://localhost/~/asakawa";
  private final String DB_USER = "sa";
  private final String DB_PASS = "";

  /*
  CREATE TABLE DATA (
    ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    TYPE VARCHAR(255) NOT NULL,
    X INT NOT NULL,
    Y INT NOT NULL,
    HP INT NOT NULL
  );
  */

  /*
  オートインクリメントリセット
  ALTER TABLE DATA ALTER COLUMN ID RESTART WITH 1;
   */

  // クラスが最初に読み込まれるときに実行される処理
  static {
    // 必要なライブラリを読み込む
    try {
      Class.forName("com.google.gson.Gson"); // このライブラリは存在していないとコンパイルできないからなくてもいい
      Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException e) {
      System.err.println("(クラスが最初に読み込まれたときのエラー)必要なライブラリが見つかりません" + e.getMessage());
    }
  }

  // 全てのデータを { NAME1：[[ TYPE, X, Y, HP ], [...]], NAME2： ... } の形で返す
  public String selectAll() {
    // キー名：[[ 1, 2, 3 ], [4, 5, 6]]
    // 実際のデータ → NAME：[[ TYPE, X, Y, HP ], [...]]
    // Entity使用 → [[ Data ], [ Data ]]
    // addメソッドを使用する為Listを使用
    LinkedHashMap<String, List<Data>> map = new LinkedHashMap<>();

    // データベースへ接続
    try (Connection conn = DriverManager.getConnection(
        JDBC_URL, DB_USER, DB_PASS)) {

      // SELECT文を準備
      String sql = "SELECT NAME,TYPE,X,Y,HP FROM DATA";
      PreparedStatement pStmt = conn.prepareStatement(sql);

      // SELECT文を実行し、結果表を取得
      ResultSet rs = pStmt.executeQuery();

      // SELECT文の結果をmapに格納
      while (rs.next()) {
        String name = rs.getString("NAME");
        String type = rs.getString("TYPE");
        int x = rs.getInt("X");
        int y = rs.getInt("Y");
        int hp = rs.getInt("HP");
        Data monsterData = new Data(type, x, y, hp);

        // NAMEをキー名にmapへ格納
        if (map.containsKey(name)) { // キーのListが存在するなら追加
          map.get(name).add(monsterData);
        } else { // しないなら作成して追加
          List<Data> list = new ArrayList<>();
          list.add(monsterData);
          map.put(name, list);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    // JSON変換
    Gson gson = new Gson();
    return gson.toJson(map);
  }

  // 全てのデータの名前だけを{ [name1, name2, name3, ...] }の形で返す
  public String selectNames() {
    Set<String> names = new LinkedHashSet<>(); // 重複要らないので

    try (Connection conn = DriverManager.getConnection(
        JDBC_URL, DB_USER, DB_PASS)) {

      // SELECT文を準備
      String sql = "SELECT NAME FROM DATA";
      PreparedStatement pStmt = conn.prepareStatement(sql);

      // SELECT文を実行
      ResultSet rs = pStmt.executeQuery();

      // Setへ追加
      while (rs.next()) {
        names.add(rs.getString("NAME"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    Gson gson = new Gson();
    return gson.toJson(names);
  }

  // データの追加。引数に保存名(String name), Dataの配列([Data, Data, Data, ...])
  public boolean insertData(String name, List<Data> monstersData) {
    try (Connection conn = DriverManager.getConnection(
        JDBC_URL, DB_USER, DB_PASS)) {

      // INSERT文を準備
      String sql = "INSERT INTO DATA(NAME,TYPE,X,Y,HP) VALUES(?,?,?,?,?)";
      PreparedStatement pStmt = conn.prepareStatement(sql);

      // データ数分ループ
      for (Data data : monstersData) {
        // パラメータを設定し、INSERT文を実行
        pStmt.setString(1, name);
        pStmt.setString(2, data.getType());
        pStmt.setInt(3, data.getX());
        pStmt.setInt(4, data.getY());
        pStmt.setInt(5, data.getHp());

        pStmt.executeUpdate(); // 失敗すればSQLExceptionが発生する訳だから追加された行数を確認する必要はないような(空の状態で呼び出されない前提)
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  // データの削除。引数に保存名(String name)。該当する保存名を全て。
  public boolean deleteData(String name) {
    try (Connection conn = DriverManager.getConnection(
        JDBC_URL, DB_USER, DB_PASS)) {

      // DELETE文を準備
      String sql = "DELETE FROM DATA WHERE NAME = ?";
      PreparedStatement pStmt = conn.prepareStatement(sql);

      // パラメータを設定
      pStmt.setString(1, name);

      // DELETE文を実行
      pStmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  // 特定(1つ)の名前の全データを{ name:[[ TYPE, X, Y, HP], [...], ...]}の形で返す
  public String selectDataByName(String name) {
    // キーは引数nameの1つのみだが、キー名nameの2次元配列で返すのでmapを使用
    LinkedHashMap<String, List<Data>> map = new LinkedHashMap<>();
    List<Data> monstersData = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection(
        JDBC_URL, DB_USER, DB_PASS)) {

      // SELECT文を準備、パラメータ設定、実行
      String sql = "SELECT TYPE,X,Y,HP FROM DATA WHERE NAME = ?";
      PreparedStatement pStmt = conn.prepareStatement(sql);
      pStmt.setString(1, name);
      ResultSet rs = pStmt.executeQuery();

      // 結果をmonstersDataリストに追加
      while (rs.next()) {
        String type = rs.getString("TYPE");
        int x = rs.getInt("X");
        int y = rs.getInt("Y");
        int hp = rs.getInt("HP");
        Data data = new Data(type, x, y, hp);
        monstersData.add(data);
      }

      // mapへ格納
      if (!monstersData.isEmpty()) {
        map.put(name, monstersData);
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    Gson gson = new Gson();
    return gson.toJson(map);
  }


}
