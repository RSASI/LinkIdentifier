//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetterSetter {
    private static String IPAddress;
    private static String Username;
    private static String Password;
    private static String Database;
    private static String InputTable;
    private static String OutputTable;
    private static int ThreadSize;
    private static int ProcessingTimeout;
    private static boolean LoadFromProxy;
    private static String ProxyIP;
    private static String ProxyPort;
    private static String Infra;
    private static int Start;
    private static int End;
    private static int Timeout;
    private static int LinkLimit;
    private static Connection con;
    private static int depthLevel;
    private static boolean sitemapFlag;
    private static boolean DepthWiseFlag;
    private static boolean junkFlag;
    private static boolean generalFlag;
    private static boolean blackListedFlag;
    private static boolean StoreLocatorFlag;
    private static boolean OtherDomain;

    public static boolean isOtherDomain() {
        return OtherDomain;
    }

    public static void setOtherDomain(boolean OtherDomain) {
        GetterSetter.OtherDomain = OtherDomain;
    }
//    private static int TimeoutForProcessingInMinutes;
    private static int TimeRelayInSeconds;
    private static int TimeoutForProcessingInMinutes;

    public GetterSetter() {
    }

    public static int getTimeoutForProcessingInMinutes() {
        return TimeoutForProcessingInMinutes;
    }

    public static void setTimeoutForProcessingInMinutes(int aTimeoutForProcessingInMinutes) {
        TimeoutForProcessingInMinutes = aTimeoutForProcessingInMinutes;
    }

    public static int getTimeRelayInSeconds() {
        return TimeRelayInSeconds;
    }

    public static void setTimeRelayInSeconds(int aTimeRelayInSeconds) {
        TimeRelayInSeconds = aTimeRelayInSeconds;
    }

    public static void setJunkFlag(boolean junkFlag) {
    }

    public static boolean isJunkFlag() {
        return junkFlag;
    }

    public static void setGeneralFlag(boolean ageneralFlag) {
        generalFlag = ageneralFlag;
    }

    public static boolean isGeneralFlag() {
        return generalFlag;
    }

    public static void setBlackListedFlag(boolean ablackListedFlag) {
        blackListedFlag = ablackListedFlag;
    }

    public static boolean isBlackListedFlag() {
        return blackListedFlag;
    }

    public static void setStoreLocatorFlag(boolean StoreLocator) {
        StoreLocatorFlag = StoreLocator;
    }

    public static boolean isStoreLocatorFlag() {
        return StoreLocatorFlag;
    }

    public static String getIPAddress() {
        return IPAddress;
    }

    public static void setSitemapFlag(boolean asitemapFlag) {
        sitemapFlag = asitemapFlag;
    }

    public static boolean isSitemapFlag() {
        return sitemapFlag;
    }

    public static void setDepthWiseFlag(boolean aDepthWiseFlag) {
        DepthWiseFlag = aDepthWiseFlag;
    }

    public static boolean isDepthWiseFlag() {
        return DepthWiseFlag;
    }

    public static String getUsername() {
        return Username;
    }

    public static String getPassword() {
        return Password;
    }

    public static String getDatabase() {
        return Database;
    }

    public static String getInputTable() {
        return InputTable;
    }

    public static String getOutputTable() {
        return OutputTable;
    }

    public static int getThreadSize() {
        return ThreadSize;
    }

    public static int getProcessingTimeout() {
        return ProcessingTimeout;
    }

    public static boolean getLoadFromProxy() {
        return LoadFromProxy;
    }

    public static String getProxyIP() {
        return ProxyIP;
    }

    public static String getProxyPort() {
        return ProxyPort;
    }

    public static int getStart() {
        return Start;
    }

    public static int getEnd() {
        return End;
    }

    public static int getTimeout() {
        return Timeout;
    }

    public static Connection getConnection() {
        return con;
    }

    public static void setIPAddress(String newIPAddress) {
        IPAddress = newIPAddress;
    }

    public static void setUsername(String newUsername) {
        Username = newUsername;
    }

    public static void setPassword(String newPassword) {
        Password = newPassword;
    }

    public static void setDatabase(String newDatabase) {
        Database = newDatabase;
    }

    public static void setInputTable(String newInputTable) {
        InputTable = newInputTable;
    }

    public static void setOutputTable(String newOutputTable) {
        OutputTable = newOutputTable;
    }

    public static void setThreadSize(int newThreadSize) {
        ThreadSize = newThreadSize;
    }

    public static void setProcessingTimeout(int newProcessingTimeout) {
        ProcessingTimeout = newProcessingTimeout;
    }

    public static void setLoadFromProxy(boolean newLoadFromProxy) {
        LoadFromProxy = newLoadFromProxy;
    }

    public static void setProxyIP(String newProxyIP) {
        ProxyIP = newProxyIP;
    }

    public static void setProxyPort(String newProxyPort) {
        ProxyPort = newProxyPort;
    }

    public static void setStart(int newStart) {
        Start = newStart;
    }

    public static void setEnd(int newEnd) {
        End = newEnd;
    }

    public static void setTimeout(int newTimeout) {
        Timeout = newTimeout;
    }

    public static void setLinkLimit(int newLinkLimit) {
        LinkLimit = newLinkLimit;
    }

    public static void setConnection() {
        try {
            if (getInfra().equalsIgnoreCase("local")) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + getIPAddress() + ":3306/" + getDatabase(), getUsername(), getPassword());
            } else if (getInfra().equalsIgnoreCase("D2k")) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection("jdbc:sqlserver://" + getIPAddress() + ";user=" + getUsername() + ";password=" + getPassword() + ";databasename=" + getDatabase());
            }
        } catch (Exception var1) {
            con = null;
//            System.exit(0);
        }

    }

    public static boolean isLoadFromProxy() {
        return LoadFromProxy;
    }

    public static int getDepthLevel() {
        return depthLevel;
    }

    public static void setDepthLevel(int adepthLevel) {
        depthLevel = adepthLevel;
    }

    public static int getLinkLimit() {
        return LinkLimit;
    }

    public static void setInfra(String aInfra) {
        Infra = aInfra;
    }

    public static String getInfra() {
        return Infra;
    }
}
