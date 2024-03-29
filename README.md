### 概要

日常でもよく使えるTODOのアプリケーションを作成。
参照、登録,更新,削除の機能を備えております。

### 作成背景

前職は整体関連の仕事をしており、なかなか管理するシステムがなかったので、
基本のベースになるような物を形にしてみたいと思いました。
また、Excelや患者情報を管理していた経験もあり形に出来るチャンスと感じました。

アーキテクチャ図

![0D834CE1-CC83-462D-BA26-97EF7E683043](https://github.com/simamumu89/Todo_API/assets/142807674/80896fa5-0bf1-488a-9b0d-3b5e52967563)

### 開発環境

* IntelliJ IDEA
* 言語：Java
* フレームワーク：Spring Framework
* DB：MySQL
* 環境：Mac、Docker
* ソース管理：Git
* プロジェクト管理：GitHub

### アプリケーション機能一覧

| HTTPメソッド | URL             | 処理内容          |
|----------|-----------------|---------------|
| GET      | なし              | 全件読み取り(取得)    |
| GET      | {id}            | 指定idの読み取り(取得) |
| POST     | todo_lists      | タスク新規登録       |
| PATCH    | todo_lists/{id} | タスクの部分更新      |
| DELETE   | todo_lists/{id} | タスクの削除        |

### DB定義

テーブル名：todo_lists

| カラム名               | データ型         | キー          | 備考             |
|--------------------|--------------|-------------|----------------|
| id                 | int          | PRIMARY KEY | ID,自動生成        |
| name               | VARCHAR(255) |             | タスク名           |
| start_date         | DATE         |             | 開始日 yyyy-mm-dd |
| scheduled_end_date | DATE         |             | 終了予定日          |
| actual_end_date    | DATE         |             | 終了実績日          |

### 各種機能の詳細と動作確認

検索機能（Read）

1. DBテーブルからTODO情報を全件取得
2. ID検索から特定のTODO情報を取得
3. DBに存在しないIDを検索した場合の例外処理

登録機能（Create）

1. TODO情報をDBテーブルに新規登録

更新機能（Update）

1. DBに存在するTODO情報の内容更新
2. 指定したidにDB情報がない場合の例外処理

削除機能（Delete）

1. IDを指定して該当するTODO情報を削除
2. DBに存在しないIDを指定して削除する場合の例外処理

### 振り返り

アプリケーションの開発を通して、Java言語の知識やフレームワークやデータベースの知識を学び、
エラーコードに対して実装出来ない事が多かったです。
また、時間を使いながらも以前よりも、本からも情報を取り入れる事で、
LocalDate = 年月日の情報だけを持つクラスだったり、List型 = クエリ結果が複数の場合やOptional = クエリ結果が1件
クエリ結果が無い場合など仕様の知識がつきました。
今後は現場に出ても多くのコードに触れて技術力を高めたいと思います。