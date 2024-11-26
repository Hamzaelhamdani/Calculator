
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class CalculatorCriteria extends BaseCriteria {

    public id: number;
     public firstNumber: number;
     public firstNumberMin: number;
     public firstNumberMax: number;
     public secondNumber: number;
     public secondNumberMin: number;
     public secondNumberMax: number;
    public operation: string;
    public operationLike: string;
     public result: number;
     public resultMin: number;
     public resultMax: number;

}
