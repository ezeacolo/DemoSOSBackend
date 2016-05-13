package com.demosos.view;

import com.demosos.view.abstractview.AbstractPdfView;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PDFView extends AbstractPdfView {

    protected void buildPdfDocument(
            Map<String, Object> model,
            Document document,
            PdfWriter writer,
            HttpServletRequest req,
            HttpServletResponse resp)
            throws Exception {

        //not implemented yet

    }

}
