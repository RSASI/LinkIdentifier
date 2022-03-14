//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VariantLookup {
    public static List<String> ltextList = new ArrayList();
    public static Map<String, String> domainlinkMap = new HashMap();

    public VariantLookup() {
    }

    public static Map<String, String> classify_levels(int plevel, String[] pvarkey, String pdomain, int pdomid, int pdepthlevel, Map<String, String> Others_link) {
        try {
            Map<String, String> Others_links_temp = new HashMap();
            Iterator it = Others_link.keySet().iterator();

            while(it.hasNext()) {
                String link;
                try {
                    link = it.next().toString();
                    link = (String)Others_link.get(link);
                    String[] linktext = link.split(",");
                    new HashSet();
                    String vartablename = "";
                    String main_domain = "";
                    String variants = "";
                    String absurl = "";
                    String sub_domain = "";
                    String lntext = "";
                    String orgtext = "";
                    String text = "";
                    String absoulteLink_temp = "";
                    String sub_Domain = "";
                    String absurl1 = "";
                    String dbvar = "";
                    String ltext = "";
                    String updvar = "";
                    String addressTabName = "";
                    String var27 = "";
                } catch (Exception var28) {
                    Loader.log.error(Loader.appendLog(""), var28);
                }

                Iterator iter = Others_links_temp.keySet().iterator();

                while(iter.hasNext()) {
                    link = iter.next().toString();
                    Initialize.Others.remove(link);
                    Others_link.remove(link);
                }

                Others_links_temp.clear();
            }
        } catch (Exception var29) {
            ;
        }

        return Others_link;
    }

    public static Map<String, String> linkTextTableVariantsLookup(Document pdocument, String purl, Set<String> psubLinksSet, int pdomid, int pdepthlevel, Set<String> subLinksOfParentURL) throws SQLException {
        Map<String, String> Others_links = new HashMap();
        Elements ele = pdocument.getElementsByTag("iframe");
        Elements ele1 = pdocument.getElementsByTag("frame");
        purl = purl.trim();
        Iterator var9 = ele1.iterator();

        Element e;
        String link;
        String tempLink;
        String ltext;
        Iterator var22;
        while(var9.hasNext()) {
            e = (Element)var9.next();
            link = "";
            link = e.attr("src");
            link = link.replaceAll("(?sim) ", "");
            link = link.replaceAll("(?sim)\r\n", "");
            link = link.replaceAll("(?sim)\n", "");
            link = link.replaceAll("(?sim)///", "/");
            if (!link.contains(purl.toLowerCase().replaceAll("http.*?www.", "").replaceAll("http.*?://", "").replaceAll("(?sim)/.*?$", ""))) {
                tempLink = link.replaceAll("(?sim)^//", "");
                tempLink = tempLink.replaceAll("(?sim)^/", "");
                if (!isMatchWithRegex(tempLink, "^https://") && !isMatchWithRegex(tempLink, "^http://") && !isMatchWithRegex(tempLink, "^www")) {
                    link = purl.replaceAll("/$", "") + "/" + link.replaceFirst("/", "");
                    DepthManager.Frame_links.add(link);
                    subLinksOfParentURL.add(link);
                }
            }

            var22 = TLD_Init.GeneralSet.iterator();

            while(var22.hasNext()) {
                ltext = (String)var22.next();
                if (link.contains(ltext)) {
                    DepthManager.Frame_links.remove(link);
                    if (subLinksOfParentURL.size() != 0) {
                        subLinksOfParentURL.remove(link);
                    }
                }
            }
        }

        ele1 = pdocument.getElementsByTag("iframe");
        var9 = ele1.iterator();

        while(var9.hasNext()) {
            e = (Element)var9.next();
            link = "";
            link = e.attr("src");
            link = link.replaceAll("(?sim) ", "");
            link = link.replaceAll("(?sim)\r\n", "");
            link = link.replaceAll("(?sim)\n", "");
            link = link.replaceAll("(?sim)///", "/");
            if (!link.contains(purl.toLowerCase().replaceAll("http.*?www.", "").replaceAll("http.*?://", "").replaceAll("(?sim)/.*?$", ""))) {
                tempLink = link.replaceAll("(?sim)^//", "");
                tempLink = tempLink.replaceAll("(?sim)^/", "");
                if (!isMatchWithRegex(tempLink, "^https://") && !isMatchWithRegex(tempLink, "^http://") && !isMatchWithRegex(tempLink, "^www")) {
                    link = purl.replaceAll("/$", "") + "/" + link.replaceFirst("/", "");
                    DepthManager.Frame_links.add(link);
                    subLinksOfParentURL.add(link);
                }
            }

            var22 = TLD_Init.GeneralSet.iterator();

            while(var22.hasNext()) {
                ltext = (String)var22.next();
                if (link.contains(ltext)) {
                    DepthManager.Frame_links.remove(link);
                    if (subLinksOfParentURL.size() != 0) {
                        subLinksOfParentURL.remove(link);
                    }
                }
            }
        }

        ltextList.clear();
        boolean is_junk = false;
        if (pdocument != null) {
            Elements anchorlist = pdocument.getElementsByTag("a");
            if (anchorlist != null) {
                Iterator var24 = anchorlist.iterator();

                while(var24.hasNext()) {
                    Element anchorLink = (Element)var24.next();
                    is_junk = false;

                    try {
                        ltext = anchorLink.text();
                        if (!ltext.isEmpty()) {
                            ltext = KeyLookup.LinkTextClean(ltext);
                        }

                        String u = anchorLink.absUrl("href");
                        if (u == null) {
                            u = "";
                        }

                        if (u.trim().length() == 0) {
                            u = anchorLink.attr("href");
                        }

                        u = u.replaceAll("(?sim) ", "");
                        u = u.replaceAll("(?sim)\r\n", "");
                        u = u.replaceAll("(?sim)\n", "");
                        u = u.replaceAll("(?sim)///", "/");
                        tempLink = u.replaceAll("(?sim)^//", "");
                        tempLink = u.replaceAll("(?sim)^/", "");
                        if (isMatchWithRegex(tempLink, "^https://") || isMatchWithRegex(tempLink, "^http://") || isMatchWithRegex(tempLink, "^www")) {
                            String js;
                            String httpsrem;
                            if (u.toLowerCase().contains("javascript")) {
                                js = anchorLink.attr("onclick");
                                if (js.toLowerCase().contains("window.location")) {
                                    httpsrem = js.split("=")[1].trim();
                                    httpsrem = httpsrem.replaceFirst("^'", "").replaceAll("'$", "");
                                    if (httpsrem.replaceAll("\\W", "").trim().length() != 0 && httpsrem != null && httpsrem.trim().length() != 0) {
                                        if (httpsrem.toLowerCase().contains("http")) {
                                            u = httpsrem;
                                        } else if (purl.endsWith("/") && httpsrem.startsWith("/")) {
                                            u = purl.replaceAll("/$", "");
                                        } else if (!purl.endsWith("/") && !httpsrem.startsWith("/")) {
                                            u = purl + "/" + httpsrem;
                                        } else {
                                            u = purl + httpsrem;
                                        }
                                    }
                                } else {
                                    u = "";
                                }
                            }

                            u = u.replaceAll("\\.\\./", "");
                            js = u.replaceAll("(?sim)(http|https)://", "");
                            js = js.replaceAll("(?sim)www\\.", "");
                            js = js.replaceAll("(?sim)/.*?$", "");
                            if (js.toLowerCase().contains(purl.toLowerCase().replaceAll("http.*?www.", "").replaceAll("http.*?://", "").replaceAll("(?sim)/.*?$", ""))) {
                                if (u.endsWith("#")) {
                                    u = u.replaceAll("#$", "");
                                }

                                if (u.endsWith("/")) {
                                    u = u.replaceAll("/$", "");
                                }

                                if (!u.toLowerCase().startsWith("mailto") && !u.toLowerCase().startsWith("javascript")) {
                                    if (u.matches("(?sim)^[a-z]*\\d*\\.(html|htm)+$")) {
                                        u = purl + u;
                                    }

                                    if (!u.toLowerCase().endsWith(".exe") && !u.toLowerCase().endsWith(".xml") && !u.toLowerCase().endsWith(".rpm") && !u.toLowerCase().endsWith(".pdf") && !u.toLowerCase().endsWith(".ppt") && !u.toLowerCase().endsWith(".pptx") && !u.toLowerCase().endsWith(".xls") && !u.toLowerCase().endsWith(".xlsx") && !u.toLowerCase().endsWith(".doc") && !u.toLowerCase().endsWith(".docx") && !u.toLowerCase().endsWith(".jpg") && !u.toLowerCase().endsWith(".png") && !u.toLowerCase().endsWith(".flv") && !u.toLowerCase().endsWith(".avi") && !u.toLowerCase().endsWith(".f4v") && !u.toLowerCase().endsWith(".mp4") && !u.toLowerCase().endsWith(".mpg") && !u.toLowerCase().endsWith(".mpeg") && !u.toLowerCase().endsWith(".rar") && !u.toLowerCase().endsWith(".zip") && !u.toLowerCase().endsWith(".gif") && !u.toLowerCase().endsWith(".jpeg") && !u.toLowerCase().endsWith(".mp3") && !u.toLowerCase().endsWith(".mp2") && !u.toLowerCase().endsWith(".3gp") && !u.toLowerCase().endsWith(".m4v") && !u.toLowerCase().endsWith(".flr") && !u.toLowerCase().endsWith(".fla") && !u.toLowerCase().endsWith(".mng") && !u.toLowerCase().endsWith(".mov") && !u.toLowerCase().endsWith(".mpe") && !u.toLowerCase().endsWith(".swf") && !u.toLowerCase().endsWith(".wmv") && !u.toLowerCase().endsWith("facebook.com") && !u.toLowerCase().endsWith("twitter.com") && !u.toLowerCase().endsWith("youtube.com") && !u.toLowerCase().endsWith("linkedin.com") && !u.toLowerCase().endsWith("pinterest.com") && !u.toLowerCase().endsWith("flickr.com") && !u.toLowerCase().endsWith("myspace.com") && !u.toLowerCase().endsWith("instagram.com") && !psubLinksSet.contains(u)) {
                                        u = u.trim();
                                        if (u.contains("aboutus.asp?active_page_")) {
                                            ;
                                        }

                                        String httpsadd;
                                        if (GetterSetter.isJunkFlag()) {
                                            Iterator var25 = TLD_Init.JunkRemovalSet.iterator();

                                            while(var25.hasNext()) {
                                                httpsadd = (String)var25.next();
                                                if (u.toLowerCase().contains(httpsadd.toLowerCase())) {
                                                    is_junk = true;
                                                    break;
                                                }
                                            }
                                        }

                                        if (!is_junk) {
                                            httpsrem = u.replaceAll("https://", "http://");
                                            httpsadd = u.replaceAll("http://", "https://");
                                            if (!psubLinksSet.contains(httpsrem) && !psubLinksSet.contains(httpsadd)) {
                                                psubLinksSet.add(u);
                                                ltextList.add(ltext);
                                                domainlinkMap.put(u, ltext);
                                                subLinksOfParentURL.add(u);
                                            }
                                            
                                            
                                            
                                        }
                                    }
                                }
                            }
                            else if (GetterSetter.isOtherDomain()){
                                                psubLinksSet.add(u);
                                                ltextList.add(ltext);
                                                domainlinkMap.put(u, ltext);
                                                subLinksOfParentURL.add(u);
                            }
                            
                            
                            
                        }
                    } catch (Exception var19) {
                        ;
                    }
                }
            }
        }

        return Others_links;
    }

    public static boolean isMatchWithRegex(String content, String regex1) {
        try {
            Pattern regex = Pattern.compile(regex1, 234);
            Matcher regexMatcher = regex.matcher(content);
            if (regexMatcher.find()) {
                return true;
            }
        } catch (Exception var4) {
            ;
        }

        return false;
    }
}
