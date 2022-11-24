package fa.training.spring.Util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.productdto.ProductDTO;
import fa.training.spring.dto.productdto.ProductDetailDTO;

public class ExcelImport {
    
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
                ProductDetailDTO productDetailDTO=new ProductDetailDTO();
                productDTO.setName(row.getCell(0).getStringCellValue());
                productDTO.setPrice(row.getCell(1).getNumericCellValue());
                productDTO.setAmount(Integer.valueOf(String.valueOf(row.getCell(2).getNumericCellValue())) );
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
