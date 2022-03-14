//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadScheduler implements Runnable {
    static Set<String> check = new TreeSet();
    String url = "";
    Set<String> s = null;
    List<String> linkText = null;
    int domid = 0;
    int deplev = 0;
    int extract_flag = 0;
    String url_source = "";
    String[] linktext = null;
    int depth = 0;

    public ThreadScheduler(String urlThread, Set<String> s, int domid, int deplev, int extract_flag, String url_source, int depth) {
        this.url = urlThread;
        this.s = s;
        this.linkText = this.linkText;
        this.domid = domid;
        this.deplev = deplev;
        this.extract_flag = extract_flag;
        this.url_source = url_source;
        this.depth = depth;
    }

    public void run() {
        try {
            if (DepthManager.linklimit >= DepthManager.insertCount) {
                ActionPerform.ActionPerformed(this.url, this.s, this.domid, this.deplev, this.extract_flag, this.depth);
            }

            int stat = 0;
            if (!this.url.isEmpty()) {
                ;
            }
        } catch (Exception var2) {
            Loader.log.error(Loader.appendLog(""), var2);
        }

    }

    public static void threadingSchedule(Set current, int tsize, Set resultset, int domainid, int depthlevel, int depth) {
        try {
            List<Future<?>> tasks = new ArrayList();
            ExecutorService executerServicesobj = Executors.newFixedThreadPool(tsize);
            Map<Integer, String> Future_task_track = new HashMap();
            Iterator it = current.iterator();
            int future_task_index_number = 0;

            for(int var11 = 0; it.hasNext(); ++future_task_index_number) {
                ++var11;
                String s = it.next().toString();
                check.add(s);
                if (DepthManager.linklimit < DepthManager.insertCount) {
                    break;
                }

                Runnable worker = new ThreadScheduler(s, resultset, domainid, depthlevel, 1, (String)null, depth);
                tasks.add(future_task_index_number, executerServicesobj.submit(worker));
                Future_task_track.put(future_task_index_number, s);
            }

            executerServicesobj.shutdown();
            long start = System.currentTimeMillis();

            while(true) {
                int seconds;
                do {
                    if (executerServicesobj.isTerminated()) {
                        return;
                    }

                    long end = System.currentTimeMillis();
                    seconds = (int)((end - start) / 1000L);
                } while(seconds < GetterSetter.getProcessingTimeout() * 60);

                if (seconds >= 300) {
                    break;
                }

                Iterator var17 = tasks.iterator();

                while(var17.hasNext()) {
                    Future<?> fut = (Future)var17.next();
                    if (!fut.isDone()) {
                        Loader.log.error(Loader.appendLog("Sub Thread " + fut + " has not finished"));
                        fut.cancel(true);
                        if (seconds >= 300) {
                            break;
                        }

                        try {
                            int index_num = tasks.indexOf(fut);
                            int stat = 3;
                            Loader.log.error(Loader.appendLog(" has not finished number " + index_num + " " + (String)Future_task_track.get(index_num)));
                            MySQL_Interface.exceptionurlinThread(domainid, (String)Future_task_track.get(index_num), depthlevel, stat);
                        } catch (Exception var21) {
                            Loader.log.error(Loader.appendLog(""), var21);
                        }
                    }
                }
            }
        } catch (Exception var22) {
            Loader.log.error(Loader.appendLog(""), var22);
        }

    }
}
