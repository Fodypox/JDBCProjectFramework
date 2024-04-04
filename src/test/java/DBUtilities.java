import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.*;

public class DBUtilities {
    public static Connection con;
    public static Statement st;
    public static ResultSet rs;
    public static final String hostURL = "jdbc:mysql://db-technostudy.ckr1jisflxpv.us-east-1.rds.amazonaws.com";
    public static final String dbSchema = hostURL + "/employees";
    public static final String username = "root";
    public static final String password = "'\"-LhCB'.%k[4S]z";

    @BeforeClass
    public void setUp(){
        con = null;
        try {
            con = DriverManager.getConnection(dbSchema,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown(){
        if (con != null){
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void printAllData(String query){
        try {
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
