package helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import helper.Crud;
import helper.Search;
import model.Student;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleMenu {

    private Scanner scanner = new Scanner(System.in);
    Map<String, Student> studentHashMap = new HashMap<>();
    Search search = new Search();
    Crud crud = new Crud(scanner);


    public void consoleMenu() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File f = new File("student.json");
        studentHashMap = mapper.readValue(f, new TypeReference<Map<String, Student>>() {
        });

        System.out.println("Select operation");
        boolean isNumber = true;
        int selectionCount;
        do {
            System.out.println("1-Student");
            System.out.println("2-Search");
            Scanner selectOperationInput = new Scanner(System.in);
            String selectOperation = selectOperationInput.next();
            selectionCount = 0;
            try {
                selectionCount = Integer.parseInt(selectOperation);
                isNumber = true;
            } catch (Exception e) {
                System.out.println("Please enter correct selection number");
                isNumber = false;
            }
            if (selectionCount != 1 && selectionCount != 2) {
                isNumber = false;
            }
        } while (!isNumber);
        boolean isContinued = false;
        do {
            switch (selectionCount) {
                case 1:
                    System.out.println("1-Create Student");
                    System.out.println("2-Delete Student");
                    System.out.println("3-Update Student");
                    System.out.println("4-Read Student");

                    boolean isNumber1 = true;
                    int selectionCount1;
                    do {
                        Scanner selectOperationInput = new Scanner(System.in);
                        String selectOperation1 = selectOperationInput.next();
                        selectionCount1 = 0;
                        try {
                            selectionCount1 = Integer.parseInt(selectOperation1);
                            isNumber = true;
                        } catch (Exception e) {
                            System.out.println("Please enter correct selection number");
                            isNumber1 = false;
                        }
                        if (selectionCount1 < 0 && selectionCount1 > 4) {
                            isNumber1 = false;
                        }
                    } while (!isNumber1);
                    switch (selectionCount1) {
                        case 1:
                            addStudent(studentHashMap);
                            System.out.println("Student created");
                            isContinued = true;
                            break;
                        case 2:
                            boolean isExist = false;
                            Optional<Student> student;
                            String idForDeletingString;
                            for (Student s : studentHashMap.values()) {
                                System.out.println(s.getId() + " " + s.getName());
                            }
                            do {
                                for (Student stu : studentHashMap.values()) {
                                    System.out.println(stu.getId() + " " + stu.getName());
                                }
                                System.out.println("Enter Student Id");
                                Scanner scanner = new Scanner(System.in);
                                idForDeletingString = scanner.next();
                                String finalIdForDeletingString = idForDeletingString;
                                student = studentHashMap.values().stream()
                                        .filter(s -> s.getId().equals(finalIdForDeletingString)).findAny();
                                if (student.isEmpty()) {
                                    isExist = false;
                                } else {
                                    isExist = true;
                                }
                            } while (!isExist);

                            deleteStudent(studentHashMap, student.get(), idForDeletingString);
                            System.out.println("Student Deleted");
                            for (Student stu : studentHashMap.values()) {
                                System.out.println(stu.getId() + " " + stu.getName());
                            }
                            isContinued = true;
                            break;
                        case 3:
                            boolean isSelection = false;
                            String selectUpdatingProperty;
                            boolean repeatUpdating = false;
                            String repeatAnswer;
                            do {
                                int selectionNumber = 0;
                                Optional<Student> stu;
                                for (Student s : studentHashMap.values()) {
                                    System.out.println(s.getId() + " " + s.getName());
                                }
                                do {
                                    boolean isExistForUpdate = false;

                                    String idForUpdatingString;
                                    do {
                                        System.out.println("Enter Student id");
                                        Scanner scanner1 = new Scanner(System.in);
                                        idForUpdatingString = scanner1.next();
                                        String finalIdForUpdatingString = idForUpdatingString;
                                        stu = studentHashMap.values().stream()
                                                .filter(s -> s.getId().equals(finalIdForUpdatingString)).findAny();
                                        if (stu.isEmpty()) {
                                            isExistForUpdate = false;
                                        } else {
                                            isExistForUpdate = true;
                                        }
                                    } while (!isExistForUpdate);
                                    System.out.println("Select Property");
                                    System.out.println("1-Update name");
                                    System.out.println("2-Update surname");
                                    System.out.println("3-Update fatherName");
                                    System.out.println("4-Update email");
                                    System.out.println("5-Update phoneNumber");
                                    Scanner scanner2 = new Scanner(System.in);
                                    selectUpdatingProperty = scanner2.next();
                                    try {
                                        selectionCount = Integer.parseInt(selectUpdatingProperty);
                                        isSelection = true;
                                    } catch (Exception e) {
                                        System.out.println("Enter Number");
                                        isSelection = false;
                                    }
                                    if (selectionCount >= 1 && selectionCount <= 5) {
                                        isSelection = true;
                                    } else {
                                        System.out.println("Enter 1-5 number");
                                        isSelection = false;
                                    }
                                    updateStudent(studentHashMap, stu.get(), selectionCount);
                                    do {
                                        System.out.println("do you want to continue? (y-yes,n-no)");
                                        Scanner scanner = new Scanner(System.in);
                                        repeatAnswer = scanner.next();
                                    } while (!repeatAnswer.equals("y") && !repeatAnswer.equals("n"));
                                    if (repeatAnswer.equals("y")) {
                                        repeatUpdating = true;
                                    }
                                    if (repeatAnswer.equals("n")) {
                                        repeatUpdating = false;
                                    }
                                } while (!isSelection);


                            } while (repeatUpdating);
                            isContinued = true;
                            break;
                        case 4:
                            crud.read(studentHashMap);
                            isContinued = true;
                            break;
                    }

                    break;


                case 2:
                    System.out.println("1-Search Student By name");
                    System.out.println("2-Search Student By surname");
                    System.out.println("3-Search Student By fathername");
                    boolean isNumberr = true;
                    int selectionCount2;
                    do {
                        Scanner selectOperationInput = new Scanner(System.in);
                        String selectOperation1 = selectOperationInput.next();
                        selectionCount2 = 0;
                        try {
                            selectionCount2 = Integer.parseInt(selectOperation1);
                            isNumber = true;
                        } catch (Exception e) {
                            System.out.println("Please enter correct selection number");
                            isNumber1 = false;
                        }
                        if (selectionCount2 < 1 && selectionCount2 > 3) {
                            isNumber1 = false;
                        }
                    } while (!isNumberr);
                    switch (selectionCount2) {

                        case 1:
                            System.out.println("Enter name");
                            Scanner scanner = new Scanner(System.in);
                            String name = scanner.next();
                            search.searchByName(studentHashMap, name);
                            break;
                        case 2:
                            System.out.println("Enter surname");
                            Scanner scanner1 = new Scanner(System.in);
                            String surname = scanner1.next();
                            search.searchBySurname(studentHashMap, surname);
                            break;
                        case 3:
                            System.out.println("Enter Father name");
                            Scanner scanner2 = new Scanner(System.in);
                            String fatherName = scanner2.next();
                            search.searchByFatherName(studentHashMap, fatherName);
                            break;

                    }


            }
        } while (isContinued);


    }

    public void addStudent(Map<String, Student> studentMap) throws IOException {
        crud.create(studentMap);
    }

    public void updateStudent(Map<String, Student> studentMap, Student student, int id) throws IOException {
        crud.update(studentMap, student, id);
    }

    public void deleteStudent(Map<String, Student> studentMap, Student student, String id) throws IOException {
        crud.delete(studentMap, student, id);
    }

}
