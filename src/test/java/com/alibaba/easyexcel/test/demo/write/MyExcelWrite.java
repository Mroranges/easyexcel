package com.alibaba.easyexcel.test.demo.write;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MyExcelWrite {

    @ExcelProperty("资源名称")
    private String sourceName;

    @ExcelProperty("仪表名称")
    private String instName;

    @ExcelProperty("对应仪表的资源名称")
    private String modified;

    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
