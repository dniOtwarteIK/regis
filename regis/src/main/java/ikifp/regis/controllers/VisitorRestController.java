package ikifp.regis.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import ikifp.regis.controllers.event.OnRegistrationCompleteEvent;
import ikifp.regis.model.Visitor;
import ikifp.regis.model.validation.ValidateData;
import ikifp.regis.model.validation.ValidationResult;
import ikifp.regis.persistence.VisitorService;

@RestController
@RequestMapping("/visitors")
public class VisitorRestController {

	@Autowired
	VisitorService visitorService;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		Collection<Visitor> visitors = visitorService.getAll();
		return new ResponseEntity<Collection<Visitor>>(visitors, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> registerVisitor(@RequestBody Visitor visitor, BindingResult result, 
			  WebRequest request) {
		
		ValidateData validator = new ValidateData();
		ValidationResult validationResult = validator.checkData(visitor);
		if (validationResult.isDataValid() != true) {
			return new ResponseEntity<>("incorrect data: " + validationResult.printErrors(), HttpStatus.BAD_REQUEST);
		} else {

			if (visitorService.addVisitor(visitor) == true) {
				try {
					String appUrl = request.getContextPath();
					eventPublisher.publishEvent(new OnRegistrationCompleteEvent(visitor));
				} catch (Exception e) {
					return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
				//return new ResponseEntity<>("ok "+appUrl, HttpStatus.CREATED);
				return new ResponseEntity<Visitor>(visitor, HttpStatus.CREATED);
				
			} else {
				return new ResponseEntity<>("Visitor with this email address has already registered in system",
						HttpStatus.CONFLICT);
			}
		}
	}

	//obsluga potwierdzenia maila przez linka
	//dlaczego eni przyjmuje '?' w adresie??????
	@RequestMapping(value="/regitrationConfirm/{token}", method=RequestMethod.GET)
	public ResponseEntity<?> confirmRegistration(@PathVariable("token") String token){
		Visitor visitor = visitorService.findVisitorByToken(token);
		boolean isRegistrationConfirmed = visitorService.confirmRegistration(visitor);
		return new ResponseEntity<>(isRegistrationConfirmed, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {

		return "test2";

	}

}
