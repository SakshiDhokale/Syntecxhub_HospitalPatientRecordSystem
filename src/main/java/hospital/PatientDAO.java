package hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // ADD PATIENT
    public boolean addPatient(Patient patient) {

        String sql = "INSERT INTO patients " +
                     "(name, age, gender, phone, disease, address) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getPhone());
            statement.setString(5, patient.getDisease());
            statement.setString(6, patient.getAddress());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while adding patient:");
            e.printStackTrace();
            return false;
        }
    }

    // GET ALL PATIENTS
    public List<Patient> getAllPatients() {

        List<Patient> patients = new ArrayList<>();

        String sql = "SELECT * FROM patients";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Patient patient = new Patient(
                    resultSet.getInt("patient_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("gender"),
                    resultSet.getString("phone"),
                    resultSet.getString("disease"),
                    resultSet.getString("address")
                );

                patients.add(patient);
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching patients:");
            e.printStackTrace();
        }

        return patients;
    }

    // SEARCH PATIENT BY ID
    public Patient getPatientById(int patientId) {

        String sql = "SELECT * FROM patients WHERE patient_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Patient(
                    resultSet.getInt("patient_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("gender"),
                    resultSet.getString("phone"),
                    resultSet.getString("disease"),
                    resultSet.getString("address")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error while searching patient:");
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE PATIENT
    public boolean updatePatient(Patient patient) {

        String sql = "UPDATE patients SET " +
                     "name = ?, age = ?, gender = ?, phone = ?, " +
                     "disease = ?, address = ? " +
                     "WHERE patient_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getPhone());
            statement.setString(5, patient.getDisease());
            statement.setString(6, patient.getAddress());
            statement.setInt(7, patient.getPatientId());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while updating patient:");
            e.printStackTrace();
            return false;
        }
    }

    // DELETE PATIENT
    public boolean deletePatient(int patientId) {

        String sql = "DELETE FROM patients WHERE patient_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while deleting patient:");
            e.printStackTrace();
            return false;
        }
    }
}