<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>トップ画面</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <a href="set" class="container link">
    <h1>配置設定へ</h1>
  </a>

  <a href="game" class="container link">
    <h1>オフライン</h1>
  </a>

  <a href="https://gg.mh4.jp/" class="container link" target="_blank">
    <h1>オンライン<span style="margin-left: 40px;">https://gg.mh4.jp</span></h1>
  </a>

  <div class="container">
    <h1 class="head-line">トップ画面</h1>

    <div class="content-container">
      <div class="content-title">操作説明</div>
      <div class="index-container">
        <h2 class="index">概要</h2>
        <p>配置設定フォームより、モンスター情報を入力して保存すると、その内容で戦えるゲーム。</p>
      </div>
      <div class="index-container">
        <h2 class="index">トップ画面</h2>
        <p>この画面。各リンクと操作説明を記載。</p>
      </div>
      <div class="index-container">
        <h2 class="index">配置設定</h2>
        <p>モンスター情報を登録するページ。</p>
        <p>キー入力に対応してるので、活用するのが快適。直接フォーム入力も可能。</p>
        <p>alertとconfirmはescape、enterキーを使おう。</p>
      </div>
      <div class="index-container">
        <h2 class="index">オフライン</h2>
        <p>オフラインゲームへのリンク。</p>
        <p>画面外に記載されているキーのみ使用。拡大推奨。</p>
        <p>ESDFキーで移動して、右側マップの中心にあるオブジェクトを攻撃すると、戦闘内容を選択する画面に移る。</p>
        <p>選択画面も使うキーは同じ。配置設定ページで保存したデータを選択すると、戦闘開始。</p>
      </div>
      <div class="index-container">
        <h2 class="index">オンライン</h2>
        <p>デプロイ先のリンク(外部リンク)。</p>
        <p>https://の部分はブラウザが補完するので必要ない。</p>
        <p>配置設定はできなくてデフォルトのwave制しかないけど、処理をサーバー側で行なっているので、マルチプレイも可能。</p>
        <p>通信環境、負荷次第ではラグい。</p>
      </div>
    </div>


  </div>

</body>
</html>