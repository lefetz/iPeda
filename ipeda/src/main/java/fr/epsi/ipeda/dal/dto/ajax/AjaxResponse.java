package fr.epsi.ipeda.dal.dto.ajax;

import java.util.LinkedList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class AjaxResponse {

	public static enum STATUS {
		SUCCESS, ERROR;
	}

	private STATUS status;

	private List<CustomError> listError;

	private String returnUrl;

	public AjaxResponse(STATUS status) {
		setStatus(status);
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public List<CustomError> getListError() {
		return listError;
	}

	public void setListError(BindingResult bindingResult) {
		listError = new LinkedList<CustomError>();
		for (FieldError error : bindingResult.getFieldErrors()) {
			CustomError customError = new CustomError();
			customError.setDefaultMessage(error.getDefaultMessage());
			customError.setField(error.getField());
			customError.setObjectName(error.getObjectName());
			listError.add(customError);
		}
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

}
