/*******************************************************************************
 * Copyright (c) 2005, 2010 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Andrea Bittau               - initial API and implementation
 *     Mukul Gandhi                - source code reused and adapted for use 
 *                                   with XalanJ's XPath 3.1 processor.
 *******************************************************************************/

package xml.xpath31.processor.types;

import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathCollationSupport;
import org.apache.xpath.XPathContext;
import org.apache.xpath.objects.ResultSequence;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.objects.XString;

/**
 * An XML Schema data type representation, of the xs:anyURI datatype.
 */
public class XSAnyURI extends XSCtrType {

	private static final long serialVersionUID = -237048813730375376L;
	
	private static final String XS_ANY_URI = "xs:anyURI";
	
	private String _value;
	
    private XPathContext fXctxt = new XPathContext();
    
    private XPathCollationSupport fXpathCollationSupport = fXctxt.getXPathCollationSupport();

    /*
     * Class constructor.
    */
	public XSAnyURI(String str) {
		_value = str;
	}

    /*
     * Class constructor.
    */
	public XSAnyURI() {
		this(null);
	}

	@Override
	public ResultSequence constructor(ResultSequence arg) throws TransformerException {
		ResultSequence resultSeq = new ResultSequence();
		
		if (arg.size() == 0) {
	       return resultSeq;     
	    }
		
		XSAnyType xsAnyType = (XSAnyType)arg.item(0);
        String strVal = xsAnyType.stringValue();
        
        resultSeq.add(new XSAnyURI(strVal));
		
		return resultSeq;
	}

	@Override
	public String typeName() {
		return "anyURI";
	}

	@Override
	public String stringType() {
		return XS_ANY_URI;
	}

	@Override
	public String stringValue() {
		return _value;
	}
	
    @Override
    public boolean equals(Object obj) {
       boolean isXsAnyUriEqual = false;
       
       String argStrValue = null;       
       try {
		   if ((obj instanceof XSAnyURI) || (obj instanceof XSString)) {
			   argStrValue = ((XSAnyType)obj).stringValue();
			   isXsAnyUriEqual = this.eq(new XSAnyURI(argStrValue)); 
		   }
		   else if (obj instanceof XString) {
			   argStrValue = ((XString)obj).str();
			   isXsAnyUriEqual = this.eq(new XSAnyURI(argStrValue)); 
		   }
       }
       catch (TransformerException ex) {
    	   isXsAnyUriEqual = false; 
       }
       
       return isXsAnyUriEqual;
    }
    
    @Override
    public int hashCode() {       
       String strVal = stringValue();       
       
       return strVal.hashCode();
    }
	
	/**
	 * Check equality between this xs:anyURI value and value
	 * of an argument that is passed to this method.
	 */
	public boolean eq(XObject arg) throws TransformerException {
	   boolean isEqual = false;
	   
	   String argStrValue = null;
	   if ((arg instanceof XSAnyURI) || (arg instanceof XSString)) {
		   argStrValue = ((XSAnyType)arg).stringValue();   
	   }
	   else if (arg instanceof XString) {
		   argStrValue = ((XString)arg).str();  
	   }
	   else {
		   throw new TransformerException("XPTY0004 : an xs:anyURI value can only be compared "
                                                                           + "with an xs:anyURI or a string value"); 
	   }
	   
	   int comparisonResult = fXpathCollationSupport.compareStringsUsingCollation(_value, argStrValue, 
                                                                                  fXctxt.getDefaultCollation());
	   isEqual = (comparisonResult == 0);	   

	   return isEqual;
	}
	
	/**
	 * Check less than relationship between this xs:anyURI value
	 * and value of an argument that is passed to this method.
	 */
	public boolean lt(XObject arg) throws TransformerException {
		boolean isLessThan = false;
		   
		String argStrValue = null;
		if ((arg instanceof XSAnyURI) || (arg instanceof XSString)) {
		   argStrValue = ((XSAnyType)arg).stringValue();   
		}
		else if (arg instanceof XString) {
		   argStrValue = ((XString)arg).str();  
		}
		else {
		   throw new TransformerException("XPTY0004 : an xs:anyURI value can only be compared "
	                                                                       + "with an xs:anyURI or a string value"); 
		}
		   
		int comparisonResult = fXpathCollationSupport.compareStringsUsingCollation(_value, argStrValue, 
	                                                                               fXctxt.getDefaultCollation());
		isLessThan = (comparisonResult < 0);	   

		return isLessThan;
	}
	
	/**
	 * Check greater than relationship between this xs:anyURI value
	 * and value of an argument that is passed to this method.
	 */
	public boolean gt(XObject arg) throws TransformerException {
		boolean isGreaterThan = false;
		   
		String argStrValue = null;
		if ((arg instanceof XSAnyURI) || (arg instanceof XSString)) {
		   argStrValue = ((XSAnyType)arg).stringValue();   
		}
		else if (arg instanceof XString) {
		   argStrValue = ((XString)arg).str();  
		}
		else {
		   throw new TransformerException("XPTY0004 : an xs:anyURI value can only be compared "
	                                                                       + "with an xs:anyURI or a string value"); 
		}
		   
		int comparisonResult = fXpathCollationSupport.compareStringsUsingCollation(_value, argStrValue, 
	                                                                               fXctxt.getDefaultCollation());
		isGreaterThan = (comparisonResult > 0);	   

		return isGreaterThan;
	}
	
	public int getType() {
        return CLASS_XS_ANY_URI;
    }

}
