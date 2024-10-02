package com.fran.xmljson.entidades;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class People {

	private String name;
	private String height;
	private List<String> films;
	private String birth_year;
	
}
