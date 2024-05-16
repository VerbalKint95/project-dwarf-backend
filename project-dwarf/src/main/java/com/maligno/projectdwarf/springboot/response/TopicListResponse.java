package com.maligno.projectdwarf.springboot.response;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicListResponse {
	
//attributes
	private List<TopicResponse> topicResponses;

}
