package org.boris.expr.function.excel;

import org.boris.expr.ExprException;
import org.boris.expr.function.DoubleInOutFunction;

public class COSH extends DoubleInOutFunction
{
    protected double evaluate(double value) throws ExprException {
        return Math.cosh(value);
    }
}
