# Spring-Security-JWT
For Job Interview

# 用戶於登入系統請求並認證
用戶於登入系統請求成功後，Server 產生 JWT token 回傳給客戶端，且之後的CRUD Request 都需帶入 Token 取得 Response Data。

## 系統環境

* 資料庫環境: PostgreSQL

## 開發環境

* 開發環境 Spring Tool Suite 3 Version: 3.9.16.RELEASE

## 建置作業
1. 使用 creat_script及data_insert_script 新增資料於資料庫

## 啟動
1. java -jar -Dserver.port=指定port號 -Dspring.datasource.username=帳號 -Dspring.datasource.password=密碼 -Ddatabase.name=資料庫名稱 demo1022-0.0.1-SNAPSHOT.jar
