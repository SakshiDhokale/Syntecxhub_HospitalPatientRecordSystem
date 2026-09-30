package hospital;

import java.sql.Date;

public class MedicalHistory {

    private int historyId;
    private int patientId;
    private String disease;
    private String diagnosis;
    private String treatment;
    private String doctor;
    private Date visitDate;

    // Constructor without history ID
    public MedicalHistory(int patientId, String disease,
                          String diagnosis, String treatment,
                          String doctor, Date visitDate) {

        this.patientId = patientId;
        this.disease = disease;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctor = doctor;
        this.visitDate = visitDate;
    }

    // Constructor with history ID
    public MedicalHistory(int historyId, int patientId,
                          String disease, String diagnosis,
                          String treatment, String doctor,
                          Date visitDate) {

        this.historyId = historyId;
        this.patientId = patientId;
        this.disease = disease;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctor = doctor;
        this.visitDate = visitDate;
    }

    // Getters

    public int getHistoryId() {
        return historyId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getDisease() {
        return disease;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getDoctor() {
        return doctor;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    // Setters

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
}