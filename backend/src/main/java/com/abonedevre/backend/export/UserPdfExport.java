package com.abonedevre.backend.export;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.abonedevre.backend.entity.User;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class UserPdfExport extends AbstractExporter {

    public void export(List<User> listUsers, HttpServletResponse httpServletResponse) throws IOException {
        super.setResponseHeader(httpServletResponse, "application/pdf", ".pdf", "users_");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, httpServletResponse.getOutputStream());
      
        document.open();
      
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(175,1,113);  
      
        Paragraph paragraph = new Paragraph("List of Users", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
      
        document.add(paragraph);
        
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        table.setWidths(new float[] {1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});
        
        writeTableHeader(table);
        writeTableData(table, listUsers);
        
        document.add(table);
        
        document.close();
    }
    
    private void writeTableHeader(PdfPTable table){

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(5);
        
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(14);
        font.setColor(0, 0, 0);
        
        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Roles", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Enabled", font));
        table.addCell(cell);
    }
    
    private void writeTableData(PdfPTable table, List<User> listUsers){
        for(User user : listUsers){
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getEmail());
            table.addCell(user.getFirstName());
            table.addCell(user.getLastName());
            // table.addCell(user.getRoles().toString());
            table.addCell(String.valueOf(user.isEnabled()));
        }
    }
  
}
