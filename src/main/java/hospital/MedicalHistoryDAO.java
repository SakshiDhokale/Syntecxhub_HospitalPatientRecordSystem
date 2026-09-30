package hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicalHistoryDAO {

    // ADD MEDICAL HISTORY
    public boolean addMedicalHistory(MedicalHistory history) {

        String sql = "INSERT INTO medical_history " +
                     "(patient_id, disease, diagnosis, treatment, doctor, visit_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, history.getPatientId());
            statement.setString(2, history.getDisease());
            statement.setString(3, history.getDiagnosis());
            statement.setString(4, history.getTreatment());
            statement.setString(5, history.getDoctor());
            statement.setDate(6, history.getVisitDate());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while adding medical history:");
            e.printStackTrace();
            return false;
        }
    }

    // GET MEDICAL HISTORY OF A PATIENT
    public List<MedicalHistory> getMedicalHistoryByPatientId(int patientId) {

        List<MedicalHistory> historyList = new ArrayList<>();

        String sql = "SELECT * FROM medical_history " +
                     "WHERE patient_id = ? " +
                     "ORDER BY visit_date DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                MedicalHistory history = new MedicalHistory(
                        resultSet.getInt("history_id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getString("disease"),
                        resultSet.getString("diagnosis"),
                        resultSet.getString("treatment"),
                        resultSet.getString("doctor"),
                        resultSet.getDate("visit_date")
                );

                historyList.add(history);
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching medical history:");
            e.printStackTrace();
        }

        return historyList;
    }

    // UPDATE MEDICAL HISTORY
    public boolean updateMedicalHistory(MedicalHistory history) {

        String sql = "UPDATE medical_history SET " +
                     "disease = ?, diagnosis = ?, treatment = ?, " +
                     "doctor = ?, visit_date = ? " +
                     "WHERE history_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, history.getDisease());
            statement.setString(2, history.getDiagnosis());
            statement.setString(3, history.getTreatment());
            statement.setString(4, history.getDoctor());
            statement.setDate(5, history.getVisitDate());
            statement.setInt(6, history.getHistoryId());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while updating medical history:");
            e.printStackTrace();
            return false;
        }
    }

    // DELETE MEDICAL HISTORY
    public boolean deleteMedicalHistory(int historyId) {

        String sql = "DELETE FROM medical_history WHERE history_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, historyId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while deleting medical history:");
            e.printStackTrace();
            return false;
        }
    }
}