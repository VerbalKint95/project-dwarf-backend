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
public class CommentListResponse {

//attributes
	private List<CommentResponse> commentList;
}
