package ikifp.regis.model.validation;

import java.util.ArrayList;

import ikifp.regis.model.Visitor;

public class ValidateData {
	//klasa sprawdzajaca logiczna poprawnosc danych - email haslo ilosc osob

	public ValidationResult checkData(Visitor visitor) {
		ValidationResult validationResult = new ValidationResult();
		validationResult.addErrors(checkEmailCorrectness(visitor.getEmail()));
		validationResult.addErrors(checkPasswordCorrectness(visitor.getPassword()));
		validationResult.addErrors(checkParticipantsNumber(visitor.getParticipantsNumber()));
		return validationResult;
	}

	private static ArrayList<String> checkEmailCorrectness(String email) {
		ArrayList<String> errors = new ArrayList<>();
		int maxLength = 100;
		if (email.length() > maxLength) {
			errors.add("too long e-mail address");
		}
		// !email.contains("@") || email.indexOf("@")==0 ||
		// email.indexOf("@")==(email.length()-1)
		if (!email.matches("\\S+@\\S+\\.\\S+") || email.length() < 3) {
			errors.add("incorrect e-mail address");
		}
		return errors;
	}

	private static ArrayList<String> checkPasswordCorrectness(String password) {
		ArrayList<String> errors = new ArrayList<>();
		int minPasswrdLength = 6;
		if (password.length() < minPasswrdLength) {
			errors.add("too short password");
		}
		return errors;
	}

	private static ArrayList<String> checkParticipantsNumber(int participantsNumber) {
		ArrayList<String> errors = new ArrayList<>();
		int maxParticipantsNumber = 60;
		if (participantsNumber > maxParticipantsNumber) {
			errors.add("too many participants - max is 60");
		}
		return errors;
	}
}
