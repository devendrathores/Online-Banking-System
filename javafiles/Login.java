import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Login extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
res.setContentType("text/html");
PrintWriter out =res.getWriter();

/*FETCHING USER INPUT DETAILS*/
String type= req.getParameter("type");
String user=req.getParameter("txtname");
String passname=req.getParameter("passname");

String s1="User";
String s2="Admin";
if(type.equals(s1)){
/*USER DETAILS CHECKING*/
String userName="system";
String userPassword="oracle";
String driverName="oracle.jdbc.driver.OracleDriver";
String url="jdbc:oracle:thin:@localhost:1521:xe";
String sql="select*from user_new_account where USERNAME=? AND PASSWORD=?";
try{
	Class.forName(driverName);
	Connection conn =DriverManager.getConnection(url,userName,userPassword);
	PreparedStatement pst = conn.prepareStatement(sql);
	pst.setString(1,user);
	pst.setString(2,passname);
	ResultSet rs = pst.executeQuery();
	HttpSession s = req.getSession();
	s.setAttribute("upassword",passname);
	if(rs.next()==true){
	s.setAttribute("uname",user);
	res.sendRedirect("afterlogin");
	}
	else{
	   RequestDispatcher disp = req.getRequestDispatcher("index.html");
	  disp.include(req,res);
	  out.println("<html>");
	  out.println("<body onload='myFunction()'>");
	  out.println("<script>");
	  out.println("function myFunction(){alert('Invalid Username or Password !!');}");
	  out.println("</script>");
	  out.println("</body>");
	  out.println("</html>");
	  }
}
catch(ClassNotFoundException e){out.println(e);}
catch(SQLException e){out.println(e);}

}else if(type.equals(s2)){
	out.println("<html>");
	out.println("<body>");
    out.println("<center>"); 
    out.println("<h1>");	
	out.println("Right now admin portal is under Maintanance");
	out.println("</h1>");
	out.println("<h1>");
	out.println("Kindly Come Later");
	out.println("</h1>");
	out.println("</center>");
	out.println("</body>");
	out.println("</html>");
}

	}
}