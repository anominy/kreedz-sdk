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
 * A kreedz API player rank entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class PlayerRankEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = PlayerRankEntity.class.getSimpleName();

	/**
	 * A point count.
	 */
	@SerializedName("points")
	private final Integer pointCount;

	/**
	 * An average point count.
	 */
	@SerializedName("average")
	private final Float avgPointCount;

	/**
	 * A rating.
	 */
	@SerializedName("rating")
	private final Float rating;

	/**
	 * A finish count.
	 */
	@SerializedName("finishes")
	private final Integer finishCount;

	/**
	 * A person identifier.
	 */
	@SerializedName("steamid64")
	private final SteamId steamId;

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
	 * Get this point count.
	 *
	 * @return	point count
	 */
	public Integer getPointCount() {
		return this.pointCount;
	}

	/**
	 * Get this average point count.
	 *
	 * @return	average point count
	 */
	public Float getAvgPointCount() {
		return this.avgPointCount;
	}

	/**
	 * Get this rating.
	 *
	 * @return	rating
	 */
	public Float getRating() {
		return this.rating;
	}

	/**
	 * Get this finish count.
	 *
	 * @return	finish count
	 */
	public Integer getFinishCount() {
		return this.finishCount;
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

		PlayerRankEntity that = (PlayerRankEntity) obj;

		return Objects.equals(this.pointCount, that.pointCount)
				&& Objects.equals(this.avgPointCount, that.avgPointCount)
				&& Objects.equals(this.rating, that.rating)
				&& Objects.equals(this.finishCount, that.finishCount)
				&& Objects.equals(this.steamId, that.steamId);
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
							this.pointCount,
							this.avgPointCount,
							this.rating,
							this.finishCount,
							this.steamId
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
					+ "pointCount=" + this.pointCount
					+ ", avgPointCount=" + this.avgPointCount
					+ ", rating=" + this.rating
					+ ", finishCount=" + this.finishCount
					+ ", steamId=" + this.steamId
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PlayerRankEntity clone() {
		return new PlayerRankEntity(this);
	}

	/**
	 * Initialize a {@link PlayerRankEntity} instance.
	 *
	 * @param pointCount		point count
	 * @param avgPointCount		average point count
	 * @param rating			rating
	 * @param finishCount		finish count
	 * @param steamId			person identifier
	 */
	private PlayerRankEntity(
			Integer pointCount,
			Float avgPointCount,
			Float rating,
			Integer finishCount,
			SteamId steamId
	) {
		this.pointCount = pointCount;
		this.avgPointCount = avgPointCount;
		this.rating = rating;
		this.finishCount = finishCount;
		this.steamId = steamId;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link PlayerRankEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private PlayerRankEntity(PlayerRankEntity that) {
		this(
				that.pointCount,
				that.avgPointCount,
				that.rating,
				that.finishCount,
				that.steamId
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
