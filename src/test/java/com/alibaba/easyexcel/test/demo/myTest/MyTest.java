package com.alibaba.easyexcel.test.demo.myTest;

import com.alibaba.easyexcel.test.demo.read.MyExcelRead;
import com.alibaba.easyexcel.test.demo.read.ReadTest;
import com.alibaba.easyexcel.test.demo.write.DemoData;
import com.alibaba.easyexcel.test.demo.write.MyExcelWrite;
import com.alibaba.easyexcel.test.util.TestFileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadTest.class);

    @Test
    public void  readTest(){

        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "MyTest.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, MyExcelRead.class, new PageReadListener<MyExcelRead>(dataList -> {
            int i = 0;
            for (MyExcelRead myExcelDeom : dataList) {
                i++;
                System.out.println(myExcelDeom);
            }
            System.out.println(i);
        })).sheet().doRead();
    }

    @Test
    public void writeTest(){
        String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, MyExcelWrite.class)
            .sheet("模板")
            .doWrite(() -> {
                // 分页查询数据
                return data();
            });
    }

    private List<MyExcelWrite> data() {


        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "MyTest.xlsx";
        List<MyExcelWrite> list = new ArrayList<>();

        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, MyExcelRead.class, new PageReadListener<MyExcelRead>(dataList -> {
            int i = 0;

            HashMap<String, String> sMap = new HashMap<>();
            ArrayList<String> iList = new ArrayList<>();
            ArrayList<String> insNames = new ArrayList<>();
            ArrayList<String> sNames = new ArrayList<>();

            for (MyExcelRead myExcelRead : dataList) {
                i++;
                String s = myExcelRead.getSourceName();
                sNames.add(s);
                s = s.substring(0, s.lastIndexOf("_"));
                s = s.replace("-","");
                sMap.put(s, myExcelRead.getSourceName());

                if (null  != myExcelRead.getInstName() ) {
                    String iN = myExcelRead.getInstName();
                    insNames.add(iN);
                    iN = iN.substring(iN.lastIndexOf("-")+1,iN.lastIndexOf("电"));
                    iList.add(iN);
                }



//                MyExcelWrite data = new MyExcelWrite();
//
//                data.setSourceName(myExcelRead.getSourceName());
//                data.setInstName(myExcelRead.getInstName());
//                list.add(data);
            }

            for (int j = 0; j < iList.size(); j++) {
                String s = sMap.get(iList.get(j));
                System.out.println(s+"-------"+iList.get(j));

                MyExcelWrite data = new MyExcelWrite();

                data.setSourceName(sNames.get(j));
                data.setInstName(insNames.get(j));
                data.setModified(s);
                list.add(data);

            }

            System.out.println(i);
        })).sheet().doRead();



        return list;
    }


    @Test
    public void  matchString(){

        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "MyTest.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, MyExcelRead.class, new PageReadListener<MyExcelRead>(dataList -> {

            HashMap<String, String> sMap = new HashMap<>();
            ArrayList<String> iList = new ArrayList<>();
            for (MyExcelRead myExcelDeom : dataList) {

                String s = myExcelDeom.getSourceName();
                s = s.substring(s.lastIndexOf("-")+1);
                s = s.substring(0, s.lastIndexOf("_"));
                sMap.put(s, myExcelDeom.getSourceName());

                if (null  != myExcelDeom.getInstName() ) {
                    String iN = myExcelDeom.getInstName();
                    iN = iN.substring(iN.lastIndexOf("/") + 1, iN.lastIndexOf("电"));
                    iList.add(iN);
                }

            }

            for (int j = 0; j < iList.size(); j++) {
                String s = sMap.get(iList.get(j));
                System.out.println(s+"-------"+iList.get(j));
            }

        })).sheet().doRead();
    }


    @Test
    public void test(){
        String s = "3栋-/-0601_9425039";
        s = s.substring(0, s.lastIndexOf("_"));
        s = s.replace("-","");

        System.out.println(s);


        String iN = "3栋/1235电表";

        iN = iN.substring(iN.lastIndexOf("-")+1,iN.lastIndexOf("电"));
        System.out.println(iN);

    }

}
