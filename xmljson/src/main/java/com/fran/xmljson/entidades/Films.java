package com.fran.xmljson.entidades;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Films {
	
	private String title;
	private long episode_id;
	private String opening_crawl;
	private List<String> characters;

}
