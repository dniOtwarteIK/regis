package ikifp.regis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ikifp.regis.persistence.VisitorService;

@RestController
@RequestMapping("/api/participants")
public class ParticipantRrestController {
	
    @Autowired
    VisitorService participantService;

}
