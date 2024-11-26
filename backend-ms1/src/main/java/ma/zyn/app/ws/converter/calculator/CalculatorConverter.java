package  ma.zyn.app.ws.converter.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.calculator.Calculator;
import ma.zyn.app.ws.dto.calculator.CalculatorDto;

@Component
public class CalculatorConverter {



    public Calculator toItem(CalculatorDto dto) {
        if (dto == null) {
            return null;
        } else {
        Calculator item = new Calculator();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getFirstNumber()))
                item.setFirstNumber(dto.getFirstNumber());
            if(StringUtil.isNotEmpty(dto.getSecondNumber()))
                item.setSecondNumber(dto.getSecondNumber());
            if(StringUtil.isNotEmpty(dto.getOperation()))
                item.setOperation(dto.getOperation());
            if(StringUtil.isNotEmpty(dto.getResult()))
                item.setResult(dto.getResult());



        return item;
        }
    }


    public CalculatorDto toDto(Calculator item) {
        if (item == null) {
            return null;
        } else {
            CalculatorDto dto = new CalculatorDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getFirstNumber()))
                dto.setFirstNumber(item.getFirstNumber());
            if(StringUtil.isNotEmpty(item.getSecondNumber()))
                dto.setSecondNumber(item.getSecondNumber());
            if(StringUtil.isNotEmpty(item.getOperation()))
                dto.setOperation(item.getOperation());
            if(StringUtil.isNotEmpty(item.getResult()))
                dto.setResult(item.getResult());


        return dto;
        }
    }


	
    public List<Calculator> toItem(List<CalculatorDto> dtos) {
        List<Calculator> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CalculatorDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CalculatorDto> toDto(List<Calculator> items) {
        List<CalculatorDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Calculator item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CalculatorDto dto, Calculator t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Calculator> copy(List<CalculatorDto> dtos) {
        List<Calculator> result = new ArrayList<>();
        if (dtos != null) {
            for (CalculatorDto dto : dtos) {
                Calculator instance = new Calculator();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
