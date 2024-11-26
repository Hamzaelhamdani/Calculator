package  ma.zyn.app.dao.specification.core.calculator;

import ma.zyn.app.dao.criteria.core.calculator.CalculatorCriteria;
import ma.zyn.app.bean.core.calculator.Calculator;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CalculatorSpecification extends  AbstractSpecification<CalculatorCriteria, Calculator>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("firstNumber", criteria.getFirstNumber(), criteria.getFirstNumberMin(), criteria.getFirstNumberMax());
        addPredicateBigDecimal("secondNumber", criteria.getSecondNumber(), criteria.getSecondNumberMin(), criteria.getSecondNumberMax());
        addPredicate("operation", criteria.getOperation(),criteria.getOperationLike());
        addPredicateBigDecimal("result", criteria.getResult(), criteria.getResultMin(), criteria.getResultMax());
    }

    public CalculatorSpecification(CalculatorCriteria criteria) {
        super(criteria);
    }

    public CalculatorSpecification(CalculatorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
