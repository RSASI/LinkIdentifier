//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class AssetProperties {
    @JsonProperty("ActiveMQ_Port")
    private String activeMQ_Port;
    @JsonProperty("Common_Queue")
    private String common_Queue;
    @JsonProperty("Input_Queue")
    private String input_Queue;
    @JsonProperty("Output_Queue")
    private String output_Queue;
    @JsonProperty("Exception_Queue")
    private String exception_Queue;
    @JsonProperty("Common_Exception_Queue")
    private String common_Exception_Queue;
    @JsonProperty("ActiveMQ_IP")
    private String activeMQ_IP;

    public AssetProperties() {
    }

    public String getActiveMQ_Port() {
        return this.activeMQ_Port;
    }

    public void setActiveMQ_Port(String activeMQ_Port) {
        this.activeMQ_Port = activeMQ_Port;
    }

    public String getCommon_Queue() {
        return this.common_Queue;
    }

    public void setCommon_Queue(String common_Queue) {
        this.common_Queue = common_Queue;
    }

    public String getInput_Queue() {
        return this.input_Queue;
    }

    public void setInput_Queue(String input_Queue) {
        this.input_Queue = input_Queue;
    }

    public String getOutput_Queue() {
        return this.output_Queue;
    }

    public void setOutput_Queue(String output_Queue) {
        this.output_Queue = output_Queue;
    }

    public String getException_Queue() {
        return this.exception_Queue;
    }

    public void setException_Queue(String exception_Queue) {
        this.exception_Queue = exception_Queue;
    }

    public String getCommon_Exception_Queue() {
        return this.common_Exception_Queue;
    }

    public void setCommon_Exception_Queue(String common_Exception_Queue) {
        this.common_Exception_Queue = common_Exception_Queue;
    }

    public String getActiveMQ_IP() {
        return this.activeMQ_IP;
    }

    public void setActiveMQ_IP(String activeMQ_IP) {
        this.activeMQ_IP = activeMQ_IP;
    }
}
