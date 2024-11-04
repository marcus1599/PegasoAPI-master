package com.example.Pegaso.Controller;

import com.example.Pegaso.Data.Models.Imagem;
import com.example.Pegaso.domain.DTO.ImagemDTO;
import com.example.Pegaso.domain.Service.Imagem.ImagensService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.Valid;

@RestController
@RequestMapping("/imagens/v1")
public class ImagensController {

    @Autowired
    private ImagensService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Imagem salvar(@RequestBody @Valid ImagemDTO imagem) {
        return service.salvar(imagem);
    }

    @PostMapping("{id}/figure")
    public byte[] addPhoto(@PathVariable Long id,
            @RequestParam("figure") Part arquivo) {
        return service.addPhoto(id, arquivo);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {

        return ResponseEntity.status(HttpStatus.OK).body(service.findTudo());
    }

}
