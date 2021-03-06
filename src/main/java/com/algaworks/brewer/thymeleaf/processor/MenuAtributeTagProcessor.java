package com.algaworks.brewer.thymeleaf.processor;

import javax.servlet.http.HttpServletRequest;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.util.FieldUtils;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

public class MenuAtributeTagProcessor extends AbstractAttributeTagProcessor {
	
	private static final String NOME_ATRIBUTO = "menu";
	private static final int PROCEDENCIA = 1000;
	
	public MenuAtributeTagProcessor(String dialectPrefix){
		super(TemplateMode.HTML, dialectPrefix, null, false, NOME_ATRIBUTO, true, PROCEDENCIA, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue,
			IElementTagStructureHandler structureHandler) {
		
		//configuração para converter @{/cidades} para brewer/cidades
		IEngineConfiguration configuration = context.getConfiguration();
		IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		IStandardExpression expression = parser.parseExpression(context, attributeValue);
        String menu = (String) expression.execute(context);

		HttpServletRequest request = ((IWebContext) context).getRequest();
		String uri = request.getRequestURI();

		if(uri.startsWith(menu)){
			String classesExistentes = tag.getAttributeValue("class");
		    structureHandler.setAttribute("class", classesExistentes + " is-active");
		}
		
	}
}
