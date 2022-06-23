import java.sql.*;
import java.io.*;
import java.util.Random;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.*;
public class Newaccount extends HttpServlet{
	 public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();

/*FETCHING USER INPUT DETAILS*/
String user= req.getParameter("txtname");
String adhar= req.getParameter("aadharno");
String email= req.getParameter("emailid");
String mobile= req.getParameter("mobileno");
String age= req.getParameter("age");
String userFather= req.getParameter("fathername");
String acctype= req.getParameter("accounttype");
int balance= Integer.parseInt(req.getParameter("amountsubmit"));
String gender= req.getParameter("gender");

/*ACCOUNT GENERATION*/
Random random = new Random();

String s="1234567890";
char[] otp = new char[10];
for(int i=0;i<10;i++){
otp[i]=s.charAt(random.nextInt(s.length()));	
}
String T[] = new String[otp.length];
for(int i=0;i<otp.length;i++){
	T[i]=String.valueOf(otp[i]);
}
//String U=Arrays.toString(T[i]);
String accountNo="";
String passname="";
for(String cars:T){
	accountNo+=cars;
}


/*JAVA DATABASE CONNECTIVITY*/
String userName="system";
String userPassword="oracle";
String driverName="oracle.jdbc.driver.OracleDriver";
String url="jdbc:oracle:thin:@localhost:1521:xe";
String sql="insert into USER_NEW_ACCOUNT values(?,?,?,?,?,?,?,?,?,?,?)";
try{
	Class.forName(driverName);
	Connection conn = DriverManager.getConnection(url,userName,userPassword);
	PreparedStatement pst = conn.prepareStatement(sql);
	pst.setString(1,user);
	pst.setString(2,adhar);
	pst.setString(3,email);
	pst.setString(4,mobile);
	pst.setString(5,age);
	pst.setString(6,userFather);
	pst.setString(7,acctype);
	pst.setInt(8,balance);
	pst.setString(9,gender);
	pst.setString(10,accountNo);
	pst.setString(11,passname);
	pst.executeUpdate();
	conn.close();

}	

	
catch(ClassNotFoundException e){
	System.out.println("driver not found");
}
catch(SQLException e){
	System.out.println(e);
}
out.println("<html>");
out.println("<head><link rel='stylesheet' href='style5.css'></head>");
out.println("<body>");
out.println("<header>");
out.println("<nav>");
out.println("<img src='images/indian-bank-logo.png' class='logo'>");
out.println("<ul>");
out.println("<li><a class='homeblack' href='home.html'>Home</a>");
out.println("<li><a class='homeblack' href='index.html'>LogIn</a>");
out.println("<li><a class='homeblack' href='signup.html'>SignUp</a>");
out.println("<li><a class='homeblack' href='contact.html'>Contact Us</a>");
out.println("</ul>");
out.println("</nav>");
out.println("</header>");
out.println("<div class='divider'></div>");

out.println("<div class='image'>");
out.println("<center><h2>");
out.println("ACCOUNT GENERATED SUCCESSFULLY !!");
out.println("</center><h2>");
out.println("<br><br>");
out.println("<p>");
out.println("<h3>");
out.print("Kindly note your your acc/no for future reference:-   ");
out.print("<mark>"+accountNo+"</mark>");
out.println("</h3>");
out.println("</p>");
out.println("</div>");
out.println("</body>");
out.println("</html>");

	
	}
}