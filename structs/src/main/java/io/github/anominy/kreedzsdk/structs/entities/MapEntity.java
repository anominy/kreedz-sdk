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

package io.github.anominy.kreedzsdk.structs.entities;

import com.google.gson.annotations.SerializedName;
import io.github.anominy.kreedzsdk.structs.types.EDifficulty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz API map entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class MapEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = MapEntity.class.getSimpleName();

	/**
	 * An identifier.
	 */
	@SerializedName("id")
	private final Integer id;

	/**
	 * A name.
	 */
	@SerializedName("name")
	private final String name;

	/**
	 * A file size.
	 */
	@SerializedName("filesize")
	private final Integer fileSize;

	/**
	 * An "isValidated" boolean value.
	 */
	@SerializedName("validated")
	private final Boolean isValidated;

	/**
	 * A difficulty.
	 */
	@SerializedName("difficulty")
	private final EDifficulty difficulty;

	/**
	 * A creation date.
	 */
	@SerializedName("created_on")
	private final DateTime createDate;

	/**
	 * An update date.
	 */
	@SerializedName("updated_on")
	private final DateTime updateDate;

	/**
	 * A workshop URL.
	 */
	@SerializedName("workshop_url")
	private final String workshopUrl;

	/**
	 * A download URL.
	 */
	@SerializedName("download_url")
	private final String downloadUrl;

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
	 * Get this identifier.
	 *
	 * @return	identifier
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Get this name.
	 *
	 * @return	name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get this file size.
	 *
	 * @return	file size
	 */
	public Integer getFileSize() {
		return this.fileSize;
	}

	/**
	 * Get this "isValidated" boolean value.
	 *
	 * @return	"isValidated" boolean value
	 */
	public Boolean getIsValidated() {
		return this.isValidated;
	}

	/**
	 * Get this difficulty.
	 *
	 * @return	difficulty
	 */
	public EDifficulty getDifficulty() {
		return this.difficulty;
	}

	/**
	 * Get this create date.
	 *
	 * @return	create date
	 */
	public DateTime getCreateDate() {
		return this.createDate;
	}

	/**
	 * Get this update date.
	 *
	 * @return	update date
	 */
	public DateTime getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * Get this workshop URL.
	 *
	 * @return	workshop URL
	 */
	public String getWorkshopUrl() {
		return this.workshopUrl;
	}

	/**
	 * Get this download URL.
	 *
	 * @return	download URL
	 */
	public String getDownloadUrl() {
		return this.downloadUrl;
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

		MapEntity that = (MapEntity) obj;

		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.name, that.name)
				&& Objects.equals(this.fileSize, that.fileSize)
				&& Objects.equals(this.isValidated, that.isValidated)
				&& this.difficulty == that.difficulty
				&& Objects.equals(this.createDate, that.createDate)
				&& Objects.equals(this.updateDate, that.updateDate)
				&& Objects.equals(this.workshopUrl, that.workshopUrl)
				&& Objects.equals(this.downloadUrl, that.downloadUrl);
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
							this.fileSize,
							this.isValidated,
							this.difficulty,
							this.createDate,
							this.updateDate,
							this.workshopUrl,
							this.downloadUrl
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
					+ "id=" + this.id
					+ ", name=\"" + this.name + "\""
					+ ", fileSize=" + this.fileSize
					+ ", isValidated=" + this.isValidated
					+ ", difficulty=" + this.difficulty
					+ ", createDate=" + this.createDate
					+ ", updateDate=" + this.updateDate
					+ ", workshopUrl=\"" + this.workshopUrl + "\""
					+ ", downloadUrl=\"" + this.downloadUrl + "\""
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MapEntity clone() {
		return new MapEntity(this);
	}

	/**
	 * Initialize a {@link MapEntity} instance.
	 *
	 * @param id			identifier
	 * @param name			name
	 * @param fileSize		file size
	 * @param isValidated	"isValidated" boolean value
	 * @param difficulty	difficulty
	 * @param createDate	create date
	 * @param updateDate	update date
	 * @param workshopUrl	workshop URL
	 * @param downloadUrl	download URL
	 */
	private MapEntity(
			Integer id,
			String name,
			Integer fileSize,
			Boolean isValidated,
			EDifficulty difficulty,
			DateTime createDate,
			DateTime updateDate,
			String workshopUrl,
			String downloadUrl
	) {
		this.id = id;
		this.name = name;
		this.fileSize = fileSize;
		this.isValidated = isValidated;
		this.difficulty = difficulty;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.workshopUrl = workshopUrl;
		this.downloadUrl = downloadUrl;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link MapEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private MapEntity(MapEntity that) {
		this(
				that.id,
				that.name,
				that.fileSize,
				that.isValidated,
				that.difficulty,
				that.createDate,
				that.updateDate,
				that.workshopUrl,
				that.downloadUrl
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
