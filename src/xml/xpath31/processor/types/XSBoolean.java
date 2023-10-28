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

import org.apache.xpath.objects.ResultSequence;

/**
 * An XML Schema data type representation, of the xs:boolean datatype.
 */
public class XSBoolean extends XSCtrType {

    private static final long serialVersionUID = -8635660165145453378L;

    private static final String XS_BOOLEAN = "xs:boolean";
    
    private boolean _value;
    
    /*
     * Class constructor.
    */
    public XSBoolean(boolean bool) {
        _value = bool;
    }

    /*
     * Class constructor.
    */
    public XSBoolean() {
        this(false);
    }

    @Override
    public ResultSequence constructor(ResultSequence arg) {        
        ResultSequence resultSeq = new ResultSequence();
        
        if (arg.size() == 0) {
           return resultSeq;     
        }
        
        XSAnyType xsAnyType = (XSAnyType)arg.item(0);
        String strVal = xsAnyType.stringValue();
           
        Boolean bool = null;
        if (isBooleanFalse(strVal)) {
           bool = Boolean.FALSE;    
        }
        else {
           bool = Boolean.TRUE;     
        }
           
        resultSeq.add(new XSBoolean(bool.booleanValue()));
           
        return resultSeq;        
    }

    @Override
    public String typeName() {
        return "boolean";
    }

    @Override
    public String stringType() {
        return XS_BOOLEAN;
    }

    @Override
    public String stringValue() {
        return "" + _value;
    }
    
    /**
     * Get the actual boolean value stored, within this object.
     * 
     * @return   the actual boolean value stored
     */
    public boolean value() {
        return _value;
    }
    
    public boolean equals(XSBoolean xsBoolean) {
        return _value == xsBoolean.value();  
    }
    
    public boolean lt(XSBoolean xsBoolean) {
        boolean resultVal = false;

        if (!value() && xsBoolean.value()) {
            resultVal = true;
        }
        
        return resultVal;  
    }
    
    public boolean gt(XSBoolean xsBoolean) {
        boolean resultVal = false;

        if (value() && !xsBoolean.value()) {
            resultVal = true;
        }
        
        return resultVal;  
    }
    
    public boolean bool() {
        return value();  
    }
    
    public int getType() {
        return CLASS_BOOLEAN;
    }
    
    /*
     * Check whether, a string value represents a boolean 
     * 'false' value.
     */
    private boolean isBooleanFalse(String strVal) {
        return strVal.equals("0") || strVal.equals("false") ||
                 strVal.equals("+0") || strVal.equals("-0") ||
                 strVal.equals("0.0E0") || strVal.equals("NaN");
    }

}
