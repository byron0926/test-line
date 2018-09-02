/**
 * 
 */
package com.byron.line.common.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Administrator
 *
 * Administrator2018年5月16日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	private int status;
	private Object obj;

	
}
