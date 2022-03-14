//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import static com.mobius.le.impl.ActionPerform.Source_clean;
import static com.mobius.le.impl.DBConnectivity.LoadProperties;
import com.mobius.le.model.AssetProperties;
import com.mobius.le.model.FinalOutput;
import com.mobius.le.model.Input;
import com.mobius.le.model.Logtracepojo;
import com.mobius.le.model.Result;
import com.mobius.le.model.TempOutput;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
//import javax.jms.Connection;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.commons.lang.StringUtils;

public class Loader {
    static AssetProperties assetProperties;
    static Input input = null;
    static FinalOutput output;
    static Result result;
    static String outputQueue;
    static ObjectMapper objectMapper;
//    static Connection activeMQCon;
    static Logger log = Logger.getLogger(Loader.class.getName());
    static String pid = "";
    static SimpleDateFormat sdf = null;
    static boolean redirectFlag = false;
    static String redirectedURL = "";
    static String startTime = "";
    static long startTime1;
    static ArrayList<TempOutput> finalOutputList = new ArrayList();
    static HashMap<String, ArrayList<String>> bucketKeyword = new HashMap();
    public static String keywordFileNames = "";
    static JSONObject config = new JSONObject();
        public static String ID;
    public static String link;
    public static ResultSet resultSet = null;
    private static Statement statement = null;


    public Loader() {
    }

    public static void main(String[] args) throws SQLException, IOException {
        long var1 = System.currentTimeMillis();

        try {
            startTime1 = System.currentTimeMillis();
            pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            System.out.println("pid:" + pid);
            PropertyConfigurator.configure("log4j.properties");
            sdf = new SimpleDateFormat("MMddyyyyHHmmssz");
            startTime = sdf.format(new Date());
            objectMapper = (new ObjectMapper()).configure(Feature.ALLOW_COMMENTS, true);
            objectMapper.setVisibilityChecker(objectMapper.getSerializationConfig().getDefaultVisibilityChecker().withFieldVisibility(Visibility.ANY).withGetterVisibility(Visibility.NONE).withSetterVisibility(Visibility.NONE).withCreatorVisibility(Visibility.NONE));

//            try {
////                output = new FinalOutput(args[1], args[7], args[6], args[2], args[0], args[5], args[3], args[8], args[4]);
//                output = new FinalOutput("","", "", "", "", "", "","","");
//                input = new Input(args[10], args[13]);
//            } catch (Exception var10) {
//                log.info(appendLog("Input Argument Missed. Length Should Be - 14"));
//                log.info(createLogJson1("Bot Initiated", 0, "InputArgumentMismatch Required Args Length - 14 " + var10.getMessage()));
//                log.error(appendLog(""), var10);
//                System.exit(0);
//            }

//            try {
//                File fileAsset = new File("AssetProperties.json");
//                byte[] jsonDataAsset = new byte[(int)fileAsset.length()];
//                FileInputStream finAsset = new FileInputStream(fileAsset);
//                finAsset.read(jsonDataAsset);
//                assetProperties = (AssetProperties)objectMapper.readValue(jsonDataAsset, AssetProperties.class);
//            } catch (Exception var9) {
//                log.info(appendLog("AssertProperties File Not Found"));
//                log.info(createLogJson1("Bot Initiation", 0, "AssertProperties File Not Found " + var9.getMessage()));
//                log.error(appendLog(""), var9);
//                System.exit(0);
//            }

            result = new Result();
//            result.setInput(input);
//            output.setResult(result);
//            activeMQCon = ActiveMQConnection.getConnection(assetProperties);

//            for(int tempActiveMQCount = 0; activeMQCon == null && tempActiveMQCount <= 2; ++tempActiveMQCount) {
//                log.info(appendLog("Connecting ... " + tempActiveMQCount));
//                activeMQCon = ActiveMQConnection.getConnection(assetProperties);
//            }

            try {
                config = LoadJSONFromFile("Configuration.json");
                loadJson(config);
                LoadProperties(config);
                getKeywordFiles();
                DBConnectivity.DBConnection();
                DBConnectivity.CreateOutputtable();
            } catch (Exception var8) {
                log.error(appendLog(""), var8);
            }

             System.out.println(">>>>>>>>>> DB Connected <<<<<<<<<<");
            try {
                PreparedStatement st1 = DBConnectivity.con.prepareStatement("BEGIN TRY BEGIN TRANSACTION SET DEADLOCK_PRIORITY ? COMMIT TRANSACTION END TRY BEGIN CATCH IF XACT_STATE() <> 0 ROLLBACK TRANSACTION END CATCH");
                st1.setInt(1, DBConnectivity.deadlockpriority);
                st1.executeUpdate();
                st1.clearBatch();
                st1.close();
            } catch (Exception var10) {
                log.info(appendLog("Error While Setting Deadlock Priority"));
                log.info(createLogJson1("Bot Initiated", 0, "Error While Setting Deadlock Priority " + var10.getMessage()));
                log.error(appendLog(""), var10);
                System.exit(0);
            }
            Statement st = DBConnectivity.con.createStatement();
//            ResultSet rs = st.executeQuery("select * from " + DBConnectivity.DBName + ".dbo." + DBConnectivity.Input_Table + " WITH (NOLOCK) where Status=0 and ID between " + DBConnectivity.Start + " and " + DBConnectivity.End + " and oid not in (select oid from " + DBConnectivity.DBName + ".dbo." + DBConnectivity.New_OutputTable + " ) order by id");
            ResultSet rs = st.executeQuery("select * from " + GetterSetter.getDatabase() + ".dbo." + GetterSetter.getInputTable() + " WITH (NOLOCK) where Status=0 and PageNotFound='false' and BlackListed!='' and ID between " + GetterSetter.getStart() + " and " + GetterSetter.getEnd() + " order by id");
            System.out.println("select * from " + GetterSetter.getDatabase() + ".dbo." + GetterSetter.getInputTable() + " WITH (NOLOCK) where Status=0 and PageNotFound='false' and BlackListed!='' and ID between " + GetterSetter.getStart() + " and " + GetterSetter.getEnd() + " order by id");
//            try {
            input = new Input("","");
                while (rs.next()) {
                    String baseurl = "";
                    ID = rs.getString("Domain_ID");
                    System.out.println("Processing ID -- "+ID);
                    link = rs.getString("Domain_Name").trim();
                    link = link.trim();
                    input.setInputId(String.valueOf(ID));
                    input.setDomainURL(link);
//                }
            
            
            
            JSONArray tempEmptyOutputJsonArray;
            String outputQueue;
            HashMap tempEmptyOutput;
            JSONObject tempJsonEmptyOutput;
//            if (input.getInputId().equals("")) {
//                log.info(appendLog("InputId is empty"));
//                log.info(createLogJson1("Bot Initiation", 0, "InputId is Empty"));
//                tempEmptyOutput = new HashMap();
//                makeOutputPojoToMap(tempEmptyOutput, new TempOutput(input.getInputId(), input.getOutputId(), input.getMobId(), input.getClientID(), input.getDomainURL(), "", "", "", "", "", "", "", "", "", "Failure", "InputId is empty", startTime, sdf.format(new Date()), ""));
//                keywordLookup("", "Link", tempEmptyOutput);
//                keywordLookup("", "LinkText", tempEmptyOutput);
//                tempJsonEmptyOutput = new JSONObject();
//                tempJsonEmptyOutput.putAll(tempEmptyOutput);
//                tempEmptyOutputJsonArray = new JSONArray();
//                tempEmptyOutputJsonArray.add(tempJsonEmptyOutput);
//                result.setOutput(tempEmptyOutputJsonArray);
//                outputQueue = objectMapper.writeValueAsString(output);
//                
//                ActiveMQConnection.sendToActiveMQQueue_XML(assetProperties, outputQueue, activeMQCon);
//                System.exit(0);
//            }

            if (input.getDomainURL().equals("")) {
                log.info(appendLog("DomainURL is empty"));
                log.info(createLogJson1("Bot Initiation", 0, "DomainURL is Empty"));
                tempEmptyOutput = new HashMap();
//                makeOutputPojoToMap(tempEmptyOutput, new TempOutput(input.getInputId(), input.getOutputId(), input.getMobId(), input.getClientID(), input.getDomainURL(), "", "", "", "", "", "", "", "", "", "Failure", "DomainURL is empty", startTime, sdf.format(new Date()), ""));
                keywordLookup("", "Link", tempEmptyOutput);
                keywordLookup("", "LinkText", tempEmptyOutput);
                tempJsonEmptyOutput = new JSONObject();
                tempJsonEmptyOutput.putAll(tempEmptyOutput);
                tempEmptyOutputJsonArray = new JSONArray();
                
                DBConnectivity.insertValues(tempEmptyOutputJsonArray,Loader.input.getInputId(), Loader.input.getDomainURL(),5,"DomainURL is Empty","false");
                
//                tempEmptyOutputJsonArray.add(tempJsonEmptyOutput);
//                result.setOutput(tempEmptyOutputJsonArray);
//                outputQueue = objectMapper.writeValueAsString(output);
////                ActiveMQConnection.sendToActiveMQQueue_XML(assetProperties, outputQueue, activeMQCon);
//                ActiveMQConnection.sendToActiveMQQueue_XML(assetProperties, outputQueue, "");
//                System.exit(0);
            }

            if (input.getDomainURL().toLowerCase().endsWith(".exe") || input.getDomainURL().toLowerCase().endsWith(".xml") || input.getDomainURL().toLowerCase().endsWith(".rpm") || input.getDomainURL().toLowerCase().endsWith(".pdf") || input.getDomainURL().toLowerCase().endsWith(".xls") || input.getDomainURL().toLowerCase().endsWith(".doc") || input.getDomainURL().toLowerCase().endsWith(".jpg") || input.getDomainURL().toLowerCase().endsWith(".png") || input.getDomainURL().toLowerCase().endsWith(".flv") || input.getDomainURL().toLowerCase().endsWith(".avi") || input.getDomainURL().toLowerCase().endsWith(".f4v") || input.getDomainURL().toLowerCase().endsWith(".mp4") || input.getDomainURL().toLowerCase().endsWith(".mpg") || input.getDomainURL().toLowerCase().endsWith(".mpeg") || input.getDomainURL().toLowerCase().endsWith(".rar") || input.getDomainURL().toLowerCase().endsWith(".zip") || input.getDomainURL().toLowerCase().endsWith(".gif") || input.getDomainURL().toLowerCase().endsWith(".jpeg") || input.getDomainURL().toLowerCase().endsWith(".mp3") || input.getDomainURL().toLowerCase().endsWith(".mp2") || input.getDomainURL().toLowerCase().endsWith(".3gp") || input.getDomainURL().toLowerCase().endsWith(".m4v") || input.getDomainURL().toLowerCase().endsWith(".flr") || input.getDomainURL().toLowerCase().endsWith(".fla") || input.getDomainURL().toLowerCase().endsWith(".mng") || input.getDomainURL().toLowerCase().endsWith(".mov") || input.getDomainURL().toLowerCase().endsWith(".mpe") || input.getDomainURL().toLowerCase().endsWith(".swf") || input.getDomainURL().toLowerCase().endsWith(".wmv")) {
                log.info(appendLog(" DomainURL is Invalid"));
                log.info(createLogJson1("Bot Initiation", 0, "DomainURL is Invalid"));
                tempEmptyOutput = new HashMap();
//                makeOutputPojoToMap(tempEmptyOutput, new TempOutput(input.getInputId(), input.getOutputId(), input.getMobId(), input.getClientID(), input.getDomainURL(), "", "", "", "", "", "", "", "", "", "Failure", "DomainURL is Invalid", startTime, sdf.format(new Date()), ""));
                keywordLookup("", "Link", tempEmptyOutput);
                keywordLookup("", "LinkText", tempEmptyOutput);
                tempJsonEmptyOutput = new JSONObject();
                tempJsonEmptyOutput.putAll(tempEmptyOutput);
                tempEmptyOutputJsonArray = new JSONArray();
                DBConnectivity.insertValues(tempEmptyOutputJsonArray,Loader.input.getInputId(), Loader.input.getDomainURL(),7,"DomainURL is Invalid","false");
                
//                tempEmptyOutputJsonArray.add(tempJsonEmptyOutput);
//                result.setOutput(tempEmptyOutputJsonArray);
//                outputQueue = objectMapper.writeValueAsString(output);
////                ActiveMQConnection.sendToActiveMQQueue_XML(assetProperties, outputQueue, activeMQCon);
//                ActiveMQConnection.sendToActiveMQQueue_XML(assetProperties, outputQueue, "");
//                System.exit(0);
            }

            log.info(createLogJson1("Bot Initiation", 1, "Bot Initiation Successfull"));
            DepthManager.manage();
//            MySQL_Interface.infra = GetterSetter.getInfra();
//            Initialize s = new Initialize();
//            s.begin();
                }
        } catch (Exception var11) {
            log.error(appendLog(""), var11);
        }

    }

    public static JSONArray indentifyRemarksOfURL() {
        Set<String> uniqueURL = new HashSet();
        HashMap<String, Integer> duplicateList = new HashMap();
        boolean flag = false;
        Iterator var3 = finalOutputList.iterator();

        while(var3.hasNext()) {
            TempOutput tempOutput = (TempOutput)var3.next();
            String tempRemarks = "";
            if (matchRegex(tempOutput.getExtractedURL(), "[\\(\\){}<>\\[\\]]$")) {
                tempRemarks = "URL_Junk";
            }

            String tempURL;
            if (tempRemarks.equals("")) {
                if (isURLBlackList(tempOutput.getExtractedURL())) {
                    tempRemarks = "BlackListDomain";
                }

                if (matchRegex(tempOutput.getLink_Text(), "\\ball links\\b") || matchRegex(tempOutput.getLink_Text(), "\\benglish\\b") || matchRegex(tempOutput.getLink_Text(), "\\bmap\\b") || matchRegex(tempOutput.getLink_Text(), "\\bmapa del sitio\\b") || matchRegex(tempOutput.getLink_Text(), "\\bsite map\\b") || matchRegex(tempOutput.getLink_Text(), "\\bsitemap\\b") || matchRegex(tempOutput.getLink_Text(), "\\babout\\b")) {
                    tempRemarks = "MatchedKeyword_Junk";
                }

                if (tempRemarks.equals("") && (matchRegex(tempOutput.getExtractedURL(), "facebook\\.") || matchRegex(tempOutput.getExtractedURL(), "twitter\\.") || matchRegex(tempOutput.getExtractedURL(), "youtube\\.") || matchRegex(tempOutput.getExtractedURL(), "linkedin\\.") || matchRegex(tempOutput.getExtractedURL(), "google\\.") || matchRegex(tempOutput.getExtractedURL(), "share=twitter") || matchRegex(tempOutput.getExtractedURL(), "share=linkedin") || matchRegex(tempOutput.getExtractedURL(), "share=plus\\.google") || matchRegex(tempOutput.getExtractedURL(), "share=facebook") || matchRegex(tempOutput.getExtractedURL(), "share=youtube"))) {
                    tempRemarks = "SM_Link_Junk";
                }

                if (!matchRegex(tempOutput.getExtractedURL(), "^https") && !matchRegex(tempOutput.getExtractedURL(), "^http") && !matchRegex(tempOutput.getExtractedURL(), "^www") && tempRemarks.equals("")) {
                    tempRemarks = "URL_Junk";
                }

                if (tempRemarks.equals("") && isMatchWithJunkKeywords(tempOutput.getExtractedURL())) {
                    tempRemarks = "URL_Junk";
                }

                tempURL = tempOutput.getExtractedURL();
                tempURL = tempURL.replaceAll("(?sim)^https://", "");
                tempURL = tempURL.replaceAll("(?sim)^http://", "");
                tempURL = tempURL.replaceAll("(?sim)^www\\.", "");
                tempURL = tempURL.replaceAll("(?sim)/$", "");
                if (!uniqueURL.add(tempURL)) {
                    tempRemarks = "Link_Duplicate";
                    if (!flag) {
                        duplicateList.put(tempURL, 1);
                        flag = true;
                    } else {
                        try {
                            int count = (Integer)duplicateList.get(tempURL);
                            ++count;
                            duplicateList.put(tempURL, count);
                        } catch (Exception var9) {
                            duplicateList.put(tempURL, 1);
                        }
                    }
                }
            }

            tempURL = tempOutput.getExtractedURL();
            tempURL = tempURL.replaceAll("(?sim)/+$", "");
            tempOutput.setExtractedURL(tempURL);
            tempOutput.setRemarks(tempRemarks);
            tempOutput.setNavigation_Travel_status(DepthManager.navigateLevel);
            tempOutput.setLinks_Count(finalOutputList.size() + "");
        }

        JSONArray tempArrayOutput = new JSONArray();
        Iterator var11 = finalOutputList.iterator();

        while(var11.hasNext()) {
            TempOutput tempOutput = (TempOutput)var11.next();
            tempOutput.setLinkDublicateCount("0");
            Iterator var14 = duplicateList.entrySet().iterator();

            while(var14.hasNext()) {
                Entry<String, Integer> entry = (Entry)var14.next();
                if (tempOutput.getRemarks().equals("Link_Duplicate")) {
                    String tempURL = tempOutput.getExtractedURL();
                    tempURL = tempURL.replaceAll("(?sim)^https://", "");
                    tempURL = tempURL.replaceAll("(?sim)^http://", "");
                    tempURL = tempURL.replaceAll("(?sim)^www\\.", "");
                    tempURL = tempURL.replaceAll("(?sim)/$", "");
                    if (tempURL.equals(entry.getKey())) {
                        tempOutput.setLinkDublicateCount(entry.getValue() + "");
                    }
                }
            }

            HashMap<String, String> tempFinalOutput = new HashMap();
            makeOutputPojoToMap(tempFinalOutput, tempOutput);
            keywordLookup(tempOutput.getExtractedURL(), "Link", tempFinalOutput);
            keywordLookup(tempOutput.getLink_Text(), "LinkText", tempFinalOutput);
            JSONObject tempOutputObject = new JSONObject();
            tempOutputObject.putAll(tempFinalOutput);
            tempArrayOutput.add(tempOutputObject);
        }

        return tempArrayOutput;
    }

    public static boolean isURLBlackList(String url) {
        Iterator var1 = TLD_Init.BlackListedSet.iterator();

        String blacklisted;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            blacklisted = (String)var1.next();
        } while(url == null || !url.contains(blacklisted));

        return true;
    }

    public static void makeOutputPojoToMap(HashMap<String, String> tempFinalOutput, TempOutput tempOutput) {
        tempFinalOutput.put("InputId", tempOutput.getInputId());
//        tempFinalOutput.put("OutputId", tempOutput.getOutputId());
//        tempFinalOutput.put("MobId", tempOutput.getMobId());
//        tempFinalOutput.put("ClientId", tempOutput.getClientId());
        tempFinalOutput.put("DomainURL", tempOutput.getURL());
        tempFinalOutput.put("ExtractedURL", tempOutput.getExtractedURL());
        tempFinalOutput.put("Navigation_Travel_status", tempOutput.getNavigation_Travel_status());
        tempFinalOutput.put("DomainRedirection", tempOutput.getRedirected_URL());
        tempFinalOutput.put("Response_Code", tempOutput.getResponse_Code());
        tempFinalOutput.put("Response_Description", tempOutput.getResponse_Description());
        tempFinalOutput.put("Links_Count", tempOutput.getLinks_Count());
        tempFinalOutput.put("Link_Text", tempOutput.getLink_Text());
        tempFinalOutput.put("Link_Type", tempOutput.getLink_Type());
        tempFinalOutput.put("Depth_Level", tempOutput.getDepth_Level());
        tempFinalOutput.put("Process_Final_Status", tempOutput.getProcess_Final_Status());
        tempFinalOutput.put("Final_Status_Descritpion", tempOutput.getFinal_Status_Descritpion());
        tempFinalOutput.put("Start_Time", tempOutput.getStart_Time());
        tempFinalOutput.put("End_Time", tempOutput.getEnd_Time());
        tempFinalOutput.put("Remarks", tempOutput.getRemarks());
        tempFinalOutput.put("Link_Duplicate_Count", tempOutput.getLinkDublicateCount());
        tempFinalOutput.put("ResponseTimeInSec", DepthManager.totalResponseTime + 1 + "");
        tempFinalOutput.put("Version", "2.1.2");
    }
    public static String key_clean(String keyStr) {
        keyStr = keyStr.replaceAll("(?sim)^\\/", "");
        keyStr = keyStr.replaceAll("(?sim)\\/$", "");
        keyStr = keyStr.replaceAll("(?sim)^\\#", "");
        keyStr = keyStr.replaceAll("(?sim)\\#$", "");
        //System.out.println("keyStr"+keyStr);
        return keyStr;
    }
    public static void keywordLookup(String linkOrLinkText, String type, HashMap<String, String> tempMap) {
        if (linkOrLinkText == null) {
            linkOrLinkText = "";
        }

        String tempColumnValue;
        String tempColumnName;
        for(Iterator var3 = bucketKeyword.entrySet().iterator(); var3.hasNext(); tempMap.put(tempColumnName, tempColumnValue)) {
            Entry<String, ArrayList<String>> entry = (Entry)var3.next();
            tempColumnValue = "";
            tempColumnName = (String)entry.getKey() + "_MatchedKeywordWith_" + type;
            if (!linkOrLinkText.trim().equals("")) {
                Iterator var7 = ((ArrayList)entry.getValue()).iterator();

                while(var7.hasNext()) {
                    String tempKey = (String)var7.next();
                    //if (linkOrLinkText.contains(tempKey)) {
                    String tkey = tempKey;
                    String tlinkOrLinkText= linkOrLinkText;
                    //System.out.println("tkey"+tkey);
                    //tempKey = tempKey.replaceAll("(?sim)^\\/", "");
                    tempKey = key_clean(tempKey);
                   if(tlinkOrLinkText.toLowerCase().contains((tkey.toLowerCase()))) {
                   //if (linkOrLinkText.contains(tempKey)) {
                        tempColumnValue = tempColumnValue + tempKey + "|";
                    }
                }

                tempColumnValue = tempColumnValue.replaceAll("(?sim)\\|$", "");
                //tempColumnValue = tempColumnValue.replaceAll("(?sim)^\\|", "");
                
                
            }
        }

    }

    public static boolean getKeywordFiles() {
        String[] fileNames = null;
        if (keywordFileNames.contains("~")) {
            fileNames = keywordFileNames.split("~");
        } else {
            fileNames = new String[]{keywordFileNames};
        }

        String[] var1 = fileNames;
        int var2 = fileNames.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String name = var1[var3];
            new ArrayList();
            ArrayList<String> tempList = readFile(name.trim() + ".txt");
            if (tempList == null) {
                return false;
            }

            bucketKeyword.put(name, tempList);
        }

        return true;
    }

    public static ArrayList<String> readFile(String fileName) {
        String str = "";
        String temp = "";
        fileName = "Bucket" + File.separator + fileName;
        ArrayList tempList = new ArrayList();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));

            while((temp = in.readLine()) != null) {
                tempList.add(temp);
            }

            in.close();
            //System.out.println(tempList);
            return tempList;
        } catch (Exception var5) {
            return null;
        }
    }

    public static String GetResponseMessage(int ResponseCode) {
        String ResponseDescription = "";

        try {
            if (ResponseCode == 100) {
                ResponseDescription = "Continue";
            } else if (ResponseCode == 101) {
                ResponseDescription = "Switching Protocols";
            } else if (ResponseCode == 200) {
                ResponseDescription = "OK";
            } else if (ResponseCode == 201) {
                ResponseDescription = "Created";
            } else if (ResponseCode == 202) {
                ResponseDescription = "Accepted";
            } else if (ResponseCode == 203) {
                ResponseDescription = "Non-Authoritative Information";
            } else if (ResponseCode == 204) {
                ResponseDescription = "No Content";
            } else if (ResponseCode == 205) {
                ResponseDescription = "Reset Content";
            } else if (ResponseCode == 206) {
                ResponseDescription = "Partial Content";
            } else if (ResponseCode == 300) {
                ResponseDescription = "Multiple Choices";
            } else if (ResponseCode == 301) {
                ResponseDescription = "Moved Permanently";
            } else if (ResponseCode == 302) {
                ResponseDescription = "Moved Temporarily";
            } else if (ResponseCode == 303) {
                ResponseDescription = "See Other";
            } else if (ResponseCode == 304) {
                ResponseDescription = "Not Modified";
            } else if (ResponseCode == 305) {
                ResponseDescription = "Use Proxy";
            } else if (ResponseCode == 306) {
                ResponseDescription = "(Unused)";
            } else if (ResponseCode == 307) {
                ResponseDescription = "Temporary Redirect";
            } else if (ResponseCode == 400) {
                ResponseDescription = "Bad Request";
            } else if (ResponseCode == 401) {
                ResponseDescription = "Unauthorized";
            } else if (ResponseCode == 402) {
                ResponseDescription = "Payment Required";
            } else if (ResponseCode == 403) {
                ResponseDescription = "Forbidden";
            } else if (ResponseCode == 404) {
                ResponseDescription = "Not Found";
            } else if (ResponseCode == 405) {
                ResponseDescription = "Method Not Allowed";
            } else if (ResponseCode == 406) {
                ResponseDescription = "Not Acceptable";
            } else if (ResponseCode == 407) {
                ResponseDescription = "Proxy Authentication Required";
            } else if (ResponseCode == 408) {
                ResponseDescription = "Request Timeout";
            } else if (ResponseCode == 409) {
                ResponseDescription = "Conflict";
            } else if (ResponseCode == 410) {
                ResponseDescription = "Gone";
            } else if (ResponseCode == 411) {
                ResponseDescription = "Length Required";
            } else if (ResponseCode == 412) {
                ResponseDescription = "Precondition Failed";
            } else if (ResponseCode == 413) {
                ResponseDescription = "Request Entity Too Large";
            } else if (ResponseCode == 414) {
                ResponseDescription = "Request-URI Too Long";
            } else if (ResponseCode == 415) {
                ResponseDescription = "Unsupported Media Type";
            } else if (ResponseCode == 416) {
                ResponseDescription = "Requested Range Not Satisfiable";
            } else if (ResponseCode == 417) {
                ResponseDescription = "Expectation Failed";
            } else if (ResponseCode == 500) {
                ResponseDescription = "Internal Server Error";
            } else if (ResponseCode == 501) {
                ResponseDescription = "Not Implemented";
            } else if (ResponseCode == 502) {
                ResponseDescription = "Bad Gateway";
            } else if (ResponseCode == 503) {
                ResponseDescription = "Service Unavailable";
            } else if (ResponseCode == 504) {
                ResponseDescription = "Gateway Timeout";
            } else if (ResponseCode == 505) {
                ResponseDescription = "HTTP Version Not Supported";
            }
        } catch (Exception var3) {
            log.error(appendLog(""), var3);
        }

        return ResponseDescription;
    }

    public static boolean isMatchWithJunkKeywords(String url) {
        Iterator var1 = TLD_Init.JunkRemovalSet.iterator();

        String junk;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            junk = (String)var1.next();
        } while(!url.contains(junk));

        return true;
    }

    public static boolean matchRegex(String match, String regexT) {
        if (match == null) {
            match = "";
        }

        try {
            Pattern regex = Pattern.compile(regexT, 234);
            Matcher regexMatcher = regex.matcher(match);
            if (regexMatcher.find()) {
                return true;
            }
        } catch (PatternSyntaxException var4) {
            ;
        } catch (Exception var5) {
            log.error(appendLog(""), var5);
        }

        return false;
    }

    public static String appendLog(String message) {
        return  pid + "-" + message;
    }

    public static String createLogJson1(String stage, int status, String log_message) {
        String json = null;

        try {
            Logtracepojo logtracepojo = new Logtracepojo();
            logtracepojo.setStage(stage);
            logtracepojo.setStatus(status + "");
            logtracepojo.setLogMessage(log_message);
            logtracepojo.setTime(String.valueOf(sdf.format(new Date())));
            logtracepojo.setProcessed_IP(output.getProcessed_IP());
            logtracepojo.setBatchName(output.getBatch_Name());
            logtracepojo.setBatchId(output.getBatch_Configuartion_ID());
            logtracepojo.setPid(Long.parseLong(pid));
            logtracepojo.setBot_name("LinkExtraction-Revamp V2.1.2");
            logtracepojo.setInput_arguments(input);
            ObjectWriter ow = (new ObjectMapper()).writer();
            json = ow.writeValueAsString(logtracepojo);
        } catch (Exception var6) {
            log.error(appendLog(""), var6);
        }

        return json;
    }

    public static JSONObject LoadJSONFromFile(String fileName) {
        JSONObject tempJsonObject = null;

        try {
            JSONParser parser = new JSONParser();
            tempJsonObject = (JSONObject)parser.parse(new FileReader(fileName));
        } catch (FileNotFoundException var3) {
            log.info(appendLog("Configuration.json File Missed"));
            log.info(createLogJson1("Bot Initiation", 0, "Configuration File Not Found " + var3.getMessage()));
            log.error(appendLog(""), var3);
            System.exit(0);
        } catch (Exception var4) {
            log.error(appendLog(""), var4);
            System.exit(0);
        }

        return tempJsonObject;
    }

    public static void loadJson(JSONObject prop) {
        try {
            
            JSONObject jsa = (JSONObject)prop.get("Database Confiugration");
            
            String IP = (String)jsa.get("IPAddress");
            GetterSetter.setUsername(jsa.get("Username").toString());
            GetterSetter.setPassword(jsa.get("Password").toString());
            GetterSetter.setIPAddress(IP);
            
            String Database = jsa.get("Database").toString();
            GetterSetter.setDatabase(Database);
            GetterSetter.setInputTable(jsa.get("InputTable").toString());
            ActionsDecider.OutTableName_Org = jsa.get("OutputTable").toString();
            GetterSetter.setOutputTable(ActionsDecider.OutTableName_Org);
            String temp = "";
            jsa = (JSONObject)prop.get("Crawling Approach - To Select the Operation which is need to Extract the links (true=Enable, false=Disable)");
            temp = jsa.get("Sitemap").toString();
            GetterSetter.setSitemapFlag(Boolean.parseBoolean(temp));
            GetterSetter.setDepthWiseFlag(Boolean.parseBoolean(jsa.get("DepthLevel").toString()));
            jsa = (JSONObject)prop.get("Mention keyword names that contains in bucket");
            keywordFileNames = jsa.get("KeywordFileNames").toString();
            jsa = (JSONObject)prop.get("Define Infra - d2k or local or mobito");
            GetterSetter.setInfra(jsa.get("Type").toString());
            jsa = (JSONObject)prop.get("Link count need to be extract");
            GetterSetter.setLinkLimit(Integer.parseInt(jsa.get("Linklimit").toString()));
            jsa = (JSONObject)prop.get("Mention Depth Level - Here Max - 10");
            GetterSetter.setDepthLevel(Integer.parseInt(jsa.get("depthLevel").toString()));
            jsa = (JSONObject)prop.get("To Select the Link Cleansing Approaches (true=Enable, false=Disable)");
            GetterSetter.setGeneralFlag(Boolean.parseBoolean(jsa.get("SocialMediaRemoval").toString()));
            GetterSetter.setBlackListedFlag(Boolean.parseBoolean(jsa.get("BlackListedDomainRemoval").toString()));
            GetterSetter.setStoreLocatorFlag(Boolean.parseBoolean(jsa.get("JunkRemoval").toString()));
            jsa = (JSONObject)prop.get("To check Store Locator (true=Enable, false=Disable)");
            GetterSetter.setJunkFlag(Boolean.parseBoolean(jsa.get("StoreLocator").toString()));
            jsa = (JSONObject)prop.get("Total Thread pool Size");
            GetterSetter.setThreadSize(Integer.parseInt(jsa.get("Threadpoolsize").toString()));
            jsa = (JSONObject)prop.get("Processing  Time Interval per URL Default = 2");
            GetterSetter.setProcessingTimeout(Integer.parseInt(jsa.get("ProcessingTimeOut").toString()));
            jsa = (JSONObject)prop.get("To load urls using proxy ips");
            GetterSetter.setLoadFromProxy(Boolean.parseBoolean(jsa.get("loadfromproxy").toString()));
            GetterSetter.setProxyIP(jsa.get("proxyip").toString());
            GetterSetter.setProxyPort(jsa.get("port").toString());
            jsa = (JSONObject)prop.get("Start record id & End Range record id");
            GetterSetter.setStart(Integer.parseInt(jsa.get("startID").toString()));
            GetterSetter.setEnd(Integer.parseInt(jsa.get("endID").toString()));
            jsa = (JSONObject)prop.get("URL Connection Timeout in milli secs");
            GetterSetter.setTimeout(Integer.parseInt(jsa.get("Timeout").toString()));
            int top=Integer.parseInt(jsa.get("TimeoutForProcessingInMinutes").toString());
            int bottom=Integer.parseInt(jsa.get("TimeRelayInSeconds").toString());
            //System.out.println(top);
            //System.out.println(bottom);
            
            GetterSetter.setTimeoutForProcessingInMinutes(top);
            GetterSetter.setTimeRelayInSeconds(Integer.parseInt(jsa.get("TimeRelayInSeconds").toString()));
            
            jsa = (JSONObject)prop.get("OtherDomains");
            GetterSetter.setOtherDomain(Boolean.parseBoolean(jsa.get("otherDomains").toString()));
            GetterSetter.setLinkLimit(Integer.parseInt(jsa.get("Linklimit").toString()));
        } catch (Exception var5) {
            log.error(appendLog(""), var5);
        }

    }
}
