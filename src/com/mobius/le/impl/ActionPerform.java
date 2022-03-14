//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;

public class ActionPerform {
    public ActionPerform() {
    }

    public static ArrayList<String> ActionPerformed(String actionurl, Set<String> s, int domainid, int depthlevel, int extract_flag, int depth) {
        int Status = 0;
        boolean is_procesed = false;
        boolean flagRedirection = false;
        Set<String> subLinksOFParentURL = new HashSet();
        Map<String, String> Others_Links = new HashMap();
        String RedirectURL = "";
        String PageSource = "";
        int sublink_responsecode = 0;
        String sublink_responsedesc = "";
        Boolean linkClassificationFlag = false;
        if (DepthManager.stopsignal != 1) {
            Document var16 = null;

            try {
                if (GetterSetter.getLoadFromProxy()) {
                    System.setProperty("http.proxyHost", GetterSetter.getProxyIP());
                    System.setProperty("http.proxyPort", GetterSetter.getProxyPort());
                }

                int status = 0;
                if (!actionurl.toLowerCase().endsWith(".exe") && !actionurl.toLowerCase().endsWith(".xml") && !actionurl.toLowerCase().endsWith(".rpm") && !actionurl.toLowerCase().endsWith(".pdf") && !actionurl.toLowerCase().endsWith(".xls") && !actionurl.toLowerCase().endsWith(".doc") && !actionurl.toLowerCase().endsWith(".jpg") && !actionurl.toLowerCase().endsWith(".png") && !actionurl.toLowerCase().endsWith(".flv") && !actionurl.toLowerCase().endsWith(".avi") && !actionurl.toLowerCase().endsWith(".f4v") && !actionurl.toLowerCase().endsWith(".mp4") && !actionurl.toLowerCase().endsWith(".mpg") && !actionurl.toLowerCase().endsWith(".mpeg") && !actionurl.toLowerCase().endsWith(".rar") && !actionurl.toLowerCase().endsWith(".zip") && !actionurl.toLowerCase().endsWith(".gif") && !actionurl.toLowerCase().endsWith(".jpeg") && !actionurl.toLowerCase().endsWith(".mp3") && !actionurl.toLowerCase().endsWith(".mp2") && !actionurl.toLowerCase().endsWith(".3gp") && !actionurl.toLowerCase().endsWith(".m4v") && !actionurl.toLowerCase().endsWith(".flr") && !actionurl.toLowerCase().endsWith(".fla") && !actionurl.toLowerCase().endsWith(".mng") && !actionurl.toLowerCase().endsWith(".mov") && !actionurl.toLowerCase().endsWith(".mpe") && !actionurl.toLowerCase().endsWith(".swf") && !actionurl.toLowerCase().endsWith(".wmv")) {
                    int hashcode = (domainid + actionurl).hashCode();
                    boolean result = Is_Duplicate(hashcode);
                    if (!result) {
                        String docStr = "";
                        if (depthlevel <= depth) {
                            ArrayList URLValidatorOutput = null;
                            if (PageSource.trim().isEmpty()) {
                                long millis = System.currentTimeMillis();
                                long startSeconds = TimeUnit.MILLISECONDS.toSeconds(millis);
                                URLValidatorOutput = URLValidator.validate(extract_flag, depthlevel, actionurl, GetterSetter.getTimeout(), domainid);
                                millis = System.currentTimeMillis();
                                long endSeconds = TimeUnit.MILLISECONDS.toSeconds(millis);
                                int diff = (int)(endSeconds - startSeconds);
                                DepthManager.totalResponseTime += diff;
                                PageSource = (String)URLValidatorOutput.get(0);
                                RedirectURL = (String)URLValidatorOutput.get(1);
                                Status = Integer.parseInt((String)URLValidatorOutput.get(2));
                                sublink_responsecode = (Integer)URLValidatorOutput.get(3);

                                try {
                                    var16 = (Document)URLValidatorOutput.get(4);
                                } catch (Exception var31) {
                                    ;
                                }
                            }

                            if (RedirectURL.length() > 1 && depth == 0) {
                                DepthManager.domainURL = RedirectURL;
                            }

                            if (RedirectURL.length() > 0) {
                                if (PageSource.trim().length() == 0) {
                                    URLValidatorOutput = URLValidator.urlClassLoader(RedirectURL, depthlevel, domainid);
                                    PageSource = (String)URLValidatorOutput.get(0);
                                    Status = (Integer)URLValidatorOutput.get(1);
                                    if (PageSource.trim().isEmpty()) {
                                        Status = 4;
                                    }

                                    sublink_responsecode = (Integer)URLValidatorOutput.get(2);
                                    sublink_responsedesc = (String)URLValidatorOutput.get(3);

                                    try {
                                        var16 = (Document)URLValidatorOutput.get(4);
                                    } catch (Exception var30) {
                                        ;
                                    }
                                }

                                String docStrTemp = PageSource.trim();
                                docStr = PageSource.trim();
                                DepthManager.stype = KeyLookup.SiteType(docStr);
                                ActionsDecider.redirectFlagManager(depthlevel);
                                if (DepthManager.depthlevel == 0) {
                                    Pattern patternRegex = Pattern.compile("[^://]+/(.*?)$", 234);
                                    Matcher regexMatcher = patternRegex.matcher(DepthManager.domainURL);
                                    if (regexMatcher.find()) {
                                        DepthManager.domainURL = DepthManager.domainURL.replace(regexMatcher.group(1), "");
                                    }
                                }

                                docStr = Source_clean(docStr);
                                if (depthlevel <= depth) {
                                    Others_Links = VariantLookup.linkTextTableVariantsLookup(URLValidator.getDocument(), DepthManager.domainURL, s, domainid, depthlevel, subLinksOFParentURL);
                                }
                            }
                        }

                        if (extract_flag != 3) {
                            Loader.log.info(Loader.appendLog("Extracted Links " + subLinksOFParentURL.size() + " for this URL " + actionurl + " at depth level " + depthlevel + " status of link " + Status));
                            String tempRedirect = "";
                            if (!"".equals(RedirectURL) && RedirectURL != null && RedirectURL.length() != 0) {
                                tempRedirect = RedirectURL;
                            } else {
                                tempRedirect = actionurl;
                            }

                            flagRedirection = isRedirectTheGivenURL(actionurl, tempRedirect);
                            if (depthlevel == 0) {
                                MySQL_Interface.insertOutputTable(domainid, tempRedirect, DepthManager.stype, depthlevel, ActionsDecider.rescode, sublink_responsecode, sublink_responsedesc, flagRedirection + "");
                                is_procesed = true;
                            } else {
                                if ("".equals(RedirectURL) || RedirectURL == null || RedirectURL.length() == 0) {
                                    RedirectURL = actionurl.trim();
                                }

                                if (!RedirectURL.isEmpty()) {
                                    MySQL_Interface.insertOutputTable(domainid, RedirectURL, DepthManager.stype, depthlevel, sublink_responsecode, sublink_responsecode, sublink_responsedesc, flagRedirection + "");
                                    is_procesed = true;
                                }
                            }
                        }
                    } else {
                        Loader.log.info(Loader.appendLog("Inside the ActionPerformed  Duplicate url at depth level :" + depthlevel + " For This URL " + actionurl));
                    }
                } else {
                     status = 1;
                    if (extract_flag != 3 && !actionurl.isEmpty()) {
                        Loader.log.info(Loader.appendLog("Inside the ActionPerformed  Non Html url at depth level :" + depthlevel + " For This URL " + actionurl));
                        MySQL_Interface.insertOutputTableNonHTML(domainid, actionurl, depthlevel, status, sublink_responsecode);
                    }
                }
            } catch (Exception var33) {
                Exception e = var33;
                Loader.log.error(Loader.appendLog("Exception URL "), var33);
                Loader.log.error(Loader.appendLog("Exception URL : " + actionurl + " at Depth level " + depthlevel + " Exception is " + var33.toString()));

                try {
                    if ("".equals(RedirectURL) || RedirectURL == null || RedirectURL.length() == 0) {
                        RedirectURL = actionurl.trim();
                    }

                    if (!RedirectURL.isEmpty()) {
                        MySQL_Interface.exceptionurlinsert(2, domainid, RedirectURL, depthlevel, sublink_responsecode, flagRedirection + "", e.toString());
                    }

                    Status = 4;
                } catch (Exception var32) {
                    ;
                }
            }
        }

        ((Map)Others_Links).clear();
        if (Status == 0 && depthlevel == 0) {
            Status = 1;
        }

        ArrayList URLOutput = new ArrayList();
        URLOutput.add(PageSource);
        URLOutput.add(String.valueOf(Status));
        return URLOutput;
    }

    public static boolean isRedirectTheGivenURL(String url, String landingUrl) {
        String tempRedirec = removeURLProtocal(landingUrl);
        String tempOriginalUrl = removeURLProtocal(url);
        return !tempRedirec.equals(tempOriginalUrl);
    }

    public static String removeURLProtocal(String tempUrl) {
        String tempRedirec = tempUrl.replaceAll("(?sim)^https://", "");
        tempRedirec = tempRedirec.replaceAll("(?sim)^http://", "");
        tempRedirec = tempRedirec.replaceAll("(?sim)^www\\.", "");
        tempRedirec = tempRedirec.replaceAll("(?sim)/$", "");
        return tempRedirec;
    }

    public static String Source_clean(String docStr) {
        docStr = docStr.replaceAll("(?sim)<script.*?>.*?</script>", "");
        docStr = docStr.replaceAll("(?sim)<style.*?>.*?</style>", "");
        docStr = docStr.replaceAll("(?sim)<!--.*?-->", "");
        docStr = docStr.replaceAll("(?sim)\\s+", " ");
        docStr = docStr.replaceAll("<[^<>]*>", "");
        docStr = docStr.trim();
        return docStr;
    }

    static boolean Is_Duplicate(int hashcode) {
        boolean result = false;

        try {
            if (DepthManager.domainid_url_hashcode.size() == 0) {
                DepthManager.domainid_url_hashcode.add(hashcode);
                result = false;
            } else {
                int data_list_count = 0;
                data_list_count = DepthManager.domainid_url_hashcode.size();
                DepthManager.domainid_url_hashcode.add(hashcode);
                if (data_list_count < DepthManager.domainid_url_hashcode.size()) {
                    result = false;
                } else {
                    result = true;
                }
            }
        } catch (Exception var3) {
            Loader.log.error(Loader.appendLog(""), var3);
        }

        return result;
    }
}
