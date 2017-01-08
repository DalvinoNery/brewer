package com.algaworks.brewer.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.algaworks.brewer.validation.AtributoConfirmacao;

public class AtributoConfirmacaoValidator implements ConstraintValidator<AtributoConfirmacao, Object>{

	private String atributo;
	private String atributoConfirmacao;
	
	@Override
	public void initialize(AtributoConfirmacao contraintAnnotation) {
		this.atributo = contraintAnnotation.atributo();
		this.atributoConfirmacao= contraintAnnotation.atributoConfirmacao();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		boolean valido = false;
		try{
		Object valorAtributo = BeanUtils.getProperty(object, this.atributo);
		Object valorAtributoConfirmcao = BeanUtils.getProperty(object, this.atributoConfirmacao);
		
		valido = ambosSaoNull(valorAtributo, valorAtributoConfirmcao) || ambosSaoIguais(valorAtributo, valorAtributoConfirmcao);
		
		}catch (Exception e) {
			throw new RuntimeException("Erro ao recuperar valores dos atributos de confirmção.");
		}
		//Caso senha e confirmação da senha sejão diferentes, será retornado para view que a confirmação está errada.
		if(!valido){
			context.disableDefaultConstraintViolation();//não mandar mensagem duplicada, porque a validação são de dois campos (Senha e Confirmação de senha)
			String mensagem = context.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder	violationBuilder = context.buildConstraintViolationWithTemplate(mensagem);
			violationBuilder.addPropertyNode(atributoConfirmacao).addConstraintViolation();
		}
		
		return valido;
	}

	private boolean ambosSaoIguais(Object valorAtributo, Object valorAtributoConfirmcao) {
		
		return valorAtributo != null && valorAtributo.equals(valorAtributoConfirmcao);
	}

	private boolean ambosSaoNull(Object valorAtributo, Object valorAtributoConfirmcao) {
		
		return valorAtributo == null && valorAtributoConfirmcao == null;
	}

}
