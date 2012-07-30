//
// Copyright 2010 GOT5 (Gang Of Tapestry 5)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package org.got5.tapestry5.jquery.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * A simple mixin that causes a zone to periodically update itself
 * The timer classes are provided by the jquery.timers plugin
 * http://archive.plugins.jquery.com/project/timers
 */
@Import(library = { "${tapestry.jquery.path}/jquery.timers.js",
        "${assets.path}/mixins/periodicupdate/periodicupdate.js" })
public class PeriodicUpdate {

    static final String EVENT_NAME = "periodicUpdate";

    /**
     * The zone that will be updated
     */
    @InjectContainer
    private Zone zone;

    @Inject
    private ComponentResources resources;

    @Environmental
    private JavaScriptSupport javaScriptSupport;

    @Inject
    private Request request;

    /**
     * Specifies how often the zone should update
     */
    @Parameter(defaultPrefix = BindingConstants.LITERAL, required = true)
    private String updateFrequency;

    @Parameter(defaultPrefix = BindingConstants.LITERAL, required = false, value = "0")
    private String maxUpdates;

    void afterRender() {
        Link link = resources.createEventLink("update");

        JSONObject config = new JSONObject();
        config.put("zoneId", zone.getClientId());
        config.put("url",  link.toAbsoluteURI());
        config.put("updateFrequency", updateFrequency);
        config.put("maxUpdates", maxUpdates);
        configure(config);

        javaScriptSupport.addInitializerCall("periodicupdate", config);

    }

    /**
     * @param config
     *            parameters object
     */
    protected void configure(JSONObject config)
    {
    }

}

