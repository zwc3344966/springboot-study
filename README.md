# springboot-study
## 上传文件时可能需要抓包查看请求数据，如果要抓包（抓包工具:Fiddler）需要配置代理并开启Fiddler：
``` java
new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888))
