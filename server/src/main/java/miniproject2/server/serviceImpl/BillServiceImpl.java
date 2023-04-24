package miniproject2.server.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import lombok.extern.slf4j.Slf4j;
import miniproject2.server.JWT.JwtFilter;
import miniproject2.server.constants.CafeConstants;
import miniproject2.server.dao.BillDao;
import miniproject2.server.dao.TableStatusDao;
import miniproject2.server.model.Bill;
import miniproject2.server.model.TableStatus;
import miniproject2.server.model.User;
import miniproject2.server.service.BillService;
import miniproject2.server.utils.CafeUtils;

@Slf4j
@Service
public class BillServiceImpl implements BillService{

    @Autowired
    JwtFilter jwtFilter; 

    @Autowired
    BillDao billDao;

    @Autowired
    TableStatusDao tableStatusDao;

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        log.info("Inside generateReport");
        try {

            String fileName;
            if(validateRequestMap(requestMap)) {
                if(requestMap.containsKey("isGenerate") && !(Boolean) requestMap.get("isGenerate")) {
                    fileName = (String) requestMap.get("uuid");
                } else {
                    fileName = CafeUtils.getUUID();
                    requestMap.put("uuid", fileName);
                    // requestMap.put("points", calculatePoints());
                    // log.info("calculated points is: ", calculatePoints());
                    insertBill(requestMap);
                }

                //pdf format
                String data = "Name: " + requestMap.get("name") + "\n" + "Contact Number: " +  requestMap.get("contactNumber") +
                            "\n" + "Email: " + requestMap.get("email") + "\n" + "Payment Method: " + requestMap.get("paymentMethod");

                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(CafeConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));

                doc.open();
                setRectangleInPdf(doc);
                log.info("!!!!!!!!! REPORT DATA IS !!!!!!!!!!!" + data);

                Paragraph chunk = new Paragraph("Coffee For Two", getFont("Header"));
                chunk.setAlignment(Element.ALIGN_CENTER);
                doc.add(chunk);

                Paragraph paragraph = new Paragraph(data + "\n \n", getFont("Data"));
                doc.add(paragraph);

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                addTableHeader(table);

                JSONArray jsonArray = CafeUtils.getJsonArrayFromString((String) requestMap.get("productDetails"));
                for ( int i = 0; i < jsonArray.length(); i++) {
                    addRows(table, CafeUtils.getMapFromJson(jsonArray.getString(i)));
                }
                doc.add(table);

                Paragraph footer = new Paragraph("Total: " +  "$" +requestMap.get("totalAmount") + 
                                    " Thank you for visiting. Please visit again!!", getFont("Data"));
                doc.add(footer);
                doc.close();
                return new ResponseEntity<>("{\"uuid\":\"" + fileName + "\"}", HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity("Required data not found", HttpStatus.BAD_REQUEST);

           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void addRows(PdfPTable table, Map<String, Object> data) {
        log.info("Inside addRows");
        table.addCell((String) data.get("name"));
        table.addCell((String) data.get("category"));
        table.addCell((String) data.get("quantity"));
        table.addCell(Double.toString((Double) data.get("price")));
        table.addCell(Double.toString((Double) data.get("total")));;

    }

    private void addTableHeader(PdfPTable table) {
        log.info("inside addTableHeader");
        Stream.of("Name", "Category", "Quantity", "Price", "Sub Total")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private Font getFont(String type) {
        log.info("Inside getFont");
        switch (type) {
            case "Header":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data":
                Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
                dataFont.setStyle(Font.BOLD);
                return dataFont;
            default:
                return new Font();
        }
    }

    private void setRectangleInPdf(Document doc) throws DocumentException {

        log.info("Inside setRectangleInPdf");
        Rectangle rect = new Rectangle(577, 825, 18, 15);
        rect.enableBorderSide(1);
        rect.enableBorderSide(2);
        rect.enableBorderSide(4);
        rect.enableBorderSide(8);
        rect.setBorderColor(BaseColor.BLACK);
        rect.setBorderWidth(1);
        doc.add(rect);
    }

    private void insertBill(Map<String, Object> requestMap) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String formatDate = localDate.format(formatters);
        log.info("DATE IS: "+ formatDate);
        try {
            
            Bill bill = new Bill();
            bill.setUuid((String) requestMap.get("uuid"));
            bill.setName((String) requestMap.get("name"));
            bill.setEmail((String) requestMap.get("email"));
            bill.setContactNumber((String) requestMap.get("contactNumber"));
            bill.setPaymentMethod((String) requestMap.get("paymentMethod"));
            bill.setTotal(Integer.parseInt((String) requestMap.get("totalAmount")));
            bill.setProductDetails((String) requestMap.get("productDetails"));
            bill.setCreatedBy(jwtFilter.getCurrentUser());
            bill.setDate(formatDate);
            billDao.save(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // private Integer calculatePoints() {
    //     Bill bill = new Bill();
    //     Integer billAmt = bill.getTotal();
    //     Integer billPoints = billAmt;
    //     return billPoints;
    // }

    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("name") && 
                requestMap.containsKey("contactNumber") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("paymentMethod") &&
                requestMap.containsKey("productDetails") &&
                requestMap.containsKey("totalAmount")
        ;
    }

    @Override
    public ResponseEntity<List<Bill>> getBills() {
        List<Bill> list = new ArrayList<>();
        if (jwtFilter.isAdmin()) {
            list = billDao.getAllBills();
        } else {
            list = billDao.getBillByUserName(jwtFilter.getCurrentUser());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        log.info("Inside getPdf: requestMap {}", requestMap);
        try {
            byte[] byteArray = new byte[0];
            if (!requestMap.containsKey("uuid") && validateRequestMap(requestMap))
                return new ResponseEntity<>(byteArray, HttpStatus.BAD_REQUEST);
            String filePath = CafeConstants.STORE_LOCATION + "\\" + (String) requestMap.get("uuid") + ".pdf";

            if(CafeUtils.isFileExist(filePath)) {
                byteArray = getByteArray(filePath);
                return new ResponseEntity<>(byteArray, HttpStatus.OK);
            } else {
                requestMap.put("isGenerate", false);
                generateReport(requestMap);
                byteArray = getByteArray(filePath);
                return new ResponseEntity<>(byteArray, HttpStatus.OK);
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getByteArray(String filePath) throws Exception {
        File initialFile = new File(filePath);
        InputStream targetStream = new FileInputStream(initialFile);
        byte[] byteArray = IOUtils.toByteArray(targetStream);
        targetStream.close();
        return byteArray;
    }

    @Override
    public ResponseEntity<String> deleteBill(Integer id) {
        try {
            Optional optional = billDao.findById(id);
            if (!optional.isEmpty()) {
                billDao.deleteById(id);
                return CafeUtils.getResponseEntity("Bill deleted successfully", HttpStatus.OK);
            }
            return CafeUtils.getResponseEntity("Bill id does not exist", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<String> addTable(Map<String, String> requeMap) {
		try {
           
                    tableStatusDao.save(getTableFromMap(requeMap));
                    return CafeUtils.getResponseEntity("Table added successfully", HttpStatus.OK);
               
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    private TableStatus getTableFromMap(Map<String, String> requeMap) {
        //Bill bill = new Bill();
        TableStatus table = new TableStatus();
        table.setTableId(requeMap.get("tableId"));
        table.setPax(Integer.parseInt((String) requeMap.get("pax")));
        table.setStatus("true");
        return table;
    }

    @Override
    public ResponseEntity<List<TableStatus>> getAllTable() {
        try {
            log.info("INSIDE GET");
            return new ResponseEntity<>(tableStatusDao.getAllTable(), HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateTable(Map<String, String> requeMap) {
        try {
           

                

                   Optional<TableStatus> optional = tableStatusDao.findById(Integer.parseInt(requeMap.get("id")));
                   if (!optional.isEmpty()) {
                    tableStatusDao.updateTableStatus(requeMap.get("status"),Integer.parseInt(requeMap.get("id")));
                    return CafeUtils.getResponseEntity("Table status updated successfully", HttpStatus.OK);

                   } else {

                        return CafeUtils.getResponseEntity("Table ID does not exist", HttpStatus.OK);

                   }
                
             
                
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteTable(Integer id) {
        try {
           

            Optional optional = tableStatusDao.findById(id);
            if (!optional.isEmpty()) {
                tableStatusDao.deleteById(id);
                 return CafeUtils.getResponseEntity("Table deleted successfully", HttpStatus.OK);
            } 
            return CafeUtils.getResponseEntity("Table ID does not exist", HttpStatus.OK);     
           
         
      
         
 } catch (Exception e) {
     e.printStackTrace();
 }

 return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
