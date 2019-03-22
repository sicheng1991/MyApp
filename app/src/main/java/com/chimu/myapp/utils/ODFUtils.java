package com.chimu.myapp.utils;

import android.os.Environment;
import android.util.Log;


import com.github.jferard.fastods.AnonymousOdsFileWriter;
import com.github.jferard.fastods.OdsDocument;
import com.github.jferard.fastods.OdsFactory;
import com.github.jferard.fastods.SimpleColor;
import com.github.jferard.fastods.Table;
import com.github.jferard.fastods.TableCellWalker;
import com.github.jferard.fastods.TableRow;
import com.github.jferard.fastods.style.TableCellStyle;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import org.jopendocument.model.OpenDocument;
import org.jopendocument.renderer.ODTRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * ODF  http://www.jopendocument.org/tutorial_pdf.html
 * Created by yangzteL on 2019/1/25 0025.
 */

public class ODFUtils {
    public static void PDF2ODF(String path){
        // Load the ODS file
        final OpenDocument doc = new OpenDocument();


        try {
            doc.loadFrom(Environment.getExternalStorageDirectory().getPath()
                    + File.separator +  "aa.ods");

            // Open the PDF document
            Document document = new Document(PageSize.A4);
            File outFile = new File("aa.pdf");

            PdfDocument pdf = new PdfDocument();

            document.addDocListener(pdf);
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            PdfWriter writer = PdfWriter.getInstance(pdf, fileOutputStream);
            pdf.addWriter(writer);

            document.open();

            // Create a template and a Graphics2D object
            Rectangle pageSize = document.getPageSize();
            int w = (int) (pageSize.getWidth() * 0.9);
            int h = (int) (pageSize.getHeight() * 0.95);
            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(w, h);


//            Graphics2D g2 = tp.createPrinterGraphics(w, h, null);
            // If you want to prevent copy/paste, you can use
            // g2 = tp.createGraphicsShapes(w, h, true, 0.9f);

            tp.setWidth(w);
            tp.setHeight(h);

            // Configure the renderer
            ODTRenderer renderer = new ODTRenderer(doc);
            renderer.setIgnoreMargins(true);
            renderer.setPaintMaxResolution(true);

            // Scale the renderer to fit width
            renderer.setResizeFactor(renderer.getPrintWidth() / w);
            // Render
//            renderer.paintComponent(g2);
//            g2.dispose();

            // Add our spreadsheet in the middle of the page
            float offsetX = (pageSize.getWidth() - w) / 2;
            float offsetY = (pageSize.getHeight() - h) / 2;
            cb.addTemplate(tp, offsetX, offsetY);
            // Close the PDF document
            document.close();
        }catch (Exception e){
            Log.i("PDF2ODF", "PDF2ODF: " + e.getMessage());
        }

    }

    /**
     * https://github.com/jferard/fastods#why-fastods
     *
     */
    public static void PDF2ODF(){
        final OdsFactory odsFactory = OdsFactory.create(Logger.getLogger("example"), Locale.US);
        final AnonymousOdsFileWriter writer = odsFactory.createWriter();
        final OdsDocument document = writer.document();
        try{
            final Table table = document.addTable("test");

            final TableCellStyle style = TableCellStyle.builder("style").backgroundColor(SimpleColor.BLUE).build();
            for (int y = 0; y < 50; y++) {
                final TableRow row = table.nextRow();
                final TableCellWalker cell = row.getWalker();
                for (int x = 0; x < 5; x++) {
                    cell.setFloatValue(x*y);
                    cell.setStyle(style);
                    cell.next();
                }
            }

            writer.saveAs(new File(Environment.getExternalStorageDirectory(), "aa.ods"));
        }catch (Exception e){
            Log.i("PDF2ODF", "PDF2ODF: " + e.getMessage());
        }


    }

}
