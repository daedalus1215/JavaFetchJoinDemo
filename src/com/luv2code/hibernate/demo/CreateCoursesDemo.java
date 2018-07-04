package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;


public class CreateCoursesDemo {

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

			// get the instructor from the db
//			int theId = 8;
//			Instructor theInstructor = session.get(Instructor.class, theId);
			
			// create some courses
			Course tempCourse1 = new Course("The Pinball Masterclass8");							

			// add some reviews
			tempCourse1.add(new Review("Great course ... loved it!"));
			tempCourse1.add(new Review("Cool course, job well done"));
			tempCourse1.add(new Review("What a dumb course, you are a idiot!"));
			
			
			
			
			
			// add courses to instructor
//			theInstructor.addCourse(tempCourse1);
			
			
			// save the courses
			System.out.println("\nSaving the course ...");
			session.save(tempCourse1);
			System.out.println("\nSaved the course ..." + tempCourse1);
			
			
			// create the students
			Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
			Student tempStudent2 = new Student("Mary", "Doe2", "john2@luv2code.com");
			
			// add students to the course
			tempCourse1.addStudent(tempStudent1);
			tempCourse1.addStudent(tempStudent2);
			
			// save the students
			System.out.println("\nSaving students ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("\nSaved the students ... " + tempCourse1.getStudents());
			
			
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