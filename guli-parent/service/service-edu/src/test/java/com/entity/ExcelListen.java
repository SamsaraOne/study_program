package com.entity;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListen extends AnalysisEventListener<ExcelEntity> {
    @Override
    public void invoke(ExcelEntity excelEntity, AnalysisContext analysisContext) {
        System.out.println(excelEntity);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头"+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
