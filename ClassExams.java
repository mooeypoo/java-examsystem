package StudentDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A system to designed to allow a professor to add 
 * students with exams and grades, and calculate 
 * averages.
 * 
 * @author Moriel Schottlender
 * S
 */
public class ClassExams {
	
	public static moUserInput mValidator = new moUserInput();
	public static Scanner keyboard = new Scanner(System.in);
	public static List<Student> studentList = new ArrayList<Student>();

	public static List<String> examList = new ArrayList<String>();
	public static List<String> examSums = new ArrayList<String>();
	public static List<String> examCounts = new ArrayList<String>();
	
	public static void main(String[] args) {		
		
		showInstructions("Intro");
		
		/** Add initial students **/
		addStudents();
		
		/** Start the command loop **/
		Boolean stop = false;
		do {
			showInstructions("General");
			int userInp = mValidator.askInputInt("Choose Action #: ");
			showInstructions(userInp);
			switch (userInp) {
				case 0: /* Quit */
					stop = true;
					break;
				case 1: /* List students */
					listAllStudents(true);
					break;
				case 2: /* Add students */
					addStudents();
					break;
				case 3: /* Add an Exam */
					String examName = keyboard.nextLine();
					System.out.println("Adding exam to students. Please fill in grades:");
					addExamAllStudents(examName);
					System.out.println("Exam '" + examName + "' added to all students.");
					break;
				case 4: /* Edit Grades */
					//list students
					int studentCounter = listArrayStudents();
					//choose a student
					int chosen = 0;
					do {
						chosen = mValidator.askInputInt("Please choose a student, by inputting the index number: 0-" + (studentCounter-1));
						if (chosen < 0 && chosen > studentCounter)
							System.err.println("ERROR: Please choose a student index between 0 to " + (studentCounter-1));
					} while (chosen < 0 && chosen > studentCounter);
					//list student exam
					System.out.println(studentList.get(chosen).getName() + " (ID: " + studentList.get(chosen).getStudentID() + ")");
					System.out.println("Choose an exam to grade:");
					int exCounter = 0;
					for (Exam ex : studentList.get(chosen).getExams()) {
						System.out.println("[" + exCounter + "] '" + ex.getName() + "' (Grade: " + ex.getGrade() + ")");
						exCounter++;
					}
					//choose student exam
					int chosenExam = 0;
					do {
						chosenExam = mValidator.askInputInt("Please choose an exam, by inputting the index number: 0-" + (exCounter-1));
						if (chosenExam < 0 && chosenExam > exCounter)
							System.err.println("ERROR: Please choose an exam index between 0 to " + (exCounter-1));
					} while (chosenExam < 0 && chosenExam > exCounter);
					//change grade
					double newGrade = mValidator.askInputDouble("Set a new grade: ");
					studentList.get(chosen).getExams().get(chosenExam).setGrade(newGrade);
					System.out.println("[Student " + studentList.get(chosen).getName() + "] Changed grade for exam '" + studentList.get(chosen).getExams().get(chosenExam).getName() + "' to " + studentList.get(chosen).getExams().get(chosenExam).getGrade());
					break;
				case 5: /* Class Average */
					getExamAverages();
					//calculate average for all exams
					if (examList.isEmpty()) {
						System.out.println("No exams registered. Please add exams first.");
					} else {
						System.out.println("Exam averages:");
						int examCounter = 0;
						double avgGrade = 0;
						for (int i=0; i<examList.size(); i++) {
							System.out.println("Exam Name: " + examList.get(i));
							double avgGradeExam = Double.parseDouble(examSums.get(i)) / Double.parseDouble(examCounts.get(i));
							avgGrade += avgGradeExam;
							examCounter++;
							if (!Double.isNaN(avgGradeExam)) {
								System.out.println("  Average: " + avgGradeExam);
							}
							System.out.println();
						}	
						if (!Double.isNaN(avgGrade) && !Double.isNaN(examCounter)) {
							double totalAvg = avgGrade / examCounter;
							System.out.println("Total Average: " + totalAvg);
						}
					}
					System.out.println();
					break;
				default:
					System.out.println("Please choose an action number: 0-5");
					break;
			}
		} while (stop == false);
				
		
		System.out.println();
		System.out.println("Thank you!");
		
	} /* End main function */
	
	/**
	 * Displays introduction and instructions to the user.
	 * @param introType Defines the introduction to present.
	 */
	public static void showInstructions(String introType) {
		System.out.println();			
		if (introType.equalsIgnoreCase("Intro")) {
			System.out.println("Welcome to Student-Exam system.");
			System.out.println();			
		} else if (introType.equalsIgnoreCase("General")) {
			System.out.println("Please choose an action from the options below:");
			System.out.println(" [1] Display List of Students.");
			System.out.println(" [2] Add Students.");
			System.out.println(" [3] Add an Exam.");
			System.out.println(" [4] Edit Grades.");
			System.out.println(" [5] See Exam Averages.");
			System.out.println(" [0] Quit.");
		} else if (introType.equalsIgnoreCase("AddStudents")) {
			System.out.println("Please add students to course:");
			System.out.println("(to stop, enter an empty name, or input 'stop')");
		}

	}

	/**
	 * Displays introduction and instructions to the user.
	 * @param introType Defines the introduction to present.
	 */
	public static void showInstructions(int introType) {
		switch (introType) {
			case 1:
				System.out.println("LIST STUDENTS");
				System.out.println("=============");
				System.out.println("Listing " + studentList.size() + " students in course:");
				break;
			case 2:
				System.out.println("ADD STUDENTS");
				System.out.println("============");
				break;
			case 3:
				System.out.println("ADD EXAM");
				System.out.println("========");
				System.out.print("What is the name of the exam: ");
				break;
			case 4:
				System.out.println("Edit Grades");
				System.out.println("===========");
				System.out.println("Choose a student to edit the grade for:");
				break;
			case 5:
				System.out.println("CLASS AVERAGE");
				System.out.println("=============");
				break;
		}
	}

	/**
	 * Add students to the system.
	 * The program will request student name
	 * and student ID number. To stop, the user
	 * will be prompted to add an empty value. 
	 */
	public static void addStudents() {
		showInstructions("AddStudents");
		Boolean stop = false;
		int counter = 1;
		String stName = "";
		int stID = 0;
		do {
			stName = ""; // reset
			stID = 0;
			System.out.print("Student #" + counter + " name: ");
			stName = keyboard.nextLine();
			if (stName.length() == 0 || stName.equalsIgnoreCase("stop")) {
				stop = true;
				break;
			} else {
				System.out.println();
				stID = mValidator.askInputInt(stName + " Student ID number: ");
				studentList.add(new Student(stName, stID));
				System.out.println(stName + ": Student added successfully.");
				counter++;
			}
		} while (stop == false);
		System.out.println((counter-1) + " students entered into the system.");
		System.out.println();

	}
	
	/**
	 * Displays all students with their student ID.
	 * 
	 * @param showExams Determines whether or not to display
	 * 					exams. If so, it will display exam 
	 * 					grade and student average.
	 */
	public static void listAllStudents(Boolean showExams) {
		for (Student s : studentList) {
			System.out.println(s.getName() + " (ID: " + s.getStudentID() + ")");
			if (showExams == true) {
				List<Exam> examList = s.getExams();
				float avgSum = 0;
				int examCounter = 0;
				if (examList.isEmpty()) {
					System.out.println("    No exams registered.");
				} else {
					for (Exam ex : examList) {
						avgSum += ex.getGrade();
						System.out.println("    " + ex.getName() + ": " + ex.getGrade());
						examCounter++;
					}
					System.out.println("        " + s.getName() + " Average: " + (avgSum/examCounter));
				}
				
			}
		}
	}
	
	/**
	 * Lists all students with their arrayList index
	 * for specific retrieval of the unique objects.
	 * 
	 * @return	The total number of students.
	 */
	public static int listArrayStudents() {
		int counter = 0;
		for (Student s : studentList) {
			System.out.println("[" + counter + "] " + s.getName() + " (ID: " + s.getStudentID() + ")");
			counter++;
		}
		return counter;
	}

	/**
	 * Prompts the user to add an exam to all available
	 * students. Students will be graded individually.
	 * 
	 * @param examName A name for the exam.
	 */
	public static void addExamAllStudents(String examName) {
		for (Student s : studentList) {
			// First check the student doesn't already have this exam:
			if (s.examExists(examName) == true) {
				// Exam already exists. Skip this student.
				System.out.println("Skipping " + s.getName() + " (ID: " + s.getStudentID() + "). Exam '" + examName + "' already exists.");
			} else {
				double sGrade = mValidator.askInputDouble(s.getName() + " (ID: " + s.getStudentID() + ") Grade: ");
				s.addExam(examName,sGrade);
			}
		}
	}
	
	/**
	 * Calculates exam averages per student.
	 * The method iterates between the student list
	 * to retrieve individual exams, then calculates
	 * the class's averages.
	 * 
	 * The information is saved in the static 
	 * properties examList, examSums and examCounts
	 * which are then used to display the averages.
	 * 
	 */
	public static void getExamAverages() {
		examList.clear();
		examSums.clear();
		examCounts.clear();
		for (Student st : studentList) {
			if (!st.getExams().isEmpty()) {
				for (Exam studentExam : st.getExams()) {
					//check if exists already in tmpExams:
					double currGrade = studentExam.getGrade();
					if (examList.contains(studentExam.getName())) {
						int ind = examList.indexOf(studentExam.getName());
						//exists. find and add:
						double tSums = Double.parseDouble(examSums.get(ind));
						int tCounts = Integer.parseInt(examCounts.get(ind));
						tCounts++;
						tSums += currGrade;
						
						examSums.set(ind, tSums + "");
						examCounts.set(ind, tCounts + "");
					} else {
						//new, add it
						examList.add(studentExam.getName());
						examSums.add(currGrade + "");
						examCounts.add("1");
					}
					
				}
			}
		}
	}
}
