package com.demosos.view;

import com.demosos.view.abstractview.AbstractExcelView;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {


        //not implemented yet


    }

}
