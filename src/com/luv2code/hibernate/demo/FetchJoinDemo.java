package com.luv2code.hibernate.demo;



import org.hibernate.query.Query;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class FetchJoinDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		
			
		try {
			
			// option 2: Hibernate query with HQL
			
			// get the instructor from the db
			int theId = 8;
			
			
			ArrayList<Course> courses = retrieveInstructorCourses(session, theId);
			
			
			// recreate the session.
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			
			// Grab the instructor
			Query<Instructor> instructorQuery = 
					session.createQuery("select i from Instructor i "
							+ "where i.id=:theInstructorId",
							Instructor.class);
			instructorQuery.setParameter("theInstructorId", theId);
			
			Instructor theInstructor = instructorQuery.getSingleResult();
			
			// Set the instructor's courses
			theInstructor.setCourses(courses);
						
			// Pull the instructor's courses from the instructor
			System.out.println("The instructor's courses: " + theInstructor.getCourses());
			
			
			session.getTransaction().commit();

		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {			
			session.close();		
			factory.close();
		}
	}
	
	
	private static ArrayList<Course> retrieveInstructorCourses(Session session, int theID) {
		session.beginTransaction();
		
		Query<Course> query = 
				session.createQuery("select c from Course c "
						+ "where c.instructor.id=:theInstructorId",
						Course.class);
		
		query.setParameter("theInstructorId", theID);
		
		ArrayList<Course> courses = (ArrayList<Course>) query.getResultList();
		
		// commit
		session.getTransaction().commit();
		session.close();
		
		return courses;
	}
}