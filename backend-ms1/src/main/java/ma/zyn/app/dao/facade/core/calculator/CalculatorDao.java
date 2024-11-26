package ma.zyn.app.dao.facade.core.calculator;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.calculator.Calculator;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CalculatorDao extends AbstractRepository<Calculator,Long>  {



}
