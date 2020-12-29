## 网络爬虫网站
WebCrawler网络爬虫网站  
  
功能导航：  
![Image text](screenShot/%E5%AF%BC%E8%88%AA%E6%A0%8F.gif)  
  
用户设置：修改密码，上传头像，上传背景图片  
  
爬虫策略：添加和修改爬取策略、入口网站  
![Image text](screenShot/%E7%88%AC%E5%8F%96%E7%AD%96%E7%95%A5.gif)  
  
主题管理：查看、创建和修改主题  
![Image text](screenShot/%E4%B8%BB%E9%A2%98%E5%88%97%E8%A1%A8.gif)  
  
任务管理：查看、创建、开始和删除任务  
![Image text](screenShot/%E4%BB%BB%E5%8A%A1%E5%88%97%E8%A1%A8.gif)  
  
爬取数据：查看爬取后的数据并可查看详细信息，导出数据、生成摘要、热词提取  
![Image text](screenShot/%E7%88%AC%E5%8F%96%E6%95%B0%E6%8D%AE.gif)

## 运行

1. 在 mysql 中创建名为 youget 的数据库，修改 `src/main/resources/application.properties` 中对应 jdbc 数据库名称与账号密码

2. 点击 IDEA 右上角绿色三角形运行，系统自动建表

3. 运行完成后打开浏览器访问 [localhost://8888](http://localhost:8888/)，注册账号并登录

4. 连接数据库，在 youget 数据库中运行根目录下的3个 youget_*.sql 文件

5. 前端页面按流程先创建主题，再创建任务、开始任务，后台开始爬取数据存入数据库，爬取完成后可查看  
  ![Image text](screenShot/process.gif)
