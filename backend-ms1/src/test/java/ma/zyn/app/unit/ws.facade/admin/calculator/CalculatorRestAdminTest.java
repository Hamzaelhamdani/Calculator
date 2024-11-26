package ma.zyn.app.unit.ws.facade.admin.calculator;

import ma.zyn.app.bean.core.calculator.Calculator;
import ma.zyn.app.service.impl.admin.calculator.CalculatorAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.calculator.CalculatorRestAdmin;
import ma.zyn.app.ws.converter.calculator.CalculatorConverter;
import ma.zyn.app.ws.dto.calculator.CalculatorDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculatorRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private CalculatorAdminServiceImpl service;
    @Mock
    private CalculatorConverter converter;

    @InjectMocks
    private CalculatorRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllCalculatorTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<CalculatorDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<CalculatorDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveCalculatorTest() throws Exception {
        // Mock data
        CalculatorDto requestDto = new CalculatorDto();
        Calculator entity = new Calculator();
        Calculator saved = new Calculator();
        CalculatorDto savedDto = new CalculatorDto();

        // Mock the converter to return the calculator object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved calculator DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<CalculatorDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        CalculatorDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved calculator DTO
        assertEquals(savedDto.getFirstNumber(), responseBody.getFirstNumber());
        assertEquals(savedDto.getSecondNumber(), responseBody.getSecondNumber());
        assertEquals(savedDto.getOperation(), responseBody.getOperation());
        assertEquals(savedDto.getResult(), responseBody.getResult());
    }

}
