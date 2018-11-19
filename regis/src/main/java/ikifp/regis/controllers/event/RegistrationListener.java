package ikifp.regis.controllers.event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import ikifp.regis.model.Visitor;
import ikifp.regis.persistence.VerificationTokenService;
import ikifp.regis.persistence.VisitorService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent>{

    @Autowired
    private VisitorService visitorService;
    
    @Autowired
    VerificationTokenService verificationTokenService;
  
    @Autowired
    private MessageSource messages;
    
    @Autowired
    private JavaMailSender mailSender;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
		
	}
	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		Visitor visitor = event.getVisitor();
		String token = UUID.randomUUID().toString();
		//String token = "aaa";
		verificationTokenService.createVerificationToken(visitor, token);
		
		String recipentEmail = visitor.getEmail();
		String messageSubject = "Registration Confirmation";
		String confirmationUrl = "http://localhost:8080/visitors/regitrationConfirm?" + token;
		//String confirmationUrl = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipentEmail);
		email.setSubject(messageSubject);
		email.setText("Please click to confirm: "+confirmationUrl);
		mailSender.send(email);
	}
}
