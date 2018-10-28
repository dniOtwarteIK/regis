package ikifp.regis.controllers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import ikifp.regis.persistence.VisitorService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent>{

    @Autowired
    private VisitorService visitorService;
  
    @Autowired
    private MessageSource messages;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent arg0) {
		// TODO Auto-generated method stub
		
	}
  
    //@Autowired
    //private JavaMailSender mailSender;
}
