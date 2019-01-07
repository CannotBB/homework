package Day29;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlOperation {

    public static void addOperation(Object... values){
        String sql="insert into user values (?,?,?,?,?)";
        try (Connection conn = JDBCUtils.getConnection()){
            try(PreparedStatement psmt=conn.prepareStatement(sql)){
                int i=1;
                for(Object ele:values){
                    psmt.setObject(i,ele);
                    i++;
                }
                psmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
