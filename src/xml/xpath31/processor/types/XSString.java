/*******************************************************************************
 * Copyright (c) 2005, 2009 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Andrea Bittau                    - initial API and implementation
 *     Mukul Gandhi <mukulg@apache.org> - source code reused and adapted for use 
 *                                        with XalanJ's XPath 3.1 processor.
 *******************************************************************************/

package xml.xpath31.processor.types;

import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathCollationSupport;
import org.apache.xpath.XPathContext;
import org.apache.xpath.objects.ResultSequence;

/**
 * An XML Schema data type representation, of the xs:string datatype.
 */
public class XSString extends XSCtrType {

    private static final long serialVersionUID = -7351932310979358488L;

    private static final String XS_STRING = "xs:string";
    
    private String _value;
    
    private XPathContext fXctxt = new XPathContext();
    
    private XPathCollationSupport fXpathCollationSupport = fXctxt.getXPathCollationSupport();
    
    /*
     * Class constructor.
    */
    public XSString(String str) {
       _value = str;       
    }

    /*
     * Class constructor.
    */
    public XSString() {
       this(null);
    }

    @Override
    public ResultSequence constructor(ResultSequence arg) {        
        ResultSequence resultSeq = new ResultSequence();
        
        if (arg.size() == 0) {
           return resultSeq;     
        }
        
        XSAnyType xsAnyType = (XSAnyType)arg.item(0);
        
        resultSeq.add(new XSString(xsAnyType.stringValue()));
           
        return resultSeq;        
    }

    @Override
    public String typeName() {
        return "string";
    }

    @Override
    public String stringType() {
        return XS_STRING;
    }

    @Override
    public String stringValue() {
        return _value;
    }
    
    /**
     * Get the actual string value stored, within this object.
     * 
     * @return   the actual string value stored
     */
    public String value() {
        return stringValue();
    }
    
    /**
     * This function implements the semantics of XPath 3.1 'eq' operator,
     * on xs:string values.
     */
    public boolean equals(XSString xsStr) throws TransformerException {
        int comparisonResult = fXpathCollationSupport.compareStringsUsingCollation(_value, xsStr.stringValue(), 
                                                                                                       fXctxt.getDefaultCollation());
        return (comparisonResult == 0); 
    }
    
    /**
     * This function implements the semantics of XPath 3.1 'lt' operator,
     * on xs:string values.
     */
    public boolean lt(XSString xsStr) throws TransformerException {
        int comparisonResult = fXpathCollationSupport.compareStringsUsingCollation(_value, xsStr.stringValue(), 
                                                                                                       fXctxt.getDefaultCollation());
        return (comparisonResult < 0);  
    }
    
    /**
     * This function implements the semantics of XPath 3.1 'gt' operator,
     * on xs:string values.
     */
    public boolean gt(XSString xsStr) throws TransformerException {
        int comparisonResult = fXpathCollationSupport.compareStringsUsingCollation(_value, xsStr.stringValue(), 
                                                                                                       fXctxt.getDefaultCollation());
        return (comparisonResult > 0);  
    }
    
    public int getType() {
        return CLASS_STRING;
    }

}
