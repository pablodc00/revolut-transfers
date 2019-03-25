package com.revolut.util;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class GenericUtil {
    
    public static Date getDateNowUTC() {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        return Date.from(now.toInstant());        
    }
}
