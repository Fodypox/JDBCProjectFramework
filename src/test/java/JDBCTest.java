import org.testng.annotations.Test;

import java.sql.*;

public class JDBCTest {
    public static Connection con;
    public static Statement st;
    public static ResultSet rs;
    public static final String hostURL = "jdbc:mysql://db-technostudy.ckr1jisflxpv.us-east-1.rds.amazonaws.com";
    public static final String dbSchema = hostURL + "/employees";
    public static final String username = "root";
    public static final String password = "'\"-LhCB'.%k[4S]z";

    @Test
    public void test1(){
        String query = "SELECT *\n" +
                "FROM employees\n" +
                "INNER JOIN dept_emp ON employees.emp_no = dept_emp.emp_no\n" +
                "WHERE dept_no = 'D001';";

        try {
            con = DriverManager.getConnection(dbSchema, username, password);
            st = con.createStatement();
            rs = st.executeQuery(query);

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
//            printing headers
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-16s",metaData.getColumnName(i));

            }
            System.out.println();
//            print the data
            int rowCount = 0;
            while (rs.next() && rowCount<=20){
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("%-16s", rs.getString(i));
                }
                System.out.println();
                rowCount++;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
