/**
 * This file is part of PaxmlCore.
 *
 * PaxmlCore is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PaxmlCore is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with PaxmlCore.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.paxml.el;

import org.paxml.core.Context;


/**
 * The expression prototype.
 * @author Xuetao Niu
 *
 */
public interface IExpression {
    /**
     * Evaluate with context.
     * @param context the context
     * @return the evaluation result
     */
    Object evaluate(Context context);
    /**
     * Evaluate with context, resulting in a string.
     * @param context the context
     * @return the result string
     */
    String evaluateString(Context context);
    /**
     * Get the actual expression's string representation. 
     * @return the string representation
     */
    String getString();
}
