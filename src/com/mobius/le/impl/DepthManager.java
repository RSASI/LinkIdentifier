//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import com.mobius.le.model.TempOutput;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DepthManager {
    public static int linksize = 0;
    public static int statuscode = 0;
    public static String domainURL = "";
    public static String linkText = "";
    public static String Extraction_Type = "";
    public static String stype = "";
    public static String serr = "";
    public static String navigateLevel = "";
    public static int increment = 1;
    public static int crawlresponse = 0;
    public static int Privacyflag = 0;
    public static int setcheck = 0;
    public static int linklimit = 0;
    public static int stopsignal = 0;
    public static int NewsReleaseflag = 0;
    public static int depthlevel = 0;
    public static int outputFlag = 0;
    public static int sitemapwebsite = 0;
    public static int framesize = 0;
    public static Statement totalcount_stmt;
    private static ArrayList<String> URLValidatorOutput = null;
    static Set<String> s1 = null;
    static Set<String> Frame_links = null;
    static Set<String> tset = new TreeSet();
    public static Map<String, String> dlmap = new HashMap();
    static Integer domainid;
    static Integer depth;
    static Set<Integer> domainid_url_hashcode = new HashSet();
    static Set<String> curr = new HashSet();
    static Set<String> ltset = new TreeSet();
    static Set<String> dcheck = new HashSet();
    static boolean Depth0_extraction = false;
    static ArrayList<String> pageNotFoundList = new ArrayList();
    static List<String> sitekeys = new ArrayList();
    static Document doc;
    static int insertCount = 0;
    static int totalResponseTime = 0;
    public static HashMap<String, String> slinktextMap = new HashMap();

    public DepthManager() {
    }

//    public static void manage(String URLID,String Link) throws SQLException {
    public static void manage() throws SQLException {
        long start = System.currentTimeMillis();
        TLD_Init.TLD_Init();
        linklimit = GetterSetter.getLinkLimit();
        new Date();
        new SimpleDateFormat("HH:mm:ss");
        pageNotFoundList = TLD_Init.readFile("Essentials/PageNotFound.txt");
        sitekeys = TLD_Init.readFile("Essentials/SiteMapKeywords.txt");
        int exceptionFlagStop = 0;
        int itr = 0;
        long Start = System.currentTimeMillis();
        int var38 = itr + 1;
        boolean lowCoverageInSitemap = false;
        domainid_url_hashcode.clear();
        Depth0_extraction = false;
        s1 = new TreeSet();
        Frame_links = new TreeSet();
        Set<String> s2 = new TreeSet();
        depthlevel = 0;
        URLValidatorOutput = null;
        String durl = "";
//        boolean exceptionflag = false;

        try {
            clearList();
            byte exceptionflag;
            if (stopsignal == 1) {
                exceptionflag = 1;
                stopProcess(exceptionflag);
            } else {
                String linkCount;
                try {
                    if (outputFlag == 1) {
                        ;
                    }

                    domainid = 1;
//                    String domain = Link;
                    String domain = Loader.link;
                    depth = GetterSetter.getDepthLevel();
                    Loader.log.info(Loader.appendLog("\nDomain ID - " + domainid));
                    Loader.log.info(Loader.appendLog("Domain Name - " + domain));
                    if (domainid == null) {
                        Loader.log.info(Loader.appendLog("Domain ID not found!"));
                        exceptionflag = 1;
                        stopProcess(exceptionflag);
                    } else if (depth == null) {
                        Loader.log.info(Loader.appendLog("Depth level not found!"));
                        exceptionflag = 1;
                        stopProcess(exceptionflag);
                    } else if (domain == null) {
                        exceptionflag = 1;
                        stopProcess(exceptionflag);
                    } else if (depth == 0) {
                        depth = 5;
                    } else {
                        if (outputFlag == 0) {
                            outputFlag = 1;
                        }

                        domain = domain.trim();
                        if (domain.startsWith("http")) {
                            domainURL = domain;
                        } else {
                            domainURL = "http://" + domain;
                        }

                        durl = domainURL;
                        if (!dcheck.contains(domainURL)) {
                            dcheck.add(domainURL);
                        } else {
                            crawlresponse = 2;
                            ActionsDecider.exmsg = "duplicate url";
                            Extraction_Type = "";
                        }

                        statuscode = 0;
                        linksize = 0;
                        
                        if (!GetterSetter.isSitemapFlag() && !GetterSetter.isDepthWiseFlag()) {
//                            System.exit(0);
                            
                        }
                    }

                    if (GetterSetter.isDepthWiseFlag()) {
                        int lsize = 0;
                        if (lowCoverageInSitemap) {
                            statuscode = 0;
                            lowCoverageInSitemap = false;
                            Extraction_Type = "Sitemap/Depth";
                            lsize = linksize;
                            ThreadScheduler.check.addAll(curr);
                        } else if (linksize == 0) {
                            Extraction_Type = "Depth";
                        }

                        if (statuscode == 0) {
                            domainURL = durl;
                            crawlresponse = 0;
                            linksize = 0;
                            if (GetterSetter.isSitemapFlag() && serr.isEmpty()) {
                                serr = ActionsDecider.exmsg;
                            }

                            for(int l = 0; l <= depth; ++l) {
                                try {
//                                    String linkCount;
                                    if (l == 0) {
                                        depthlevel = 0;
                                        framesize = 0;
                                        ThreadScheduler.check.add(domainURL);
                                        linkCount = "";
                                        Loader.log.info("ActionPerform - depth level -0 start");
                                        URLValidatorOutput = ActionPerform.ActionPerformed(domainURL, s1, domainid, depthlevel, 1, depth);
                                        if (stype.equalsIgnoreCase("Frame Site") && Frame_links.size() > 0) {
                                            ArrayList<String> URLValidatorOutput_temp = new ArrayList();
    
                                            URLValidatorOutput_temp.addAll(URLValidatorOutput);
                                            Set<String> s1_temp = new TreeSet();
                                            s1_temp.addAll(s1);
                                            s1.clear();
                                            Iterator var18 = Frame_links.iterator();

                                            while(var18.hasNext()) {
                                                String frame_link = (String)var18.next();
                                                frame_link = frame_link.replaceAll("\\.\\./", "");
                                                String temp = frame_link.replaceAll("(?sim)(http|https)://", "");
                                                temp = temp.replaceAll("(?sim)www\\.", "");
                                                temp = temp.replaceAll("(?sim)/.*?$", "");
                                                if (temp.toLowerCase().contains(domainURL.toLowerCase().replaceAll("http.*?www.", "").replaceAll("http.*?://", "").replaceAll("(?sim)/.*?$", ""))) {
                                                    URLValidatorOutput = ActionPerform.ActionPerformed(frame_link, s1, domainid, depthlevel, 1, depth);
                                                    ++framesize;
                                                    s1_temp.addAll(s1);
                                                }
                                            }

                                            if (framesize != 0) {
                                                linksize = framesize;
                                                Loader.log.info(Loader.appendLog("Extracting " + linksize + " framelinks"));
                                            }

                                            URLValidatorOutput.clear();
                                            URLValidatorOutput.addAll(URLValidatorOutput_temp);
                                            s1.clear();
                                            s1.addAll(s1_temp);
                                        }

                                        crawlresponse = Integer.parseInt((String)URLValidatorOutput.get(1));
                                        if (s1.size() == 0) {
                                            navigateLevel = "Depth " + depthlevel + " Level Completed - No More Levels";
                                            break;
                                        }

                                        linksize = s1.size();
                                        Loader.log.info("ActionPerform - depth level -0 end");
                                    }

                                    if (linklimit != 0 && insertCount >= linklimit) {
                                        ActionsDecider.exmsg = "Link count exceeds " + linklimit;
                                        crawlresponse = 6;
                                        break;
                                    }

                                    if (l == 1) {
                                        depthlevel = 1;
                                        Loader.log.info("ActionPerform - depth level -1 start");
                                        Loader.log.info(Loader.appendLog("Extracting " + s1.size() + " links at depth 1..."));
                                        linksize = framesize + s1.size();
                                        linkCount = Integer.toString(linksize);
                                        if (s1.isEmpty()) {
                                            navigateLevel = "Depth 0 Level Completed - No More Levels";
                                            break;
                                        }

                                        curr = s1;
                                        ltset = s1;
                                        dlmap = VariantLookup.domainlinkMap;
                                        if (s1.size() > 25) {
                                            selectedNodes(s1, tset, 25);
                                            setcheck = 1;
                                        }

                                        if (insertCount >= linklimit) {
                                            ActionsDecider.exmsg = "Link count exceeds " + linklimit;
                                            crawlresponse = 6;
                                            break;
                                        }

                                        if (setcheck == 1) {
                                            ThreadScheduler.threadingSchedule(tset, GetterSetter.getThreadSize(), s2, domainid, depthlevel, depth);
                                            setcheck = 0;
                                        } else {
                                            ThreadScheduler.threadingSchedule(s1, GetterSetter.getThreadSize(), s2, domainid, depthlevel, depth);
                                        }

                                        curr.clear();
                                        s1.clear();
                                        tset.clear();
                                        URLValidator.psource = "";
                                        Loader.log.info("ActionPerform - depth level  -1  end");
                                    }

                                    int sizeOfLink;
                                    if (l == 2) {
                                        depthlevel = 2;
                                        Loader.log.info("ActionPerform - depth level -2 start");
                                        Loader.log.info(Loader.appendLog("Extracting " + s2.size() + " links at depth 2..."));
                                        sizeOfLink = s2.size();
                                        linkCount = Integer.toString(sizeOfLink);
                                        linksize += sizeOfLink;
                                        if (s2.isEmpty()) {
                                            navigateLevel = "Depth 1 Level Completed - No More Levels";
                                            break;
                                        }

                                        curr = s2;
                                        ltset = s2;
                                        if (s2.size() > 20) {
                                            selectedNodes(s2, tset, 20);
                                            setcheck = 1;
                                        }

                                        if (insertCount >= linklimit) {
                                            ActionsDecider.exmsg = "Link count exceeds " + linklimit;
                                            crawlresponse = 6;
                                            break;
                                        }

                                        if (setcheck == 1) {
                                            ThreadScheduler.threadingSchedule(tset, GetterSetter.getThreadSize(), s1, domainid, depthlevel, depth);
                                            setcheck = 0;
                                        } else {
                                            ThreadScheduler.threadingSchedule(s2, GetterSetter.getThreadSize(), s1, domainid, depthlevel, depth);
                                        }

                                        curr.clear();
                                        s2.clear();
                                        tset.clear();
                                        URLValidator.psource = "";
                                        Loader.log.info("ActionPerform - depth level -2 end");
                                    }

                                    if (l == 3) {
                                        depthlevel = 3;
                                        Loader.log.info("ActionPerform - depth level -3 start");
                                        Loader.log.info(Loader.appendLog("Extracting " + s1.size() + " links at depth 3..."));
                                        sizeOfLink = s1.size();
                                        linkCount = Integer.toString(sizeOfLink);
                                        linksize += sizeOfLink;
                                        curr = s1;
                                        ltset = s1;
                                        if (s1.isEmpty()) {
                                            navigateLevel = "Depth 2 Level Completed - No More Levels";
                                            break;
                                        }

                                        if (s1.size() > 15) {
                                            selectedNodes(s1, tset, 15);
                                            setcheck = 1;
                                        }

                                        if (insertCount >= linklimit) {
                                            ActionsDecider.exmsg = "Link count exceeds " + linklimit;
                                            crawlresponse = 6;
                                            break;
                                        }

                                        if (setcheck == 1) {
                                            ThreadScheduler.threadingSchedule(tset, GetterSetter.getThreadSize(), s2, domainid, depthlevel, depth);
                                            setcheck = 0;
                                        } else {
                                            ThreadScheduler.threadingSchedule(s1, GetterSetter.getThreadSize(), s2, domainid, depthlevel, depth);
                                        }

                                        curr.clear();
                                        s1.clear();
                                        tset.clear();
                                        URLValidator.psource = "";
                                        Loader.log.info("ActionPerform - depth level - 3 end");
                                    }

                                    if (l == 4) {
                                        depthlevel = 4;
                                        Loader.log.info(Loader.appendLog("Extracting " + s2.size() + " links at depth 4..."));
                                        sizeOfLink = s2.size();
                                        linksize += sizeOfLink;
                                        linkCount = Integer.toString(sizeOfLink);
                                        if (s2.isEmpty()) {
                                            navigateLevel = "Depth 3 Level Completed - No More Levels";
                                            break;
                                        }

                                        curr = s2;
                                        ltset = s2;
                                        if (s2.size() > 10) {
                                            selectedNodes(s2, tset, 10);
                                            setcheck = 1;
                                        }

                                        if (insertCount >= linklimit) {
                                            ActionsDecider.exmsg = "Link count exceeds " + linklimit;
                                            crawlresponse = 6;
                                            break;
                                        }

                                        if (setcheck == 1) {
                                            ThreadScheduler.threadingSchedule(tset, GetterSetter.getThreadSize(), s1, domainid, depthlevel, depth);
                                            setcheck = 0;
                                        } else {
                                            ThreadScheduler.threadingSchedule(s2, GetterSetter.getThreadSize(), s1, domainid, depthlevel, depth);
                                        }

                                        curr.clear();
                                        s2.clear();
                                        tset.clear();
                                        URLValidator.psource = "";
                                    }

                                    if (l == 5) {
                                        depthlevel = 5;
                                        Loader.log.info(Loader.appendLog("Extracting " + s1.size() + " links at depth 5..."));
                                        sizeOfLink = s1.size();
                                        linksize += sizeOfLink;
                                        linkCount = Integer.toString(sizeOfLink);
                                        if (s1.isEmpty()) {
                                            navigateLevel = "Depth 4 Level Completed - No More Levels";
                                            break;
                                        }

                                        curr = s1;
                                        ltset = s1;
                                        if (s1.size() > 10) {
                                            selectedNodes(s1, tset, 10);
                                            setcheck = 1;
                                        }

                                        if (insertCount >= linklimit) {
                                            ActionsDecider.exmsg = "Link count exceeds " + linklimit;
                                            crawlresponse = 6;
                                            break;
                                        }

                                        if (setcheck == 1) {
                                            ThreadScheduler.threadingSchedule(tset, GetterSetter.getThreadSize(), s2, domainid, depthlevel, depth);
                                            setcheck = 0;
                                        } else {
                                            ThreadScheduler.threadingSchedule(s1, GetterSetter.getThreadSize(), s2, domainid, depthlevel, depth);
                                        }

                                        curr.clear();
                                        s1.clear();
                                        tset.clear();
                                        URLValidator.psource = "";
                                    }

                                    if (l == 6) {
                                        depthlevel = 6;
                                        Loader.log.info(Loader.appendLog("Extracting " + s2.size() + " links at depth 6..."));
                                        sizeOfLink = s2.size();
                                        linksize += sizeOfLink;
                                        linkCount = Integer.toString(sizeOfLink);
                                        if (s2.isEmpty()) {
                                            navigateLevel = "Depth 5 Level Completed - No More Levels";
                                            break;
                                        }

                                        curr = s2;
                                        ltset = s2;
                                        if (s2.size() > 10) {
                                            selectedNodes(s2, tset, 10);
                                            setcheck = 1;
                                        }

                                        if (insertCount >= linklimit) {
                                            ActionsDecider.exmsg = "Link count exceeds " + linklimit;
                                            crawlresponse = 6;
                                            break;
                                        }

                                        if (setcheck == 1) {
                                            ThreadScheduler.threadingSchedule(tset, GetterSetter.getThreadSize(), s1, domainid, depthlevel, depth);
                                            setcheck = 0;
                                        } else {
                                            ThreadScheduler.threadingSchedule(s2, GetterSetter.getThreadSize(), s1, domainid, depthlevel, depth);
                                        }

                                        curr.clear();
                                        s2.clear();
                                        tset.clear();
                                        URLValidator.psource = "";
                                    }

                                    if (l == 7) {
                                        depthlevel = 7;
                                        Loader.log.info(Loader.appendLog("Extracting " + s1.size() + " links at depth 7..."));
                                        sizeOfLink = s1.size();
                                        linksize += sizeOfLink;
                                        linkCount = Integer.toString(sizeOfLink);
                                        if (s1.isEmpty()) {
                                            navigateLevel = "Depth 6 Level Completed - No More Levels";
                                            break;
                                        }

                                        curr = s1;
                                        ltset = s1;
                                        if (s1.size() > 10) {
                                            selectedNodes(s1, tset, 10);
                                            setcheck = 1;
                                        }

                                        if (insertCount >= linklimit) {
                                            ActionsDecider.exmsg = "Link count exceeds " + linklimit;
                                            crawlresponse = 6;
                                            break;
                                        }

                                        if (setcheck == 1) {
                                            ThreadScheduler.threadingSchedule(tset, GetterSetter.getThreadSize(), s2, domainid, depthlevel, depth);
                                            setcheck = 0;
                                        } else {
                                            ThreadScheduler.threadingSchedule(s1, GetterSetter.getThreadSize(), s2, domainid, depthlevel, depth);
                                        }

                                        curr.clear();
                                        s1.clear();
                                        tset.clear();
                                        URLValidator.psource = "";
                                    }

                                    if (l == 8) {
                                        depthlevel = 8;
                                        Loader.log.info(Loader.appendLog("Extracting " + s2.size() + " links at depth 8..."));
                                        sizeOfLink = s2.size();
                                        linksize += sizeOfLink;
                                        linkCount = Integer.toString(sizeOfLink);
                                        if (s2.isEmpty()) {
                                            navigateLevel = "Depth 7 Level Completed - No More Levels";
                                            break;
                                        }

                                        curr = s2;
                                        ltset = s2;
                                        if (s2.size() > 10) {
                                            selectedNodes(s2, tset, 10);
                                            setcheck = 1;
                                        }

                                        if (insertCount >= linklimit) {
                                            ActionsDecider.exmsg = "Link count exceeds " + linklimit;
                                            crawlresponse = 6;
                                            break;
                                        }

                                        if (setcheck == 1) {
                                            ThreadScheduler.threadingSchedule(tset, GetterSetter.getThreadSize(), s1, domainid, depthlevel, depth);
                                            setcheck = 0;
                                        } else {
                                            ThreadScheduler.threadingSchedule(s2, GetterSetter.getThreadSize(), s1, domainid, depthlevel, depth);
                                        }

                                        curr.clear();
                                        s2.clear();
                                        tset.clear();
                                        URLValidator.psource = "";
                                    }

                                    if (l == 9) {
                                        depthlevel = 9;
                                        Loader.log.info(Loader.appendLog("Extracting " + s1.size() + " links at depth 9..."));
                                        sizeOfLink = s1.size();
                                        linksize += sizeOfLink;
                                        linkCount = Integer.toString(sizeOfLink);
                                        if (s1.isEmpty()) {
                                            navigateLevel = "Depth 8 Level Completed - No More Levels";
                                            break;
                                        }

                                        curr = s1;
                                        ltset = s1;
                                        if (s1.size() > 10) {
                                            selectedNodes(s1, tset, 10);
                                            setcheck = 1;
                                        }

                                        if (insertCount >= linklimit) {
                                            ActionsDecider.exmsg = "Link count exceeds " + linklimit;
                                            crawlresponse = 6;
                                            break;
                                        }

                                        if (setcheck == 1) {
                                            ThreadScheduler.threadingSchedule(tset, GetterSetter.getThreadSize(), s2, domainid, depthlevel, depth);
                                            setcheck = 0;
                                        } else {
                                            ThreadScheduler.threadingSchedule(s1, GetterSetter.getThreadSize(), s2, domainid, depthlevel, depth);
                                        }

                                        curr.clear();
                                        s1.clear();
                                        tset.clear();
                                        URLValidator.psource = "";
                                    }

                                    if (l == 10) {
                                        depthlevel = 10;
                                        Loader.log.info(Loader.appendLog("Extracting " + s2.size() + " links at depth 10..."));
                                        sizeOfLink = s2.size();
                                        linksize += sizeOfLink;
                                        linkCount = Integer.toString(sizeOfLink);
                                        if (s2.isEmpty()) {
                                            navigateLevel = "Depth 9 Level Completed - No More Levels";
                                            break;
                                        }

                                        curr = s2;
                                        ltset = s2;
                                        if (s2.size() > 10) {
                                            selectedNodes(s2, tset, 10);
                                            setcheck = 1;
                                        }

                                        if (insertCount >= linklimit) {
                                            ActionsDecider.exmsg = "Link count exceeds " + linklimit;
                                            crawlresponse = 6;
                                            break;
                                        }

                                        if (setcheck == 1) {
                                            ThreadScheduler.threadingSchedule(tset, GetterSetter.getThreadSize(), s1, domainid, depthlevel, depth);
                                            setcheck = 0;
                                        } else {
                                            ThreadScheduler.threadingSchedule(s2, GetterSetter.getThreadSize(), s1, domainid, depthlevel, depth);
                                        }

                                        curr.clear();
                                        s2.clear();
                                        tset.clear();
                                        URLValidator.psource = "";
                                    }
                                } catch (Exception var35) {
                                    exceptionflag = 1;
                                    var35.printStackTrace();
                                    Loader.log.error(Loader.appendLog(""), var35);
                                    break;
                                }
                            }

                            ArrayList<TempOutput> tempOut = Loader.finalOutputList;
                            if (crawlresponse == 0) {
                                crawlresponse = 1;
                            }

                            if (crawlresponse == 1 && ActionsDecider.redirectflag == 1) {
                                crawlresponse = 3;
                                ActionsDecider.exmsg = "Redirected!";
                            }

                            if (crawlresponse == 6) {
                                ActionsDecider.exmsg = "Process Timeout!";
                            }
                        }

                        if (lsize > 0) {
                            linksize += lsize;
                        }
                    }

                    if (Loader.finalOutputList.size() >= GetterSetter.getLinkLimit()) {
                        Loader.log.info(Loader.appendLog("Given Linklimit has reached !"));
                    } 
                    
                    DBConnectivity.insertValues(Loader.finalOutputList,Loader.input.getInputId(), Loader.input.getDomainURL(),1,"Process Executed Successsfully","true");
                    
//Commented for DB Module 
//                    JSONArray finalOutputJsonArry = Loader.indentifyRemarksOfURL();
//                    
//
//                    
//                    for (int i = 0; i < finalOutputJsonArry.size(); i++) {
//                     JSONObject jsonObj = (JSONObject)new JSONParser().parse(finalOutputJsonArry.get(i).toString());
//                     jsonObj.keySet().forEach(keyStr ->
//    {
//        Object keyvalue = jsonObj.get(keyStr);
//        System.out.println("key: "+ keyStr + " value: " + keyvalue);
//
//        //for nested objects iteration if required
//        //if (keyvalue instanceof JSONObject)
//        //    printJsonObject((JSONObject)keyvalue);
//    });
//           
//
//                System.out.println(finalOutputJsonArry.get(i));
//                
//                
//                
//        } 
                    
                    
//                    Loader.result.setOutput(finalOutputJsonArry);
//                    String outputQueue = Loader.objectMapper.writeValueAsString(Loader.output);
                    Loader.log.info(Loader.createLogJson1("Process Execution", 1, "Process Executed With Records - 1"));
////                    ActiveMQConnection.sendToActiveMQQueue_XML(Loader.assetProperties, outputQueue, Loader.activeMQCon);
//                    ActiveMQConnection.sendToActiveMQQueue_XML(Loader.assetProperties, outputQueue, "");
                    long end = System.currentTimeMillis();
                    long ProcessedTime = end - start;
                    long second = ProcessedTime / 1000L % 60L;
                    long minute = ProcessedTime / 60000L % 60L;
                    long hour = ProcessedTime / 3600000L % 24L;
                    String hms = "";
                    if (hour > 0L) {
                        hms = String.format("%02d Hr %02d Min %02d Sec", hour, minute, second);
                    } else if (minute > 0L) {
                        hms = String.format("%02d Min %02d Sec", minute, second);
                    } else if (second > 0L) {
                        hms = String.format("%02d Sec", second);
                    } else {
                        hms = String.format("%02d Sec", 1);
                    }

                    long stopTime = System.currentTimeMillis();
                    long elapsedTime = stopTime - Loader.startTime1;
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
                    int min = (int)seconds / 60;
                    Loader.log.info(Loader.appendLog("\nTotal Processing Time - " + hms));
                    Loader.log.info(Loader.createLogJson1("Bot Termination", 1, "Record Processed Successfully. Total Time Taken for Process Completion - " + hms));
                    crawlresponse = 0;
                    serr = "";
                    navigateLevel = "";
                    Extraction_Type = "";
                    URLValidator.LoadedUrl = "";
                    linksize = 0;
                } catch (Exception var36) {
                    Loader.log.error(Loader.appendLog(" "), var36);
                    Loader.log.info(Loader.createLogJson1("Process Execution", 0, "Execution Failed" + var36.getMessage()));
                    JSONArray tempEmptyOutputJsonArray = new JSONArray();
                    DBConnectivity.insertValues(tempEmptyOutputJsonArray,Loader.input.getInputId(), Loader.input.getDomainURL(),3,"Execution Failed" + var36.getMessage(),"false");


//                    HashMap<String, String> tempEmptyOutput = new HashMap();
////                    Loader.makeOutputPojoToMap(tempEmptyOutput, new TempOutput(Loader.input.getInputId(), Loader.input.getOutputId(), Loader.input.getMobId(), Loader.input.getClientID(), Loader.input.getDomainURL(), "", "", "", "", "", "", "", "", "", "Failure", "Process Ends With Exception - " + var36.getMessage(), Loader.startTime, Loader.sdf.format(new Date()), ""));
//                    Loader.makeOutputPojoToMap(tempEmptyOutput, new TempOutput(Loader.input.getInputId(), Loader.input.getDomainURL(), "", "", "", "", "", "", "", "", "", "Failure", "Process Ends With Exception - " + var36.getMessage(), Loader.startTime, Loader.sdf.format(new Date()), ""));
//                    Loader.keywordLookup("", "Link", tempEmptyOutput);
//                    Loader.keywordLookup("", "LinkText", tempEmptyOutput);
//                    JSONObject tempJsonEmptyOutput = new JSONObject();
//                    tempJsonEmptyOutput.putAll(tempEmptyOutput);
//                    JSONArray tempEmptyOutputJsonArray = new JSONArray();
//                    tempEmptyOutputJsonArray.add(tempJsonEmptyOutput);
//                    Loader.result.setOutput(tempEmptyOutputJsonArray);
//                    linkCount = Loader.objectMapper.writeValueAsString(Loader.output);
////                    ActiveMQConnection.sendToActiveMQQueue_XML(Loader.assetProperties, linkCount, Loader.activeMQCon);
//                    ActiveMQConnection.sendToActiveMQQueue_XML(Loader.assetProperties, linkCount, "");
//                    System.exit(0);
                }
            }
        } catch (Exception var37) {
            ;
        }

        Runtime.getRuntime().gc();
        exceptionFlagStop = 0;
//        System.exit(0);
    }

    public static void process(String PageSource, int stat) {
        new ArrayList();
        List<String> lmod = new ArrayList();
        List<String> cfreq = new ArrayList();
        List<String> prio = new ArrayList();
        String tlink = domainURL.replaceAll("(.*?)/$", "$1");
        tlink = dedupes(tlink);
        String domaincheck = "";
        domaincheck = domainURL.replaceAll("http.*?://", "");
        domaincheck = domaincheck.replaceAll("www.", "");
        int j = 0;
        if (j == 0) {
            int var28 = j + 1;
            curr.add(domaincheck);
        }

        String links = "";
        String lastmod = "";
        String changefreq = "";
        String priority = "";
        if (!PageSource.equals("") && PageSource.length() > 0) {
            if (!KeyLookup.IsNotFound(PageSource)) {
                links = KeyLookup.RegexMatcher(PageSource, "<loc>(.*?)</loc>", 1);
            } else if (serr.isEmpty()) {
                serr = KeyLookup.RegexMatch(PageSource, "<errors>(.*?)</errors>", 1);
                if (serr.isEmpty()) {
                    serr = ActionsDecider.exmsg;
                }
            }

            if (!links.equals("") && links.length() > 0) {
                String[] Splitedlinks = links.split(",");
                int traverse;
                if (stat == 1) {
                    int linkCount = 0;
                    traverse = Splitedlinks.length;
                    linksize += traverse;
                } else {
                    linksize = Splitedlinks.length;
                    String var30 = Integer.toString(linksize);
                }

                if (linksize < linklimit && linklimit != 0) {
                    if (!KeyLookup.IsNotFound(PageSource)) {
                        lastmod = KeyLookup.RegexMatcher(PageSource, "<lastmod>(.*?)</lastmod>", 1);
                        changefreq = KeyLookup.RegexMatcher(PageSource, "<changefreq>(.*?)</changefreq>", 1);
                        priority = KeyLookup.RegexMatcher(PageSource, "<priority>(.*?)</priority>", 1);
                    }

                    try {
                        if (!lastmod.isEmpty()) {
                            lmod = Split(lastmod);
                        }

                        if (!changefreq.isEmpty()) {
                            cfreq = Split(changefreq);
                        }

                        if (!priority.isEmpty()) {
                            prio = Split(priority);
                        }
                    } catch (Exception var27) {
                        Loader.log.error(Loader.appendLog(""), var27);
                    }

                    traverse = 0;
                    int setcheck = 0;
                    boolean skip = false;
                    String locate = "";
                    String testlink = "";
                    String[] var19 = Splitedlinks;
                    int var20 = Splitedlinks.length;

                    for(int var21 = 0; var21 < var20; ++var21) {
                        String link = var19[var21];
                        skip = false;
                        Iterator var23 = TLD_Init.JunkRemovalSet.iterator();

                        String cfreq_value;
                        while(var23.hasNext()) {
                            cfreq_value = (String)var23.next();
                            if (link.contains(cfreq_value)) {
                                skip = true;
                                break;
                            }
                        }

                        if (!skip && link.trim().length() > 0) {
                            link = dedupes(link.replaceAll("(.*?)/$", "$1"));
                            testlink = link.replaceAll("http.*?://", "");
                            testlink = testlink.replaceAll("www.", "");
                            if (!curr.contains(testlink)) {
                                setcheck = 1;
                                if (!link.isEmpty()) {
                                    link = link.replaceAll(" ", "");
                                    link = link.trim();

                                    try {
                                        if (setcheck==1) {
                                            locate = storeLocator(link);
                                            String lmod_value = "";
                                            cfreq_value = "";
                                            String prio_value = "";
                                            if (((List)lmod).size() > traverse) {
                                                lmod_value = (String)((List)lmod).get(traverse);
                                            }

                                            if (((List)cfreq).size() > traverse) {
                                                cfreq_value = (String)((List)cfreq).get(traverse);
                                            }

                                            if (((List)prio).size() > traverse) {
                                                prio_value = (String)((List)prio).get(traverse);
                                            }

                                            if (!link.toLowerCase().endsWith(".exe") && !link.toLowerCase().endsWith(".xml") && !link.toLowerCase().endsWith(".rpm") && !link.toLowerCase().endsWith(".pdf") && !link.toLowerCase().endsWith(".xls") && !link.toLowerCase().endsWith(".doc") && !link.toLowerCase().endsWith(".jpg") && !link.toLowerCase().endsWith(".png") && !link.toLowerCase().endsWith(".flv") && !link.toLowerCase().endsWith(".avi") && !link.toLowerCase().endsWith(".f4v") && !link.toLowerCase().endsWith(".mp4") && !link.toLowerCase().endsWith(".mpg") && !link.toLowerCase().endsWith(".mpeg") && !link.toLowerCase().endsWith(".rar") && !link.toLowerCase().endsWith(".zip") && !link.toLowerCase().endsWith(".gif") && !link.toLowerCase().endsWith(".jpeg") && !link.toLowerCase().endsWith(".mp3") && !link.toLowerCase().endsWith(".mp2") && !link.toLowerCase().endsWith(".3gp") && !link.toLowerCase().endsWith(".m4v") && !link.toLowerCase().endsWith(".flr") && !link.toLowerCase().endsWith(".fla") && !link.toLowerCase().endsWith(".mng") && !link.toLowerCase().endsWith(".mov") && !link.toLowerCase().endsWith(".mpe") && !link.toLowerCase().endsWith(".swf") && !link.toLowerCase().endsWith(".wmv") && !link.toLowerCase().endsWith(".css") && !link.trim().isEmpty()) {
                                                curr.add(testlink);
                                            }

                                            ++traverse;
                                            setcheck = 0;
                                        }
                                    } catch (Exception var26) {
                                        Loader.log.error(Loader.appendLog(""), var26);
                                    }
                                }
                            }
                        }
                    }

                    statuscode = 1;
                    crawlresponse = 1;
                    Extraction_Type = "Sitemap";
                    curr.remove(domaincheck);
                    linksize = curr.size();
                } else {
                    statuscode = 1;
                    crawlresponse = 5;
                    Extraction_Type = "Sitemap";
                }
            }
        }

    }

    public static String dedupes(String link) {
        link = link.replaceAll("(?sim)/$", "");
        link = link.replaceAll("(?sim)//$", "");
        link = link.replaceAll("(?sim)#$", "");
        return link;
    }

    public static void sitemap_xml_in_website(String url) {
        String PageSource = "";
       boolean pcode = false;
        PageSource = URLValidator.JsoupSource(url);
        if (PageSource.contains("<sitemap>") && PageSource.contains("</sitemap>")) {
            String newSource = KeyLookup.RegexMatcher(PageSource, "<sitemap>\\s*<loc>(.*?)</loc>\\s*.*?</sitemap>", 1);
            String[] newSitemapLinks = newSource.split(",");
            String[] var5 = newSitemapLinks;
            int var6 = newSitemapLinks.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String newLinks = var5[var7];
                if (linksize >= linklimit) {
                    break;
                }

                String nested_site_PageSource = URLValidator.JsoupSource_For_Nested(newLinks);
                process(nested_site_PageSource, 1);
                pcode = true;
                if (nested_site_PageSource.contains("<loc>") && nested_site_PageSource.contains("</loc>")) {
                    if (!serr.contains("Valid")) {
                        serr = "Valid";
                    }
                } else if (serr.isEmpty()) {
                    serr = "No <loc> tag available";
                }
            }
        }

        if (!pcode) {
            process(PageSource, 0);
        }

        if (PageSource.contains("<loc>") && PageSource.contains("</loc>")) {
            if (!serr.contains("Valid")) {
                serr = "Valid";
            } else if (serr.isEmpty()) {
                serr = "No <loc> tag available";
            }
        }

    }

    public static List<String> Split(String links) {
        String[] splitter = new String[0];
        splitter = links.split(",");
        List<String> collected = new ArrayList(Arrays.asList(splitter));
        return collected;
    }

    public static String storeLocator(String targetLookup) {
        String result = "";
        Iterator var2 = TLD_Init.storeLocatorSet.iterator();

        while(var2.hasNext()) {
            String locate = (String)var2.next();
            if (targetLookup.contains(locate)) {
                result = "yes";
            }
        }

        return result;
    }

    public static void stopProcess(int exceptionflag) {
    }

    public static void removedupesInSitemap(HashSet check, HashSet tempSet) {
        Iterator it1 = check.iterator();
        String remover = "";
        int var4 = 0;

        while(true) {
            String s;
            Iterator var12;
            String junk;
            do {
                if (!it1.hasNext()) {
                    return;
                }

                s = it1.next().toString();
                ++var4;
                String wwwrem = s.replaceAll("www.", "");
                String slashrem = s.replaceAll("/$", "");
                String hashrem = s.replaceAll("/#$", "");
                String wwwadd = "";
                String slashadd = "";
                String hashadd = "";
                if (!s.contains("www.")) {
                    wwwadd = s.replaceAll("://", "://www.");
                }

                if (!s.endsWith("/")) {
                    slashadd = s + "/";
                }

                if (!s.endsWith("/#")) {
                    hashadd = s + "/#";
                }

                if (tempSet.contains(wwwrem)) {
                    tempSet.remove(wwwrem);
                }

                if (tempSet.contains(slashrem)) {
                    tempSet.remove(slashrem);
                }

                if (tempSet.contains(hashrem)) {
                    tempSet.remove(hashrem);
                }

                if (tempSet.contains(wwwadd)) {
                    tempSet.remove(wwwadd);
                }

                if (tempSet.contains(slashadd)) {
                    tempSet.remove(slashadd);
                }

                if (tempSet.contains(hashadd)) {
                    tempSet.remove(hashadd);
                }

                if (GetterSetter.isGeneralFlag()) {
                    var12 = TLD_Init.GeneralSet.iterator();

                    while(var12.hasNext()) {
                        junk = (String)var12.next();
                        if (s.contains(junk)) {
                            tempSet.remove(s);
                        }
                    }
                }

                if (GetterSetter.isBlackListedFlag()) {
                    var12 = TLD_Init.BlackListedSet.iterator();

                    while(var12.hasNext()) {
                        junk = (String)var12.next();
                        if (s.contains(junk)) {
                            tempSet.remove(s);
                        }
                    }
                }
            } while(!GetterSetter.isJunkFlag());

            var12 = TLD_Init.JunkRemovalSet.iterator();

            while(var12.hasNext()) {
                junk = (String)var12.next();
                if (s.contains(junk)) {
                    tempSet.remove(s);
                }
            }
        }
    }

    public static void selectedNodes(Set<String> allNodes, Set<String> selected, int no) {
        selected.clear();
        int x = 0;
        int i = 0;
        HashMap<String, Integer> tempLinks = getLinksWithSlashCount(allNodes);
        Map<String, Integer> sortedLinksWithSlashCount = sortByValues(tempLinks);
        Set<Integer> tempUpdate_Of_domainid_url_hashcode = new HashSet();
        tempUpdate_Of_domainid_url_hashcode.addAll(domainid_url_hashcode);
        Iterator var8 = sortedLinksWithSlashCount.entrySet().iterator();

        while(true) {
            while(true) {
                String link;
                int count;
                do {
                    do {
                        if (!var8.hasNext()) {
                            return;
                        }

                        Entry entry = (Entry)var8.next();
                        link = (String)entry.getKey();
                        count = (Integer)entry.getValue();
                    } while(link == null);
                } while("".equals(link));

                boolean flagOfDublicate = false;
                int hascode = (domainid + link).hashCode();
                if (tempUpdate_Of_domainid_url_hashcode.size() == 0) {
                    tempUpdate_Of_domainid_url_hashcode.add(hascode);
                } else if (!tempUpdate_Of_domainid_url_hashcode.add(hascode)) {
                    flagOfDublicate = true;
                }

                if (selected.size() < no && count != 0 && !flagOfDublicate) {
                    selected.add(link);
                } else if (!link.trim().isEmpty()) {
                    MySQL_Interface.insertOutputTable(domainid, link, "", depthlevel, 0, 0, "", "");
                    ThreadScheduler.check.add(link);
                }
            }
        }
    }

    public static <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map) {
        List<Entry<K, V>> entries = new LinkedList(map.entrySet());
        Collections.sort(entries, new Comparator<Entry<K, V>>() {
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                return ((Comparable)o1.getValue()).compareTo(o2.getValue());
            }
        });
        Map<K, V> sortedMap = new LinkedHashMap();
        Iterator var3 = entries.iterator();

        while(var3.hasNext()) {
            Entry<K, V> entry = (Entry)var3.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static HashMap<String, Integer> getLinksWithSlashCount(Set<String> allNodes) {
        HashMap<String, Integer> tempLinksWithSlashCount = new HashMap();
        Iterator iterator = allNodes.iterator();

        int slashCount;
        for(String link = ""; iterator.hasNext(); tempLinksWithSlashCount.put(link, slashCount)) {
            link = iterator.next().toString();
            String tempLink = link.replaceAll("(?sim)^http.*?://", "");
            tempLink = tempLink.replaceAll("(?sim)/$", "");
            String[] tempSlashCountArray = tempLink.split("/");
            slashCount = 0;
            if (tempSlashCountArray != null) {
                slashCount = tempSlashCountArray.length - 1;
            }
        }

        return tempLinksWithSlashCount;
    }

    public static void SitemapLinkText(Document doc) {
        try {
            Elements ele = doc.getElementsByTag("a");
            String ltext = "";
            String link = "";
            if (ele != null) {
                Iterator var4 = ele.iterator();

                while(var4.hasNext()) {
                    Element e = (Element)var4.next();
                    ltext = e.text();
                    link = e.absUrl("href");
                    if (!link.isEmpty()) {
                        slinktextMap.put(link, ltext);
                    }
                }
            }
        } catch (Exception var6) {
            ;
        }

    }

    public static String regSelected(String source, String reg, int no) {
        String result = "";
        Pattern regex = Pattern.compile(reg, 234);

        for(Matcher regexMatcher = regex.matcher(source); regexMatcher.find(); result = regexMatcher.group(no).replaceAll("<.*?>", "")) {
            ;
        }

        return result;
    }

    public static void clearList() {
        linksize = 0;
        ActionsDecider.redirecturl = "";
        ActionsDecider.resdesc = "";
        ActionsDecider.exmsg = "";
        ActionsDecider.resdesc = "";
        ActionsDecider.rescode = 0;
        ActionsDecider.exmsg = "";
        ActionsDecider.redirectflag = 0;
        crawlresponse = 0;
        dlmap.clear();
        VariantLookup.domainlinkMap.clear();
        Initialize.variableinitialize();
    }

    public static String cleanPage(String Page) {
        Page = Page.replaceAll("<script.*?>.*?</script>", "");
        Page = Page.replaceAll("(?sim)<style.*?>.*?</style>", "");
        Page = Page.replaceAll("(?sim)<!--.*?-->", "");
        Page = Page.replaceAll("(?sim)\\s+", " ");
        return Page;
    }

    public static class CleanThread implements Runnable {
        String Page;

        public CleanThread(String Page) {
            this.Page = Page;
        }

        public void run() {
            this.Page = DepthManager.cleanPage(this.Page);
        }
    }
}
