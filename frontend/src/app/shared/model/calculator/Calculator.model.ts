
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class CalculatorDto extends BaseDto{

    public firstNumber: null | number;

    public secondNumber: null | number;

    public operation: string;

    public result: null | number;



    constructor() {
        super();

        this.firstNumber = null;
        this.secondNumber = null;
        this.operation = '';
        this.result = null;

        }

}
