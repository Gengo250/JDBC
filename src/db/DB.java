package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();

                String url = props.getProperty("dburl").trim();
                String user = props.getProperty("user").trim();
                String password = props.getProperty("password").trim();

                System.out.println("Diretório atual = " + System.getProperty("user.dir"));
                System.out.println("URL = [" + url + "]");
                System.out.println("USER = [" + user + "]");
                System.out.println("PASS_LEN = " + password.length());

                conn = DriverManager.getConnection(url, user, password);
            }
            catch (SQLException e) {
                throw new DbException("Erro ao conectar ao banco: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e) {
                throw new DbException("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try {
            File file = new File("db.properties");
            System.out.println("Lendo arquivo em: " + file.getAbsolutePath());

            FileInputStream fs = new FileInputStream(file);
            Properties props = new Properties();
            props.load(fs);
            fs.close();
            return props;
        }
        catch (IOException e) {
            throw new DbException("Erro ao ler db.properties: " + e.getMessage());
        }
    }

    public static void closeStatement(Statement st){
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}