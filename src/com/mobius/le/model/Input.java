//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Input {
    @JsonProperty("MobId")
    private String mobId;
    @JsonProperty("InputId")
    private String inputId;
    @JsonProperty("ClientId")
    private String clientID;
    @JsonProperty("OutputId")
    private String outputId;
    @JsonProperty("DomainURL")
    private String domainURL;

//    public Input(String mobId, String inputId, String clientID, String outputId, String domainURL) {
    public Input(String inputId,String domainURL) {
//        this.mobId = mobId;
        this.inputId = inputId;
//        this.clientID = clientID;
//        this.outputId = outputId;
        this.domainURL = domainURL;
    }

    public String getInputId() {
        return this.inputId;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId;
    }

    public String getClientID() {
        return this.clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getOutputId() {
        return this.outputId;
    }

    public void setOutputId(String outputId) {
        this.outputId = outputId;
    }

    public String getDomainURL() {
        return this.domainURL;
    }

    public void setDomainURL(String domainURL) {
        this.domainURL = domainURL;
    }

    public Input() {
    }

    public String getMobId() {
        return this.mobId;
    }

    public void setMobId(String mobId) {
        this.mobId = mobId;
    }

    
}
