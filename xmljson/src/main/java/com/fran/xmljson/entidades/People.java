package com.fran.xmljson.entidades;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class People {

	@SerializedName("name")
	private String nombre;
	private String height;
	private List<String> films;
	private String birth_year;
	
}
