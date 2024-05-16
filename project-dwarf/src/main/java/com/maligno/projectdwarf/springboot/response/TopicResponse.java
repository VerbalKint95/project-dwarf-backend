package com.maligno.projectdwarf.springboot.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponse {
	
//attributes
	private long id;
	private String title;
	private String content;
	private long userId;
	private LocalDate creationDate;

}
