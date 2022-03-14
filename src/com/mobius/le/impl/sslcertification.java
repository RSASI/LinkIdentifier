//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import com.sun.net.ssl.internal.ssl.Provider;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class sslcertification {
    public sslcertification() {
    }

    public static void main(String[] args) {
    }

    public static String LoadURL(String url) {
        try {
            SSLContext ssl_ctx = SSLContext.getInstance("TLS");
            TrustManager[] trust_mgr = get_trust_mgr();
            ssl_ctx.init((KeyManager[])null, trust_mgr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
            URL u = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection)u.openConnection();
            con.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String host, SSLSession sess) {
                    return host.equals("localhost");
                }
            });
            ActionsDecider.rescode = con.getResponseCode();
            if (ActionsDecider.rescode == 200) {
                URLValidator.LoadedUrl = url;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";

            while((line = br.readLine()) != null) {
                sb.append(line);
            }

            if (sb.length() != 0) {
                return sb.toString();
            }
        } catch (Exception var8) {
            Loader.log.error(Loader.appendLog(""), var8);
        }

        return "";
    }

    public static String SSl_fun(String url) {
        StringBuffer sb = new StringBuffer();

        try {
            doTrustToCertificates();
            URL u = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection)u.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";

            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception var6) {
            Loader.log.error(Loader.appendLog(""), var6);
        }

        return sb.toString();
    }

    public static void doTrustToCertificates() throws Exception {
        Security.addProvider(new Provider());
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
            }
        }};
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init((KeyManager[])null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
                    ;
                }

                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    private static TrustManager[] get_trust_mgr() {
        TrustManager[] certs = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String t) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String t) {
            }
        }};
        return certs;
    }
}
