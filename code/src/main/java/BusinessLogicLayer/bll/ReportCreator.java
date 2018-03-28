package BusinessLogicLayer.bll;

import DataLayer.model.Enrolment;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.io.FileOutputStream;

/**
 * Created by Boros on 3/28/2018.
 */
public class ReportCreator {

    public static void createReport(Enrolment enrolment) {

        try {
            int columnCount = 3;
            Document doc = new Document();

            PdfWriter.getInstance(doc, new FileOutputStream(enrolment.getStudent().getName() + ".pdf"));
            doc.open();

            //adding headers
            Paragraph paragraph=new Paragraph();
            paragraph.add("Customer:"+enrolment.getStudent().toString()+"\n");
            paragraph.add("Enrolled courses:\n ");
            doc.add(paragraph);
            PdfPTable pdfTable = new PdfPTable(columnCount);
            pdfTable.addCell(new PdfPCell(new Phrase("Course name")));
            pdfTable.addCell(new PdfPCell(new Phrase("Enrolment date")));
            pdfTable.addCell(new PdfPCell(new Phrase("Grade")));

            int limit = enrolment.getEnrolledCourses().size();
            for (int row = 0; row < limit; row++) {
                pdfTable.addCell(new PdfPCell(new Phrase(String.valueOf(row+1))));
                pdfTable.addCell(new PdfPCell(new Phrase(enrolment.getEnrolledCourses().get(row).getCourse().getName())));
                pdfTable.addCell(new PdfPCell(new Phrase(enrolment.getEnrolledCourses().get(row).getDate().toString())));
                pdfTable.addCell(new PdfPCell(new Phrase(String.valueOf(enrolment.getEnrolledCourses().get(row).getGrade()))));
            }

            doc.add(pdfTable);
            doc.close();

        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
