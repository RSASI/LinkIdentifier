//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mobius.le.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.json.simple.JSONArray;

public class Result {
    @JsonProperty("Output")
    private JSONArray output;
    @JsonProperty("Input")
    Input input;

    public Result() {
    }

    public JSONArray getOutput() {
        return this.output;
    }

    public void setOutput(JSONArray output) {
        this.output = output;
    }

    public Input getInput() {
        return this.input;
    }

    public void setInput(Input input) {
        this.input = input;
    }
}
