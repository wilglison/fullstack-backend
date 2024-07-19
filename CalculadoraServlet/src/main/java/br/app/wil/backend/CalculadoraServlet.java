package br.app.wil.backend;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalculadoraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
        String operacao = request.getParameter("operacao");
        Float resultado;

        try {
            float numero1 = Float.parseFloat(num1);
            float numero2 = Float.parseFloat(num2);

            switch (operacao) {
                case "soma":
                    resultado = numero1 + numero2;
                    break;
                case "subtracao":
                    resultado = numero1 - numero2;
                    break;
                case "multiplicacao":
                    resultado = numero1 * numero2;
                    break;
                case "divisao":
                    if (numero2 == 0) {
                        throw new ArithmeticException("Divisão por zero não é permitida.");
                    }
                    resultado = numero1 / numero2;
                    break;
                default:
                    throw new IllegalArgumentException("Operação inválida: " + operacao);
            }

            response.getWriter().println(resultado.toString());
        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: num1 e num2 devem ser valores numéricos.");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        } catch (ArithmeticException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }
}
