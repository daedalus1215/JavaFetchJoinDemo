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
			
			// get the student mary			
			int maryStudentId = 8;

			
			Student maryStudent = session.get(Student.class, maryStudentId);
			System.out.println("\nMary Student " + maryStudent.toString());
			
			
			// create more courses
			Course javaCourse = new Course("Java Course");
			Course phpCourse = new Course("Php Course");
			Course javascriptCourse = new Course("JavaScript Course");
			Course xmlCourse = new Course("XML Course");
			
			// add mary to the courses
			javaCourse.addStudent(maryStudent);
			phpCourse.addStudent(maryStudent);
			javascriptCourse.addStudent(maryStudent);
			xmlCourse.addStudent(maryStudent);
			
			// save the courses
			session.save(javaCourse);
			session.save(phpCourse);
			session.save(javascriptCourse);
			session.save(xmlCourse);
			
			
			// print out the course stuff
			System.out.println("\nJavaCourse students " + javaCourse.getStudents());
			System.out.println("\nPhpCourse students " + phpCourse.getStudents());
			System.out.println("\nJavaScriptCourse students " + javascriptCourse.getStudents());
			System.out.println("\nXmlCourse students " + xmlCourse.getStudents());
			
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