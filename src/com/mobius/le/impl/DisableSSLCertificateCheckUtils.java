//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public final class DisableSSLCertificateCheckUtils {
    private DisableSSLCertificateCheckUtils() {
    }

    public static void disableChecks() throws NoSuchAlgorithmException, KeyManagementException {
        try {
            (new URL("https://0.0.0.0/")).getContent();
        } catch (IOException var2) {
            ;
        }

        SSLContext context = SSLContext.getInstance("SSLv3");
        TrustManager[] trustManagerArray = new TrustManager[]{new DisableSSLCertificateCheckUtils.NullX509TrustManager()};
        context.init((KeyManager[])null, trustManagerArray, (SecureRandom)null);
        HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new DisableSSLCertificateCheckUtils.NullHostnameVerifier());
    }

    private static class NullHostnameVerifier implements HostnameVerifier {
        private NullHostnameVerifier() {
        }

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static class NullX509TrustManager implements X509TrustManager {
        private NullX509TrustManager() {
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
