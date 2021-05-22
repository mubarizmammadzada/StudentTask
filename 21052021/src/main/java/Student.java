public class Student {
    private String id;
    private static int idCount;
    private String name;
    private String surName;
    private String phoneNumber;
    private String email;
    private String fatherName;

    public Student() {}
    public Student(String name, String surName,String fatherName,String email,String phoneNumber) {
        idCount++;
        this.id = "St" + idCount;
        this.name = name;
        this.surName = surName;
        this.fatherName=fatherName;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }



    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getId() {
        return id;
    }


}
