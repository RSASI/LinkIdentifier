/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.mobius.le.impl.DBConnectivity;
import static com.mobius.le.impl.DBConnectivity.LoadProperties;
import com.mobius.le.impl.GetterSetter;
import static com.mobius.le.impl.Loader.LoadJSONFromFile;
import static com.mobius.le.impl.Loader.appendLog;
import static com.mobius.le.impl.Loader.getKeywordFiles;
import static com.mobius.le.impl.Loader.loadJson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang.WordUtils;
import org.json.simple.JSONObject;

/**
 *
 * @author SASIKUMAR
 */
public class ThreadMain {
    
    static JSONObject config = new JSONObject();
//        public static int ID;
    public static String link;
    public static ResultSet resultSet = null;
    private static Statement statement = null;
    public static String ID="";
    public static                    String DomainID="";
    public static                    String DomainName="";

    public static void main(String[] args) throws SQLException {
                    try {
                config = LoadJSONFromFile("Configuration.json");
                loadJson(config);
                LoadProperties(config);
//                getKeywordFiles();
                DBConnectivity.DBConnection();
            } catch (Exception var8) {
//                log.error(appendLog(""), var8);
                var8.printStackTrace();
            }
                    
                     System.out.println(">>>>>>>>>> DB Connected <<<<<<<<<<");
                      ExecutorService executor = Executors.newFixedThreadPool(5);
                     Statement st = DBConnectivity.con.createStatement();
//            ResultSet rs = st.executeQuery("select * from " + DBConnectivity.DBName + ".dbo." + DBConnectivity.Input_Table + " WITH (NOLOCK) where Status=0 and ID between " + DBConnectivity.Start + " and " + DBConnectivity.End + " and oid not in (select oid from " + DBConnectivity.DBName + ".dbo." + DBConnectivity.New_OutputTable + " ) order by id");
                    ResultSet rs = st.executeQuery("select * from " + GetterSetter.getDatabase() + ".dbo.DomainValidation WITH (NOLOCK) where Status=0 order by id");
                    while (rs.next()){
                        ID=rs.getString(1);
                        DomainID=rs.getString(2);
                        DomainName=rs.getString(3);
//                        System.out.println("Domain Name "+DomainName);
                       Execute_ContentDownload content_extractor = new Execute_ContentDownload(ID, DomainID, DomainName);
                       executor.execute(content_extractor);
                      
                    }
                    executor.shutdown();
                while (!executor.isTerminated()) {
                }
                executor.shutdown();

    }

    
    public static class Execute_ContentDownload
            implements Runnable {

        String InputID;
        String DomainName = "";
        String DomainID = "";

        public Execute_ContentDownload(String InputID, String DomainID, String DomainName) {
            this.InputID = InputID;
            this.DomainID = DomainID;
            this.DomainName = DomainName;
            
        }

        @Override
        public void run() {
            try {

                String ErrorStatus = "";
                String ErrorStatusDescription = "";
                System.out.println("ID : " + this.InputID);
                System.out.println("Loading URL: " + this.DomainName);
                
                

                } catch (Exception ex) {
                }

            
        }
    }
    
}
