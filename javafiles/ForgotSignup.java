import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ForgotSignup extends HttpServlet{
public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();

String accountNo="";

/*FETCHING USER INPUT DETAILS*/
String user= req.getParameter("username");
String adhar=req.getParameter("aadharno");

/*JDBC DATABASE CONNECTIVITY*/
String userName="system";
String userPassword="oracle";
String driverName="oracle.jdbc.driver.OracleDriver";
String url="jdbc:oracle:thin:@localhost:1521:xe";
String sql="select ACCOUNT_NO from USER_NEW_ACCOUNT where AADHARNO=?";
try{
Class.forName(driverName);
Connection conn=DriverManager.getConnection(url,userName,userPassword);
PreparedStatement pst=conn.prepareStatement(sql);
pst.setString(1,adhar);
ResultSet rs=pst.executeQuery();
if(rs.next()==true){
	accountNo=rs.getString("ACCOUNT_NO");
}

conn.close();
}
catch(ClassNotFoundException e){System.out.println("driver not found");}
catch(SQLException e){System.out.println(e);}
out.println("<html>");
out.println("<head>");
out.println("<link rel='stylesheet' href='style6.css'>");
out.println("</head>");
out.println("<body>");
out.println("<header>");
out.println("<nav>");
out.println("<img src='images/indian-bank-logo.png' class='logo'>");
out.println("</nav>");
out.println("</header>");
out.println("<div class='divider'></div>");
out.println("<div class='image'>");
out.println("Your Account/No is: "+"<mark>"+accountNo+"</mark>"+"  ");
out.print("  .Kindly remember for next time!!");
out.println("<br><br>");
out.println("<button><a href='signup.html'>Back to SignUp Page</a></button>");
out.println("</div>");
out.println("</body>");
out.println("</html>");




   }
}
