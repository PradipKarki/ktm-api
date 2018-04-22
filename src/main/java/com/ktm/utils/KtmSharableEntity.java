package com.ktm.utils;

public enum KtmSharableEntity {
	TWITTER(0),
	YOUTUBE(1),
	RSS_NEWS(2),
	DOCUMENTARY(3);
	
    /**
     * Value for this KtmSharableEntity
     */
    public final int value;
 
    private KtmSharableEntity(int value) {
        this.value = value;
    }
}
