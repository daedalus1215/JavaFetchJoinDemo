package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;


public class GetCoursesForMary2 {

	public static void main(String[] args) {
		final SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		final Session session = factory.openSession();
		
		
			
		try {
			session.beginTransaction();
			
			// get the student mary			
			int maryStudentId = 8;

			
			Student maryStudent = session.get(Student.class, maryStudentId);
			System.out.println("\nMary Student " + maryStudent.toString());
			
			
			System.out.println("\nMary's courses: " + maryStudent.getCourses());
			
			// commit
			session.getTransaction().commit();
			
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {			
			session.close();
			
			factory.close();
		}
	}

}