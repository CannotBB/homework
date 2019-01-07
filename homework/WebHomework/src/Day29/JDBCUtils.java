package Day29;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtils {
    private static String driver="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://localhost:3306/luo";
    private static String username="root";
    private static String password="123456";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    public static void insert(String sql,Object... values){
        try(Connection conn=getConnection()){
            try(PreparedStatement psmt=conn.prepareStatement(sql)){
                int i=1;
                for(Object ele:values){
                    psmt.setObject(i,ele);
                    i++;
                }
                psmt.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
