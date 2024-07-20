package br.ufg.inf.backend.spring.controller;

import br.ufg.inf.backend.spring.model.Categoria;
import br.ufg.inf.backend.spring.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
	
@Controller
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/categorias")
	public String listarCategorias(Model model, @RequestParam(required = false) String sucesso) {
		model.addAttribute("categorias", categoriaService.listarCategorias());
		model.addAttribute("sucesso", sucesso);
		return "categorias";
	}
	
	@GetMapping("/categorias/adicionar")
	public String mostrarFormularioAdicionarCategoria() {
		return "adicionar-categoria";
	}
	
	@PostMapping("/categorias")
	public String adicionarCategoria(@RequestParam String nome, RedirectAttributes redirectAttributes) {
		Categoria categoria = new Categoria();
		categoria.setNome(nome);
		categoriaService.salvarCategoria(categoria);
		redirectAttributes.addAttribute("sucesso", "Categoria salva com sucesso!");
		return "redirect:/categorias";
	}

	@GetMapping("/categorias/editar")
	public String mostrarFormularioEditarCategoria(@RequestParam("id") Long id, Model model) {
		Categoria categoria = categoriaService.obterCategoria(id);
		model.addAttribute("categoria", categoria);
		return "editar-categoria";
	}
	@PostMapping("/categorias/editar")
	public String editarCategoria(@RequestParam("id") Long id, @RequestParam("nome") String nome, RedirectAttributes redirectAttributes) {
		Categoria categoria = categoriaService.obterCategoria(id);
		categoria.setNome(nome);
		categoriaService.salvarCategoria(categoria);
		redirectAttributes.addAttribute("sucesso", "Categoria atualizado com sucesso!");
		return "redirect:/categorias";
	}
	@PostMapping("/categorias/deletar")
	public String deletarCategoria(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		categoriaService.deletarCategoria(id);
		redirectAttributes.addAttribute("sucesso", "Categoria deletado com sucesso!");
		return "redirect:/categorias";
	}
}