package com.zwc.controller.upload;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class SslUtil {
    private static void trustAllHttpsCertificates() {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;

        SSLContext sc = null;
        try {
            //TLS
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    static class miTM implements TrustManager, X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
    }

    public static CloseableHttpClient createSSLClientDefault(){
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy(){
                //信任所有
                public boolean isTrusted(X509Certificate[] chain,String authType) throws CertificateException{
                    return true; }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    /**
     * 忽略HTTPS请求的SSL证书，必须在openConnection之前调用
     * @throws Exception
     */
    public static void ignoreSsl() throws Exception{
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

}