package com.zwc.controller.upload;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.*;

/**
 * @author: zhangwch
 * @create: 2020-10-17 00:31
 **/
public class UploadTest {
    // 可以设置为Https,如：private static HttpsURLConnection conn = null;
    private static HttpURLConnection conn = null;
    private static InputStream in = null;
    private static final String DEFAULT_CHARSET = "utf-8";

    public static void main(String[] args) throws Exception {
        String url = "http://127.0.0.1:8080/upload";
//        String url = "http://127.0.0.1:8080/upload1";

        String body = "{\n" +
                "    \"AA\": {\n" +
                "        \"BB\": \"123123123123\",\n" +
                "        \"CC\":\"1\"\n" +
                "    }\n" +
                "}";

        List<String> fileList = new ArrayList<>();
        fileList.add("155.pdf");
//        fileList.add("116.pdf");
//        fileList.add("b21f95a9eeecbba7a6693e83caed31c.jpg");
//        fileList.add("ydd001报文.txt");
//        需开启代理
//        sends(body, fileMap, url, new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)));
        sends(body, fileList, url, null);
        receives();
    }

    public static void sends(String body,List<String> fileList, String urlString, Proxy proxy) throws Exception {
        OutputStream out = null;
        try {
            // 构造URL
            URL url = new URL(urlString);
            if (null != proxy) {
                System.out.println("===================代理================");
                // 忽略ssl证书不信任
                SslUtil.ignoreSsl();
                // 打开连接
                conn = (HttpURLConnection) url.openConnection(proxy);
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //设置请求超时为15s
            conn.setConnectTimeout(15 * 1000);
            conn.setRequestMethod("POST");

            String end = "\r\n";
            String twoHyphens = "--";
            String boundary = "--------------------------" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            out = new DataOutputStream(conn.getOutputStream());

            if (null != body && !"".equals(body)) {
                StringBuilder sd = new StringBuilder();
                sd.append(twoHyphens + boundary + end);
                sd.append("Content-Disposition: form-data; name=\"params\"" + end + end);
                sd.append(body);
                sd.append(end);
                out.write(sd.toString().getBytes());
            }

            for (int i = 0; i < fileList.size(); i++) {
                StringBuilder fileSd = new StringBuilder();
                String fileName = fileList.get(i);
                fileSd.append(twoHyphens + boundary + end);
                /**
                 * name = "file" : file需与服务端接收参数对应，否则会报错
                 */
                fileSd.append(String.format("Content-Disposition: form-data; name=\"file\"; filename=\"%s\"",
                        fileName) + end + end);
                out.write(fileSd.toString().getBytes());
                // HttpDownloadUtil.download():返回落盘到本地的文件的全路径
                // getContent():读取文件返回字节
                write(out, "C:\\Users\\Administrator\\Desktop\\" + fileName);
                out.write(end.getBytes());
            }
            out.write((twoHyphens + boundary + twoHyphens + end).getBytes());
            out.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw e;
                }
            }

        }
    }

    public static void write(OutputStream out, String filePath) throws IOException {
        File file = new File(filePath);
        DataInputStream in = null;
        try {
            in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            in.close();
        }
    }

    public static void receives() throws IOException {
        // TODO Auto-generated method stub
        String charset = getResponseCharset(conn.getContentType());
        byte[] rbuf = null;
        InputStream es = conn.getErrorStream();
        try {
            in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "ISO8859-1"));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            rbuf = buffer.toString().getBytes("ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            throw e;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw e;

        } finally {
            if (es != null) {
                try {
                    es.close();
                } catch (IOException e) {
                    throw e;
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw e;
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        String res = new String(rbuf);
        System.out.println("receive:\n" + res);
    }

    private static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARSET;

        if (null != ctype && !"".equals(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (null != pair[1] && !"".equals(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }
        return charset;
    }
}
