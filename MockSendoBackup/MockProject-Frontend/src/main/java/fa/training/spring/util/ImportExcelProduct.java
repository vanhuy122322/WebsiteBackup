package fa.training.spring.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.product.ProductDTO;
import fa.training.spring.dto.product.ProductDetailDTO;

public class ImportExcelProduct {
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<ProductDTO> productDTOs;
	
	public ImportExcelProduct() {
		
	}

	public ImportExcelProduct(List<ProductDTO> productDTOs) {
		super();
		this.productDTOs = productDTOs;
			workbook = new XSSFWorkbook();
	}
	
	private void createCell(Row row,int columnCount,Object value,CellStyle style) {
	    sheet.autoSizeColumn(columnCount);
	    Cell cell=row.createCell(columnCount);
	    if(value instanceof Long) {
	        cell.setCellValue((Long) value);
	    } else if(value instanceof Integer) {
	        cell.setCellValue((Integer) value);
	    }else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
	    else if(value instanceof Double) {
                cell.setCellValue((Double) value);
	    }else {
	        cell.setCellValue((String) value);
	    }
	    cell.setCellStyle(style);
	}
	
	private void writeHeaderLine() {
	    sheet=workbook.createSheet("Product");
	    
	    Row row = sheet.createRow(0);
	    CellStyle style=workbook.createCellStyle();
	    XSSFFont font=workbook.createFont();
	    font.setBold(true);
	    font.setFontHeight(20);
	    style.setFont(font);
	    style.setAlignment(HorizontalAlignment.CENTER);
	    createCell(row,0,"Product Information",style);
	    sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
	    font.setFontHeightInPoints((short) (10));
	    
	    row=sheet.createRow(1);
	    font.setBold(true);
	    font.setFontHeight(16);
	    style.setFont(font);
	    createCell(row,0,"Product Name",style);
	    createCell(row,1,"Product Price",style);
	    createCell(row,2,"Product Amount",style);
	    createCell(row,3,"Product Image",style);
	    createCell(row,4,"Product ShopName",style);
	    createCell(row,5,"Product Category",style);
	    createCell(row,6,"Product Subcategory",style);
	    createCell(row,7,"Product Title",style);
	    createCell(row,8,"Product Description",style);
	    createCell(row,9,"Product Material",style);
	    createCell(row,10,"Product Origin",style);
	    createCell(row,11,"Product Size",style);
	    createCell(row,12,"Product Color",style);
	}
	
	private void writeDataLines() {
	    int rowCount=2;
	    
	    CellStyle style=workbook.createCellStyle();
	    XSSFFont font=workbook.createFont();
	    font.setFontHeight(14);
	    style.setFont(font);
	    
	    for(ProductDTO productDTO: productDTOs) {
	        Row row=sheet.createRow(rowCount++);
	        int columnCount=0;
	        createCell(row, columnCount++, productDTO.getName(), style);
	        createCell(row, columnCount++, productDTO.getPrice(), style);
	        createCell(row, columnCount++, productDTO.getAmount(), style);
	        createCell(row, columnCount++, productDTO.getImage(), style);
	        createCell(row, columnCount++, productDTO.getShopName(), style);
	        createCell(row, columnCount++, productDTO.getCategory(), style);
	        createCell(row, columnCount++, productDTO.getSubCategory(), style);
	        createCell(row, columnCount++, productDTO.getProductDetail().getTitle(), style);
	        createCell(row, columnCount++, productDTO.getProductDetail().getDescription(), style);
	        createCell(row, columnCount++, productDTO.getProductDetail().getMaterial(), style);
	        createCell(row, columnCount++, productDTO.getProductDetail().getOrigin(), style);
	        createCell(row, columnCount++, productDTO.getProductDetail().getSize(), style);
	        createCell(row, columnCount++, productDTO.getProductDetail().getColor(), style);
	    }
	}
	
	public void export(HttpServletResponse response) throws IOException{
	    writeHeaderLine();
	    writeDataLines();
	    
	    ServletOutputStream outputStream=response.getOutputStream();
	    workbook.write(outputStream);
	    workbook.close();
	    outputStream.close();
	}
	

	public RequestDTO<ProductDTO> readExcelFile(MultipartFile excelFile) {
		RequestDTO<ProductDTO> requestDTO = new RequestDTO<ProductDTO>();
		List<ProductDTO> listProductDTO = new ArrayList<>();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
			Sheet sheet = workbook.getSheet("listProductDTO");
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i);
				ProductDTO productDTO = new ProductDTO();
				ProductDetailDTO productDetailDTO = new ProductDetailDTO();
				productDTO.setName(row.getCell(0).getStringCellValue());
				productDTO.setPrice(row.getCell(1).getNumericCellValue());
				productDTO.setAmount((int) row.getCell(2).getNumericCellValue());
				productDTO.setImage(row.getCell(3).getStringCellValue());
				productDTO.setShopName(row.getCell(4).getStringCellValue());
                productDTO.setCategory(row.getCell(5).getStringCellValue());
                productDTO.setSubCategory(row.getCell(6).getStringCellValue());
                productDetailDTO.setTitle(row.getCell(7).getStringCellValue());
                productDetailDTO.setDescription(row.getCell(8).getStringCellValue());
                productDetailDTO.setMaterial(row.getCell(9).getStringCellValue());
                productDetailDTO.setOrigin(row.getCell(10).getStringCellValue());
                productDetailDTO.setSize(row.getCell(11).getStringCellValue());
                productDetailDTO.setColor(row.getCell(12).getStringCellValue());
                productDTO.setProductDetail(productDetailDTO);
				listProductDTO.add(productDTO);
				requestDTO.setListData(listProductDTO);
			}
			workbook.close();

		} catch (Exception e) {

		}
		return requestDTO;
	}
}
