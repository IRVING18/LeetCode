###  执行demo前的工作
1. 替换url： ClientManager
	String url = "http://www.jiejie888.com:8080/test/jjd/api";


2. 替换私钥：ClientManager类：
	 
	 生成一套RSA秘钥，将私钥替换ClientManager类中的privateKey

3. 替换uid :ClientManager类

	把RSA公钥发给捷捷贷对接负责人,申请uid。
	等待捷捷贷对接负责人的反馈uid,然后替换ClientManager类中uid,完成后可执行demo

注意事项：
OpenapiClient.java为模拟客户端请求server的代码;
业务参数有中文的需要用URLDecoder进行UTF-8编码