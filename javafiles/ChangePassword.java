import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ChangePassword extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
res.setContentType("text/html");
PrintWriter out =res.getWriter();
/*FETCHING USER INPUT DETAILS*/
HttpSession s = req.getSession();
String name=(String)s.getAttribute("uname");
String password=(String)s.getAttribute("upassword");

String pass=req.getParameter("Password");
String repass=req.getParameter("RePassword");
String acc="";

if(pass.equals(repass)){
	String userName="system";
	  String userPassword="oracle";
	  String driverName="oracle.jdbc.driver.OracleDriver";
	  String url="jdbc:oracle:thin:@localhost:1521:xe";
	  String sql="select ACCOUNT_NO from user_new_account where USERNAME=? AND PASSWORD=?";
	  try{
	    Class.forName(driverName);
	    Connection conn =DriverManager.getConnection(url,userName,userPassword);
	    PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1,name);
	    pst.setString(2,password);
	    ResultSet rs = pst.executeQuery();
		if(rs.next()==true){acc=rs.getString("ACCOUNT_NO");}
        }catch(ClassNotFoundException e){out.println(e);}
        catch(SQLException e){out.println(e);}
		
	   String SQL="update user_new_account set PASSWORD=? where ACCOUNT_NO=?";
	   try{
	    Class.forName(driverName);
	    Connection conn1 =DriverManager.getConnection(url,userName,userPassword);
	    PreparedStatement pst1 = conn1.prepareStatement(SQL);
		pst1.setString(1,pass);
	    pst1.setString(2,acc);
	    pst1.executeUpdate();
        }catch(ClassNotFoundException e){out.println(e);}
        catch(SQLException e){out.println(e);}
       RequestDispatcher disp = req.getRequestDispatcher("changepassword.html");
	   disp.include(req,res);
	   out.println("<html>");
	   out.println("<body onload='myFunction()'>");
	   out.println("<script>");
	   out.println("function myFunction(){alert('Password change Successfully !!');}");
	   out.println("</script>");
	   out.println("</body>");
	   out.println("</html>");
}
else{
	  
	  RequestDispatcher dispp = req.getRequestDispatcher("changepassword.html");
	   dispp.include(req,res);
	   out.println("<html>");
	   out.println("<body onload='myFunction()'>");
	   out.println("<script>");
	   out.println("function myFunction(){alert('Password doesn't match with the second one !!');}");
	   out.println("</script>");
	   out.println("</body>");
	   out.println("</html>");
}

  }
}	