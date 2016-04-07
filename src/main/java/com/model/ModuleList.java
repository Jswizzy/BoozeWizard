package com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModuleList implements Serializable
{

//    @JsonProperty("Module")
    private List<Module> modules = new ArrayList<Module> ();

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "ModuleList{" +
                "modules=" + modules +
                '}';
    }
}
