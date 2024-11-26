package  ma.zyn.app.ws.facade.admin.calculator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.calculator.Calculator;
import ma.zyn.app.dao.criteria.core.calculator.CalculatorCriteria;
import ma.zyn.app.service.facade.admin.calculator.CalculatorAdminService;
import ma.zyn.app.ws.converter.calculator.CalculatorConverter;
import ma.zyn.app.ws.dto.calculator.CalculatorDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/calculator/")
public class CalculatorRestAdmin {




    @Operation(summary = "Finds a list of all calculators")
    @GetMapping("")
    public ResponseEntity<List<CalculatorDto>> findAll() throws Exception {
        ResponseEntity<List<CalculatorDto>> res = null;
        List<Calculator> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CalculatorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a calculator by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CalculatorDto> findById(@PathVariable Long id) {
        Calculator t = service.findById(id);
        if (t != null) {
            CalculatorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  calculator")
    @PostMapping("")
    public ResponseEntity<CalculatorDto> save(@RequestBody CalculatorDto dto) throws Exception {
        if(dto!=null){
            Calculator myT = converter.toItem(dto);
            Calculator t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CalculatorDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  calculator")
    @PutMapping("")
    public ResponseEntity<CalculatorDto> update(@RequestBody CalculatorDto dto) throws Exception {
        ResponseEntity<CalculatorDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Calculator t = service.findById(dto.getId());
            converter.copy(dto,t);
            Calculator updated = service.update(t);
            CalculatorDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of calculator")
    @PostMapping("multiple")
    public ResponseEntity<List<CalculatorDto>> delete(@RequestBody List<CalculatorDto> dtos) throws Exception {
        ResponseEntity<List<CalculatorDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Calculator> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified calculator")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a calculator and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CalculatorDto> findWithAssociatedLists(@PathVariable Long id) {
        Calculator loaded =  service.findWithAssociatedLists(id);
        CalculatorDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds calculators by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CalculatorDto>> findByCriteria(@RequestBody CalculatorCriteria criteria) throws Exception {
        ResponseEntity<List<CalculatorDto>> res = null;
        List<Calculator> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CalculatorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated calculators by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CalculatorCriteria criteria) throws Exception {
        List<Calculator> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CalculatorDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets calculator data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CalculatorCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CalculatorDto> findDtos(List<Calculator> list){
        List<CalculatorDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CalculatorDto> getDtoResponseEntity(CalculatorDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CalculatorRestAdmin(CalculatorAdminService service, CalculatorConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CalculatorAdminService service;
    private final CalculatorConverter converter;





}
