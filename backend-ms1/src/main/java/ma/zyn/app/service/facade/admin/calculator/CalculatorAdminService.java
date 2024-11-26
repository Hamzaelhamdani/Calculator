package ma.zyn.app.service.facade.admin.calculator;

import java.util.List;
import ma.zyn.app.bean.core.calculator.Calculator;
import ma.zyn.app.dao.criteria.core.calculator.CalculatorCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CalculatorAdminService {







	Calculator create(Calculator t);

    Calculator update(Calculator t);

    List<Calculator> update(List<Calculator> ts,boolean createIfNotExist);

    Calculator findById(Long id);

    Calculator findOrSave(Calculator t);

    Calculator findByReferenceEntity(Calculator t);

    Calculator findWithAssociatedLists(Long id);

    List<Calculator> findAllOptimized();

    List<Calculator> findAll();

    List<Calculator> findByCriteria(CalculatorCriteria criteria);

    List<Calculator> findPaginatedByCriteria(CalculatorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CalculatorCriteria criteria);

    List<Calculator> delete(List<Calculator> ts);

    boolean deleteById(Long id);

    List<List<Calculator>> getToBeSavedAndToBeDeleted(List<Calculator> oldList, List<Calculator> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
