package com.alibaba.easyexcel.test.demo.read;

import lombok.Data;

import java.util.Date;

@Data
public class MyExcelRead {

    private String sourceName;
    private String InstName;


    public String getInstName() {
        return InstName;
    }

    public void setInstName(String instName) {
        InstName = instName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
