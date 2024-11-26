package ma.zyn.app.service.impl.admin.calculator;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.calculator.Calculator;
import ma.zyn.app.dao.criteria.core.calculator.CalculatorCriteria;
import ma.zyn.app.dao.facade.core.calculator.CalculatorDao;
import ma.zyn.app.dao.specification.core.calculator.CalculatorSpecification;
import ma.zyn.app.service.facade.admin.calculator.CalculatorAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class CalculatorAdminServiceImpl implements CalculatorAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Calculator update(Calculator t) {
        Calculator loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Calculator.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Calculator findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Calculator findOrSave(Calculator t) {
        if (t != null) {
            Calculator result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Calculator> findAll() {
        return dao.findAll();
    }

    public List<Calculator> findByCriteria(CalculatorCriteria criteria) {
        List<Calculator> content = null;
        if (criteria != null) {
            CalculatorSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CalculatorSpecification constructSpecification(CalculatorCriteria criteria) {
        CalculatorSpecification mySpecification =  (CalculatorSpecification) RefelexivityUtil.constructObjectUsingOneParam(CalculatorSpecification.class, criteria);
        return mySpecification;
    }

    public List<Calculator> findPaginatedByCriteria(CalculatorCriteria criteria, int page, int pageSize, String order, String sortField) {
        CalculatorSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CalculatorCriteria criteria) {
        CalculatorSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Calculator> delete(List<Calculator> list) {
		List<Calculator> result = new ArrayList();
        if (list != null) {
            for (Calculator t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Calculator create(Calculator t) {
        Calculator loaded = findByReferenceEntity(t);
        Calculator saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Calculator findWithAssociatedLists(Long id){
        Calculator result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Calculator> update(List<Calculator> ts, boolean createIfNotExist) {
        List<Calculator> result = new ArrayList<>();
        if (ts != null) {
            for (Calculator t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Calculator loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Calculator t, Calculator loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Calculator findByReferenceEntity(Calculator t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<Calculator> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Calculator>> getToBeSavedAndToBeDeleted(List<Calculator> oldList, List<Calculator> newList) {
        List<List<Calculator>> result = new ArrayList<>();
        List<Calculator> resultDelete = new ArrayList<>();
        List<Calculator> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<Calculator> oldList, List<Calculator> newList, List<Calculator> resultUpdateOrSave, List<Calculator> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Calculator myOld = oldList.get(i);
                Calculator t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Calculator myNew = newList.get(i);
                Calculator t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public CalculatorAdminServiceImpl(CalculatorDao dao) {
        this.dao = dao;
    }

    private CalculatorDao dao;
}
