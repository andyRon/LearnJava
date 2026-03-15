package top.andyron.learnjava.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/slow/hi")
public class HiServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		// simulate slow operation:
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		PrintWriter pw = resp.getWriter();
		pw.write("<h1>Hi, Bob!</h1>");
		pw.flush();
	}
}
