package model;

public class Data { // DATAテーブルのレコードを表すEntity(p422参考)なくてもいい
  // ID,NAMEは保持しない
  private String type;
  private int x;
  private int y;
  private int hp;

  public Data(String type, int x, int y, int hp) {
    this.type = type;
    this.x = x;
    this.y = y;
    this.hp = hp;
  }
  public String getType() { return type; }
  public int getX() { return x; }
  public int getY() { return y; }
  public int getHp() { return hp; }
}