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
import io.github.anominy.steamid.SteamId;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz API player entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class PlayerEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = PlayerEntity.class.getSimpleName();

	/**
	 * A person identifier.
	 */
	@SerializedName("steamid64")
	private final SteamId steamId;

	/**
	 * An "isBanned" boolean value.
	 */
	@SerializedName("is_banned")
	private final Boolean isBanned;

	/**
	 * A record count.
	 */
	@SerializedName("total_records")
	private final Integer recordCount;

	/**
	 * A person name.
	 */
	@SerializedName("name")
	private final String name;

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
	 * Get this person identifier.
	 *
	 * @return	person identifier
	 */
	public SteamId getSteamId() {
		return this.steamId;
	}

	/**
	 * Get this "isBanned" boolean value.
	 *
	 * @return	"isBanned" boolean value
	 */
	public Boolean getIsBanned() {
		return this.isBanned;
	}

	/**
	 * Get this record count.
	 *
	 * @return	record count
	 */
	public Integer getRecordCount() {
		return this.recordCount;
	}

	/**
	 * Get this person name.
	 *
	 * @return	person name
	 */
	public String getName() {
		return this.name;
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

		PlayerEntity that = (PlayerEntity) obj;

		return Objects.equals(this.steamId, that.steamId)
				&& Objects.equals(this.isBanned, that.isBanned)
				&& Objects.equals(this.recordCount, that.recordCount)
				&& Objects.equals(this.name, that.name);
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
							this.steamId,
							this.isBanned,
							this.recordCount,
							this.name
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
					+ "steamId=" + this.steamId
					+ ", isBanned=" + this.isBanned
					+ ", recordCount=" + this.recordCount
					+ ", name=\"" + this.name + "\""
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PlayerEntity clone() {
		return new PlayerEntity(this);
	}

	/**
	 * Initialize a {@link PlayerEntity} instance.
	 *
	 * @param steamId		person identifier
	 * @param isBanned		"isBanned" boolean value
	 * @param recordCount	record count
	 * @param name			person name
	 */
	private PlayerEntity(
			SteamId steamId,
			Boolean isBanned,
			Integer recordCount,
			String name
	) {
		this.steamId = steamId;
		this.isBanned = isBanned;
		this.recordCount = recordCount;
		this.name = name;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link PlayerEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private PlayerEntity(PlayerEntity that) {
		this(
				that.steamId,
				that.isBanned,
				that.recordCount,
				that.name
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
