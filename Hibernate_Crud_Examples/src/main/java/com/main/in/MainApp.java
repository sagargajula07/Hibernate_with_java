package com.main.in;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.entity.in.Employee;

public class MainApp {   

	private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	
	public static void main(String agrs[]) {
		MainApp app = new MainApp();
		//app.insertEmployee();
		//app.readEmployee();
		//app.deleteEmployee();
		//app.listEmployee();
        //app.updateEmployee();
		//app.listEmployeeDetails();
		app.readlistEmployeeDetails();
	}
	
	public void insertEmployee() {
		try(Session session = sessionFactory.openSession()){
			
			Employee emp = new Employee();
			emp.setName("xyz");
			emp.setEmail("xyz@gmail.com");
			emp.setDepartment("Development");
			
			Transaction txn = session.beginTransaction();
			session.save(emp);
			txn.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readEmployee() {                    
		try(Session session = sessionFactory.openSession()){
			Employee emp = session.get(Employee.class, 1);
			System.out.println(emp);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteEmployee() {
		try(Session session = sessionFactory.openSession()) {
           Transaction txn = session.beginTransaction();
           
            Employee emp = session.get(Employee.class, 1);

            session.delete(emp);

            txn.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
          
        }
		
			
		
	}
	public void listEmployee() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		for ( int i=0; i<100; i++ ) {
		    Employee emp = new Employee();
			emp.setName("xyz3");
			emp.setEmail("xyz3@gmail.com");
			emp.setDepartment("Development3");
		    
		    session.save(emp);
		   if ( i % 20 == 0 ) { //20, same as the JDBC batch size
		        //flush a batch of inserts and release memory:
		       session.flush();
		       session.clear();
		    }
		}

		tx.commit();   
		}
	public void updateEmployee() {
		try(Session session = sessionFactory.openSession()){
			Employee emp = session.get(Employee.class, 3);
			emp.setId(emp.getId());
			emp.setName("raju");
			emp.setEmail("raju@gmail.com");
			emp.setDepartment("rajudepartment");
			Transaction txn = session.beginTransaction();
			session.save(emp);
			txn.commit();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listEmployeeDetails() {
		try(Session session=sessionFactory.openSession()){
			for(int i = 1;i<=10;i++) {
				Employee emp=new Employee();
				emp.setName("sagar" + i);
				emp.setEmail("sagar@gmail.com" + i);
				emp.setDepartment("development" + i);
				Transaction txn = session.beginTransaction();
				session.save(emp);
				txn.commit();	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void readlistEmployeeDetails() {
		try(Session session = sessionFactory.openSession()){
		String hql = "from Employee"; 
		Query query = session. createQuery(hql); 
		List<Employee> results = query. list();
		for(Employee a:results) {
			System.out.println(a);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

	

