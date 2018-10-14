package ikifp.regis.persistence;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ikifp.regis.model.Visitor;

@Component("visitorService")
public class VisitorService {

	DatabaseConnector connector;

	public VisitorService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Visitor> getAll() {
		return connector.getSession().createCriteria(Visitor.class).list();
	}

	public Visitor addVisitor(Visitor visitor) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(visitor);
		transaction.commit();
		return visitor;
	}

	public Visitor findVisitorByEmail(String email) {
		Transaction transaction = connector.getSession().beginTransaction();
		String hql = "FROM Visitor V WHERE V.email=" + email;
		Query query = connector.getSession().createQuery(hql);
		Visitor visitor = (Visitor)query.list().get(0);
		return visitor;
	}

}
