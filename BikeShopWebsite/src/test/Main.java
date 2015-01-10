package test;

import hibernate.utils.HibernateUtilities;

import org.hibernate.Session;

public class Main {
	public static void main(String[] args) {
        Session session = HibernateUtilities.getSessionFactory().openSession();
//        session.beginTransaction();
//        Student student = new Student();
//
//        student.setStudentName("JavaFun");
//        student.setStudentAge("19");
//
//        session.save(student);
//        session.getTransaction().commit();
//        System.out.println("Great! Student was saved");
//        
//        Query namedQuery = session.getNamedQuery(Student.QUERY_BY_STUDENT_ID);
//        namedQuery.setInteger("studentId", 1);
//        Student uniqueResult = (Student) namedQuery.uniqueResult();
//        System.out.println(uniqueResult);
        HibernateUtilities.shutdown();
	}
}
