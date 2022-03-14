//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class TempOutput {
    @JsonProperty("InputId")
    private String InputId;
//    @JsonProperty("OutputId")
//    private String OutputId;
//    @JsonProperty("MobId")
//    private String MobId;
//    @JsonProperty("ClientId")
//    private String ClientId;
    @JsonProperty("DomainURL")
    private String URL;
    @JsonProperty("ExtractedURL")
    private String extractedURL;
    @JsonProperty("Navigation_Travel_status")
    private String Navigation_Travel_status;
    @JsonProperty("DomainRedirection")
    private String Redirected_URL;
    @JsonProperty("Response_Code")
    private String Response_Code;
    @JsonProperty("Response_Description")
    private String Response_Description;
    @JsonProperty("Links_Count")
    private String Links_Count;
    @JsonProperty("Link_Text")
    private String Link_Text;
    @JsonProperty("Link_Type")
    private String Link_Type;
    @JsonProperty("Depth_Level")
    private String Depth_Level;
    @JsonProperty("Process_Final_Status")
    private String Process_Final_Status;
    @JsonProperty("Final_Status_Scenario")
    private String Final_Status_Descritpion;
    @JsonProperty("Start_Time")
    private String Start_Time;
    @JsonProperty("End_Time")
    private String End_Time;
    @JsonProperty("Remarks")
    private String remarks;
    @JsonProperty("Link_Duplicate_Count")
    private String linkDublicateCount;

    public String getLinkDublicateCount() {
        return this.linkDublicateCount;
    }

    public void setLinkDublicateCount(String linkDublicateCount) {
        this.linkDublicateCount = linkDublicateCount;
    }

    public TempOutput() {
    }

//    public TempOutput(String InputId, String OutputId, String MobId, String ClientId, String URL, String extractedURL, String Navigation_Travel_status, String Redirected_URL, String Response_Code, String Response_Description, String Links_Count, String Link_Text, String Link_Type, String Depth_Level, String Process_Final_Status, String Final_Status_Descritpion, String startTime, String endTime, String remarks) {
    public TempOutput(String InputId,String URL, String extractedURL, String Navigation_Travel_status, String Redirected_URL, String Response_Code, String Response_Description, String Links_Count, String Link_Text, String Link_Type, String Depth_Level, String Process_Final_Status, String Final_Status_Descritpion, String startTime, String endTime, String remarks) {
        this.InputId = InputId;
//        this.OutputId = OutputId;
//        this.MobId = MobId;
//        this.ClientId = ClientId;
        this.URL = URL;
        this.extractedURL = extractedURL;
        this.Navigation_Travel_status = Navigation_Travel_status;
        this.Redirected_URL = Redirected_URL;
        this.Response_Code = Response_Code;
        this.Response_Description = Response_Description;
        this.Links_Count = Links_Count;
        this.Link_Text = Link_Text;
        this.Link_Type = Link_Type;
        this.Depth_Level = Depth_Level;
        this.Process_Final_Status = Process_Final_Status;
        this.Final_Status_Descritpion = Final_Status_Descritpion;
        this.Start_Time = startTime;
        this.End_Time = endTime;
        this.remarks = remarks;
    }

    public String getExtractedURL() {
        return this.extractedURL;
    }

    public void setExtractedURL(String extractedURL) {
        this.extractedURL = extractedURL;
    }

    public String getStart_Time() {
        return this.Start_Time;
    }

    public void setStart_Time(String Start_Time) {
        this.Start_Time = Start_Time;
    }

    public String getEnd_Time() {
        return this.End_Time;
    }

    public void setEnd_Time(String End_Time) {
        this.End_Time = End_Time;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInputId() {
        return this.InputId;
    }

    public void setInputId(String InputId) {
        this.InputId = InputId;
    }

//    public String getOutputId() {
//        return this.OutputId;
//    }
//
//    public void setOutputId(String OutputId) {
//        this.OutputId = OutputId;
//    }
//
//    public String getMobId() {
//        return this.MobId;
//    }
//
//    public void setMobId(String MobId) {
//        this.MobId = MobId;
//    }
//
//    public String getClientId() {
//        return this.ClientId;
//    }
//
//    public void setClientId(String ClientId) {
//        this.ClientId = ClientId;
//    }

    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getNavigation_Travel_status() {
        return this.Navigation_Travel_status;
    }

    public void setNavigation_Travel_status(String Navigation_Travel_status) {
        this.Navigation_Travel_status = Navigation_Travel_status;
    }

    public String getRedirected_URL() {
        return this.Redirected_URL;
    }

    public void setRedirected_URL(String Redirected_URL) {
        this.Redirected_URL = Redirected_URL;
    }

    public String getResponse_Code() {
        return this.Response_Code;
    }

    public void setResponse_Code(String Response_Code) {
        this.Response_Code = Response_Code;
    }

    public String getResponse_Description() {
        return this.Response_Description;
    }

    public void setResponse_Description(String Response_Description) {
        this.Response_Description = Response_Description;
    }

    public String getLinks_Count() {
        return this.Links_Count;
    }

    public void setLinks_Count(String Links_Count) {
        this.Links_Count = Links_Count;
    }

    public String getLink_Text() {
        return this.Link_Text;
    }

    public void setLink_Text(String Link_Text) {
        this.Link_Text = Link_Text;
    }

    public String getLink_Type() {
        return this.Link_Type;
    }

    public void setLink_Type(String Link_Type) {
        this.Link_Type = Link_Type;
    }

    public String getDepth_Level() {
        return this.Depth_Level;
    }

    public void setDepth_Level(String Depth_Level) {
        this.Depth_Level = Depth_Level;
    }

    public String getProcess_Final_Status() {
        return this.Process_Final_Status;
    }

    public void setProcess_Final_Status(String Process_Final_Status) {
        this.Process_Final_Status = Process_Final_Status;
    }

    public String getFinal_Status_Descritpion() {
        return this.Final_Status_Descritpion;
    }

    public void setFinal_Status_Descritpion(String Final_Status_Descritpion) {
        this.Final_Status_Descritpion = Final_Status_Descritpion;
    }
}
