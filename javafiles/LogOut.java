import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class LogOut extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
res.setContentType("text/html");
PrintWriter out =res.getWriter();

RequestDispatcher disp=req.getRequestDispatcher("index.html");
disp.include(req,res);

HttpSession s =req.getSession(false);
s.invalidate();
out.println("<html>");
	  out.println("<body onload='myFunction()'>");
	  out.println("<script>");
	  out.println("function myFunction(){alert('You have Logout Successfully');}");
	  out.println("</script>");
	  out.println("</body>");
	  out.println("</html>");
	
	}
}