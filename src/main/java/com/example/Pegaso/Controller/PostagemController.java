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

import com.example.Pegaso.domain.Service.Postagem.PostagemService;
import com.example.Pegaso.domain.VO.V1.PostagemVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("Postagem/v1")
@Tag(name = "Posts", description = "Endpoints for Managing Posts")
public class PostagemController {

        @Autowired
        private PostagemService service;

        @PostMapping(value = "/adicionar", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Adds a new Post", description = "Adds a new Post by passing in a JSON or XML representation of the post", tags = {
                        "Posts" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(schema = @Schema(implementation = PostagemVO.class))
                                        }),
                                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content),
                        })
        public ResponseEntity<Object> savePostagem(@RequestBody @Valid PostagemVO postagem) throws Exception {
                return ResponseEntity.status(HttpStatus.CREATED).body(service.savePostagem(postagem));
        }

        @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE})
        @Operation(summary = "Finds all Posts", description = "Finds all Posts", tags = { "Posts" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PostagemVO.class)))
                        }),
                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content),
        })
        public ResponseEntity<List<PostagemVO>> getPosts() throws Exception {
                return ResponseEntity.status(HttpStatus.OK).body(service.findAllPost());
        }

        @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Find a Post", description = "Find a Post", tags = { "Posts" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = PostagemVO.class))

                        }),
                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content),
        })

        public ResponseEntity<Object> getOnePost(@PathVariable(value = "id") Long id) throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(service.findPostById(id));
        }

        @GetMapping(value = "/vo/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Find a Post", description = "Find a Post", tags = { "Posts" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = PostagemVO.class))

                        }),
                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content),
        })
        public ResponseEntity<Object> getOnePostCostom(@PathVariable(value = "id") Long id) throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(service.findByIdPostagemCostomized(id));
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Deletes a Post", description = "Deletes a Person by passing id", tags = {
                        "Posts" }, responses = {
                                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content),
                        })
        public ResponseEntity<Object> deletePost(@PathVariable(value = "id") Long id) throws Exception {

                service.deletePost(id);
                return ResponseEntity.status(HttpStatus.OK).body("Post deleted Sucefully");
        }

        @PutMapping(value = "/Update/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Updates a Post", description = "Updates a Post by passing in a JSON or XML representation of the post!", tags = {
                        "Posts" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(schema = @Schema(implementation = PostagemVO.class))

                                        }),
                                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content),
                        })
        public ResponseEntity<Object> updatePost(@PathVariable(value = "id") Long id,
                        @RequestBody @Valid PostagemVO post) throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(service.update(post, id));
        }

}
