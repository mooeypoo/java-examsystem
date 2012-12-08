package StudentDB;
/**
 * Defines student exams with grading system.
 * @author Moriel Schottlender
 *
 */
public class Exam {
	private static int numExams = 0;
	private int examID = 0;
	private String examName = "";
	private double examGrade = 0;
	private Student examStudent;

	/**
	 * The Exam constructor creates individual ID
	 * and is attached to a student.
	 * 
	 * @param name	Exam name.
	 * @param st	Student object to be attached
	 * 				to.
	 * @param grade	Exam grade.
	 */
	public Exam(String name, Student st, double grade) {
		this.numExams++;
		this.examID = this.numExams;

		this.examStudent = st;
		this.examName = name;
		this.examGrade = grade;
	}
	
	/**
	 * Sets the grade for the exam.
	 * 
	 * @param grade	The new grade to set.
	 */
	public void setGrade(double grade) {
		this.examGrade = grade;
	}
	
	/**
	 * Sets the name of the exam.
	 * 
	 * @param name	The new exam name.
	 */
	public void setName(String name) {
		this.examName = name;
	}
	
	/**
	 * Returns the name of the exam.
	 * 
	 * @return	The exam name.
	 */
	public String getName() {
		return this.examName;
	}
	
	/**
	 * Returns the unique ID of the exam.
	 * 
	 * @return ID number for the exam.
	 */
	public int getID() {
		return this.examID;
	}
	
	/**
	 * Returns the grade for this exam.
	 * @return	Exam grade.
	 */
	public double getGrade() {
		return this.examGrade;
	}
	
	
}
