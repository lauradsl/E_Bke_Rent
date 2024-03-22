package com.ebikerrent.alquilerbicicletas.controller;

import com.ebikerrent.alquilerbicicletas.dto.entrada.reserva.ReservaEntradaDto;
import com.ebikerrent.alquilerbicicletas.dto.salida.reserva.ReservaSalidaDto;
import com.ebikerrent.alquilerbicicletas.exceptions.ResourceNotFoundException;
import com.ebikerrent.alquilerbicicletas.service.IReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reservas")
public class ReservaController {
    private final IReservaService iReservaService;

    public ReservaController(IReservaService iReservaService) {
        this.iReservaService = iReservaService;
    }

    @Operation(summary = "Registro de una nueva reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva guardada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<ReservaSalidaDto> registrarReserva(@Valid @RequestBody ReservaEntradaDto reservaEntradaDto) throws ResourceNotFoundException, ResourceNotFoundException {
        return new ResponseEntity<>(iReservaService.registrarReserva(reservaEntradaDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Listado de todas las reservas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de reservas obtenida correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/listar")
    public ResponseEntity<List<ReservaSalidaDto>> listarTodasLasReservas() {
        return new ResponseEntity<>(iReservaService.listarReservas(), HttpStatus.OK);
    }

    @Operation(summary = "Búsqueda de reserva por producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Búsqueda de reserva por producto",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReservaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/buscarReservaPorProducto")
    public ResponseEntity<Boolean> buscarReservaPorProducto(@Valid @RequestBody ReservaEntradaDto reservaEntradaDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(iReservaService.buscarReservaPorProducto(reservaEntradaDto), HttpStatus.OK);
    }
}
