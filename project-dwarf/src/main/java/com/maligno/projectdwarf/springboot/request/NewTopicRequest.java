package com.maligno.projectdwarf.springboot.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewTopicRequest {

//attributes
	private String title;
	private String content;
	
}
