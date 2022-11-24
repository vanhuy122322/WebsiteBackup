//package fa.training.spring.controller.excelexportcontrollergroup;
//
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import fa.training.spring.Util.ExcelExporter;
//import fa.training.spring.dto.traineedto.TraineeDTO;
//import fa.training.spring.service.traineeservice.TraineeService;
//
//@Controller
//@RequestMapping("/api/v1/excel/trainee")
//public class ExcelController {
//	
//	@Autowired
//    TraineeService traineeServiceImpl;
//	
//	@GetMapping
//	public void exportToExcel(HttpServletResponse response) throws IOException {
//		response.setContentType("application/octet-stream");
//		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//		String currentDateTime = dateFormatter.format(new Date());
//		String headerKey = "Content-Disposition";
//		String headerValue = "attachment; filename=trainees_" + currentDateTime + ".xlsx";
//		response.setHeader(headerKey, headerValue);
//		List<TraineeDTO> traineeDTOs = traineeServiceImpl.getAllTrainee();
//		ExcelExporter excelExporter = new ExcelExporter(traineeDTOs);
//		excelExporter.export(response);
//	}
//}
