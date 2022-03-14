//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import java.util.concurrent.Callable;

public class ActionsDecider implements Callable {
    public static int rescode = 0;
    public static int redirectflag = 0;
    public static String proximity = "";
    public static String exmsg = "";
    public static String redirecturl = "";
    public static String resdesc = "";
    public static String OutTableName_Org = "";

    public ActionsDecider() {
    }

    public String call() {
        try {
            DepthManager.manage();
        } catch (Exception var2) {
            Loader.log.error(Loader.appendLog(""), var2);
        }

        return "";
    }

    public static void redirectFlagManager(int deplev) {
        if (deplev == 0 && redirecturl.trim().length() == 0 && redirectflag == 1) {
            redirectflag = 0;
        }

        if (redirectflag == 1 && deplev == 0 && redirecturl.trim().length() != 0) {
            DepthManager.domainURL = redirecturl;
        }

    }
}
