package com.bootcamp.controllers;

import com.bootcamp.dto.UFDTO;
import com.bootcamp.exceptions.DesafioException;
import com.bootcamp.exceptions.ErrorResponseDesafio;
import com.bootcamp.services.UFService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UFControllerTest {

    private UFService ufService;
    private UFController ufController;

    @BeforeEach
    void setUp() {
        ufService = mock(UFService.class);
        ufController = new UFController(ufService);
    }

    @Test
    void getAllUF_ReturnsUF_WhenCodigoUFIsProvided() throws DesafioException {
        UFDTO ufDTO = new UFDTO();
        when(ufService.getFindById(1L)).thenReturn(ufDTO);

        ResponseEntity<?> response = ufController.getAllUF(1L, null, null, null);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ufDTO, response.getBody());
    }

    @Test
    void getAllUF_ReturnsListOfUF_WhenNoCodigoUFIsProvided() throws DesafioException {
        List<UFDTO> ufList = Arrays.asList(new UFDTO(), new UFDTO());
        when(ufService.getAll(null, null, null, null)).thenReturn(ufList);

        ResponseEntity<?> response = ufController.getAllUF(null, null, null, null);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ufList, response.getBody());
    }

    @Test
    void getAllUF_ReturnsErrorResponse_WhenDesafioExceptionIsThrown() throws DesafioException {
        when(ufService.getFindById(1L)).thenThrow(new DesafioException("Error"));

        ResponseEntity<?> response = ufController.getAllUF(1L, null, null, null);

        assertEquals(404, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ErrorResponseDesafio);
    }

    @Test
    void createUF_ReturnsCreatedUF_WhenValidUFDTOIsProvided() throws DesafioException {
        UFDTO ufDTO = new UFDTO();
        List<UFDTO> ufList = Arrays.asList(ufDTO);
        when(ufService.create(any(UFDTO.class))).thenReturn(ufList);

        ResponseEntity<?> response = ufController.createUF(ufDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ufList, response.getBody());
    }

    @Test
    void createUF_ReturnsErrorResponse_WhenDesafioExceptionIsThrown() throws DesafioException {
        UFDTO ufDTO = new UFDTO();
        when(ufService.create(any(UFDTO.class))).thenThrow(new DesafioException("Error"));

        ResponseEntity<?> response = ufController.createUF(ufDTO);

        assertEquals(404, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ErrorResponseDesafio);
    }

    @Test
    void updateUF_ReturnsUpdatedUF_WhenValidUFDTOIsProvided() throws DesafioException {
        UFDTO ufDTO = new UFDTO();
        List<UFDTO> ufList = Arrays.asList(ufDTO);
        when(ufService.update(any(UFDTO.class))).thenReturn(ufList);

        ResponseEntity<?> response = ufController.updateUF(ufDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ufList, response.getBody());
    }

    @Test
    void atualizacaoUF_RetornaErrosResposta_QuandoDesafioExceptionIsThrown() throws DesafioException {
        UFDTO ufDTO = new UFDTO();
        when(ufService.update(any(UFDTO.class))).thenThrow(new DesafioException("Error"));

        ResponseEntity<?> response = ufController.updateUF(ufDTO);

        assertEquals(404, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ErrorResponseDesafio);
    }
}
