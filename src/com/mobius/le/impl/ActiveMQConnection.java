////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//package com.mobius.le.impl;
//
//import com.mobius.le.model.AssetProperties;
//import javax.jms.Connection;
//import javax.jms.Destination;
//import javax.jms.MessageProducer;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//import org.apache.activemq.ActiveMQConnectionFactory;
//
//public class ActiveMQConnection {
//    public ActiveMQConnection() {
//    }
//
//    public static Connection getConnection(AssetProperties assetPro) {
//        Connection connection = null;
//
//        try {
//            ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://" + assetPro.getActiveMQ_IP() + ":61616");
//            connection = cf.createConnection();
//            connection.start();
//            return connection;
//        } catch (Exception var3) {
//            Loader.log.error(Loader.appendLog("Active MQ Connection Exception ---"), var3);
//            return null;
//        }
//    }
//
//    public static boolean sendToActiveMQQueue_XML(AssetProperties assetPro, String outputJson, Connection con) {
//        Connection connection = null;
//        int tempActiveMQCount = 0;
//        if (con == null) {
//            Loader.log.info(Loader.createLogJson1("ActiveMQ Connection", 0, "ActiveMQ Connection Failure "));
//            System.exit(0);
//        } else {
//            Loader.log.info(Loader.createLogJson1("ActiveMQ Connection", 1, "ActiveMQ Connection Successfull"));
//        }
//
//        while(tempActiveMQCount <= 3) {
//            try {
//                tempActiveMQCount = tempActiveMQCount + 1;
//                connection = con;
//                Session session = con.createSession(false, 1);
//                Destination destination = session.createQueue(assetPro.getOutput_Queue());
//                MessageProducer messageProducer = session.createProducer(destination);
//                TextMessage message = session.createTextMessage();
//                message.setText(outputJson);
//                messageProducer.send(message);
//                Loader.log.info(Loader.createLogJson1("Output Queue Message", 1, "Message Pushed to Queue Successfully"));
//
//                try {
//                    session.close();
//                    connection.close();
//                } catch (Exception var10) {
//                    Loader.log.error(Loader.appendLog("Active MQ Session/Connection close Exception ---"), var10);
//                }
//                break;
//            } catch (Exception var11) {
//                if (tempActiveMQCount > 3) {
//                    Loader.log.info(Loader.createLogJson1("Output Queue Message", 0, "Queue Message Push Failure " + var11.getMessage()));
//                    Loader.log.error(Loader.appendLog("Retrying Connection Attempt--"+tempActiveMQCount+"-----"), var11);
//                    return false;
//                }
//
//                Loader.log.info(Loader.appendLog("Try Number :" + tempActiveMQCount));
//                con = getConnection(Loader.assetProperties);
//            }
//        }
//
//        return true;
//    }
//}
