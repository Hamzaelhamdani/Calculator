package  ma.zyn.app.ws.dto.calculator;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalculatorDto  extends AuditBaseDto {

    private BigDecimal firstNumber  ;
    private BigDecimal secondNumber  ;
    private String operation  ;
    private BigDecimal result  ;




    public CalculatorDto(){
        super();
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








}
