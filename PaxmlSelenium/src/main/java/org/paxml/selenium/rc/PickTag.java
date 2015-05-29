/**
 * This file is part of PaxmlSelenium.
 *
 * PaxmlSelenium is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PaxmlSelenium is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with PaxmlSelenium.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.paxml.selenium.rc;

import org.apache.commons.lang3.StringUtils;
import org.paxml.annotation.Tag;
import org.paxml.core.Context;

/**
 * The pick tag impl.
 * 
 * @author Xuetao Niu
 * 
 */
@Tag(name = "pick")
public class PickTag extends LocatorTag {
	
    /**
     * {@inheritDoc}
     */
    @Override
    protected Object onCommandAfterWait(Context context) {
        String locator = getLocator();
        SeleniumUtils util = getSeleniumUtils(context);
        String result = util.getText(locator);
        if (StringUtils.isEmpty(result)) {
            result = util.getValue(locator);
        }
        return result;
    }

}
