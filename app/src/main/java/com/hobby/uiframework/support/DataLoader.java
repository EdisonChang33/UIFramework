package com.hobby.uiframework.support;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author EdisonChang
 */
public abstract class DataLoader {

	public static final int LOAD_STATE_NORMAL = 1;
	public static final int LOAD_STATE_FAILED = 2;
	public static final int LOAD_STATE_LOADING = 3;
	public static final int LOAD_STATE_FINISHED = 4;

	private final AtomicInteger loadFlag = new AtomicInteger(LOAD_STATE_NORMAL);

	public DataLoader() {
		setLoadState(LOAD_STATE_NORMAL);
	}

	public final void setLoadState(int loadState) {
		loadFlag.getAndSet(loadState);
	}

	public int getLoadState() {
		return loadFlag.get();
	}

	public abstract void loadMore();

	public abstract void loadData();

	public abstract boolean isEmpty();

	public abstract void cancel();

	public abstract void setUrl(String url);

	public abstract String getUrl();
}
