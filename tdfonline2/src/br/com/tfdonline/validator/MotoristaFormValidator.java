package br.com.tfdonline.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.tfdonline.modelo.Motorista;


@Component
public class MotoristaFormValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Motorista.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Motorista motorista = (Motorista) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.motoristaForm.nome");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.motoristaForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefone", "NotEmpty.motoristaForm.telefone");
		

	}

}