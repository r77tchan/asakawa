<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>配置設定</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <a href="index.jsp" class="container link">
    <h1>トップ画面へ</h1>
  </a>

  <div class="container">

    <h1 class="head-line">配置設定</h1>
    <div class="content-container">

      <div class="content-title">保存内容</div>
      <div class="storage-container">
        <ul class="storage-ul">
          <div class="storageName">データベース</div>
        </ul>
      </div>
      <div class="storage-container">
        <ul class="storage-ul">
          <div class="storageName">アプリケーションスコープ</div>
        </ul>
      </div>
      <div class="storage-container">
        <ul class="storage-ul">
          <div class="storageName">セッションスコープ</div>
        </ul>
      </div>
      <div class="storage-container">
        <ul class="storage-ul">
          <div class="storageName">ローカルストレージ</div>
        </ul>
      </div>

    </div>



    <div class="content-container">
      <div class="content-title">登録フォーム</div>


      <form action="add" method="post">

        <label>
          <div class="form-group">
            <span class="formSpan formName">保存名:</span>
            <input type="text" id="name" name="name" maxlength="10" required>
          </div>
        </label>



        <div class="mns-data">
        </div>


        <div class="tap-button-container">
          <span class="tap-button" id="mnsDataAdd">フォーム拡張</span>
        </div>

        <div class="submit">
          <!-- buttonはデフォルトがtype="submit" -->
          <button type="submit"  name="action" value="0">データベース保存</button>
          <button type="submit"  name="action" value="1">アプリケーションスコープ保存</button>
          <button type="submit"  name="action" value="2">セッションスコープ保存</button>
          <button type="submit"  name="action" value="3">リクエストスコープ保存&移動</button>
          <button type="submit" value="4">ローカルストレージ保存</button>
        </div>

        <div id="form-guide-message">*拡張後に保存ボタンが表示されます</div>
      </form>


      <div class="slider">
        size <input type="range" id="widthSlider" min="10" max="100" value="50">
      </div>
      <canvas width="592" height="592"></canvas><!-- 37*16=592 -->
      <p><span class="canvas-guide">1つ前を選択</span>Vキー</p>
      <p><span class="canvas-guide">1つ後を選択</span>Bキー</p>
      <p><span class="canvas-guide">選択モンスターを削除</span>Cキー</p>
      <p><span class="canvas-guide">選択のy座標減少</span>Eキー</p>
      <p><span class="canvas-guide">選択のx座標減少</span>Sキー</p>
      <p><span class="canvas-guide">選択のy座標増加</span>Dキー</p>
      <p><span class="canvas-guide">選択のx座標増加</span>Fキー</p>
      <p><span class="canvas-guide">10倍加速</span>G+座標キー</p>
      <p><span class="canvas-guide">選択のhp減少</span>Nキー</p>
      <p><span class="canvas-guide">選択のhp増加</span>Mキー</p>
      <p><span class="canvas-guide">固定モンスター挿入</span>R, T, Y, U, I, O, P, Z, X</p>
      <div class="monsters-key-guide-container">
        <div class="monster-container">
          <img src="images/wizardGhost.png" onclick="addMonster('wizardGhost')" alt="wizardGhost" width="64" height="64">
          <span class="monster-label">R</span>
        </div>
        <div class="monster-container">
          <img src="images/wizardLily.png" onclick="addMonster('wizardLily')" alt="wizardLily" width="64" height="64">
          <span class="monster-label">T</span>
        </div>
        <div class="monster-container">
          <img src="images/mummy.png" onclick="addMonster('mummy')" alt="mummy" width="64" height="64">
          <span class="monster-label">Y</span>
        </div>
        <div class="monster-container">
          <img src="images/frog.png" onclick="addMonster('frog')" alt="frog" width="64" height="64">
          <span class="monster-label">U</span>
        </div>
        <div class="monster-container">
          <img src="images/wolf.png" onclick="addMonster('wolf')" alt="wolf" width="64" height="64">
          <span class="monster-label">I</span>
        </div>
        <div class="monster-container">
          <img src="images/tanuki.png" onclick="addMonster('tanuki')" alt="tanuki" width="64" height="64">
          <span class="monster-label">O</span>
        </div>
        <div class="monster-container">
          <img src="images/zombie.png" onclick="addMonster('zombie')" alt="zombie" width="64" height="64">
          <span class="monster-label">P</span>
        </div>
        <div class="monster-container">
          <img src="images/grimReaper.png" onclick="addMonster('grimReaper')" alt="grimReaper" width="64" height="64">
          <span class="monster-label">Z</span>
        </div>
        <div class="monster-container">
          <img src="images/whiteKnight.png" onclick="addMonster('whiteKnight')" alt="whiteKnight" width="64" height="64">
          <span class="monster-label">X</span>
        </div>
      </div>

    </div>
    </div>
    <script>
      let dbData = {};
      let appData = {};
      let sesData = {};
      <% String dbData = (String)request.getAttribute("dbData"); %>
      <% String appData = (String)request.getAttribute("appData"); %>
      <% String sesData = (String)request.getAttribute("sesData"); %>
      <% if (dbData != null) { %>
      dbData = JSON.parse('<%= dbData %>');
      <% } %>
      <% if (appData != null) { %>
      appData = JSON.parse('<%= appData %>');
      <% } %>
      <% if (sesData != null) { %>
      sesData = JSON.parse('<%= sesData %>');
      <% } %>
    </script>
    <script src="js/set.js"></script>
  </body>
  </html>