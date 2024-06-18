package br.com.campos.pedidos.adapters.in.controller;

import br.com.campos.pedidos.adapters.in.controller.request.OrderRequest;
import br.com.campos.pedidos.adapters.out.response.OrderResponse;
import br.com.campos.pedidos.application.ports.in.order.CreateOrderInputPort;
import br.com.campos.pedidos.application.ports.in.order.DeleteOrderInputPort;
import br.com.campos.pedidos.application.ports.in.order.GetOrderInputPort;
import br.com.campos.pedidos.application.ports.in.order.UpdateOrderInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
@Tag(name = "Pedidos (Order)", description = "Contém todas as operações relativas ao cadastro de pedidos (Orders)")
@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    private CreateOrderInputPort createOrderInputPort;
    private UpdateOrderInputPort updateOrderInputPort;
    private GetOrderInputPort getOrderInputPort;
    private DeleteOrderInputPort deleteOrderInputPort;
    public OrderController(CreateOrderInputPort createOrderInputPort,
                           UpdateOrderInputPort updateOrderInputPort,
                           GetOrderInputPort getOrderInputPort,
                           DeleteOrderInputPort deleteOrderInputPort) {
        this.createOrderInputPort = createOrderInputPort;
        this.updateOrderInputPort = updateOrderInputPort;
        this.getOrderInputPort = getOrderInputPort;
        this.deleteOrderInputPort = deleteOrderInputPort;
    }
    @Operation(summary = "Criar um novo pedido", description = "Cria um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest order) {
        OrderResponse createdOrder = createOrderInputPort.createOrder(order);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdOrder.getOrderId())
                        .toUri())
                .body(createdOrder);
    }
    @Operation(summary = "Atualizar um pedido existente", description = "Atualiza os dados de um pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id,
                                                     @RequestBody OrderRequest order) {
        OrderResponse updatedOrder = updateOrderInputPort.updateOrder(id, order);
        return ResponseEntity.ok(updatedOrder);
    }
    @Operation(summary = "Obter um pedido pelo ID", description = "Retorna um pedido específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        Optional<OrderResponse> orderResponse = getOrderInputPort.getOrderById(id);
        return orderResponse.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Obter todos os pedidos", description = "Retorna uma lista de todos os pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos encontrados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = getOrderInputPort.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    @Operation(summary = "Excluir um pedido", description = "Exclui um pedido pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido excluído com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        deleteOrderInputPort.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
