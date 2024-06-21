package br.ufg.inf.backend;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class CalculadoraServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num1=request.getParameter("num1");
		String num2=request.getParameter("num2");
		String operacao=request.getParameter("operacao");
		Float resultado;
		switch (operacao) {
		case "+":
			resultado=Float.parseFloat(num1)+Float.parseFloat(num2);
			break;
		case "-":
			resultado=Float.parseFloat(num1)-Float.parseFloat(num2);
			break;
		case "*":
			resultado=Float.parseFloat(num1)*Float.parseFloat(num2);
			break;
		case "/": 
			resultado=Float.parseFloat(num1)/Float.parseFloat(num2);
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + operacao);
		}

		response.getWriter().println(resultado.toString());
	}
}
