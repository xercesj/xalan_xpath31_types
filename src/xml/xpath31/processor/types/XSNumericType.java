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

import org.apache.xpath.objects.ResultSequence;

/**
 * This class serves as base type, of all the XML Schema built-in 
 * numeric types.
 */
public class XSNumericType extends XSCtrType {

    private static final long serialVersionUID = 6842313858622701811L;

    @Override
    public ResultSequence constructor(ResultSequence arg) throws TransformerException {
        return null;
    }

    @Override
    public String typeName() {
        return null;
    }

    @Override
    public String stringType() {
        return null;
    }

    @Override
    public String stringValue() {
        return null;
    }

}
