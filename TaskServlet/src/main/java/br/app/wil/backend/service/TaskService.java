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

	public HttpServletResponse atualizar(String indexStr, String newTask, HttpServletResponse response) throws IOException {
		try {
			int index = Integer.parseInt(indexStr);
			if (newTask != null && !newTask.trim().isEmpty()) {
				if (index >= 0 && index < tasks.size()) {
					tasks.set(index, newTask);
					notificacaoService.enviarNotificacao("Tarefa atualizada: " + newTask, "user@example.com");
					response.getWriter().print("Tarefa atualizada: " + newTask);
					response.setStatus(HttpServletResponse.SC_OK);
				} else {
					notificacaoService.enviarNotificacao("Erro ao atualizar tarefa: tarefa não encontrada! "+index , "user@example.com");
					response.getWriter().print("Erro ao atualizar tarefa: tarefa não encontrada!");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
			} else {
				notificacaoService.enviarNotificacao("Erro ao atualizar tarefa: tarefa inválida", "user@example.com");
				response.getWriter().print("Erro ao atualizar tarefa: tarefa inválida");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		} catch (NumberFormatException e) {
			response.getWriter().print("Índice enviado não é numérico!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			notificacaoService.enviarNotificacao("Erro ao atualizar tarefa: índice não numérico", "user@example.com");
		} catch (IOException e) {
			response.getWriter().print("Erro ao excluir tarefa!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			notificacaoService.enviarNotificacao("Erro ao atualizar tarefa! " + e.getMessage(), "user@example.com");
		}
		return response;
	}

	public HttpServletResponse remover(String indexStr, HttpServletResponse response) throws IOException {

		try {
			int index = Integer.parseInt(indexStr);
			if (index >= 0 && index < tasks.size()) {
				String removedTask = tasks.remove(index);
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().print("Tarefa excluida: " + removedTask);
				notificacaoService.enviarNotificacao("Tarefa excluida: " + removedTask, "user@example.com");
			} else {
				response.getWriter().print("Tarefa não encontrada!");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				notificacaoService.enviarNotificacao("Erro ao excluir tarefa: índice inválido", "user@example.com");
			}
		} catch (NumberFormatException e) {
			response.getWriter().print("Índice enviado não é numérico!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			notificacaoService.enviarNotificacao("Erro ao excluir tarefa: índice não numérico", "user@example.com");
		} catch (IOException e) {
			response.getWriter().print("Erro ao excluir tarefa!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			notificacaoService.enviarNotificacao("Erro ao excluir tarefa! " + e.getMessage(), "user@example.com");
		}
		return response;
	}
}
