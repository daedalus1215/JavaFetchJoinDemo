package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class CreateCourseAndStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		
			
		try {
			session.beginTransaction();

			// get the instructor from the db
			int theId = 8;
			Instructor theInstructor = session.get(Instructor.class, theId);
			
			// create some courses
			Course tempCourse  = new Course("Air Guitar - The Ultimate Guide");
			Course tempCourse1 = new Course("The Pinball Masterclass");							

			// add courses to instructor
			theInstructor.addCourse(tempCourse1);
			theInstructor.addCourse(tempCourse);
			
			// save the courses
			session.save(tempCourse1);
			session.save(tempCourse);
			
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