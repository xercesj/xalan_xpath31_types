/*******************************************************************************
 * Copyright (c) 2023 Mukul Gandhi, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Mukul Gandhi        -    initial API and implementation
 *******************************************************************************/

package xml.xpath31.processor.types;

import org.apache.xpath.objects.ResultSequence;

/**
 * A representation of the xs:token datatype.
 */
public class XSToken extends XSNormalizedString {
	
	private static final long serialVersionUID = -8504348395152396275L;
	
	private static final String XS_TOKEN = "xs:token";

	/**
	 * Initialises using the supplied string value.
	 * 
	 * @param strVal    the string to initialise to
	 */
	public XSToken(String strVal) {
		super(strVal);
	}

	/**
	 * Initialises to null.
	 */
	public XSToken() {
		this(null);
	}

	/**
	 * Retrieves the datatype's full pathname.
	 * 
	 * @return "xs:token" which is the datatype's full pathname
	 */
	public String stringType() {
		return XS_TOKEN;
	}

	/**
	 * Retrieves the datatype's name.
	 * 
	 * @return "token" which is the datatype's name
	 */
	public String typeName() {
		return "token";
	}

	/**
	 * Creates a new ResultSequence consisting of the extractable String in the
	 * supplied ResultSequence
	 * 
	 * @param arg
	 *            The ResultSequence from which to extract the String
	 * @return New ResultSequence consisting of the supplied String
	 * @throws DynamicError
	 */
	public ResultSequence constructor(ResultSequence arg) throws javax.xml.transform.TransformerException {
		ResultSequence resultSeq = new ResultSequence();

		if (arg.size() == 0) {
		   return resultSeq;
		}

		XSAnyType xsAnyType = (XSAnyType)arg.item(0);

		String srcString = xsAnyType.stringValue();
		
		if (!isSatisfiesConstraints(srcString)) {
			throw new javax.xml.transform.TransformerException("FORG0006 : The supplied string value cannot be used to construct "
                                                                             + "xs:token value, since the input string contains prohibited "
                                                                             + "characters for a xs:token value.");	
		}
		else {
			resultSeq.add(new XSToken(srcString));
		}

		return resultSeq;
	}
	
	/*
	 * Determine whether, the supplied string value satisfies constraints for 
	 * the datatype, xs:token. 
	 */
	protected boolean isSatisfiesConstraints(String srcString) {
	   
		boolean isXsToken = true;
		
		// Satisfies constraints for the data type xs:normalizedString, and additionally 
		// must also satisfy following constraints,
		// The string must not have leading or trailing spaces and that have no internal 
		// sequences of two or more spaces.
		if (!super.isSatisfiesConstraints(srcString) || srcString.startsWith(" ") || 
				                                        srcString.endsWith(" ")   || 
				                                        srcString.indexOf("  ") != -1) {
			isXsToken = false;
		}
		
		return isXsToken;
		  
	} // isSatisfiesConstraints
	
    public int getType() {
        return CLASS_XS_TOKEN;
    }

}
