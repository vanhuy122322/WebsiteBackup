//package fa.training.spring.service.emailservice.emailserviceimpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import fa.training.spring.dto.EmailDetail;
//import fa.training.spring.service.emailservice.EmailService;
//
//@Service
//public class EmailServiceImpl implements EmailService{
//
//	@Autowired
//	private JavaMailSender javaMailSender;
//	
//	@Value("${spring.mail.username}") private String sender;
//	
//	@Override
//	public boolean sendSimpleMail(EmailDetail details) {
//		 // Try block to check for exceptions
//        try {
//            // Creating a simple mail message
//            SimpleMailMessage mailMessage
//                = new SimpleMailMessage();
//            // Setting up necessary details
//            mailMessage.setFrom(sender);
//            mailMessage.setTo(details.getRecipient());
//            mailMessage.setText(details.getMsgBody());
//            mailMessage.setSubject(details.getSubject());
//            // Sending the mail
//            javaMailSender.send(mailMessage);
//            return true;
//        }
//        catch (Exception e) {
//            return false;
//        }
//	}
//
//	
//}
