package ma.zyn.app.unit.service.impl.admin.calculator;

import ma.zyn.app.bean.core.calculator.Calculator;
import ma.zyn.app.dao.facade.core.calculator.CalculatorDao;
import ma.zyn.app.service.impl.admin.calculator.CalculatorAdminServiceImpl;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class CalculatorAdminServiceImplTest {

    @Mock
    private CalculatorDao repository;
    private AutoCloseable autoCloseable;
    private CalculatorAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CalculatorAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCalculator() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCalculator() {
        // Given
        Calculator toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCalculator() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCalculatorById() {
        // Given
        Long idToRetrieve = 1L; // Example Calculator ID to retrieve
        Calculator expected = new Calculator(); // You need to replace Calculator with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Calculator result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Calculator constructSample(int i) {
		Calculator given = new Calculator();
        given.setFirstNumber(BigDecimal.TEN);
        given.setSecondNumber(BigDecimal.TEN);
        given.setOperation("operation-"+i);
        given.setResult(BigDecimal.TEN);
        return given;
    }

}
