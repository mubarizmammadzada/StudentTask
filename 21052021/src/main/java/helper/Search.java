package helper;

import model.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Search {
    public void searchByName(Map<String, Student> studentMap, String name) {
        List<Student> students = studentMap.values().stream()
                .filter(s -> s.getName().trim().toLowerCase().startsWith(name)).collect(Collectors.toList());
        for (Student s : students) {
            System.out.println(s.getId() + "," + s.getName() + ","
                    + s.getSurName() + "," + s.getFatherName() + ","
                    + s.getEmail() + "," + s.getPhoneNumber());
        }
    }

    public  void searchBySurname(Map<String, Student> studentMap, String surname) {
        List<Student> students = studentMap.values().stream()
                .filter(s -> s.getSurName().trim().toLowerCase().startsWith(surname)).collect(Collectors.toList());
        for (Student s : students) {
            System.out.println(s.getId() + "," + s.getName() + ","
                    + s.getSurName() + "," + s.getFatherName() + ","
                    + s.getEmail() + "," + s.getPhoneNumber());
        }
    }

    public  void searchByFatherName(Map<String, Student> studentMap, String fatherName) {
        List<Student> students = studentMap.values().stream()
                .filter(s -> s.getFatherName().trim().toLowerCase().startsWith(fatherName)).collect(Collectors.toList());
        for (Student s : students) {
            System.out.println(s.getId() + "," + s.getName() + ","
                    + s.getSurName() + "," + s.getFatherName() + ","
                    + s.getEmail() + "," + s.getPhoneNumber());
        }
    }
}
