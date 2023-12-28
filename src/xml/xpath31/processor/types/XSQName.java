/*******************************************************************************
 * Copyright (c) 2023 Mukul Gandhi, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Mukul Gandhi       - initial API and implementation
 *******************************************************************************/

package xml.xpath31.processor.types;

import org.apache.xpath.objects.XObject;

/**
 * An XML Schema data type representation, of the xs:QName datatype.
 */
public class XSQName extends XObject {

	private static final long serialVersionUID = -8325816735017548359L;
	
	private String localName;
	
	private String namespaceUri;
	
	private String prefix;
	
	public XSQName(String localName, String namespaceUri, String prefix) {
	   this.localName = localName;
	   this.namespaceUri = namespaceUri;
	   this.prefix = prefix;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getNamespaceUri() {
		return namespaceUri;
	}

	public void setNamespaceUri(String namespaceUri) {
		this.namespaceUri = namespaceUri;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
    public int hashCode() {
	   // We need to build somewhat like below, sufficiently
	   // functionally unique string value to compute the corresponding
	   // hashCode value thereafter.
	   String secretStrValue = "{" + prefix + ":" + 
                                     namespaceUri + "}" + 
			                         localName;
	   
	   return secretStrValue.hashCode();
	}
	
	@Override
    public boolean equals(Object obj) {
		boolean isQNameEqual = false;
		
		if (obj instanceof XSQName) {
		   isQNameEqual = this.equals((XSQName)obj);
		}
		
		return isQNameEqual; 
	}
	
	/*
	 * Get whether, two XSQName values are equal. 
	 */
	public boolean equals(XSQName xsQName) {
		boolean isQNameEqual = true;
		
		if (!localName.equals(xsQName.getLocalName())) {
			isQNameEqual = false;	
		}
		else if (((namespaceUri == null) && (xsQName.getNamespaceUri() != null)) ||				
			      !namespaceUri.equals(xsQName.getNamespaceUri())) {
			isQNameEqual = false;
		}
		else if ((namespaceUri != null) && (xsQName.getNamespaceUri() == null)) {
			isQNameEqual = false;	
		}
		else if ((namespaceUri != null) && (xsQName.getNamespaceUri() != null) && 
				 (!namespaceUri.equals(xsQName.getNamespaceUri()))) {
			isQNameEqual = false;
		}
				
		return isQNameEqual;
	}
	
	/*
	 * Get custom string value of this object.
	 */
	public String str() {
		String strVal = "";
		
		if (namespaceUri != null) {
			strVal = '{' + namespaceUri + '}' + localName; 	
		}
		else {
			strVal = localName; 
		}
		
		return strVal;
	}

}
