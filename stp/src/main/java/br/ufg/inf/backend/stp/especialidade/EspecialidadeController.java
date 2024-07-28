package br.ufg.inf.backend.stp.especialidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

	@Autowired
	private EspecialidadeService service;

	@GetMapping
	public List<Especialidade> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Especialidade obter(@PathParam(value = "id") Long especialidadeId) {
		return service.obter(especialidadeId);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathParam(value = "id") Long especialidadeId) {
		service.remover(especialidadeId);
	}

	@PostMapping
	public Especialidade adicionar(@RequestBody Especialidade especialidade) {
		return service.salvar(especialidade);
	}

	@PutMapping("/{id}")
	public Especialidade atualizar(@PathParam(value = "id") Long especialidadeId, @RequestBody Especialidade especialidade) {
		return service.salvar(especialidadeId, especialidade);
	}
	
}
