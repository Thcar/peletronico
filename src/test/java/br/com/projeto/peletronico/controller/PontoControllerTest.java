package br.com.projeto.peletronico.controller;

import br.com.projeto.peletronico.domain.Funcionario;
import br.com.projeto.peletronico.domain.Ponto;
import br.com.projeto.peletronico.service.PontoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PontoController.class)
class PontoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PontoService pontoService;

    @Test
    @DisplayName("Dado um id de funcionario valido, ao bater ponto, deve retornar um json de ponto valido e status HTTP 201")
    void t001() throws Exception {

        //Setup:
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Otavio");

        Ponto ponto = new Ponto();
        ponto.setId(1L);
        ponto.setData(LocalDate.of(2022, 10, 10));
        ponto.setFuncionario(funcionario);

        Mockito.when(pontoService.baterPonto(Mockito.eq(1L))).thenReturn(ponto);

        int idFuncionario = 1;
        var request = MockMvcRequestBuilders.post("/ponto/funcionario/{idFuncionario}", idFuncionario);

        //Execute:
        var actualResult = this.mockMvc.perform(request);

        //Assert:
        actualResult
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.nomeColaborador", Matchers.is("Otavio")))
                .andExpect(status().isCreated());

    }
}