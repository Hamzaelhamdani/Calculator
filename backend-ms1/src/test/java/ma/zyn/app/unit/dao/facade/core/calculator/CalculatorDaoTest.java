package ma.zyn.app.unit.dao.facade.core.calculator;

import ma.zyn.app.bean.core.calculator.Calculator;
import ma.zyn.app.dao.facade.core.calculator.CalculatorDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CalculatorDaoTest {

@Autowired
    private CalculatorDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Calculator entity = new Calculator();
        entity.setId(id);
        underTest.save(entity);
        Calculator loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Calculator entity = new Calculator();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Calculator loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Calculator> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Calculator> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Calculator given = constructSample(1);
        Calculator saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
