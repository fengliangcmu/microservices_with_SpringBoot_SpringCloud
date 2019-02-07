package com.example.demo.filters;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public FilteringBean retrieveSomeBean() {
		
		FilteringBean bean = new FilteringBean("value1", "value2","value3","value4");
		return bean;
	}
	
	//dynamic filtering
	@GetMapping("/filteringDynamic")
	public MappingJacksonValue retrieveSomeBeanDynamic() {
		
		FilteringBean bean = new FilteringBean("value1", "value2","value3", "value4");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		
		//following FilteringBeanFilter has to be annotated for the class of FilteringBean to make it work.
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilteringBeanFilter",filter);
		MappingJacksonValue mapping = new MappingJacksonValue(bean); // here it's a bean. but list of beans also works.
		mapping.setFilters(filters);
		return mapping;
	}

}
