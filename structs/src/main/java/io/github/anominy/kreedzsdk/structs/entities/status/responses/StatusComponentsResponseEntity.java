/*
 * Copyright 2023 anominy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.anominy.kreedzsdk.structs.entities.status.responses;

import com.google.gson.annotations.SerializedName;
import io.github.anominy.kreedzsdk.structs.entities.status.StatusComponentEntity;
import io.github.anominy.kreedzsdk.structs.entities.status.StatusPageEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A kreedz status API components response entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class StatusComponentsResponseEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = StatusComponentsResponseEntity.class.getSimpleName();

	/**
	 * A page.
	 */
	@SerializedName("page")
	private final StatusPageEntity page;

	/**
	 * A list of components.
	 */
	@SerializedName("components")
	private final List<StatusComponentEntity> components;

	/**
	 * A {@link #hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link #toString()} cache.
	 */
	private transient volatile String stringCache;

	/**
	 * A {@link #hashCodeCache} mutex.
	 */
	private transient Object hashCodeCacheMutex;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private transient Object stringCacheMutex;

	/**
	 * Initialize this mutex objects.
	 */
	private void initMutexObjects() {
		this.hashCodeCacheMutex = new Object();
		this.stringCacheMutex = new Object();
	}

	/**
	 * Override the {@code #readResolve} method to set up
	 * the object cache mutexes after deserialization.
	 *
	 * @return	this instance
	 */
	private Object readResolve() {
		this.initMutexObjects();

		return this;
	}

	/**
	 * Get this page.
	 *
	 * @return	page
	 */
	public StatusPageEntity getPage() {
		return this.page;
	}

	/**
	 * Get this list of components.
	 *
	 * @return	list of components
	 */
	public List<StatusComponentEntity> getComponents() {
		return this.components;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}

		StatusComponentsResponseEntity that = (StatusComponentsResponseEntity) obj;

		return Objects.equals(this.page, that.page)
				&& Objects.equals(this.components, that.components);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		if (this.hashCodeCache != null) {
			return this.hashCodeCache;
		}

		synchronized (this.hashCodeCacheMutex) {
			if (this.hashCodeCache != null) {
				return this.hashCodeCache;
			}

			return (this.hashCodeCache
					= Objects.hash(
							this.page,
							this.components
					)
			);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (this.stringCache != null) {
			return this.stringCache;
		}

		synchronized (this.stringCacheMutex) {
			if (this.stringCache != null) {
				return this.stringCache;
			}

			return (this.stringCache = SIMPLE_NAME + "["
					+ "page=" + this.page
					+ ", components=" + this.components
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StatusComponentsResponseEntity clone() {
		return new StatusComponentsResponseEntity(this);
	}

	/**
	 * Initialize a {@link StatusComponentsResponseEntity} instance.
	 *
	 * @param page			page
	 * @param components	list of components
	 */
	private StatusComponentsResponseEntity(
			StatusPageEntity page,
			List<StatusComponentEntity> components
	) {
		this.page = page;
		this.components = components;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link StatusComponentsResponseEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private StatusComponentsResponseEntity(StatusComponentsResponseEntity that) {
		this(
				that.page,
				that.components
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
