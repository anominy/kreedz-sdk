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

package io.github.anominy.kreedzsdk.structs.entities.status;

import com.google.gson.annotations.SerializedName;
import io.github.anominy.kreedzsdk.structs.types.status.EStatus;
import io.github.anominy.kreedzsdk.structs.types.status.EStatusIndicator;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A kreedz status API incident entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public class StatusIncidentEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = StatusIncidentEntity.class.getSimpleName();

	/**
	 * An identifier.
	 */
	@SerializedName("id")
	protected final String id;

	/**
	 * A name.
	 */
	@SerializedName("name")
	protected final String name;

	/**
	 * A status.
	 */
	@SerializedName("status")
	protected final EStatus status;

	/**
	 * A creation date.
	 */
	@SerializedName("created_at")
	protected final DateTime createDate;

	/**
	 * An update date.
	 */
	@SerializedName("updated_at")
	protected final DateTime updateDate;

	/**
	 * A monitor date.
	 */
	@SerializedName("monitoring_at")
	protected final DateTime monitorDate;

	/**
	 * A resolve date.
	 */
	@SerializedName("resolved_at")
	protected final DateTime resolveDate;

	/**
	 * An impact.
	 */
	@SerializedName("impact")
	protected final EStatusIndicator impact;

	/**
	 * A short URL.
	 */
	@SerializedName("shortlink")
	protected final String shortUrl;

	/**
	 * A page identifier.
	 */
	@SerializedName("page_id")
	protected final String pageId;

	/**
	 * A list of updates.
	 */
	@SerializedName("incident_updates")
	protected final List<StatusUpdateEntity> updates;

	/**
	 * A list of components.
	 */
	@SerializedName("components")
	protected final List<StatusComponentEntity> components;

	/**
	 * A {@link #hashCode()} cache.
	 */
	protected transient volatile Integer hashCodeCache;

	/**
	 * A {@link #toString()} cache.
	 */
	protected transient volatile String stringCache;

	/**
	 * A {@link #hashCodeCache} mutex.
	 */
	protected transient Object hashCodeCacheMutex;

	/**
	 * A {@link #stringCache} mutex.
	 */
	protected transient Object stringCacheMutex;

	/**
	 * Initialize this mutex objects.
	 */
	protected void initMutexObjects() {
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
	 * Get this identifier.
	 *
	 * @return	identifier
	 */
	public final String getId() {
		return this.id;
	}

	/**
	 * Get this name.
	 *
	 * @return	name
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * Get this status.
	 *
	 * @return	status
	 */
	public final EStatus getStatus() {
		return this.status;
	}

	/**
	 * Get this create date.
	 *
	 * @return	create date
	 */
	public final DateTime getCreateDate() {
		return this.createDate;
	}

	/**
	 * Get this update date.
	 *
	 * @return	update date
	 */
	public final DateTime getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * Get this monitor date.
	 *
	 * @return	monitor date
	 */
	public final DateTime getMonitorDate() {
		return this.monitorDate;
	}

	/**
	 * Get this resolve date.
	 *
	 * @return	resolve date
	 */
	public final DateTime getResolveDate() {
		return this.resolveDate;
	}

	/**
	 * Get this impact.
	 *
	 * @return	impact
	 */
	public final EStatusIndicator getImpact() {
		return this.impact;
	}

	/**
	 * Get this short URL.
	 *
	 * @return	short URL
	 */
	public final String getShortUrl() {
		return this.shortUrl;
	}

	/**
	 * Get this page identifier.
	 *
	 * @return	page identifier
	 */
	public final String getPageId() {
		return this.pageId;
	}

	/**
	 * Get this list of updates.
	 *
	 * @return	list of updates
	 */
	public final List<StatusUpdateEntity> getUpdates() {
		return this.updates;
	}

	/**
	 * Get this list of components.
	 *
	 * @return	list of components
	 */
	public final List<StatusComponentEntity> getComponents() {
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

		StatusIncidentEntity that = (StatusIncidentEntity) obj;

		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.name, that.name)
				&& this.status == that.status
				&& Objects.equals(this.createDate, that.createDate)
				&& Objects.equals(this.updateDate, that.updateDate)
				&& Objects.equals(this.monitorDate, that.monitorDate)
				&& Objects.equals(this.resolveDate, that.resolveDate)
				&& this.impact == that.impact
				&& Objects.equals(this.shortUrl, that.shortUrl)
				&& Objects.equals(this.pageId, that.pageId)
				&& Objects.equals(this.updates, that.updates)
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
							this.id,
							this.name,
							this.status,
							this.createDate,
							this.updateDate,
							this.monitorDate,
							this.resolveDate,
							this.impact,
							this.shortUrl,
							this.pageId,
							this.updates,
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
					+ "id=\"" + this.id + "\""
					+ ", name=\"" + this.name + "\""
					+ ", status=" + this.status
					+ ", createDate=" + this.createDate
					+ ", updateDate=" + this.updateDate
					+ ", monitorDate=" + this.monitorDate
					+ ", resolveDate=" + this.resolveDate
					+ ", impact=" + this.impact
					+ ", shortUrl=\"" + this.shortUrl + "\""
					+ ", pageId=\"" + this.pageId + "\""
					+ ", updates=" + this.updates
					+ ", components=" + this.components
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StatusIncidentEntity clone() {
		return new StatusIncidentEntity(this);
	}

	/**
	 * Initialize a {@link StatusIncidentEntity} instance.
	 *
	 * @param id			identifier
	 * @param name			name
	 * @param status		status
	 * @param createDate	create date
	 * @param updateDate	update date
	 * @param monitorDate	monitor date
	 * @param resolveDate	resolve date
	 * @param impact		impact
	 * @param shortUrl		short URL
	 * @param pageId		page identifier
	 * @param updates		list of updates
	 * @param components	list of components
	 */
	StatusIncidentEntity(
			String id,
			String name,
			EStatus status,
			DateTime createDate,
			DateTime updateDate,
			DateTime monitorDate,
			DateTime resolveDate,
			EStatusIndicator impact,
			String shortUrl,
			String pageId,
			List<StatusUpdateEntity> updates,
			List<StatusComponentEntity> components
	) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.monitorDate = monitorDate;
		this.resolveDate = resolveDate;
		this.impact = impact;
		this.shortUrl = shortUrl;
		this.pageId = pageId;
		this.updates = updates;
		this.components = components;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link StatusIncidentEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	StatusIncidentEntity(StatusIncidentEntity that) {
		this(
				that.id,
				that.name,
				that.status,
				that.createDate,
				that.updateDate,
				that.monitorDate,
				that.resolveDate,
				that.impact,
				that.shortUrl,
				that.pageId,
				that.updates,
				that.components
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
