package ikifp.regis.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ikifp.regis.model.Visitor;
import ikifp.regis.model.validation.ValidateData;
import ikifp.regis.model.validation.ValidationResult;
import ikifp.regis.persistence.VisitorService;

@RestController
@RequestMapping("/visitors")
public class VisitorRestController {

	@Autowired
	VisitorService visitorService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		Collection<Visitor> visitors = visitorService.getAll();
		return new ResponseEntity<Collection<Visitor>>(visitors, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> registerVisitor(@RequestBody Visitor visitor) {

		ValidateData validator = new ValidateData();
		ValidationResult validationResult = validator.checkData(visitor);
		if (validationResult.isDataValid() != true) {
			return new ResponseEntity<>("incorrect data: " + validationResult.printErrors(), HttpStatus.BAD_REQUEST);
		} else {
			if (visitorService.findVisitorByEmail(visitor.getEmail()) == null) {
				visitorService.addVisitor(visitor);

				return new ResponseEntity<Visitor>(visitor, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Visitor with this email address has already registered in system",
						HttpStatus.CONFLICT);
			}
		}

	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {

		return "test2";

	}

}
