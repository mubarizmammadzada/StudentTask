
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;

import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    ;

    public static void main(String[] args) throws IOException {
        Map<String, Student> studentHashMap = new HashMap<>();
//        ObjectMapper mapperr = new ObjectMapper();
////        mapperr.enable(SerializationFeature.INDENT_OUTPUT);
//        Student stu = new Student("Salam", "asd", "asd", "asdasd", "asdasd");
//        studentHashMap.put(stu.getId(),stu);
//        mapperr.writeValue(new File("student.json"), studentHashMap);
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
                            create(studentHashMap);
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

                            delete(studentHashMap, student.get(), idForDeletingString);
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
                                    update(studentHashMap, stu.get(), selectionCount);
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
                            for (Student s : studentHashMap.values()) {
                                System.out.println(s.getId() + "," + s.getName() + ","
                                        + s.getSurName() + "," + s.getFatherName() + ","
                                        + s.getEmail() + "," + s.getPhoneNumber());
                            }
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
                switch (selectionCount2){
                    case 1:
                        System.out.println("Enter name");
                        Scanner scanner=new Scanner(System.in);
                        String name=scanner.next();
                        searchByName(studentHashMap,name);
                        break;
                    case 2:
                        System.out.println("Enter surname");
                        Scanner scanner1=new Scanner(System.in);
                        String surname=scanner1.next();
                        searchBySurname(studentHashMap,surname);
                        break;
                    case 3:
                        System.out.println("Enter Father name");
                        Scanner scanner2=new Scanner(System.in);
                        String fatherName=scanner2.next();
                        searchByFatherName(studentHashMap,fatherName);
                        break;

                }


            }
        } while (isContinued);


    }

    public static void create(Map<String, Student> studentHashMap) throws IOException {
        System.out.println("Enter Student Name");
        Scanner scanner = new Scanner(System.in);
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

    public static void delete(Map<String, Student> studentMap, Student student, String id) throws IOException {

        studentMap.remove(student.getId());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("student.json"), studentMap);


    }

    public static void update(Map<String, Student> studentMap, Student stu, int selectionNumber) throws IOException {

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

    public static void searchByName(Map<String,Student> studentMap,String name){
        List<Student> students=studentMap.values().stream()
                .filter(s->s.getName().trim().toLowerCase().startsWith(name)).collect(Collectors.toList());
        for (Student s:students){
            System.out.println(s.getId() + "," + s.getName() + ","
                    + s.getSurName() + "," + s.getFatherName() + ","
                    + s.getEmail() + "," + s.getPhoneNumber());
        }
    }
    public static void searchBySurname(Map<String,Student> studentMap,String surname){
        List<Student> students=studentMap.values().stream()
                .filter(s->s.getSurName().trim().toLowerCase().startsWith(surname)).collect(Collectors.toList());
        for (Student s:students){
            System.out.println(s.getId() + "," + s.getName() + ","
                    + s.getSurName() + "," + s.getFatherName() + ","
                    + s.getEmail() + "," + s.getPhoneNumber());
        }
    }
    public static void searchByFatherName(Map<String,Student> studentMap,String fatherName){
        List<Student> students=studentMap.values().stream()
                .filter(s->s.getFatherName().trim().toLowerCase().startsWith(fatherName)).collect(Collectors.toList());
        for (Student s:students){
            System.out.println(s.getId() + "," + s.getName() + ","
                    + s.getSurName() + "," + s.getFatherName() + ","
                    + s.getEmail() + "," + s.getPhoneNumber());
        }
    }
}
