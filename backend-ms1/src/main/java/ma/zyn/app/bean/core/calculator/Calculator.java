package ma.zyn.app.bean.core.calculator;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "calculator")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="calculator_seq",sequenceName="calculator_seq",allocationSize=1, initialValue = 1)
public class Calculator  extends BaseEntity     {




    private BigDecimal firstNumber = BigDecimal.ZERO;

    private BigDecimal secondNumber = BigDecimal.ZERO;

    @Column(length = 500)
    private String operation;

    private BigDecimal result = BigDecimal.ZERO;



    public Calculator(){
        super();
    }

    public Calculator(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="calculator_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public BigDecimal getFirstNumber(){
        return this.firstNumber;
    }
    public void setFirstNumber(BigDecimal firstNumber){
        this.firstNumber = firstNumber;
    }
    public BigDecimal getSecondNumber(){
        return this.secondNumber;
    }
    public void setSecondNumber(BigDecimal secondNumber){
        this.secondNumber = secondNumber;
    }
    public String getOperation(){
        return this.operation;
    }
    public void setOperation(String operation){
        this.operation = operation;
    }
    public BigDecimal getResult(){
        return this.result;
    }
    public void setResult(BigDecimal result){
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculator calculator = (Calculator) o;
        return id != null && id.equals(calculator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

