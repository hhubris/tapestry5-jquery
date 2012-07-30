package org.got5.tapestry5.jquery.pages;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

/**
 */
public class PeriodicUpdate {

    @Persist
    @Property
    Integer zone1Count;

    @Persist
    @Property
    Integer zone2Count;

    @Inject
    private Request request;

    @InjectComponent
    private org.apache.tapestry5.corelib.components.Zone zone1;

    @InjectComponent
    private org.apache.tapestry5.corelib.components.Zone zone2;

    void setupRender() {
        if (zone1Count == null) {
            zone1Count = 0;
            zone2Count = 0;
        }
    }

    @OnEvent(value = "update", component = "zone1")
    Object onZone1Update() {
        zone1Count++;
        return request.isXHR() ? zone1.getBody() : null;
    }

    Object onUpdateFromZone2() {
        zone2Count++;
        return request.isXHR() ? zone2.getBody() : null;

    }
}
