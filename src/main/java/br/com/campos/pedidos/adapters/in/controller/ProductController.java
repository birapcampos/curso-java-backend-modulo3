package br.com.campos.pedidos.adapters.in.controller;

import br.com.campos.pedidos.adapters.in.controller.request.ProductRequest;
import br.com.campos.pedidos.adapters.out.response.ProductResponse;
import br.com.campos.pedidos.application.ports.in.product.ProductInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Produtos", description = "Contém todas as operações relativas ao cadastro de produtos")
@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private ProductInputPort productInputPort;

    public ProductController(ProductInputPort productInputPort) {
        this.productInputPort=productInputPort;
    }

    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest product) {
        var productResponse = productInputPort.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @Operation(summary = "Obter um produto pelo ID", description = "Retorna um produto específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> getProduct(@PathVariable Long id) {
        Optional<ProductResponse> productResponse = productInputPort.getProduct(id);
        return ResponseEntity.ok(productResponse);
    }

    @Operation(summary = "Obter todos os produtos", description = "Retorna uma lista de todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productInputPort.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @Operation(summary = "Excluir um produto", description = "Exclui um produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productInputPort.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Atualizar um produto existente", description = "Atualiza os dados de um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                         @Valid @RequestBody ProductRequest product) {
        ProductResponse updatedProduct = productInputPort.updateProduct(id,product);
        return ResponseEntity.ok(updatedProduct);
    }
}
