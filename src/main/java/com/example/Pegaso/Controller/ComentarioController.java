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

import com.example.Pegaso.Data.Repository.DicaRepository;
import com.example.Pegaso.Data.Repository.UsuarioRepository;
import com.example.Pegaso.domain.Service.Comentario.ComentarioService;
import com.example.Pegaso.domain.VO.V1.ComentarioVO;
import com.example.Pegaso.domain.VO.V1.DicaVO;
import com.example.Pegaso.exceptions.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("Comentario/v1")
@Tag(name = "Comments", description = "Endpoints for Managing Comments")
public class ComentarioController {

        @Autowired
        private ComentarioService serviceComment;
        @Autowired
        private DicaRepository dicaRepository;
        @Autowired
        private UsuarioRepository userRepository;

        @PostMapping(value = "/Adicionar", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Adds a new Comment", description = "Adds a new Comment by passing in a JSON or XML representation of the comment", tags = {
                        "Comments" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(schema = @Schema(implementation = ComentarioVO.class)) }), })

        public ResponseEntity<Object> saveComment(@RequestBody @Valid ComentarioVO comentario) throws Exception {

                return ResponseEntity.status(HttpStatus.CREATED).body(serviceComment.saveComment(comentario));
        }

        @GetMapping(value = "FindByDica/{idDica}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Finds all Comments", description = "Finds all Comments", tags = {
                        "Comments" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DicaVO.class))) }),
                                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content),
                        })
        public ResponseEntity<List<ComentarioVO>> getCommentsByDica(@PathVariable(value = "idDica") Long idDica)
                        throws Exception {

                var entity = dicaRepository.findById(idDica).orElseThrow(
                                () -> new ResourceNotFoundException("Searched comment with specified id not found"));
                return ResponseEntity.status(HttpStatus.OK).body(serviceComment.findByDicaContainin(entity));
        }

        @GetMapping(value = "FindByUser/{idUser}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Finds all Comments", description = "Finds all Comments", tags = {
                        "Comments" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DicaVO.class))) }),
                                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content),
                        })
        public ResponseEntity<List<ComentarioVO>> getCommentsByUser(@PathVariable(value = "idUser") Long idUsuario)
                        throws Exception {

                var entity = userRepository.findById(idUsuario).orElseThrow(
                                () -> new ResourceNotFoundException("Searched comment with specified id not found"));
                return ResponseEntity.status(HttpStatus.OK).body(serviceComment.findByUserContainin(entity));
        }

        @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Finds all Comments", description = "Finds all Comments", tags = {
                        "Comments" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ComentarioVO.class))) }),
                                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content) })

        public ResponseEntity<List<ComentarioVO>> getComments() throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(serviceComment.findAllComment());
        }

        @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Find a Comment", description = "Find a Comment", tags = { "Comments" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = ComentarioVO.class)) }),
                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content) })

        public ResponseEntity<Object> getOneComment(@PathVariable(value = "id") Long id) throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(serviceComment.findCommentById(id));
        }

        @GetMapping(value = "/vo/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Find a Comment", description = "Find a Comment", tags = { "Comments" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = ComentarioVO.class)) }),
                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content) })

        public ResponseEntity<Object> getOneCommentCostom(@PathVariable(value = "id") Long id) throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(serviceComment.findByIdCommentCostomized(id));
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Deletes a Comment", description = "Deletes a Person by passing id", tags = {
                        "Comments" }, responses = {
                                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content) })

        public ResponseEntity<Object> deleteComment(@PathVariable(value = "id") Long id) throws Exception {

                serviceComment.deleteComment(id);
                return ResponseEntity.status(HttpStatus.OK).body("Comment deleted Sucefully");
        }

        @PutMapping(value = "/Update/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Updates a Comment", description = "Updates a Comment by passing in a JSON or XML representation of the comment!", tags = {
                        "Comments" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(schema = @Schema(implementation = ComentarioVO.class)) }),
                                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content) })

        public ResponseEntity<Object> updateComment(@PathVariable(value = "id") Long id,
                        @RequestBody @Valid ComentarioVO comment) throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(serviceComment.updateComment(comment, id));
        }

}