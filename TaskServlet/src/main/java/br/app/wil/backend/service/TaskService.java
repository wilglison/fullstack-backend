package br.app.wil.backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

public class TaskService {
	private List<String> tasks;
	private NotificacaoService notificacaoService;

	public TaskService(NotificacaoService notificacaoService) {
		this.tasks = new ArrayList<>();
		this.notificacaoService = notificacaoService;
	}

	public HttpServletResponse listar(HttpServletResponse response) {
		try {
			if(tasks.isEmpty()) {
				response.getWriter().print("Nenhuma Tarefa encontrada!");
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				notificacaoService.enviarNotificacao("Listando tarefas - Nenhuma Tarefa encontrada!", "user@example.com");
			}else {
				for (String task : tasks) {
					response.getWriter().println(tasks.indexOf(task)+1+" "+task);
				}
				response.setStatus(HttpServletResponse.SC_OK);
				notificacaoService.enviarNotificacao("Listando tarefas", "user@example.com");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	public HttpServletResponse adicionar(String[] tasksArray, HttpServletResponse response) throws IOException {
		String tarefasMsg="";
		if (tasksArray != null && tasksArray.length > 0) {
			for (String task : tasksArray) {
				if (task != null && !task.trim().isEmpty()) {
					tasks.add(task);
					tarefasMsg += task+", ";
				}
			}
			notificacaoService.enviarNotificacao("Tarefas adicionadas: " + tarefasMsg, "user@example.com");
			response.getWriter().print("Tarefas adicionadas: " + tarefasMsg);
			response.setStatus(HttpServletResponse.SC_CREATED);
			return response;
		}
		response.getWriter().print("Tarefa inválida recebida!");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		notificacaoService.enviarNotificacao("Tarefa inválida recebida!", "user@example.com");
		return response;
	}

	public HttpServletResponse atualizar(int index, String newTask, HttpServletResponse response) throws IOException {
		try {
			if (newTask != null && !newTask.trim().isEmpty()) {
				if (index >= 0 && index < tasks.size()) {
					tasks.set(index, newTask);
					notificacaoService.enviarNotificacao("Tarefa atualizada: " + newTask, "user@example.com");
					response.getWriter().print("Tarefa atualizada: " + newTask);
					response.setStatus(HttpServletResponse.SC_OK);
				} else {
					notificacaoService.enviarNotificacao("Erro ao atualizar tarefa: índice inválido", "user@example.com");
					response.getWriter().print("Erro ao atualizar tarefa: índice inválido");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
			} else {
				notificacaoService.enviarNotificacao("Erro ao atualizar tarefa: tarefa inválida", "user@example.com");
				response.getWriter().print("Erro ao atualizar tarefa: tarefa inválida");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		} catch (NumberFormatException | IOException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().print("Erro ao atualizar tarefa!" +e.getCause());
			notificacaoService.enviarNotificacao("Erro ao atualizar tarefa!" +e.getCause(), "user@example.com");
		}
		return response;
	}

	public HttpServletResponse remover(int index, HttpServletResponse response) throws IOException {

		try {
			if (index >= 0 && index < tasks.size()) {
				String removedTask = tasks.remove(index);
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().print("Tarefa removida: " + removedTask);
				notificacaoService.enviarNotificacao("Tarefa removida: " + removedTask, "user@example.com");
			} else {
				response.getWriter().print("Tarefa não encontrada!");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				notificacaoService.enviarNotificacao("Erro ao remover tarefa: índice inválido", "user@example.com");
			}
		} catch (NumberFormatException | IOException e) {
			response.getWriter().print("Erro ao excluir tarefa!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			notificacaoService.enviarNotificacao("Erro ao excluir tarefa!" +e.getCause(), "user@example.com");
		}
		return response;
	}
}
