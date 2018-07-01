package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Utility {
    public static List<List<String>> dataList;
    public static Statement stmt;
    public static ResultSet rs;

    public static Statement createCon() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
        Statement stmt = con.createStatement();
        return stmt;
    }

    public static ResultSet result(Statement stmt) throws Exception {
        ResultSet rs = stmt.executeQuery("select * from emp where id='5171'");
        while(rs.next()) {
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
        }

        return rs;
    }
}
