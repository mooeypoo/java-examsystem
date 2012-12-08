package StudentDB;

import java.util.ArrayList;
import java.util.List;
/**
 * Defines a student in the system. Must have a unique ID.
 * @author Moriel Schottlender
 *
 */
public class Student {
	
	private static int numStudents = 0;
	private String studentName = "";
	private int studentID = 0;
	private int numExams = 0;
	private List<Exam> studentExams = new ArrayList<Exam>();
	
	/**
	 * The constructor adds a student with 
	 * name and student id.
	 * 
	 * @param name	Student name
	 * @param id	Student ID
	 */
	public Student(String name, int id) {
		this.studentName = name;
		this.studentID = id;
		this.numStudents++;
	}
	
	/**
	 * This method attaches an exam object
	 * to the student exam ArrayList. It is
	 * possible to add multiple exams, each
	 * with its own name and grade.
	 * 
	 * @param exObj	Exam object to be attached
	 */
	public void addExam(Exam exObj) {
		this.studentExams.add(exObj);
		this.numExams++;
	}
	
	/**
	 * This method attaches an exam object
	 * to the student exam ArrayList. It is
	 * possible to add multiple exams, each
	 * with its own name and grade.
	 * 
	 * @param exName	The name of the exam
	 * @param exGrade	The grade of the exam
	 */
	public void addExam(String exName, double exGrade) {
		//make sure there are no duplicates:
		Boolean error=false;
		for (Exam ex : this.studentExams) {
			if (ex.getName() == exName) {
				//duplicate exists. Break the operation:
				error = true;
				System.err.println("[Student " + this.studentName + "] ERROR: This student already has an exam with this name.");
				break;
			}
		}
		if (error == false) {
			this.studentExams.add(new Exam(exName, this, exGrade));
			this.numExams++;
			System.out.println("[Student " + this.studentName + "] Exam " + exName + " was added with grade " + exGrade);
		}
	}
	
	/**
	 * Verify that no exams with the same name exist
	 * for this student.
	 * 
	 * @param examName	The exam name to test.
	 * @return			True if exam exists; False
	 * 					otherwise.
	 */
	public Boolean examExists(String examName) {
		for (Exam exam : studentExams) {
			if (exam.getName().trim().equalsIgnoreCase(examName.trim())) {
				return true;
			}
		}
		return false;
	}
	
	public Exam getExam(String examName) {
		for (Exam ex : this.studentExams) {
			if (ex.getName() == examName) {
				return ex;
			}
		}
		return null;
	}
	/**
	 * This method allows the change of an exam
	 * grade for an exam that is attached to 
	 * the student. It will look for the exam
	 * by name.
	 * 
	 * @param exName	The name of the exam
	 * @param newGrade	The new grade for this
	 * 					exam.
	 */
	public void changeExam(String exName, double newGrade) {
		Boolean error = true;
		for (Exam ex : this.studentExams) {
			if (ex.getName() == exName) {
				ex.setGrade(newGrade);
				System.out.println("[Student " + this.studentName + "] Exam " + exName + " grade changed to " + newGrade);
				error = false;
				break;
			}
		}
		if (error == true) {
			System.err.println("[Student " + this.studentName + "] ERROR: Requested exam ('" + exName + "') was not found.");
		}
		
	}
	
	/**
	 * Get all the exam for this particular
	 * student.
	 * 
	 * @return	ListArray of Exam objects.
	 */
	public List<Exam> getExams() {
		return this.studentExams;
	}
	
	/**
	 * Get the student name.
	 * @return	Student name.
	 */
	public String getName() {
		return this.studentName;
	}
	
	/**
	 * Get the student ID
	 * @return	Student ID (numerical)
	 */
	public int getStudentID() {
		return this.studentID;
	}
	
	/**
	 * Set or change student name.
	 * @param name	Student name.
	 */
	public void setName(String name) {
		this.studentName = name;
	}
	

	/**
	 * Set the student ID.
	 * @param id	The student ID.
	 */
	public void setStudentID(int id) {
		this.studentID = id;
	}
	


}
