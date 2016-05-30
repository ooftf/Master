package com.dks.master.mastervolley;

import android.content.Context;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.Hashtable;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by master on 2016/4/21.
 */
public class OkHttpsHurlStack extends HurlStack{
    private static Hashtable<String,SSLSocketFactory> socketFactoryMap;
    Context context;
    OkHttpClient okHttpClient ;
    OkHttpsHurlStack(Context context){
        this.context =context;
        okHttpClient =  new OkHttpClient();
    }
    private static Hashtable<String,SSLSocketFactory> getSocketFactoryMap(Context context){
        if(socketFactoryMap == null){
            try {
                String[] hosts = {"kyfw.12306.cn"};//作用的域名
                //int[] certRes = {R.raw.kyfw};
                int[] certRes = {15};//密钥地址
                String[] certPass = {"asdfqaz"};//打开密钥的密码
                socketFactoryMap = new Hashtable<>(hosts.length);
                for (int i = 0; i < certRes.length; i++) {
                    int res = certRes[i];
                    String password = certPass[i];
                    SSLSocketFactory sslSocketFactory = createSSLSocketFactory(context, res, password);
                    socketFactoryMap.put(hosts[i], sslSocketFactory);
                }
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        }
    return socketFactoryMap;
    }
    private static SSLSocketFactory createSSLSocketFactory(Context context, int res, String password)
            throws CertificateException,
            NoSuchAlgorithmException,
            IOException,
            KeyStoreException,
            KeyManagementException {
        InputStream inputStream = context.getResources().openRawResource(res);
        KeyStore keyStore = KeyStore.getInstance("BKS");
        keyStore.load(inputStream, password.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), new SecureRandom());
        return sslContext.getSocketFactory();
    }
    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        if ("https".equals(url.getProtocol()) && getSocketFactoryMap(context).containsKey(url.getHost())) {
            HttpsURLConnection connection = (HttpsURLConnection) new OkUrlFactory(okHttpClient).open(url);
            connection.setSSLSocketFactory(getSocketFactoryMap(context).get(url.getHost()));
            return connection;
        } else {
            return  new OkUrlFactory(okHttpClient).open(url);
        }
    }
}
