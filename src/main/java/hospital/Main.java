package hospital;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n======================================");
            System.out.println("   HOSPITAL PATIENT RECORD SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Add New Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Search Patient");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Medical History");
            System.out.println("7. Exit");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:
                    addPatient();
                    break;

                case 2:
                    viewAllPatients();
                    break;

                case 3:
                    searchPatient();
                    break;

                case 4:
                    updatePatient();
                    break;

                case 5:
                    deletePatient();
                    break;

                case 6:
                    medicalHistoryMenu();
                    break;

                case 7:
                    System.out.println("Thank you for using Hospital Patient Record System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ================= ADD PATIENT =================

    private static void addPatient() {

        System.out.println("\n========== ADD NEW PATIENT ==========");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter disease: ");
        String disease = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        Patient patient = new Patient(
                name,
                age,
                gender,
                phone,
                disease,
                address
        );

        PatientDAO patientDAO = new PatientDAO();

        if (patientDAO.addPatient(patient)) {
            System.out.println("Patient added successfully!");
        } else {
            System.out.println("Failed to add patient.");
        }
    }

    // ================= VIEW PATIENTS =================

    private static void viewAllPatients() {

        System.out.println("\n========== ALL PATIENTS ==========");

        PatientDAO patientDAO = new PatientDAO();

        List<Patient> patients = patientDAO.getAllPatients();

        if (patients.isEmpty()) {

            System.out.println("No patients found.");

        } else {

            for (Patient patient : patients) {

                System.out.println("--------------------------------------");
                System.out.println("Patient ID : " + patient.getPatientId());
                System.out.println("Name       : " + patient.getName());
                System.out.println("Age        : " + patient.getAge());
                System.out.println("Gender     : " + patient.getGender());
                System.out.println("Phone      : " + patient.getPhone());
                System.out.println("Disease    : " + patient.getDisease());
                System.out.println("Address    : " + patient.getAddress());
            }

            System.out.println("--------------------------------------");
        }
    }

    // ================= SEARCH PATIENT =================

    private static void searchPatient() {

        System.out.println("\n========== SEARCH PATIENT ==========");

        System.out.print("Enter patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());

        PatientDAO patientDAO = new PatientDAO();

        Patient patient = patientDAO.getPatientById(patientId);

        if (patient == null) {

            System.out.println("Patient not found.");

        } else {

            System.out.println("\nPatient Found!");
            System.out.println("--------------------------------------");
            System.out.println("Patient ID : " + patient.getPatientId());
            System.out.println("Name       : " + patient.getName());
            System.out.println("Age        : " + patient.getAge());
            System.out.println("Gender     : " + patient.getGender());
            System.out.println("Phone      : " + patient.getPhone());
            System.out.println("Disease    : " + patient.getDisease());
            System.out.println("Address    : " + patient.getAddress());
            System.out.println("--------------------------------------");
        }
    }

    // ================= UPDATE PATIENT =================

    private static void updatePatient() {

        System.out.println("\n========== UPDATE PATIENT ==========");

        System.out.print("Enter patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());

        PatientDAO patientDAO = new PatientDAO();

        Patient existingPatient = patientDAO.getPatientById(patientId);

        if (existingPatient == null) {

            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter new phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter new disease: ");
        String disease = scanner.nextLine();

        System.out.print("Enter new address: ");
        String address = scanner.nextLine();

        Patient patient = new Patient(
                patientId,
                name,
                age,
                gender,
                phone,
                disease,
                address
        );

        if (patientDAO.updatePatient(patient)) {
            System.out.println("Patient updated successfully!");
        } else {
            System.out.println("Failed to update patient.");
        }
    }

    // ================= DELETE PATIENT =================

    private static void deletePatient() {

        System.out.println("\n========== DELETE PATIENT ==========");

        System.out.print("Enter patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());

        PatientDAO patientDAO = new PatientDAO();

        Patient patient = patientDAO.getPatientById(patientId);

        if (patient == null) {

            System.out.println("Patient not found.");
            return;
        }

        System.out.println("Patient: " + patient.getName());

        System.out.print("Are you sure you want to delete? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {

            if (patientDAO.deletePatient(patientId)) {
                System.out.println("Patient deleted successfully!");
            } else {
                System.out.println("Failed to delete patient.");
            }

        } else {

            System.out.println("Delete operation cancelled.");
        }
    }

    // ================= MEDICAL HISTORY MENU =================

    private static void medicalHistoryMenu() {

        while (true) {

            System.out.println("\n======================================");
            System.out.println("         MEDICAL HISTORY");
            System.out.println("======================================");
            System.out.println("1. Add Medical History");
            System.out.println("2. View Patient History");
            System.out.println("3. Update Medical History");
            System.out.println("4. Delete Medical History");
            System.out.println("5. Back");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:
                    addMedicalHistory();
                    break;

                case 2:
                    viewMedicalHistory();
                    break;

                case 3:
                    updateMedicalHistory();
                    break;

                case 4:
                    deleteMedicalHistory();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ================= ADD MEDICAL HISTORY =================

    private static void addMedicalHistory() {

        System.out.println("\n========== ADD MEDICAL HISTORY ==========");

        System.out.print("Enter patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());

        PatientDAO patientDAO = new PatientDAO();

        if (patientDAO.getPatientById(patientId) == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter disease: ");
        String disease = scanner.nextLine();

        System.out.print("Enter diagnosis: ");
        String diagnosis = scanner.nextLine();

        System.out.print("Enter treatment: ");
        String treatment = scanner.nextLine();

        System.out.print("Enter doctor name: ");
        String doctor = scanner.nextLine();

        System.out.print("Enter visit date (YYYY-MM-DD): ");
        Date visitDate = Date.valueOf(scanner.nextLine());

        MedicalHistory history = new MedicalHistory(
                patientId,
                disease,
                diagnosis,
                treatment,
                doctor,
                visitDate
        );

        MedicalHistoryDAO historyDAO = new MedicalHistoryDAO();

        if (historyDAO.addMedicalHistory(history)) {
            System.out.println("Medical history added successfully!");
        } else {
            System.out.println("Failed to add medical history.");
        }
    }

    // ================= VIEW MEDICAL HISTORY =================

    private static void viewMedicalHistory() {

        System.out.println("\n========== VIEW MEDICAL HISTORY ==========");

        System.out.print("Enter patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());

        PatientDAO patientDAO = new PatientDAO();

        Patient patient = patientDAO.getPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("Patient Name: " + patient.getName());

        MedicalHistoryDAO historyDAO = new MedicalHistoryDAO();

        List<MedicalHistory> historyList =
                historyDAO.getMedicalHistoryByPatientId(patientId);

        if (historyList.isEmpty()) {

            System.out.println("No medical history found.");

        } else {

            for (MedicalHistory history : historyList) {

                System.out.println("--------------------------------------");
                System.out.println("History ID : " + history.getHistoryId());
                System.out.println("Disease    : " + history.getDisease());
                System.out.println("Diagnosis  : " + history.getDiagnosis());
                System.out.println("Treatment  : " + history.getTreatment());
                System.out.println("Doctor     : " + history.getDoctor());
                System.out.println("Visit Date : " + history.getVisitDate());
            }

            System.out.println("--------------------------------------");
        }
    }

    // ================= UPDATE MEDICAL HISTORY =================

    private static void updateMedicalHistory() {

        System.out.println("\n========== UPDATE MEDICAL HISTORY ==========");

        System.out.print("Enter history ID: ");
        int historyId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new disease: ");
        String disease = scanner.nextLine();

        System.out.print("Enter new diagnosis: ");
        String diagnosis = scanner.nextLine();

        System.out.print("Enter new treatment: ");
        String treatment = scanner.nextLine();

        System.out.print("Enter new doctor: ");
        String doctor = scanner.nextLine();

        System.out.print("Enter new visit date (YYYY-MM-DD): ");
        Date visitDate = Date.valueOf(scanner.nextLine());

        MedicalHistory history = new MedicalHistory(
                historyId,
                patientId,
                disease,
                diagnosis,
                treatment,
                doctor,
                visitDate
        );

        MedicalHistoryDAO historyDAO = new MedicalHistoryDAO();

        if (historyDAO.updateMedicalHistory(history)) {
            System.out.println("Medical history updated successfully!");
        } else {
            System.out.println("Failed to update medical history.");
        }
    }

    // ================= DELETE MEDICAL HISTORY =================

    private static void deleteMedicalHistory() {

        System.out.println("\n========== DELETE MEDICAL HISTORY ==========");

        System.out.print("Enter history ID: ");
        int historyId = Integer.parseInt(scanner.nextLine());

        MedicalHistoryDAO historyDAO = new MedicalHistoryDAO();

        System.out.print("Are you sure you want to delete? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {

            if (historyDAO.deleteMedicalHistory(historyId)) {
                System.out.println("Medical history deleted successfully!");
            } else {
                System.out.println("Failed to delete medical history.");
            }

        } else {

            System.out.println("Delete operation cancelled.");
        }
    }
}