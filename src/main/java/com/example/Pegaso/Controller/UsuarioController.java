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

import com.example.Pegaso.domain.Service.Usuario.UsuarioService;
import com.example.Pegaso.domain.VO.V1.UsuarioVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("Usuario/v1")
@Tag(name = "Users", description = "Endpoints for Managing Users")
public class UsuarioController {

        @Autowired
        private UsuarioService serviceUser;

        @PostMapping(value = "/Adicionar", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Adds a new User", description = "Adds a new User by passing in a JSON or XML representation of the user", tags = {
                        "Users" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(schema = @Schema(implementation = UsuarioVO.class)) }),
                                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content) })

        public ResponseEntity<Object> saveUser(@RequestBody @Valid UsuarioVO usuario) throws Exception {

                return ResponseEntity.status(HttpStatus.CREATED).body(serviceUser.saveUser(usuario));
        }

        @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Finds all Users", description = "Finds all Users", tags = { "Users" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioVO.class))) }),
                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content) })

        public ResponseEntity<List<UsuarioVO>> getUsers() throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(serviceUser.findAllUser());
        }

        @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Find a User", description = "Find a User", tags = { "Users" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = UsuarioVO.class)) }),
                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content) })

        public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") Long id) throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(serviceUser.findUserById(id));
        }

        @GetMapping(value = "/vo/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Find a User", description = "Find a User", tags = { "Users" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = UsuarioVO.class)) }),
                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content) })

        public ResponseEntity<Object> getOneUserCostom(@PathVariable(value = "id") Long id) throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(serviceUser.findByIdUserCostomized(id));
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Deletes a User", description = "Deletes a Person by passing id", tags = {
                        "Users" }, responses = {
                                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content) })

        public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id) throws Exception {

                serviceUser.deleteUser(id);
                return ResponseEntity.status(HttpStatus.OK).body("User deleted Sucefully");
        }

        @PutMapping(value = "/Update/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
                                        MediaType.APPLICATION_XML_VALUE })
        @Operation(summary = "Updates a User", description = "Updates a User by passing in a JSON or XML representation of the user!", tags = {
                        "Users" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(schema = @Schema(implementation = UsuarioVO.class)) }),
                                        @ApiResponse(description = "BadRequest", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "InternalError", responseCode = "500", content = @Content) })

        public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long id,
                        @RequestBody @Valid UsuarioVO user) throws Exception {

                return ResponseEntity.status(HttpStatus.OK).body(serviceUser.updateUser(user, id));
        }

}