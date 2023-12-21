/*******************************************************************************
 * Copyright (c) 2005, 2009 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Andrea Bittau                    - initial API and implementation
 *     Mukul Gandhi                     - source code reused and adapted for use 
 *                                        with XalanJ's XPath 3.1 processor.
 *******************************************************************************/

package xml.xpath31.processor.types;

import java.math.BigDecimal;

import javax.xml.transform.TransformerException;

import org.apache.xpath.objects.ResultSequence;

/**
 * An XML Schema data type representation, of the xs:dayTimeDuration 
 * datatype.
 */
public class XSDayTimeDuration extends XSDuration {

    private static final long serialVersionUID = 4194060959383397526L;
    
    private static final String XS_DAY_TIME_DURATION = "xs:dayTimeDuration";

	/**
	 * Initializes an XSDayTimeDuration object, with the supplied parameters. If more than 24 
	 * hours is supplied, the number of days is adjusted accordingly. The same occurs for
	 * minutes and seconds.
	 * 
	 * @param days       number of days in this duration of time
	 * @param hours      number of hours in this duration of time
	 * @param minutes    number of minutes in this duration of time
	 * @param seconds    number of seconds in this duration of time
	 * @param negative   true if this duration of time represents a backwards passage
	 *                   through time. false otherwise.
	 */
	public XSDayTimeDuration(int days, int hours, int minutes, double seconds, 
	                                                               boolean negative) {
		super(0, 0, days, hours, minutes, seconds, negative);
	}

	/**
	 * Initializes an XSDayTimeDuration object, to the given number of seconds.
	 * 
	 * @param secs    number of seconds in the duration of time
	 */
	public XSDayTimeDuration(double secs) {
		super(0, 0, 0, 0, 0, Math.abs(secs), secs < 0);
	}

	/**
	 * Initializes an XSDayTimeDuration object, to a duration of no time 
	 * (i.e, 0 days, 0 hours, 0 minutes, 0 seconds).
	 */
	public XSDayTimeDuration() {
		super(0, 0, 0, 0, 0, 0.0, false);
	}

	/**
	 * A method to construct an xdm sequence comprising a
	 * xs:dayTimeDuration value, given input data as argument
	 * to this method.
	 * 
	 * @throws TransformerException 
	 */
	public ResultSequence constructor(ResultSequence arg) throws TransformerException {
        ResultSequence resultSeq = new ResultSequence();
        
        if (arg.size() == 0) {
           return resultSeq;     
        }
        
        XSAnyType xsAnyType = (XSAnyType)arg.item(0);
        
        XSDuration xsDuration = castToDayTimeDuration(xsAnyType);
        
        resultSeq.add(xsDuration);

        return resultSeq;	
	}

	
	/**
	 * Creates a new XSDuration object, by parsing the supplied String
	 * representation of XSDuration.
	 * 
	 * @param    strVal      String representation of XSDuration value
	 * 
	 * @return               new XSDuration object, representing the 
	 *                       supplied string.
	 */
	public static XSDuration parseDayTimeDuration(String strVal) throws TransformerException {
		
	    boolean isDurationNegative = false;
		
		int days = 0;
		int hours = 0;
		int minutes = 0;
		double seconds = 0;

		String pstr = null;
		String tstr = null;

		if (strVal.startsWith("-P")) {
			isDurationNegative = true;
			pstr = strVal.substring(2, strVal.length());
		} else if (strVal.startsWith("P")) {
			isDurationNegative = false;
			pstr = strVal.substring(1, strVal.length());
		} else {
		    throw new TransformerException("XTTE0570 : The supplied string value '" + strVal + "' "
                                                                                          + "cannot be parsed to a xs:dayTimeDuration value.");
		}

		try {
			int index = pstr.indexOf('D');
			boolean actionStatus = false;

			if (index == -1) {
				if (pstr.startsWith("T")) {
					tstr = pstr.substring(1, pstr.length());
				} else {
				    throw new TransformerException("XTTE0570 : The supplied string value '" + strVal + "' "
                                                                                                  + "cannot be parsed to a xs:dayTimeDuration value.");
				}
			} else {
				String digit = pstr.substring(0, index);
				days = Integer.parseInt(digit);
				tstr = pstr.substring(index + 1, pstr.length());

				if (tstr.startsWith("T")) {
					tstr = tstr.substring(1, tstr.length());
				} else {
					if (tstr.length() > 0) {
					   throw new TransformerException("XTTE0570 : The supplied string value '" + strVal + "' "
                                                                                                     + "cannot be parsed to a xs:dayTimeDuration value.");
					}
					tstr = "";
					actionStatus = true;
				}
			}

			index = tstr.indexOf('H');
			if (index != -1) {
				String digit = tstr.substring(0, index);
				hours = Integer.parseInt(digit);
				tstr = tstr.substring(index + 1, tstr.length());
				actionStatus = true;
			}

			index = tstr.indexOf('M');
			if (index != -1) {
				String digit = tstr.substring(0, index);
				minutes = Integer.parseInt(digit);
				tstr = tstr.substring(index + 1, tstr.length());
				actionStatus = true;
			}

			index = tstr.indexOf('S');
			if (index != -1) {
				String digit = tstr.substring(0, index);
				seconds = Double.parseDouble(digit);
				tstr = tstr.substring(index + 1, tstr.length());
				actionStatus = true;
			}
			if (actionStatus) {
				if (tstr.length() != 0) {
				    throw new TransformerException("XTTE0570 : The supplied string value '" + strVal + "' "
                                                                                                  + "cannot be parsed to a xs:dayTimeDuration value.");
				}
			} else {
			    throw new TransformerException("XTTE0570 : The supplied string value '" + strVal + "' "
                                                                                                     + "cannot be parsed to a xs:dayTimeDuration value.");
			}
		} 
		catch (TransformerException ex) {
	        throw ex;  
	    }
		catch (Exception ex) {
		    throw new TransformerException("XTTE0570 : The supplied string value '" + strVal + "' "
                                                                                          + "cannot be parsed to a xs:dayTimeDuration value.");
		}

		return new XSDayTimeDuration(days, hours, minutes, seconds, isDurationNegative);
	}

	/**
	 * Get the datatype's name.
	 * 
	 * @return   'dayTimeDuration', which is this datatype's name
	 */
	public String typeName() {
		return "dayTimeDuration";
	}

	/**
	 * Get the datatype's name.
	 * 
	 * @return  'xs:dayTimeDuration', which is this datatype's name
	 */
	public String stringType() {
		return XS_DAY_TIME_DURATION;
	}
	
	/**
	 * Method to add an XSDayTimeDuration value, to this 
	 * XSDayTimeDuration value.
	 */
	public XSDayTimeDuration add(XSDayTimeDuration xsDayTimeDuration) {       
        double sum = value() + xsDayTimeDuration.value();

        return new XSDayTimeDuration(sum);
    }
	
    /**
     * Method to subtract an XSDayTimeDuration value, from this 
     * XSDayTimeDuration value.
     */
    public XSDayTimeDuration subtract(XSDayTimeDuration xsDayTimeDuration) {       
        double diff = value() - xsDayTimeDuration.value();

        return new XSDayTimeDuration(diff);
    }
    
    /**
     * Method to multiply an XSDayTimeDuration value represented by this
     * object, with a numeric value represented by an argument passed to
     * this method.
     * 
     * @throws TransformerException 
     */
    public XSDayTimeDuration mult(XSAnyType xsAnyType) throws TransformerException {
        
        XSDayTimeDuration result = null;
        
        if (xsAnyType instanceof XSNumericType) {
           String argStrVal = ((XSNumericType)xsAnyType).stringValue();
           XSDouble argDoubleVal = new XSDouble(argStrVal);
           if (argDoubleVal.nan()) {
              throw new TransformerException("FOCA0005 : Cannot multiply an XSDayTimeDuration value with NaN.");  
           }
           else {
              result = new XSDayTimeDuration(value() * argDoubleVal.doubleValue()); 
           }
        }
        else {
           throw new TransformerException("FOCA0005 : Cannot multiply an XSDayTimeDuration value with a "
                                                                                                   + "non-numeric value"); 
        }
        
        return result;
    }
    
    /**
     * Method to divide this XSDayTimeDuration value, by a value (that needs to be
     * either a numeric value or a XSDayTimeDuration value) that is passed as an
     * argument to this method.
     * 
     * @throws TransformerException 
     */
    public XSDayTimeDuration div(XSAnyType xsAnyType) throws TransformerException {
        
        XSDayTimeDuration result = null;
        
        if (xsAnyType instanceof XSNumericType) {
           String argStrVal = ((XSNumericType)xsAnyType).stringValue();
           XSDouble argDoubleVal = new XSDouble(argStrVal);
           if (argDoubleVal.nan()) {
              throw new TransformerException("FOCA0005 : Cannot divide an XSDayTimeDuration value with NaN.");  
           }
           else if (argDoubleVal.zero()) {
              throw new TransformerException("FODT0001 : Cannot divide an XSDayTimeDuration value with zero."); 
           }
           else if (argDoubleVal.infinite()) {
              double doubleResultVal = value() / argDoubleVal.doubleValue();
              result = new XSDayTimeDuration(doubleResultVal);
           }
           else {
              BigDecimal bigDecimal1 = new BigDecimal(value());
              BigDecimal bigDecimal2 = new BigDecimal(argDoubleVal.doubleValue());
              BigDecimal bigDecimalResult = bigDecimal1.divide(new BigDecimal(bigDecimal2.doubleValue()), 
                                                                                                 18, BigDecimal.ROUND_HALF_EVEN);
              result = new XSDayTimeDuration(bigDecimalResult.doubleValue());
           }
        }
        else if (xsAnyType instanceof XSDayTimeDuration) {
           double dbl2 = ((XSDayTimeDuration)xsAnyType).seconds();
           
           if (dbl2 != 0) {
               BigDecimal bigDecimal1 = new BigDecimal(value());
               BigDecimal bigDecimal2 = new BigDecimal(dbl2);
               BigDecimal bigDecimalResult = bigDecimal1.divide(new BigDecimal(bigDecimal2.doubleValue()), 
                                                                                                  18, BigDecimal.ROUND_HALF_EVEN);
               result = new XSDayTimeDuration(bigDecimalResult.doubleValue());  
           }
           else {
              throw new TransformerException("FODT0001 : Cannot divide an XSDayTimeDuration value, with a XSDayTimeDuration "
                                                                                               + "value that represents zero seconds."); 
           }
        }
        else {
           throw new TransformerException("FORG0006 : Cannot divide an XSDayTimeDuration value, with a value that is of "
                                                                                   + "a type other than numeric or XSDayTimeDuration.");
        }
        
        return result;
    }
    
    public int getType() {
        return CLASS_XS_DAYTIME_DURATION;
    }
	
	/**
     * Do a data type cast, of a XSAnyType value to an XSDuration
     * value. 
	 * @throws TransformerException 
     */
	private XSDuration castToDayTimeDuration(XSAnyType xsAnyType) throws TransformerException {        
	    if (xsAnyType instanceof XSDuration) {
            XSDuration xsDuration = (XSDuration) xsAnyType;
            
            return new XSDayTimeDuration(xsDuration.days(), xsDuration.hours(), 
                                         xsDuration.minutes(), xsDuration.seconds(), 
                                         xsDuration.negative());
        }
        
        return parseDayTimeDuration(xsAnyType.stringValue());
    }
	
}
