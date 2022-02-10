package br.com.app.controleasy.api.v1.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	@ApiModelProperty(position = 1)
	private Integer status;
	@ApiModelProperty(position = 2)
	private OffsetDateTime timestamp;
	@ApiModelProperty(position = 3)
	private String type;
	@ApiModelProperty(position = 4)
	private String title;
	@ApiModelProperty(position = 5)
	private String detail;
	@ApiModelProperty(position = 6)
	private String userMessage;
	@ApiModelProperty(position = 7)
	private List<Object> objects;

	@ApiModel("ObjetoProblema")
	@Getter
	@Builder
	public static class Object {

		private String name;
		private String userMessage;

	}

}
