import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class SignUp extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();

/*FETCHING USER INPUT DETAILS*/
String type= req.getParameter("type");
String accountNo=req.getParameter("Accountnum");
String user=req.getParameter("txtname");
String passname=req.getParameter("passname");
String repassname=req.getParameter("repassname");

String s1="User";
String s2="Admin";


/*USER COMES FOR SIGNUP PAGE*/
if(type.equals(s1)){
	//CHECKING USER ENTER ACCOUNT/NO EXIST OR NOT
	  String userName="system";
	  String userPassword="oracle";
	  String driverName="oracle.jdbc.driver.OracleDriver";
	  String url="jdbc:oracle:thin:@localhost:1521:xe";
	  String sql="select ACCOUNT_NO from USER_NEW_ACCOUNT where ACCOUNT_NO=?";
	  try{
	  Class.forName(driverName);
	  Connection conn = DriverManager.getConnection(url,userName,userPassword);
	  PreparedStatement pst = conn.prepareStatement(sql);
	  pst.setString(1,accountNo);
	  ResultSet rs = pst.executeQuery();
	  
	//IF ACCOUNT/NO EXIST  
    if(rs.next()==true){
	   //CHECKING USER ENTER BOTH PASSWORDS ARE SAME OR NOT	
    if(passname.equals(repassname)){

	  String SQL="update USER_NEW_ACCOUNT set PASSWORD=? where ACCOUNT_NO=?";
	  try{
	  Class.forName(driverName);
	  Connection CONN = DriverManager.getConnection(url,userName,userPassword);
	  PreparedStatement PST = CONN.prepareStatement(SQL);
	  PST.setString(1,passname);
	  PST.setString(2,accountNo);
	  PST.executeUpdate();
	  conn.close();
	  }
	  catch(ClassNotFoundException e){System.out.println("driver not found");}
	  catch(SQLException e){System.out.println(e);}
String tableName="H"+accountNo;
String Sql="create table "+tableName+"(ACCOUNT varchar(10), AMOUNT varchar(8),TIME varchar(50))";
try{
Class.forName(driverName);
Connection Conn = DriverManager.getConnection(url,userName,userPassword);
Statement st = Conn.createStatement();
st.executeUpdate(Sql);
Conn.close();
}
catch(ClassNotFoundException e){out.println("driver not found");}
catch(SQLException e){out.println(e);}
	  
	  RequestDispatcher disp = req.getRequestDispatcher("index.html");
	  disp.include(req,res);
	  
	  out.println("<html>");
	  out.println("<body onload='myFunction()'>");
	  out.println("<script>");
	  out.println("function myFunction(){alert('You have Successfully signed up your account for Netbanking');}");
	  out.println("</script>");
	  out.println("</body>");
	  out.println("</html>");

	
	}//IF BOTH PASSWORDS ARE NOT SAME	
	 else if(passname!=repassname){
	  out.println("<center>");
	  out.println("<h3>Password doesn't match with second Password</h3>");
	  out.println("<br><br><br><br>");
	  out.println("<button><a href='signup.html'>Try Again</a></button>");
	   out.println("</center>");
	 }
  }
    //IF ACCOUNT/NO DOESN'T EXIST
else{ 
      out.println("<center>");
	  out.println("<h3>User Account/No Does Not Exist!</h3>");
	  out.println("<br><br><br><br>");
	  out.println("<button><a href='signup.html'>Try Again</a></button>");
	   out.println("</center>");
      
	  }  
}
	  catch(ClassNotFoundException e){System.out.println("driver not found");}
	  catch(SQLException e){System.out.println(e);}  
}

/*ADMIN COMES FOR SIGNUP PAGE*/
else if(type.equals(s2)){
	out.println("<html>");
	out.println("<body>");
    out.println("<center>"); 
    out.println("<h1>");	
	out.println("Right now admin signup is not available");
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