//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import com.mobius.le.model.TempOutput;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.swing.DefaultListModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Initialize {
    static Map<String, String> Others = new HashMap();
    public static Set<String> GeneralSites = new TreeSet();
    public static long Start = 0L;
    Thread threadProcess;
    DefaultListModel model;

    public Initialize() {
    }

    public void clear() {
        if (this.model != null) {
            this.model.removeAllElements();
        }

        DepthManager.depthlevel = 0;
        ThreadScheduler.check.clear();
        DepthManager.depthlevel = 0;
        DepthManager.NewsReleaseflag = 0;
        DepthManager.Privacyflag = 1;
        Others.clear();
    }

    public static void variableinitialize() {
        DepthManager.domainURL = "";
        ThreadScheduler.check.clear();
        DepthManager.depthlevel = 0;
        DepthManager.NewsReleaseflag = 0;
        DepthManager.Privacyflag = 1;
        Others.clear();
    }

    public void begin() {
        try {
            this.threadProcess = new Thread(new Initialize.process());
            this.threadProcess.start();
        } catch (Exception var2) {
            Loader.log.error(Loader.appendLog(""), var2);
        }

    }

    public static void clearset() {
        DepthManager.stopsignal = 1;
        ThreadScheduler.check.clear();
        DepthManager.depthlevel = 0;
        DepthManager.NewsReleaseflag = 0;
        DepthManager.Privacyflag = 1;
        Others.clear();
    }

    class process extends ActionsDecider implements Runnable {
        process() {
        }

        public void run() {
            try {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Future<String> future = executor.submit(new ActionsDecider());
                int timeOutForProcessing = GetterSetter.getTimeoutForProcessingInMinutes() * 60;
                timeOutForProcessing -= GetterSetter.getTimeRelayInSeconds();
                boolean timeOutflag = false;

                try {
                    future.get((long)timeOutForProcessing, TimeUnit.SECONDS);
                } catch (TimeoutException var10) {
                    timeOutflag = true;
                    future.cancel(true);
                    Loader.log.info(Loader.createLogJson1("Process Execution", 0, "Process Timeout "));
                    if (Loader.finalOutputList.size() == 0) {
                        Loader.log.info(Loader.appendLog("Process limit exceeded.."));
                        Loader.log.error(Loader.appendLog(""), var10);
                        JSONArray tempEmptyOutputJsonArray = new JSONArray();
                       DBConnectivity.insertValues(tempEmptyOutputJsonArray,Loader.input.getInputId(), Loader.input.getDomainURL(),4,"Process limit exceeded..","false");
                        
                        
//                        HashMap<String, String> tempEmptyOutput = new HashMap();
////                        Loader.makeOutputPojoToMap(tempEmptyOutput, new TempOutput(Loader.input.getInputId(), Loader.input.getOutputId(), Loader.input.getMobId(), Loader.input.getClientID(), Loader.input.getDomainURL(), "", "", "", "", "", "", "", "", "", "Failure", "Process Timeout", Loader.startTime, Loader.sdf.format(new Date()), ""));
//                        Loader.makeOutputPojoToMap(tempEmptyOutput, new TempOutput(Loader.input.getInputId(),  Loader.input.getDomainURL(), "", "", "", "", "", "", "", "", "", "Failure", "Process Timeout", Loader.startTime, Loader.sdf.format(new Date()), ""));
//                        Loader.keywordLookup("", "Link", tempEmptyOutput);
//                        Loader.keywordLookup("", "LinkText", tempEmptyOutput);
//                        JSONObject tempJsonEmptyOutput = new JSONObject();
//                        tempJsonEmptyOutput.putAll(tempEmptyOutput);
//                        JSONArray tempEmptyOutputJsonArray = new JSONArray();
//                        tempEmptyOutputJsonArray.add(tempJsonEmptyOutput);
//                        Loader.result.setOutput(tempEmptyOutputJsonArray);
//                        String outputQueuex = Loader.objectMapper.writeValueAsString(Loader.output);
////                        ActiveMQConnection.sendToActiveMQQueue_XML(Loader.assetProperties, outputQueuex, Loader.activeMQCon);
//                        ActiveMQConnection.sendToActiveMQQueue_XML(Loader.assetProperties, outputQueuex, "");
//                        System.exit(0);
                    } else {
                        Loader.log.info(Loader.appendLog("Process limit exceeded..with output"));

                        TempOutput tempOutput;
                        String tempFlag;
                        for(Iterator var6 = Loader.finalOutputList.iterator(); var6.hasNext(); tempOutput.setFinal_Status_Descritpion(tempFlag)) {
                            tempOutput = (TempOutput)var6.next();
                            tempFlag = tempOutput.getFinal_Status_Descritpion();
                            if ("".equals(tempFlag)) {
                                tempFlag = "Process TimeOut";
                            } else {
                                tempFlag = tempFlag + " - Process TimeOut";
                            }
                        }
                        JSONArray finalOutputJsonArry = Loader.indentifyRemarksOfURL();
                        DBConnectivity.insertValues(finalOutputJsonArry,Loader.input.getInputId(), Loader.input.getDomainURL(),6,"Process TimeOut","true");
                        
//                        Loader.result.setOutput(finalOutputJsonArry);
//                        String outputQueue = Loader.objectMapper.writeValueAsString(Loader.output);
////                        ActiveMQConnection.sendToActiveMQQueue_XML(Loader.assetProperties, outputQueue, Loader.activeMQCon);
//                        ActiveMQConnection.sendToActiveMQQueue_XML(Loader.assetProperties, outputQueue, "");
//                        System.exit(0);
                    }
                } catch (Exception var11) {
                    Loader.log.error("Exception ", var11);
                }

                executor.shutdownNow();
            } catch (Exception var12) {
                Loader.log.error(Loader.appendLog(""), var12);
            }

        }
    }
}
