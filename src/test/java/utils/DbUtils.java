package utils;

import java.sql.*;
import java.util.*;

public class DbUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce_tests"
            + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "!BJdialdial30";

    public static List<Map<String, Object>> query(String sql) throws Exception {
        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= cols; i++) {
                    row.put(meta.getColumnName(i), rs.getObject(i));
                }
                results.add(row);
            }
        }
        return results;
    }

    // For INSERT / UPDATE / DELETE queries
    public static int execute(String sql) throws Exception {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(sql);
        }
    }

}
