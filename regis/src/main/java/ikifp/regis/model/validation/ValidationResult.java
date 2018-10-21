package ikifp.regis.model.validation;

import java.util.ArrayList;

public class ValidationResult {
	private ArrayList<String> errors;

	public ValidationResult() {
		errors = new ArrayList<String>();
	}

	public boolean isDataValid() {
		boolean isValid = false;
		if (this.errors.size() == 0) {
			isValid = true;
		}
		return isValid;
	}

	public void addError(String error) {
		this.errors.add(error);
	}

	public void addErrors(ArrayList<String> errors) {
		this.errors.addAll(errors);
	}
	
	public String printErrors() {
		String errorsInString ="";
		for (String err : this.errors) {
			errorsInString=errorsInString+err+"\n";
		}
		return errorsInString;
		
	}
}
