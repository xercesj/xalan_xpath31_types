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
 * An XML Schema data type representation, of the xs:double datatype.
 */
public class XSDouble extends XSNumericType {

    private static final long serialVersionUID = -2666052244390163961L;

    private static final String XS_DOUBLE = "xs:double";
	
	private Double _value;
	
	private XPath3DecimalFormat xpath3DecimalFormat = new XPath3DecimalFormat(
	                                                              "0.################E0");

	/*
	 * Class constructor.
	 */
	public XSDouble(double val) {
	    _value = new Double(val);
	}

	/*
     * Class constructor.
     */
	public XSDouble() {
	    this(0);
	}

	/*
     * Class constructor.
     */
	public XSDouble(String strVal) throws javax.xml.transform.TransformerException {
		try {
			if (strVal.equals("-INF")) {
				_value = new Double(Double.NEGATIVE_INFINITY);
			} else if (strVal.equals("INF")) {
				_value = new Double(Double.POSITIVE_INFINITY);
			} else {
				_value = new Double(strVal);
			}
		} catch (NumberFormatException ex) {
			throw new javax.xml.transform.TransformerException("FORG0006 : The string value '" + 
		                                                             strVal + "' cannot be cast to xs:double."); 
		}
	}

	/**
	 * Get a XSDouble object, corresponding to the string valued 
	 * argument provided.
	 * 
	 * @param strVal   string value, to be parsed to an XSDouble object
	 * 
	 * @return      an XSDouble object, corresponding to the string
	 *              argument provided.
	 */
	public static XSDouble parseDouble(String strVal) throws javax.xml.transform.TransformerException {	    
		try {
			Double d1 = null;
			
			if (strVal.equals("INF")) {
				d1 = new Double(Double.POSITIVE_INFINITY);
			} else if (strVal.equals("-INF")) {
				d1 = new Double(Double.NEGATIVE_INFINITY);
			} else {
				d1 = new Double(strVal);
			}
			
			return new XSDouble(d1.doubleValue());			
		} catch (NumberFormatException ex) {
		    throw new javax.xml.transform.TransformerException("FORG0006 : The string value '" + 
		                                                             strVal + "' cannot be cast to xs:double.");
		}		
	}
	
	@Override
    public ResultSequence constructor(ResultSequence arg) throws 
                                                              javax.xml.transform.TransformerException {
        ResultSequence resultSeq = new ResultSequence();
        
        if (arg.size() == 0) {
           return resultSeq;     
        }
        
        XSAnyType xsAnyType = (XSAnyType)arg.item(0);
        
        XSDouble xsAnyTypeConvertedToDouble = null;
        
        if (xsAnyType instanceof XSBoolean) {
            if (xsAnyType.stringValue().equals("true")) {
                xsAnyTypeConvertedToDouble = new XSDouble(1.0E0);
            } else {
                xsAnyTypeConvertedToDouble = new XSDouble(0.0E0);
            }
        }
        else {
            xsAnyTypeConvertedToDouble = parseDouble(xsAnyType.stringValue());
        }
        
        resultSeq.add(xsAnyTypeConvertedToDouble);
        
        return resultSeq;
    }

    @Override
    public String typeName() {
        return "double";
    }

    @Override
    public String stringType() {
        return XS_DOUBLE;
    }

    @Override
    public String stringValue() {
        if (zero()) {
            return "0";
        }

        if (negativeZero()) {
            return "-0";
        }

        if (nan()) {
            return "NaN";
        }

        return xpath3DecimalFormat.performStrFormatting(_value);
    }
    
    /*
     * Check whether this XSDouble object represents -0.
     * 
     * @return    true if this XSDouble object represents -0.
     *            false otherwise.
     */
    public boolean negativeZero() {
        return (Double.compare(_value.doubleValue(), -0.0E0) == 0);
    }

    /**
     * Get the actual double primitive value, corresponding to 
     * this XSDouble object.
     */
    public double doubleValue() {
        return _value.doubleValue();
    }
    
    /**
     * Check whether this XSDouble object represents NaN.
     */
    public boolean nan() {
        return Double.isNaN(_value.doubleValue());
    }

    /**
     * Check whether this XSDouble object represents an 
     * infinite number.
     */
    public boolean infinite() {
        return Double.isInfinite(_value.doubleValue());
    }

    /**
     * Check whether this XSDouble object represents 0.
     */
    public boolean zero() {
        return (Double.compare(_value.doubleValue(), 0.0E0) == 0);
    }
    
    public boolean equals(XSDouble xsDouble) {
        return _value.equals(xsDouble.doubleValue()); 
    }
    
    public boolean lt(XSDouble xsDouble) {
        return doubleValue() < xsDouble.doubleValue(); 
    }
    
    public boolean gt(XSDouble xsDouble) {
        return doubleValue() > xsDouble.doubleValue(); 
    }
    
    public int getType() {
        return CLASS_XS_DOUBLE;
    }
    
}
