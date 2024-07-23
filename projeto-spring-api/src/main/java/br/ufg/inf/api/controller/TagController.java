package br.ufg.inf.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufg.inf.api.model.Tag;
import br.ufg.inf.api.service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> listarTags() {
        return tagService.listarTags();
    }

    @PostMapping
    public Tag adicionarTag(@RequestBody Tag tag) {
        return tagService.salvarTag(tag);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tag> obterTag(@PathVariable Long id) {
        Tag tag = tagService.obterTag(id);
        if (tag != null) {
            return ResponseEntity.ok(tag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}	