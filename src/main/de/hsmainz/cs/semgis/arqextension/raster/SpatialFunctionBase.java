/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsmainz.cs.semgis.arqextension.raster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.Function;
import org.apache.jena.sparql.function.FunctionEnv;

/**
 *
 *
 */
public abstract class SpatialFunctionBase implements Function {

    /**
     * {@inheritDoc}
     */
    @Override
    public final NodeValue exec(Binding binding, ExprList args, String uri, FunctionEnv env) {
        List<NodeValue> evalArgs = new ArrayList<>();
        for (Iterator<Expr> iter = args.iterator(); iter.hasNext();) {
            Expr e = iter.next();
            NodeValue x = e.eval(binding, env);
            evalArgs.add(x);
        }
        return exec(binding, evalArgs, uri, env);
    }

    protected abstract NodeValue exec(Binding binding, List<NodeValue> evalArgs, String uri, FunctionEnv env);

    /**
     * {@inheritDoc}
     */
    @Override
    public final void build(String uri, ExprList args) {
        String[] argTypes = getArgumentTypes();
        int numArgs = argTypes.length;
        if ((null == args && numArgs != 0) || (null != args && numArgs != args.size())) {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < argTypes.length; i++) {
                String arg = argTypes[i];
                message.append(arg);
                if (i < argTypes.length - 1) {
                    message.append(", ");
                }
            }
            throw new QueryBuildException(
                    String.format("%s requires %d arguments: %s", uri, argTypes.length, message));
        }
    }

    protected abstract String[] getRestOfArgumentTypes();

    protected abstract String[] getArgumentTypes();

}
