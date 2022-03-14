//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.net.ssl.SSLException;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class URLValidator {
    private static Document document = null;
    public static String psource = "";
    public static String LoadedUrl = "";
    public static String redirect = "";
    public static ArrayList<String> sitemapLinkText = new ArrayList();

    public URLValidator() {
    }

    public static ArrayList<Object> validate(int extract_flag, int deplev, String url, int timeout, int domid) throws IOException {
        int Status = 0;
        String docStr = "";
        String URL = "";
        Set<String> redirectURLSet = new TreeSet();
        Response response = null;
        String orgURL = url;
        int sublink_responsecode = 0;
        if (extract_flag != 3) {
            try {
                try {
                    url = String.format(url, URLEncoder.encode("", "UTF-8"));
                } catch (Exception var20) {
                    url = url;
                }

                URL = url;
                redirectURLSet.add(url);
                if (deplev == 0) {
                    try {
                        response = Jsoup.connect(url.replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(false).timeout(timeout).execute();
                        ActionsDecider.rescode = response.statusCode();
                        if (ActionsDecider.rescode == 200) {
                            LoadedUrl = "";

                            try {
                                LoadedUrl = response.url().toString();
                            } catch (Exception var19) {
                                Loader.log.error(Loader.appendLog(""), var19);
                                LoadedUrl = url;
                            }

                            if (LoadedUrl.isEmpty()) {
                                LoadedUrl = url;
                            }
                        }
                    } catch (UnknownHostException var29) {
                        UnknownHostException ue = var29;

                        try {
                            if (!url.toLowerCase().contains("www.")) {
                                response = Jsoup.connect(url.replaceFirst("(http.*?://)", "$1www.").replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(false).timeout(timeout).execute();
                                ActionsDecider.rescode = response.statusCode();
                                if (ActionsDecider.rescode == 200) {
                                    LoadedUrl = "";

                                    try {
                                        LoadedUrl = response.url().toString();
                                    } catch (Exception var18) {
                                        LoadedUrl = url;
                                    }

                                    if (LoadedUrl.isEmpty()) {
                                        LoadedUrl = url;
                                    }
                                }
                            } else {
                                ActionsDecider.exmsg = ue.getMessage();
                                if (!url.isEmpty()) {
                                    ;
                                }

                                Status = 4;
                            }
                        } catch (Exception var28) {
                            ActionsDecider.exmsg = var28.getMessage();
                            if (!url.isEmpty()) {
                                ;
                            }

                            Status = 4;
                        }
                    } catch (SSLException var30) {
                        try {
                            if (!url.toLowerCase().contains("https")) {
                                url = url.replaceFirst("http", "https").replaceAll(" ", "%20");
                            }

                            URL = url;
                            document = Jsoup.parse(sslcertification.SSl_fun(url), url);
                        } catch (Exception var27) {
                            ActionsDecider.exmsg = var27.getMessage();
                            if (!url.isEmpty()) {
                                ;
                            }

                            Status = 4;
                        }
                    } catch (IOException var31) {
                        IOException ie = var31;

                        try {
                            if (!url.toLowerCase().contains("www.")) {
                                response = Jsoup.connect(url.replaceFirst("(http.*?://)", "$1www.").replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(false).timeout(timeout).execute();
                                ActionsDecider.rescode = response.statusCode();
                                if (ActionsDecider.rescode == 200) {
                                    LoadedUrl = "";

                                    try {
                                        LoadedUrl = response.url().toString();
                                    } catch (Exception var17) {
                                        LoadedUrl = url;
                                    }

                                    if (LoadedUrl.isEmpty()) {
                                        LoadedUrl = url;
                                    }
                                }
                            } else {
                                ActionsDecider.exmsg = ie.getMessage();
                                if (!url.isEmpty()) {
                                    ;
                                }

                                Status = 4;
                            }
                        } catch (Exception var26) {
                            ActionsDecider.exmsg = var26.getMessage();
                            if (!url.isEmpty()) {
                                ;
                            }

                            Status = 4;
                        }
                    } catch (Exception var32) {
                        ActionsDecider.exmsg = var32.getMessage();
                        if (!url.isEmpty()) {
                            ;
                        }

                        Status = 4;
                    }

                    if (ActionsDecider.rescode == 302 || ActionsDecider.rescode == 301 || ActionsDecider.rescode == 303) {
                        int count = 0;

                        do {
                            if (count == 10) {
                                ActionsDecider.exmsg = "Redirecting more than 10 times!";
                                if (!url.isEmpty()) {
                                    ;
                                }

                                Status = 4;
                                break;
                            }

                            String redirecturl = response.header("location");
                            if (!redirecturl.toLowerCase().startsWith("http") && !redirecturl.toLowerCase().startsWith("www")) {
                                String rurl = response.header("location").trim();
                                if (rurl.toLowerCase().startsWith("http")) {
                                    redirecturl = url;
                                    URL = url;
                                } else {
                                    url = url.replaceAll("(?sim)(^.*?\\w)(/\\w.*$)", "$1");
                                    if (!rurl.trim().startsWith("/")) {
                                        redirecturl = url + "/" + rurl;
                                    } else {
                                        redirecturl = url + rurl;
                                    }
                                }
                            }

                            if (redirecturl.trim().length() != 0) {
                                if (url.equalsIgnoreCase(redirecturl)) {
                                    ++count;
                                } else {
                                    url = redirecturl;
                                    URL = redirecturl;
                                    ActionsDecider.redirectflag = 1;

                                    try {
                                        response = Jsoup.connect(url.replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(false).timeout(timeout).execute();
                                        ActionsDecider.rescode = response.statusCode();
                                        if (ActionsDecider.rescode == 200) {
                                            LoadedUrl = url;
                                        }

                                        ++count;
                                    } catch (SSLException var25) {
                                        try {
                                            if (!url.toLowerCase().contains("https")) {
                                                url = url.replaceFirst("http", "https").replaceAll(" ", "%20");
                                            }

                                            document = Jsoup.parse(sslcertification.SSl_fun(url), url);
                                        } catch (Exception var24) {
                                            ActionsDecider.exmsg = var24.getMessage();
                                            if (!redirecturl.isEmpty()) {
                                                ;
                                            }

                                            Status = 4;
                                        }
                                    }
                                }
                            }
                        } while(ActionsDecider.rescode == 302 || ActionsDecider.rescode == 301 || ActionsDecider.rescode == 303);

                        if (ActionsDecider.redirectflag == 1) {
                            ActionsDecider.redirecturl = url;
                        }
                    }
                } else {
                    response = Jsoup.connect(url.replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(true).timeout(timeout).execute();
                    sublink_responsecode = response.statusCode();
                }

                if (response != null) {
                    ActionsDecider.resdesc = response.statusMessage();
                    document = response.parse();
                }

                if (deplev == 0) {
                    Elements meta = document.select("meta");
                    Iterator var38 = meta.iterator();

                    while(var38.hasNext()) {
                        Element met = (Element)var38.next();
                        if (met.attr("http-equiv").toLowerCase().contains("refresh")) {
                            ActionsDecider.redirectflag = 1;

                            try {
                                String rurl = met.attr("content").split("=")[1].trim();
                                if (rurl.replaceAll("[^a-zA-Z0-9]+$", "").equalsIgnoreCase(url.replaceAll("[^a-zA-Z0-9]+$", ""))) {
                                    break;
                                }

                                ActionsDecider.redirectflag = 1;
                                if (!rurl.replaceAll("http(.*?)www(.*?)\\.", "").replaceAll("www(.*?)\\.", "").startsWith(url.replaceAll("http(.*?)www(.*?)\\.", "").replaceAll("www(.*?)\\.", "")) && !rurl.toLowerCase().startsWith("http")) {
                                    if (url.trim().endsWith("/") && rurl.trim().startsWith("/") && !rurl.toLowerCase().startsWith("http")) {
                                        url = url.trim().replaceAll("/$", "") + rurl.trim();
                                    } else if (!url.trim().endsWith("/") && !rurl.trim().startsWith("/") && !rurl.toLowerCase().startsWith("http")) {
                                        url = url + "/" + rurl;
                                    } else {
                                        url = url.trim() + rurl.trim();
                                    }
                                } else {
                                    url = rurl;
                                }

                                ActionsDecider.redirecturl = url;
                            } catch (Exception var23) {
                                ;
                            }
                        }
                    }

                    if (ActionsDecider.redirectflag == 1 && ActionsDecider.redirecturl.trim().length() != 0) {
                        try {
                            response = Jsoup.connect(ActionsDecider.redirecturl.replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(true).timeout(timeout).execute();
                            ActionsDecider.rescode = response.statusCode();
                            if (ActionsDecider.rescode == 200) {
                                LoadedUrl = url;
                            }

                            Status = 1;
                            ActionsDecider.resdesc = response.statusMessage();
                            if (response != null) {
                                document = response.parse();
                            }
                        } catch (SSLException var22) {
                            try {
                                if (!url.toLowerCase().contains("https")) {
                                    url = url.replaceFirst("http", "https").replaceAll(" ", "%20");
                                }

                                document = Jsoup.parse(sslcertification.SSl_fun(url), url);
                            } catch (Exception var21) {
                                ActionsDecider.exmsg = var21.getMessage();
                                if (!url.isEmpty()) {
                                    ;
                                }

                                Status = 4;
                            }
                        }

                        URL = ActionsDecider.redirecturl;
                    }
                }

                if (!orgURL.replaceAll("(?sim)http(s|)://", "").equals(URL.replaceAll("(?sim)http(s|)://", ""))) {
                    if (!redirectURLSet.contains(ActionsDecider.redirecturl)) {
                        redirectURLSet.add(ActionsDecider.redirecturl);
                        URL = ActionsDecider.redirecturl;
                    } else {
                        URL = "";
                        document = null;
                    }
                }

                if (document != null) {
                    docStr = document.toString();
                }
            } catch (IOException var33) {
                document = Jsoup.connect(url).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(true).timeout(timeout).get();
                if (document != null) {
                    docStr = document.toString();
                }
            } catch (Exception var34) {
                ;
            }
        } else if (extract_flag == 3) {
            docStr = "";
        }

        if (!docStr.isEmpty()) {
            psource = docStr;
        }

        if (Status == 0) {
            Status = 1;
        }

        ArrayList<Object> URLOutput = new ArrayList();
        URLOutput.add(docStr);
        URLOutput.add(URL);
        URLOutput.add(String.valueOf(Status));
        if (sublink_responsecode == 0 && ActionsDecider.rescode != 0) {
            sublink_responsecode = ActionsDecider.rescode;
        }

        URLOutput.add(sublink_responsecode);
        URLOutput.add(document);
        return URLOutput;
    }

    public static String JsoupSource(String url) {
        DepthManager.crawlresponse = 0;
        url = url.trim();
        int tout = GetterSetter.getTimeout();
        if (!url.contains("http")) {
            url = "http://" + url;
        }

        Response response = null;
        Document doc = null;
        String PageSource = "";
        LoadedUrl = url;

        try {
            url = String.format(url, URLEncoder.encode("", "UTF-8"));

            try {
                response = Jsoup.connect(url.replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(false).timeout(tout).execute();
                ActionsDecider.rescode = response.statusCode();
                if (ActionsDecider.rescode == 200) {
                    try {
                        LoadedUrl = response.url().toString();
                    } catch (Exception var19) {
                        ;
                    }
                }
            } catch (UnknownHostException var20) {
                UnknownHostException ue = var20;

                try {
                    if (!url.toLowerCase().contains("www.")) {
                        response = Jsoup.connect(url.replaceFirst("(http.*?://)", "$1www.").replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(false).timeout(tout).execute();
                        ActionsDecider.rescode = response.statusCode();
                        DepthManager.crawlresponse = 4;
                        if (ActionsDecider.rescode == 200) {
                            try {
                                LoadedUrl = response.url().toString();
                            } catch (Exception var17) {
                                ;
                            }
                        }
                    } else {
                        ActionsDecider.exmsg = ue.getMessage();
                    }
                } catch (Exception var18) {
                    ActionsDecider.exmsg = var18.getMessage();
                    DepthManager.crawlresponse = 4;
                    Loader.log.error(Loader.appendLog(""), var18);
                }
            } catch (SSLException var21) {
                try {
                    DepthManager.crawlresponse = 4;
                    if (!url.toLowerCase().contains("https")) {
                        url = url.replaceFirst("http", "https").replaceAll(" ", "%20");
                    }

                    doc = Jsoup.parse(sslcertification.SSl_fun(url));
                } catch (Exception var16) {
                    Loader.log.error(Loader.appendLog(""), var16);
                    ActionsDecider.exmsg = var16.getMessage();
                }
            } catch (IOException var22) {
                IOException ie = var22;

                try {
                    DepthManager.crawlresponse = 4;
                    if (!url.toLowerCase().contains("www.")) {
                        response = Jsoup.connect(url.replaceFirst("(http.*?://)", "$1www.").replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(false).timeout(tout).execute();
                        ActionsDecider.rescode = response.statusCode();
                        if (ActionsDecider.rescode == 200) {
                            try {
                                LoadedUrl = response.url().toString();
                            } catch (Exception var14) {
                                ;
                            }
                        }
                    } else {
                        ActionsDecider.exmsg = ie.getMessage();
                    }
                } catch (Exception var15) {
                    DepthManager.crawlresponse = 4;
                    Loader.log.error(Loader.appendLog(""), var15);
                    ActionsDecider.exmsg = var15.getMessage();
                }
            } catch (Exception var23) {
                DepthManager.crawlresponse = 4;
                Loader.log.error(Loader.appendLog(""), var23);
                ActionsDecider.exmsg = var23.getMessage();
            }

            try {
                if (ActionsDecider.rescode == 302 || ActionsDecider.rescode == 301 || ActionsDecider.rescode == 303) {
                    int count = 0;
                    int co = 0;

                    do {
                        if (count == 10) {
                            ActionsDecider.exmsg = "Redirecting more than 10 times!";
                            break;
                        }

                        String redirecturl = response.header("location");
                        if (!redirecturl.toLowerCase().startsWith("http") && !redirecturl.toLowerCase().startsWith("www")) {
                            String rurl = response.header("location").trim();
                            if (rurl.toLowerCase().startsWith("http")) {
                                redirecturl = url;
                            } else {
                                ++co;
                                if (co == 10) {
                                    break;
                                }

                                url = url.replaceAll("(?sim)(^.*?\\w)(/\\w.*$)", "$1");
                                if (!rurl.startsWith("/")) {
                                    redirecturl = url + "/" + rurl;
                                } else {
                                    redirecturl = url + rurl;
                                }
                            }
                        }

                        if (redirecturl.trim().length() != 0) {
                            if (url.equalsIgnoreCase(redirecturl)) {
                                ++count;
                            } else {
                                url = redirecturl;
                                ActionsDecider.redirectflag = 1;

                                try {
                                    response = Jsoup.connect(url.replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(false).timeout(tout).execute();
                                    ActionsDecider.rescode = response.statusCode();
                                    if (ActionsDecider.rescode == 200) {
                                        try {
                                            LoadedUrl = response.url().toString();
                                            redirect = LoadedUrl;
                                        } catch (Exception var11) {
                                            ;
                                        }
                                    }

                                    ++count;
                                } catch (SSLException var12) {
                                    try {
                                        if (!url.toLowerCase().contains("https")) {
                                            url = url.replaceFirst("http", "https").replaceAll(" ", "%20");
                                        }

                                        document = Jsoup.parse(sslcertification.SSl_fun(url), url);
                                    } catch (Exception var10) {
                                        ActionsDecider.exmsg = var10.getMessage();
                                    }
                                } catch (IOException var13) {
                                    ;
                                }
                            }
                        }
                    } while(ActionsDecider.rescode == 302 || ActionsDecider.rescode == 301 || ActionsDecider.rescode == 303);
                }
            } catch (Exception var24) {
                ;
            }

            if (PageSource.equals("")) {
                PageSource = response.body();
            }
        } catch (Exception var25) {
            Loader.log.error(Loader.appendLog(""), var25);
            DepthManager.crawlresponse = 4;
            ActionsDecider.exmsg = var25.getMessage();
        }

        System.gc();
        if (DepthManager.crawlresponse == 0) {
            DepthManager.crawlresponse = 1;
        }

        doc = Jsoup.parse(PageSource);
        DepthManager.doc = doc;
        return PageSource;
    }

    public static String JsoupSource_For_Nested(String url) {
        url = url.trim();
        int tout = GetterSetter.getTimeout();
        if (!url.contains("http")) {
            url = "http://" + url;
        }

        Response response = null;
        Document doc = null;
        String PageSource = "";
        int rescode = 0;

        try {
            url = String.format(url, URLEncoder.encode("", "UTF-8"));

            try {
                response = Jsoup.connect(url.replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(false).timeout(tout).execute();
                rescode = response.statusCode();
            } catch (UnknownHostException var17) {
                try {
                    if (!url.toLowerCase().contains("www.")) {
                        response = Jsoup.connect(url.replaceFirst("(http.*?://)", "$1www.").replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(false).timeout(tout).execute();
                        rescode = response.statusCode();
                    }
                } catch (Exception var16) {
                    Loader.log.error(Loader.appendLog(""), var16);
                }
            } catch (SSLException var18) {
                try {
                    if (!url.toLowerCase().contains("https")) {
                        url = url.replaceFirst("http", "https").replaceAll(" ", "%20");
                    }

                    doc = Jsoup.parse(sslcertification.SSl_fun(url));
                } catch (Exception var15) {
                    Loader.log.error(Loader.appendLog(""), var15);
                }
            } catch (IOException var19) {
                try {
                    if (!url.toLowerCase().contains("www.")) {
                        response = Jsoup.connect(url.replaceFirst("(http.*?://)", "$1www.").replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(false).timeout(tout).execute();
                        rescode = response.statusCode();
                    }
                } catch (Exception var14) {
                    Loader.log.error(Loader.appendLog(""), var14);
                }
            } catch (Exception var20) {
                Loader.log.error(Loader.appendLog(""), var20);
            }

            try {
                if (rescode == 302 || rescode == 301 || rescode == 303) {
                    int count = 0;
                    int co = 0;

                    while(count != 10) {
                        String redirecturl = response.header("location");
                        if (!redirecturl.toLowerCase().startsWith("http") && !redirecturl.toLowerCase().startsWith("www")) {
                            String rurl = response.header("location").trim();
                            if (rurl.toLowerCase().startsWith("http")) {
                                redirecturl = url;
                            } else {
                                ++co;
                                if (co == 10) {
                                    break;
                                }

                                url = url.replaceAll("(?sim)(^.*?\\w)(/\\w.*$)", "$1");
                                if (!rurl.startsWith("/")) {
                                    redirecturl = url + "/" + rurl;
                                } else {
                                    redirecturl = url + rurl;
                                }
                            }
                        }

                        if (redirecturl.trim().length() != 0) {
                            if (url.equalsIgnoreCase(redirecturl)) {
                                ++count;
                            } else {
                                url = redirecturl;

                                try {
                                    response = Jsoup.connect(url.replaceAll(" ", "%20")).userAgent("Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0").followRedirects(false).timeout(tout).execute();
                                    rescode = response.statusCode();
                                    ++count;
                                } catch (SSLException var12) {
                                    try {
                                        if (!url.toLowerCase().contains("https")) {
                                            url = url.replaceFirst("http", "https").replaceAll(" ", "%20");
                                        }

                                        document = Jsoup.parse(sslcertification.SSl_fun(url), url);
                                    } catch (Exception var11) {
                                        ;
                                    }
                                } catch (IOException var13) {
                                    ;
                                }
                            }
                        }

                        if (rescode != 302 && rescode != 301 && rescode != 303) {
                            break;
                        }
                    }
                }
            } catch (Exception var21) {
                ;
            }

            if (PageSource.equals("")) {
                PageSource = response.body();
            }
        } catch (Exception var22) {
            Loader.log.error(Loader.appendLog(""), var22);
        }

        return PageSource;
    }

    public static ArrayList<Object> urlClassLoader(String link, int deplev, int domid) {
        String baseurl = "";
        String docStr = "";
        int Status = 0;
        String Content = "";
        String Desc = "";
        int Code = 0;

        try {
            baseurl = link;
            URL suburl = new URL(link);
            HttpURLConnection huc = (HttpURLConnection)suburl.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            huc.setRequestMethod("GET");
            huc.setConnectTimeout(50000);
            huc.setReadTimeout(50000);
            huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; suburl; Windows NT 5.1; de-AT; rv:1.8a1) Gecko/20040520");
            huc.connect();

            try {
                Code = huc.getResponseCode();
                ActionsDecider.rescode = Code;
                Desc = huc.getResponseMessage();
                ActionsDecider.resdesc = Desc;
                boolean redirect = false;
                if (Code != 200) {
                    if (Code == 302 || Code == 301 || Code == 303) {
                        redirect = true;
                    }
                } else {
                    try {
                        baseurl = huc.getURL().toString();
                        LoadedUrl = baseurl;
                    } catch (Exception var14) {
                        Loader.log.error(Loader.appendLog(""), var14);
                    }
                }

                if (redirect) {
                    String newUrl = huc.getHeaderField("Location");
                    baseurl = newUrl;
                    huc = (HttpURLConnection)(new URL(newUrl)).openConnection();
                    HttpURLConnection.setFollowRedirects(true);
                    huc.setRequestMethod("GET");
                    huc.setConnectTimeout(50000);
                    huc.setReadTimeout(50000);
                    huc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; suburl; Windows NT 5.1; de-AT; rv:1.8a1) Gecko/20040520");
                    huc.connect();
                }
            } catch (Exception var16) {
                ActionsDecider.exmsg = var16.getMessage();
                if (!link.isEmpty()) {
                    ;
                }

                Status = 1;
            }

            try {
                docStr = ParseLinkToURLClass(huc, domid, link, deplev);
                Content = docStr;

                try {
                    document = Jsoup.parse(docStr, baseurl);
                } catch (Exception var13) {
                    ;
                }
            } catch (Exception var15) {
                ActionsDecider.exmsg = var15.getMessage();
                if (!link.isEmpty()) {
                    ;
                }

                Status = 1;
            }

            if (Content == null || Content == "") {
                ActionsDecider.exmsg = "No PageSource";
                if (!link.isEmpty()) {
                    ;
                }

                Status = 1;
            }

            huc.disconnect();
        } catch (Exception var17) {
            ;
        }

        Status = 0;
        ArrayList<Object> URLOutput = new ArrayList();
        URLOutput.add(docStr);
        URLOutput.add(Integer.valueOf(Status));
        URLOutput.add(Code);
        URLOutput.add(Desc);
        URLOutput.add(document);
        return URLOutput;
    }

    public static String ParseLinkToURLClass(HttpURLConnection huc, int domid, String link, int deplev) {
        StringBuilder strbuffer = new StringBuilder();

        try {
            String line = "";
            strbuffer.setLength(0);
            BufferedReader bufferreader = new BufferedReader(new InputStreamReader(huc.getInputStream()));

            while((line = bufferreader.readLine()) != null) {
                strbuffer.append(line);
            }

            bufferreader.close();
        } catch (Exception var7) {
            ActionsDecider.exmsg = var7.getMessage();
            if (!link.isEmpty()) {
                ;
            }
        }

        return strbuffer.toString();
    }

    public static Document getDocument() {
        return document;
    }

    public static void setDocument(Document aDocument) {
        document = aDocument;
    }

    public static ArrayList<Object> GetPageByRubyParser(String url, ScriptEngine jrubyscriptobject) {
        String page = "";
        String lurl = "";
        String msg = "";
        String content_length = "";
        String internal_status = "";
        String error_desc = "";
        int code = 0;

        try {
            List<String> outputrubylist = (List)jrubyscriptobject.eval("fetch_url('" + url + "')");
            page = (String)outputrubylist.get(0);
            internal_status = (String)outputrubylist.get(1);
            lurl = (String)outputrubylist.get(2);
            content_length = (String)outputrubylist.get(3);
            String http_response = (String)outputrubylist.get(4);
            String[] http_resp = http_response.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "").split(",");

            try {
                code = Integer.parseInt(http_resp[0]);
                msg = http_resp[1];
            } catch (Exception var13) {
                ;
            }

            error_desc = (String)outputrubylist.get(5);
            document = Jsoup.parse(page);
        } catch (ScriptException var14) {
            Loader.log.error(Loader.appendLog(""), var14);
        }

        ArrayList<Object> URLOutput = new ArrayList();
        URLOutput.add(page);
        URLOutput.add(lurl);
        URLOutput.add(0);
        URLOutput.add(code);
        URLOutput.add(document);
        return URLOutput;
    }
}
