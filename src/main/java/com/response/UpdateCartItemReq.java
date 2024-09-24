package com.response;

import lombok.Data;

@Data
public class UpdateCartItemReq {

	private Long cartItemId;
	private  int quentity;
}
