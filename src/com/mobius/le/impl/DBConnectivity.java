/*
 * Decompiled with CFR 0_118.
 */
package com.mobius.le.impl;

import static com.mobius.le.impl.Loader.appendLog;
import static com.mobius.le.impl.Loader.createLogJson1;
import static com.mobius.le.impl.Loader.log;
import com.mobius.le.model.TempOutput;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DBConnectivity {

    public static Connection con = null;
    public static String IPAddress = "";
    public static String DBName = "";
    public static String UserName = "";
    public static String Password = "";
    static String Input_Table = "";
    public static String New_OutputTable = "";
    public static int Start;
    public static int End;
    public static int Timeout;
    public static int Thread_Pool;
    Properties props = null;
    static String Offline;
    public static int deadlockpriority;
    public static int lockout1;
    public static int lockout2;
    public static String MailUsername;
    public static String MailTo;
    public static String MailCC;
    public static String MailBCC;
    public static String Mail_Subject;
    public static String Mail_Desc;
    public static String CTPInsertion;
    public static String Highbeam_insertion;
    public static String URL_Exclusion_Regex;
    public static String InstanceNO;
    public static String processname;
    static String Stage;
    public static String TrackerTable;
    public static String shortdescription;
    public static String Processtype;

    public static String UserAgent = "";
    public static String MetaURLRegex = "";

    public static int LoopCount = 0;
    public static int ConnectionTimeOut;

    public static void LoadProperties(JSONObject prop) throws Exception {
         try {
            
            JSONObject jsa = (JSONObject)prop.get("Database Confiugration");
            
            IPAddress = (String)jsa.get("IPAddress");
            GetterSetter.setUsername(jsa.get("Username").toString());
            GetterSetter.setPassword(jsa.get("Password").toString());
            GetterSetter.setIPAddress(IPAddress);
            
            DBName = jsa.get("Database").toString();
            deadlockpriority=Integer.parseInt(jsa.get("Deadlockpriority").toString());
            GetterSetter.setDatabase(DBName);
            GetterSetter.setInputTable(jsa.get("InputTable").toString());
            ActionsDecider.OutTableName_Org = jsa.get("OutputTable").toString();
            GetterSetter.setOutputTable(ActionsDecider.OutTableName_Org);
            String temp = "";
            jsa = (JSONObject)prop.get("Crawling Approach - To Select the Operation which is need to Extract the links (true=Enable, false=Disable)");
            temp = jsa.get("Sitemap").toString();
            GetterSetter.setSitemapFlag(Boolean.parseBoolean(temp));
            GetterSetter.setDepthWiseFlag(Boolean.parseBoolean(jsa.get("DepthLevel").toString()));
            jsa = (JSONObject)prop.get("Mention keyword names that contains in bucket");
            Loader.keywordFileNames = jsa.get("KeywordFileNames").toString();
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
//            GetterSetter.setLinkLimit(Integer.parseInt(jsa.get("Linklimit").toString()));
        } catch (Exception var5) {
            log.error(appendLog(""), var5);
        }

    }

    public static void DBConnection() throws SQLException, ClassNotFoundException, Exception {
//        DBConnectivity.LoadProperties();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String ConnectionUrl = "jdbc:sqlserver://"+GetterSetter.getIPAddress();
        con = DriverManager.getConnection("jdbc:sqlserver://" + GetterSetter.getIPAddress() + ";" + "user=" + GetterSetter.getUsername() + ";" + "Password=" + GetterSetter.getPassword() + ";" + "DatabaseName=" + GetterSetter.getDatabase());
    }

    static {
        Offline = "";
        MailUsername = "";
        MailTo = "";
        MailCC = "";
        MailBCC = "";
        Mail_Subject = "";
        Mail_Desc = "";
        CTPInsertion = "";
        Highbeam_insertion = "";
        URL_Exclusion_Regex = "";
        InstanceNO = "";
        processname = "";
        Stage = "";
        TrackerTable = "";
        shortdescription = "";
        Processtype = "";
    }
    
    public static void insertValues(ArrayList finalOutputList,String URLID,String DomainLink, int statusflag,String Remarks,String ProcessFlag) throws SQLException{
    
        if (ProcessFlag.equalsIgnoreCase("true")){
        
        Iterator temparray = Loader.finalOutputList.iterator();
        while (temparray.hasNext()){
            
            
            
        TempOutput tempOutput = (TempOutput)temparray.next();
        int count=0;
        Statement st = DBConnectivity.con.createStatement();      
//            ResultSet rs = st.executeQuery("select * from " + DBConnectivity.DBName + ".dbo." + DBConnectivity.Input_Table + " WITH (NOLOCK) where Status=0 and ID between " + DBConnectivity.Start + " and " + DBConnectivity.End + " and oid not in (select oid from " + DBConnectivity.DBName + ".dbo." + DBConnectivity.New_OutputTable + " ) order by id");
        
            System.out.println("select * from " + GetterSetter.getDatabase() + ".dbo." + GetterSetter.getOutputTable() + " WITH (NOLOCK) where extractedURL = '"+tempOutput.getExtractedURL()+"'");    
            ResultSet rs1 = st.executeQuery("select * from " + GetterSetter.getDatabase() + ".dbo." + GetterSetter.getOutputTable() + " WITH (NOLOCK) where extractedURL = '"+tempOutput.getExtractedURL()+"'");    
            while(rs1.next()){
                count++;
            }    
        
        st.close();
        rs1.close();
        
        if (count<1){
        PreparedStatement pstmt = null;
        try{
            String qry = "INSERT INTO " + DBConnectivity.DBName + ".dbo." + GetterSetter.getOutputTable() + "  ([URL_ID],[DomainURL],[extractedURL],[Navigation_Travel_status],[Redirected_URL],[Response_Code],[Response_Description],[Links_Count],[Link_Text],[Link_Type],[Depth_Level],[Process_Final_Status],[Final_Status_Descritpion],[startTime],[endTime],[remarks],[Hashcode],[Status]) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pstmt = DBConnectivity.con.prepareStatement(qry);
                pstmt.setString(1, URLID);
                pstmt.setString(2, DomainLink);
                pstmt.setString(3, tempOutput.getExtractedURL());
                pstmt.setString(4, tempOutput.getNavigation_Travel_status());
                pstmt.setString(5, tempOutput.getRedirected_URL());
                pstmt.setString(6, tempOutput.getResponse_Code());
                pstmt.setString(7, tempOutput.getResponse_Description());
                pstmt.setString(8, tempOutput.getLinks_Count());
                pstmt.setString(9, tempOutput.getLink_Text());
                pstmt.setString(10, tempOutput.getLink_Type());
                pstmt.setString(11, tempOutput.getDepth_Level());
                pstmt.setString(12, tempOutput.getProcess_Final_Status());
                pstmt.setString(13, tempOutput.getFinal_Status_Descritpion());
                pstmt.setString(14, tempOutput.getStart_Time());
                pstmt.setString(15, tempOutput.getEnd_Time());
                pstmt.setString(16, Remarks);
                pstmt.setInt(17, (DomainLink.trim() + tempOutput.getExtractedURL()).hashCode());
                pstmt.setInt(18,statusflag);
                pstmt.executeUpdate();
                pstmt.clearBatch();
                pstmt.clearWarnings();
                pstmt.close();
                
        }catch(SQLException r){
            r.printStackTrace();
        }
        }
        }
        System.out.println("Record Inserted:" + URLID);
            UpdateInputTable(URLID, DomainLink, statusflag);
        }else{
         PreparedStatement pstmt = null;
        try{
          String qry = "INSERT INTO " + DBConnectivity.DBName + ".dbo." + GetterSetter.getOutputTable() + "  ([URL_ID],[DomainURL],[extractedURL],[Navigation_Travel_status],[Redirected_URL],[Response_Code],[Response_Description],[Links_Count],[Link_Text],[Link_Type],[Depth_Level],[Process_Final_Status],[Final_Status_Descritpion],[startTime],[endTime],[remarks],[Hashcode],[Status]) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pstmt = DBConnectivity.con.prepareStatement(qry);
                pstmt.setInt(1, Integer.parseInt(Loader.input.getInputId()));
                pstmt.setString(2, DomainLink);
                pstmt.setString(3, "");
                pstmt.setString(4, "");
                pstmt.setString(5, "");
                pstmt.setString(6, "");
                pstmt.setString(7, "");
                pstmt.setString(8, "");
                pstmt.setString(9, "");
                pstmt.setString(10, "");
                pstmt.setString(11, "");
                pstmt.setString(12, "");
                pstmt.setString(13, "");
                pstmt.setString(14, "");
                pstmt.setString(15, "");
                pstmt.setString(16, Remarks);
                pstmt.setInt(17, 0);
                pstmt.setInt(18,statusflag);
                pstmt.executeUpdate();
                pstmt.clearBatch();
                pstmt.clearWarnings();
                pstmt.close();
                
        }catch(SQLException r){
            r.printStackTrace();
        }
        UpdateInputTable(URLID, DomainLink, statusflag);
        }
        
        
        
    }
    
    
    public static void UpdateInputTable(String URLID,String DomainLink, int statusflag){
        try {
            
            CallableStatement sd = null;
            sd = DBConnectivity.con.prepareCall(" update " + GetterSetter.getInputTable() + " with(updlock) set Status = ?  where Domain_ID = ? ");
            sd.setInt(1, statusflag);
            sd.setString(2, URLID);
//            sd.setString(3, ErrorDesc);
//            sd.setString(4, Procesname);
//            sd.setString(5, currentdate);
//            sd.setString(6, StTime);
            sd.executeUpdate();
            System.out.println("Update count:" + sd.getUpdateCount());
            sd.clearBatch();
            sd.clearWarnings();
            sd.close();
        } catch (Exception ell) {
            ell.printStackTrace();
        }
        
        System.out.println("Input Table Updated for ID :" + URLID);
        
    }
    
    public static void CreateOutputtable() {
        PreparedStatement ps = null;
        String query = "";
        query = "if not exists (select * from  " + GetterSetter.getDatabase() + ".INFORMATION_SCHEMA.TABLES where TABLE_NAME ='" + GetterSetter.getOutputTable() + "' )\n" + "    CREATE TABLE [dbo].["+GetterSetter.getOutputTable()+"](\n" +
"	[id] [int] IDENTITY(1,1) NOT NULL,\n" +
"	[URL_ID] [nvarchar](50) NOT NULL,\n" +
"	[DomainURL] [nvarchar](max) NULL,\n" +
"	[extractedURL] [nvarchar](max) NULL,\n" +
"	[Navigation_Travel_status] [nvarchar](50) NULL,\n" +
"	[Redirected_URL] [nvarchar](max) NULL,\n" +
"	[Response_Code] [nvarchar](10) NULL,\n" +
"	[Response_Description] [nvarchar](max) NULL,\n" +
"	[Links_Count] [nvarchar](10) NULL,\n" +
"	[Link_Text] [nvarchar](max) NULL,\n" +
"	[Link_Type] [nvarchar](max) NULL,\n" +
"	[Depth_Level] [nvarchar](50) NULL,\n" +
"	[Process_Final_Status] [nvarchar](max) NULL,\n" +
"	[Final_Status_Descritpion] [nvarchar](max) NULL,\n" +
"	[startTime] [nvarchar](50) NULL,\n" +
"	[endTime] [nvarchar](50) NULL,\n" +
"	[remarks] [nvarchar](max) NULL,\n" +
"	[Hashcode] [int] NULL,\n" +
"	[Status] [int] NULL\n" +
") ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]";
        try {
//            DBConnectivity.DBConnection();
            ps = DBConnectivity.con.prepareStatement(query);
            ps.executeUpdate();
            System.out.println(GetterSetter.getOutputTable() + " Output Table Created ");
        }catch (Exception s){
            log.info(appendLog("DB Connection Failure"));
            log.info(createLogJson1("Bot Initiation", 0, "DB Connection Failure " + s.getMessage()));
        
        }
        }
    
    
    
}
