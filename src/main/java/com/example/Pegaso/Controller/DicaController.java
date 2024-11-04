package com.example.Pegaso.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pegaso.Data.Repository.PostagemRepository;
import com.example.Pegaso.domain.Service.Dica.DicaService;
import com.example.Pegaso.domain.Service.Postagem.PostagemService;
import com.example.Pegaso.domain.VO.V1.DicaVO;
import com.example.Pegaso.domain.VO.V1.DicaVO_OutPut;
import com.example.Pegaso.exceptions.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/Dicas")
@Tag(name = "Dicas", description = "Endpoints for Managing Dicas")
public class DicaController {

        @Autowired
        private DicaService service;
        @Autowired
        private PostagemRepository postRepository;
        @Autowired
        private PostagemService postService;

        // --------------------Adicionar Dica----------------------------------//

        @PostMapping(value = "/{idPostagem}/Adicionar", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Adds a new Dica", description = "Adds a new Dica by passing in a JSON or XML representation of the post", tags = {
                        "Dicas" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(schema = @Schema(implementation = DicaVO.class))
                                        })
                        })
        public ResponseEntity<Object> saveDica(@RequestBody @Valid DicaVO dica,
                        @PathVariable(value = "idPostagem") Long idPostagem) throws Exception {

                var entityPost = postService.findPostById(idPostagem);

                return ResponseEntity.status(HttpStatus.CREATED).body(service.saveDica(dica, entityPost));
        }

        // -----------------------------------Buscar Todas
        // Dicas--------------------------//
        @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Finds all Dicas", description = "Finds all Dicas", tags = { "Dicas" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DicaVO.class)))
                        })
        })
        public ResponseEntity<List<DicaVO>> getDicas() throws Exception {
                return ResponseEntity.status(HttpStatus.OK).body(service.findAllDicas());
        }

        // ----------------------------------Buscar Todas Dicas por
        // postagem--------------------------//
        @GetMapping(value = "FindBy/{idPostagem}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Finds all Dicas", description = "Finds all Dicas", tags = { "Dicas" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DicaVO.class)))
                        }),
        })
        public ResponseEntity<List<DicaVO_OutPut>> getDicasByPostagem(
                        @PathVariable(value = "idPostagem") Long idPostagem) throws Exception {

                var entity = postRepository.findById(idPostagem).orElseThrow(
                                () -> new ResourceNotFoundException("Searched post with specified id not found"));
                return ResponseEntity.status(HttpStatus.OK).body(service.findByPostagemContainin(entity));
        }

        // ----------Buscar Uma Dica------//
        @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Find a Dica", description = "Find a Dica", tags = { "Dicas" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = DicaVO.class))

                        })

        })

        public ResponseEntity<Object> getOneDica(@PathVariable(value = "id") Long id) throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        }

        // -------------------------------------Busca uma dica
        // Personalizada--------------------------------//
        @GetMapping(value = "/vo/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Find a Dica", description = "Find a Dica", tags = { "Dicas" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = DicaVO.class))

                        })
        })
        public ResponseEntity<Object> getOneDicaCostom(@PathVariable(value = "id") Long id) throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(service.findByIdDicaCostomized(id));
        }

        // ------------------------------------Excluir Dica por
        // id------------------------------------//
        @DeleteMapping("/{id}")
        @Operation(summary = "Deletes a Dica", description = "Deletes a Dica by passing id", tags = {
                        "Dicas" }, responses = {
                                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content),
                        })
        public ResponseEntity<Object> deleteDica(@PathVariable(value = "id") Long id) throws Exception {

                service.deleteDica(id);
                return ResponseEntity.status(HttpStatus.OK).body("Dica deleted Sucefully");
        }

        // ----------------------------------------------Atualizar
        // dica--------------------------------//
        @PutMapping(value = "/Update/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Updates a Dica", description = "Updates a dica by passing in a JSON or XML representation of the dica!", tags = {
                        "Dicas" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(schema = @Schema(implementation = DicaVO.class)) })

        })
        public ResponseEntity<Object> updateDica(@PathVariable(value = "id") Long id,
                        @RequestBody @Valid DicaVO dica) throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(service.update(dica, id));
        }

}
