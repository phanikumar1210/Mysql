/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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


/**
 *
 * @author miracle
 */
public class ViewData extends HttpServlet {

   public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
    { 
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
	String uname=request.getParameter("username");
        	 String pwd=request.getParameter("password");
             try{                             
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("com.ibm.db2.jcc.DB2Driver");
            System.out.println("driver loading");
            System.out.println("driver loaded successfully");
            //Connection con=DriverManager.getConnection("jdbc:mysql://10.240.0.2:3306/sample","root","ilikerandompasswords");
            //Connection con=DriverManager.getConnection("jdbc:db2://172.17.0.142:50001/itgdb","mssusr15","miracle15");
            Connection con=DriverManager.getConnection("jdbc:mysql://192.168.5.10:3306/sample?user=root&password=ilikerandompasswords&useUnicode=true&characterEncoding=UTF-8");
            Statement stmt = con.createStatement();
            PreparedStatement pst=con.prepareStatement("select * from login where user=? AND password=?");
            pst.setString(1, uname);
            pst.setString(2, pwd);
            ResultSet rs=pst.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            rs=stmt.executeQuery("SELECT * FROM login");
            out.println("<html><body><center><table border=2><tr><td>");
            out.println(rsmd.getColumnLabel(1)+"</td><td>"+rsmd.getColumnLabel(2)+"</td><td>");
                   while(rs.next())
                   {
                    out.println("<tr><td>"+String.valueOf(rs.getString(1))+"</td><td>"+String.valueOf(rs.getString(2))+"</td><td>");
                 }
                //rs=stmt.executeQuery("SHOW TABLES");         
	}catch (Exception ex) {
                out.println(ex);
            }
    }
}