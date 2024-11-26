package  ma.zyn.app.dao.criteria.core.calculator;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class CalculatorCriteria extends  BaseCriteria  {

    private String firstNumber;
    private String firstNumberMin;
    private String firstNumberMax;
    private String secondNumber;
    private String secondNumberMin;
    private String secondNumberMax;
    private String operation;
    private String operationLike;
    private String result;
    private String resultMin;
    private String resultMax;



    public String getFirstNumber(){
        return this.firstNumber;
    }
    public void setFirstNumber(String firstNumber){
        this.firstNumber = firstNumber;
    }   
    public String getFirstNumberMin(){
        return this.firstNumberMin;
    }
    public void setFirstNumberMin(String firstNumberMin){
        this.firstNumberMin = firstNumberMin;
    }
    public String getFirstNumberMax(){
        return this.firstNumberMax;
    }
    public void setFirstNumberMax(String firstNumberMax){
        this.firstNumberMax = firstNumberMax;
    }
      
    public String getSecondNumber(){
        return this.secondNumber;
    }
    public void setSecondNumber(String secondNumber){
        this.secondNumber = secondNumber;
    }   
    public String getSecondNumberMin(){
        return this.secondNumberMin;
    }
    public void setSecondNumberMin(String secondNumberMin){
        this.secondNumberMin = secondNumberMin;
    }
    public String getSecondNumberMax(){
        return this.secondNumberMax;
    }
    public void setSecondNumberMax(String secondNumberMax){
        this.secondNumberMax = secondNumberMax;
    }
      
    public String getOperation(){
        return this.operation;
    }
    public void setOperation(String operation){
        this.operation = operation;
    }
    public String getOperationLike(){
        return this.operationLike;
    }
    public void setOperationLike(String operationLike){
        this.operationLike = operationLike;
    }

    public String getResult(){
        return this.result;
    }
    public void setResult(String result){
        this.result = result;
    }   
    public String getResultMin(){
        return this.resultMin;
    }
    public void setResultMin(String resultMin){
        this.resultMin = resultMin;
    }
    public String getResultMax(){
        return this.resultMax;
    }
    public void setResultMax(String resultMax){
        this.resultMax = resultMax;
    }
      

}
