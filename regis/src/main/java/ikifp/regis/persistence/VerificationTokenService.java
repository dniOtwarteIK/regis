package ikifp.regis.persistence;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import ikifp.regis.model.VerificationToken;
import ikifp.regis.model.Visitor;

@Component("verificationTokenService")
public class VerificationTokenService {
	
	DatabaseConnector connector;
	
	public VerificationTokenService() {
		connector=DatabaseConnector.getInstance();
	}

	public void createVerificationToken(Visitor visitor, String token) {
		String hql = "FROM Visitor V WHERE V.email= :usrEmailParam";
		Query query = connector.getSession().createQuery(hql);
		query.setParameter("usrEmailParam", visitor.getEmail());
		if (query.list().size() != 0) {
			Transaction transaction= connector.getSession().beginTransaction();
			VerificationToken visitorToken = new VerificationToken();
			visitorToken.setToken(token);
			visitorToken.setVisitor(visitor);
			connector.getSession().save(visitorToken);
			transaction.commit();
		} else {
			throw new NullPointerException();
		}	
	}
	
	public VerificationToken findToken(String token) {
		return (VerificationToken) connector.getSession().get(VerificationToken.class, token);
		
	}
}
