import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Validation extends HttpServlet {

   public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
    { 
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
try{                             //mysql try block
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("com.ibm.db2.jcc.DB2Driver");
            System.out.println("driver loading");
            System.out.println("Changed successfully");
            Connection con=DriverManager.getConnection("jdbc:mysql://phanikumar1210.cloudapp.net:3306/miracle?user=root&password=miracle&useUnicode=true&characterEncoding=UTF-8");
          // Connection con=DriverManager.getConnection("jdbc:db2://172.17.0.142:50001/itgdb","mssusr15","miracle15");
            String uname=request.getParameter("username");
            String pwd=request.getParameter("password"); 
            Statement stmt = con.createStatement();
           // stmt.execute("CREATE TABLE login(username VARCHAR(60), password VARCHAR(60))");
            PreparedStatement ps = con.prepareStatement("insert into login values(?,?)");
            ps.setString(1,uname);
            ps.setString(2,pwd);
            ps.executeUpdate();     
            out.println("inserted successfully");
  
 }
catch (Exception ex) {
                out.println(ex);
            }
    }
}