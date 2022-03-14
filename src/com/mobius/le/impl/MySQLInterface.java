//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import com.mobius.le.model.TempOutput;
import java.util.Date;

class MySQL_Interface {
    public static String infra = "";
    static int i = 0;

    MySQL_Interface() {
    }

    public static void insertOutputTableNonHTML(int domid, String url, int Depth_Level, int File_Status, int responsecode) {
        try {
            int hashcode = (domid + url).hashCode();
            if (DepthManager.linklimit > DepthManager.insertCount) {
//                Loader.finalOutputList.add(new TempOutput(Loader.input.getInputId(), Loader.input.getOutputId(), Loader.input.getMobId(), Loader.input.getClientID(), Loader.input.getDomainURL(), url, "", "", "", "", "", "", "", Depth_Level + "", "Faliure", "Process Completed", Loader.startTime, Loader.sdf.format(new Date()), ""));
                Loader.finalOutputList.add(new TempOutput(Loader.input.getInputId(),Loader.input.getDomainURL(), url, "", "", "", "", "", "", "", Depth_Level + "", "Faliure", "Process Completed", Loader.startTime, Loader.sdf.format(new Date()), ""));
                ++DepthManager.insertCount;
            }
        } catch (Exception var6) {
            if (!var6.getMessage().contains("Duplicate entry") && !var6.getMessage().contains("Cannot insert duplicate key")) {
                Loader.log.error(Loader.appendLog(""), var6);
            }
        }

    }

    public static void insertOutputTable(int domid, String urlinsert, String stype, int deplev, int rescode, int responsecode, String sublink_responsedesc, String domainRedirection) {
        int hashcode = 0;
        String tempinserturl = "";
        String ltext = "";

        try {
            tempinserturl = urlinsert.replaceAll(".*://", "");
            tempinserturl = tempinserturl.replaceAll("(.*?)/$", "$1");
            if (!DepthManager.dlmap.isEmpty()) {
                ltext = (String)DepthManager.dlmap.get(urlinsert);
            } else {
                ltext = " ";
            }

            hashcode = (domid + urlinsert).hashCode();
            String metasource = "";
            if (stype.isEmpty()) {
                stype = "Type Not Classified";
            }

            int tempRes = 0;
//            int tempRes;
            if (rescode == 0) {
                tempRes = responsecode;
            } else {
                tempRes = rescode;
            }

            if (sublink_responsedesc.trim().equals("")) {
                sublink_responsedesc = Loader.GetResponseMessage(tempRes);
            }

            if (DepthManager.linklimit > DepthManager.insertCount) {
//                Loader.finalOutputList.add(new TempOutput(Loader.input.getInputId(), Loader.input.getOutputId(), Loader.input.getMobId(), Loader.input.getClientID(), Loader.input.getDomainURL(), urlinsert, "", domainRedirection, "" + tempRes, sublink_responsedesc, "", ltext, stype, deplev + "", "Success", "Process Completed", Loader.startTime, Loader.sdf.format(new Date()), ""));
                Loader.finalOutputList.add(new TempOutput(Loader.input.getInputId(), Loader.input.getDomainURL(), urlinsert, "", domainRedirection, "" + tempRes, sublink_responsedesc, "", ltext, stype, deplev + "", "Success", "Process Completed", Loader.startTime, Loader.sdf.format(new Date()), ""));
                ++DepthManager.insertCount;
            }
        } catch (Exception var13) {
            Loader.log.error(Loader.appendLog(""), var13);
        }

    }

    public static void exceptionurlinsert(int stat, int domid, String url, int deplev, int rescode, String domainRedirection, String exceptionMessage) {
        try {
            url = url.replaceAll("(?sim)\n", "");
            url = url.replaceAll("(?sim)\r\n", "");
            if (DepthManager.linklimit > DepthManager.insertCount) {
//                Loader.finalOutputList.add(new TempOutput(Loader.input.getInputId(), Loader.input.getOutputId(), Loader.input.getMobId(), Loader.input.getClientID(), Loader.input.getDomainURL(), url, "", domainRedirection, "" + rescode, "", "", "", "", deplev + "", "Faliure", "Exception while loading URL :" + exceptionMessage, Loader.startTime, Loader.sdf.format(new Date()), ""));
                Loader.finalOutputList.add(new TempOutput(Loader.input.getInputId(), Loader.input.getDomainURL(), url, "", domainRedirection, "" + rescode, "", "", "", "", deplev + "", "Faliure", "Exception while loading URL :" + exceptionMessage, Loader.startTime, Loader.sdf.format(new Date()), ""));
                ++DepthManager.insertCount;
            }
        } catch (Exception var8) {
            Loader.log.error(Loader.appendLog(""), var8);
        }

    }

    public static void exceptionurlinThread(int domid, String url, int deplev, int stat) {
        try {
            int hashcode = (domid + url).hashCode();
            if (DepthManager.linklimit > DepthManager.insertCount) {
//                Loader.finalOutputList.add(new TempOutput(Loader.input.getInputId(), Loader.input.getOutputId(), Loader.input.getMobId(), Loader.input.getClientID(), Loader.input.getDomainURL(), url, "", "", "0", "", "", "", "", deplev + "", "Faliure", "Exception in Thread", Loader.startTime, Loader.sdf.format(new Date()), ""));
                Loader.finalOutputList.add(new TempOutput(Loader.input.getInputId(),  Loader.input.getDomainURL(), url, "", "", "0", "", "", "", "", deplev + "", "Faliure", "Exception in Thread", Loader.startTime, Loader.sdf.format(new Date()), ""));
                ++DepthManager.insertCount;
            }
        } catch (Exception var5) {
            Loader.log.error(Loader.appendLog(""), var5);
        }

    }
}
