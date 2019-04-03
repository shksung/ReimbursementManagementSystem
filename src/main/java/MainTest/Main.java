package MainTest;

import com.dao.ReimbDaoImpl;
import com.dao.UserDaoImpl;
import com.model.Reimbursement;
import com.model.User;

public class Main {

	public static void main(String[] args) {
		User userTest = new User("dsafds","dfdasf","dasfdasf","dasfdasf", "df");
		Reimbursement reimbTest = new Reimbursement(50, "test");
		UserDaoImpl user = new UserDaoImpl();
		ReimbDaoImpl test = new ReimbDaoImpl();
		
		user.insertUser(userTest);
		test.insertTest(reimbTest);
		

	}

}
