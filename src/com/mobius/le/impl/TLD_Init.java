//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TLD_Init {
    private static final String tldNamesFileName = "Essentials/tld-names.txt";
    private static Set<String> tldSet = new HashSet();
    public static Set<String> storeLocatorSet = new HashSet();
    public static Set<String> GeneralSet = new HashSet();
    public static Set<String> BlackListedSet = new HashSet();
    public static Set<String> JunkRemovalSet = new HashSet();

    public TLD_Init() {
    }

    public static void TLD_Init() {
        try {
            FileReader file = new FileReader("Essentials/tld-names.txt");
            BufferedReader br = new BufferedReader(file);

            String scan;
            while((scan = br.readLine()) != null) {
                tldSet.add(scan.toLowerCase());
            }

            br.close();
            file.close();
        } catch (Exception var3) {
            Loader.log.error(Loader.appendLog(""), var3);
//            System.exit(0);
        }

        if (GetterSetter.isStoreLocatorFlag()) {
            storeLocatorSet = read("Essentials/StoreLocatorList.txt");
        }

        if (GetterSetter.isGeneralFlag()) {
            GeneralSet = read("Essentials/GeneralSites.txt");
        }

        BlackListedSet = read("Essentials/BlackList_Domain.txt");
        JunkRemovalSet = read("Essentials/JunkKeywords.txt");
    }

    public static ArrayList<String> readFile(String fileName) {
        ArrayList Variants = new ArrayList();

        try {
            FileReader file = new FileReader(fileName);
            BufferedReader br = new BufferedReader(file);

            String scan;
            while((scan = br.readLine()) != null) {
                Variants.add(scan.trim());
            }

            br.close();
            file.close();
        } catch (Exception var5) {
            Loader.log.error(Loader.appendLog(""), var5);
//            System.exit(0);
        }

        return Variants;
    }

    public static Set<String> read(String fileName) {
        HashSet Variants = new HashSet();

        try {
            FileReader file = new FileReader(fileName);
            BufferedReader br = new BufferedReader(file);

            String scan;
            while((scan = br.readLine()) != null) {
                Variants.add(scan.trim());
            }

            br.close();
            file.close();
        } catch (Exception var5) {
            Loader.log.error(Loader.appendLog(""), var5);
//            System.exit(0);
        }

        return Variants;
    }

    public static boolean TLD_Check(String str) {
        return tldSet.contains(str.toLowerCase());
    }
}
