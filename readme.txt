アプリケーション使い方

index.jspがあります。
トップ画面(index.jsp)より各画面へのリンクがあります。
各画面に操作キーを記載しています。


// データベース名asakawa
private final String JDBC_URL =
    "jdbc:h2:tcp://localhost/~/asakawa";
private final String DB_USER = "sa";
private final String DB_PASS = "";


// 使用するテーブル名DATA
CREATE TABLE DATA (
  ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  NAME VARCHAR(255) NOT NULL,
  TYPE VARCHAR(255) NOT NULL,
  X INT NOT NULL,
  Y INT NOT NULL,
  HP INT NOT NULL
);


