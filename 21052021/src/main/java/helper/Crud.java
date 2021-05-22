package helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Student;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Crud {
    private Scanner scanner;

    public Crud(Scanner scanner) {
        this.scanner = scanner;
    }

    public void create(Map<String, Student> studentHashMap) throws IOException {
        System.out.println("Enter Student Name");
        String studentNameCreate = scanner.next();
        System.out.println("Enter Student Surname");
        Scanner scanner1 = new Scanner(System.in);
        String studentSurnameCreate = scanner1.next();
        System.out.println("Enter Student  Father Name");
        Scanner scanner2 = new Scanner(System.in);
        String studentFatherNameCreate = scanner2.next();
        System.out.println("Enter Student Email");
        boolean isEmail = true;
        String studentEmailCreate;
        do {
            System.out.println("Enter Student email");
            Scanner scanner3 = new Scanner(System.in);
            studentEmailCreate = scanner.next();
            if (!studentEmailCreate.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                isEmail = false;
            } else {
                isEmail = true;
            }
        } while (!isEmail);

        boolean isPhoneNumber = true;
        String studentPhoneNumberCreate;
        do {
            System.out.println("Enter Student Phone Number");
            Scanner scanner3 = new Scanner(System.in);
            studentPhoneNumberCreate = scanner.next();
            if (!studentPhoneNumberCreate.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")) {
                isPhoneNumber = false;
            } else {
                isPhoneNumber = true;
            }
        } while (!isPhoneNumber);
        Student newStudent = new Student(studentNameCreate, studentSurnameCreate, studentFatherNameCreate,
                studentEmailCreate, studentPhoneNumberCreate);
        studentHashMap.put(newStudent.getId(), newStudent);
        ObjectMapper mapper1 = new ObjectMapper();
        mapper1.writeValue(new File("student.json"), studentHashMap);

    }

    public void read(Map<String, Student> studentHashMap) {
        for (Student s : studentHashMap.values()) {
            System.out.println(s.getId() + "," + s.getName() + ","
                    + s.getSurName() + "," + s.getFatherName() + ","
                    + s.getEmail() + "," + s.getPhoneNumber());
        }
    }

    public void delete(Map<String, Student> studentMap, Student student, String id) throws IOException {

        studentMap.remove(student.getId());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("student.json"), studentMap);


    }

    public void update(Map<String, Student> studentMap, Student stu, int selectionNumber) throws IOException {

        switch (selectionNumber) {
            case 1:
                System.out.println("Enter Student Name");
                Scanner scanner = new Scanner(System.in);
                String studentNameUpdate = scanner.next();
                stu.setName(studentNameUpdate);
                break;
            case 2:
                System.out.println("Enter Student Surname");
                Scanner scanner1 = new Scanner(System.in);
                String studentSurnameUpdate = scanner1.next();
                stu.setSurName(studentSurnameUpdate);
                break;
            case 3:
                System.out.println("Enter Student  Father Name");
                Scanner scanner2 = new Scanner(System.in);
                String studentFatherNameUpdate = scanner2.next();
                stu.setFatherName(studentFatherNameUpdate);
                break;
            case 4:
                System.out.println("Enter Student Email");
                boolean isEmail = true;
                String studentEmailUpdate;
                do {
                    System.out.println("Enter Student email");
                    Scanner scanner3 = new Scanner(System.in);
                    studentEmailUpdate = scanner3.next();
                    if (!studentEmailUpdate.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        isEmail = false;
                    } else {
                        isEmail = true;
                    }
                } while (!isEmail);
                stu.setEmail(studentEmailUpdate);
                break;
            case 5:
                boolean isPhoneNumber = true;
                String studentPhoneNumberUpdate;
                do {
                    System.out.println("Enter Student Phone Number");
                    Scanner scanner4 = new Scanner(System.in);
                    studentPhoneNumberUpdate = scanner4.next();
                    if (!studentPhoneNumberUpdate.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")) {
                        isPhoneNumber = false;
                    } else {
                        isPhoneNumber = true;
                    }
                } while (!isPhoneNumber);
                stu.setPhoneNumber(studentPhoneNumberUpdate);
                break;


        }
        studentMap.put(stu.getId(), stu);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("student.json"), studentMap);


    }


}
