//package fa.training.spring.controller.emailcontroller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import fa.training.spring.dto.EmailDetail;
//import fa.training.spring.dto.RequestDTO;
//import fa.training.spring.dto.ResponseDTO;
//import fa.training.spring.service.emailservice.EmailService;
//
//@RestController
//@RequestMapping("/api/v1/email")
//public class EmailController {
//	
//	@Autowired
//	private EmailService emailService;
//
//	@PostMapping("/sendMail")
//	public ResponseEntity<ResponseDTO<EmailDetail>>  sendMail(@RequestBody RequestDTO<EmailDetail> requestDTO) {
//		ResponseDTO<EmailDetail> resp = new ResponseDTO<EmailDetail>();
//		if(emailService.sendSimpleMail(requestDTO.getData()));{
//			resp.setMessage("Send mail success..!");
//			resp.setStatus(HttpStatus.OK);
//			return ResponseEntity.ok(resp);
//		}
//			
//	}
//}
