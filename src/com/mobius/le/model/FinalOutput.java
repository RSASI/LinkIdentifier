//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class FinalOutput {
    @JsonProperty("Client_Name")
    private String Client_Name;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Result")
    private Result Result;
    @JsonProperty("Processed_IP")
    private String Processed_IP;
    @JsonProperty("Asset_Name")
    private String Asset_Name;
    @JsonProperty("Application_Name")
    private String Application_Name;
    @JsonProperty("Batch_Configuartion_ID")
    private String Batch_Configuartion_ID;
    @JsonProperty("Project_ID")
    private String Project_ID;
    @JsonProperty("Recurrence_ID")
    private String Recurrence_ID;
    @JsonProperty("Batch_Name")
    private String Batch_Name;

    public FinalOutput(String Client_Name, String Status, String Processed_IP, String Asset_Name, String Application_Name, String Batch_Configuartion_ID, String Project_ID, String Recurrence_ID, String Batch_Name) {
        this.Client_Name = Client_Name;
        this.Status = Status;
        this.Processed_IP = Processed_IP;
        this.Asset_Name = Asset_Name;
        this.Application_Name = Application_Name;
        this.Batch_Configuartion_ID = Batch_Configuartion_ID;
        this.Project_ID = Project_ID;
        this.Recurrence_ID = Recurrence_ID;
        this.Batch_Name = Batch_Name;
    }

    public String getClient_Name() {
        return this.Client_Name;
    }

    public void setClient_Name(String Client_Name) {
        this.Client_Name = Client_Name;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getProcessed_IP() {
        return this.Processed_IP;
    }

    public void setProcessed_IP(String Processed_IP) {
        this.Processed_IP = Processed_IP;
    }

    public String getAsset_Name() {
        return this.Asset_Name;
    }

    public void setAsset_Name(String Asset_Name) {
        this.Asset_Name = Asset_Name;
    }

    public String getApplication_Name() {
        return this.Application_Name;
    }

    public void setApplication_Name(String Application_Name) {
        this.Application_Name = Application_Name;
    }

    public String getBatch_Configuartion_ID() {
        return this.Batch_Configuartion_ID;
    }

    public void setBatch_Configuartion_ID(String Batch_Configuartion_ID) {
        this.Batch_Configuartion_ID = Batch_Configuartion_ID;
    }

    public String getProject_ID() {
        return this.Project_ID;
    }

    public void setProject_ID(String Project_ID) {
        this.Project_ID = Project_ID;
    }

    public String getRecurrence_ID() {
        return this.Recurrence_ID;
    }

    public void setRecurrence_ID(String Recurrence_ID) {
        this.Recurrence_ID = Recurrence_ID;
    }

    public String getBatch_Name() {
        return this.Batch_Name;
    }

    public void setBatch_Name(String Batch_Name) {
        this.Batch_Name = Batch_Name;
    }

    public Result getResult() {
        return this.Result;
    }

    public void setResult(Result Result) {
        this.Result = Result;
    }
}
