package hospital;

public class Patient {

    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String disease;
    private String address;

    // Constructor without patient ID
    public Patient(String name, int age, String gender,
                   String phone, String disease, String address) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.disease = disease;
        this.address = address;
    }

    // Constructor with patient ID
    public Patient(int patientId, String name, int age, String gender,
                   String phone, String disease, String address) {

        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.disease = disease;
        this.address = address;
    }

    // Getters
    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getDisease() {
        return disease;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}