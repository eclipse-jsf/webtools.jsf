package org.eclipse.jst.pagedesigner.preview;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.w3c.dom.Element;

/**
 * Default implementation of IELValueResolver, used when no contributor-provided resolver has
 * managed to resolve the EL expression.
 * 
 * @author ian.trimble@oracle.com
 */
public class DefaultELValueResolver implements IELValueResolver {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.preview.IELValueResolver#resolve(org.w3c.dom.Element, java.lang.String)
	 */
	public String resolve(Element element, String elExpression) {
		String ret = elExpression;
		if (elExpression != null) {
			List<VariablePropertyPair> possibleVars = getPossiblePageVariables(elExpression);
			for (VariablePropertyPair pageVar: possibleVars) {
				Object objPageVar = PageExpressionContext.getCurrent().getPageVariable(pageVar.getVariableName());
				if (objPageVar instanceof Properties) {
					String resolvedValue = ((Properties)objPageVar).getProperty(pageVar.getPropertyName());
					if (resolvedValue != null) {
						ret = resolvedValue;
						break;
					}
				}
			}
		}
		return ret;
	}

	private List<VariablePropertyPair> getPossiblePageVariables(String elExpression) {
		List<VariablePropertyPair> possibleVars = new ArrayList<VariablePropertyPair>();
		if (
				elExpression != null &&
				(elExpression.startsWith("#{") || elExpression.startsWith("${")) //$NON-NLS-1$ //$NON-NLS-2$
				&& elExpression.endsWith("}")) { //$NON-NLS-1$
			String exp = elExpression.substring(2, elExpression.length() - 1);
			int dotPos = exp.indexOf('.');
			while (dotPos > -1 && dotPos < exp.length() - 1) {
				String var = exp.substring(0, dotPos);
				String prop = exp.substring(dotPos + 1);
				possibleVars.add(new VariablePropertyPair(var, prop));
				dotPos = exp.indexOf('.', dotPos + 1);
			}
		}
		return possibleVars;
	}



	class VariablePropertyPair {
		private String variableName;
		private String propertyName;

		/**
		 * Create an instance.
		 * 
		 * @param variableName Variable name.
		 * @param propertyName Property name.
		 */
		public VariablePropertyPair(String variableName, String propertyName) {
			this.variableName = variableName;
			this.propertyName = propertyName;
		}

		/**
		 * Get variable name.
		 * @return Variable name.
		 */
		public String getVariableName() {
			return variableName;
		}

		/**
		 * Get property name.
		 * @return Property name.
		 */
		public String getPropertyName() {
			return propertyName;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return "VariablePropertyPair[" + variableName + ", " + propertyName + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}

	}

}
