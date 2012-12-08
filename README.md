# Java Exam System

This Java program was written as part of a lab exercise. It might be useful as a reference for anyone interested in learning Java. 

## Purpose

This program allows a professor to add students to a course, grade their exams, and calculate averages. Students may have multiple exams, and some students may have an exam that others do not. The system requests input continuously until an exit command (input 0 in the main menu prompt) is entered.

Documentation is in the code itself, and the code is javadoc-ready.

## Quick Overview

There are more options that can be added based on the Student and Exam objects. This demonstration presents the most common basic applications of the objects. For a full documentation, please see javadoc comments in the raw code.

### moUserInput Class

This is a custom class meant to validate and streamline user input. The most relevant methods that are used are type validation methods and the continuous input validation loop.

#### Type Validation

These methods validate a string input and determine whether or not it can be resolved into a specific requested type. It is used to make sure user-inputted values are consistent with expected types. For instance, if a user inputs student ID number as a string, the method will return false.

#### Input Loop

This method ensures the user is requested to enter proper value in case the value is erroneous without creating an exception that breaks the operation. If a type validation returns false, the loop will present an error message and request the user to again insert a proper input. This will repeat until the input is deemed valid. Once it is deemed valid, the loop method will return the value in the proper type.

For instance, requesting a student ID will prompt the user to enter an integer-type input. If the user inserts any other type (decimal or string) the method will deny the input and request another entry. Once the input is entered correctly, the string is converted to an integer for the remaining operation.

Documentation for the specific methods is described in the raw code.

### Student Class
This defines a student object, with student name and ID number. It also holds student exams, a collection (ListArray) of the Exam objects that are associated with the student.

Documentation for the specific methods is described in the raw code.

### Exam Class
This class defines the exam objects. Each exam has a unique name and system ID, as well as a grade. These objects are related to a specific student.

Documentation for the specific methods is described in the raw code.


## Credit

Moriel Schottlender, 2012

The program is shared with permission. 
Remember: copying this code as your own is plagiarism! You are free to use and learn from the code, change and adapt it for your needs, as long as you maintain the proper credits for the original creator.
