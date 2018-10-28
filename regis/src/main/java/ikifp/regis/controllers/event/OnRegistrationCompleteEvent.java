package ikifp.regis.controllers.event;

import org.springframework.context.ApplicationEvent;

import ikifp.regis.model.Visitor;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
	
	private Visitor visitor;

	public OnRegistrationCompleteEvent(Visitor visitor) {
		super(visitor);
		this.visitor=visitor;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

}
