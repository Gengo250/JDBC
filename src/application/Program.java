package application;

import db.DB;
import db.DBIntegridyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "DELETE FROM department " +
                            "WHERE Id = ?"
            );

            st.setInt(1, 5);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Done! " + rowsAffected + " row(s) affected");
            } else {
                System.out.println("No rows affected");
            }

        } catch (SQLException e) {
            throw new DBIntegridyException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}