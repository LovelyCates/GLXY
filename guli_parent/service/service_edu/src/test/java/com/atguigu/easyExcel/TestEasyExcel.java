package com.atguigu.easyExcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {

    public static void main(String[] args) {

        // 实现 excel 写操作
        // 1. 设置写入文件夹地址和excel文件名称
        String filename = "E:\\write.xlsx";

        // 2. 调用 easyexcel 方法 实现写操作
//        EasyExcel.write(filename, DemoData.class)
//                .sheet("学生列表")
//                .doWrite(getData());


        // 实现 excel 读操作
        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet().doRead();
    }

    // 创建方法List集合
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy" + i);
            list.add(data);
        }
        return list;
    }

}
