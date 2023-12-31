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

package io.github.anominy.kreedzsdk.client.services.requests.mode;

import io.github.anominy.kreedzsdk.clientapi.IModeService;
import io.github.anominy.kreedzsdk.structs.entities.ModeEntity;
import io.github.anominy.uwretrofit.services.requests.IRequest;
import io.github.anominy.uwretrofit.services.requests.impl.AbstractRequestManager;
import retrofit2.Call;

import java.util.Objects;

/**
 * A request for /modes/name/.../ endpoint.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class GetModeByNameRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetModeByNameRequest.class.getSimpleName();

	/**
	 * A mode name.
	 */
	private final String modeName;

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
	 * Initialize a {@link GetModeByNameRequest} instance.
	 *
	 * @param modeName	mode name
	 */
	private GetModeByNameRequest(String modeName) {
		this.modeName = modeName;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetModeByNameRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetModeByNameRequest(GetModeByNameRequest that) {
		this(that.modeName);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
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

		GetModeByNameRequest that = (GetModeByNameRequest) obj;

		return Objects.equals(this.modeName, that.modeName);
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
					= Objects.hash(this.modeName));
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
					+ "modeName=\"" + this.modeName + "\""
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetModeByNameRequest clone() {
		return new GetModeByNameRequest(this);
	}

	/**
	 * A request manager for /modes/name/.../ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends AbstractRequestManager<GetModeByNameRequest, ModeEntity> {

		/**
		 * A mode service.
		 */
		private final IModeService modeService;

		/**
		 * A mode name.
		 */
		private String modeName;

		/**
		 * Initialize a {@link GetModeByNameRequest.Manager} instance.
		 *
		 * @param modeService	mode service
		 */
		public Manager(IModeService modeService) {
			if (modeService == null) {
				throw new IllegalArgumentException("Mode service mustn't be <null>");
			}

			this.modeService = modeService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetModeByNameRequest build() {
			return new GetModeByNameRequest(this.modeName);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<ModeEntity> call(GetModeByNameRequest request) {
			return this.modeService.getMode(request.modeName);
		}

		/**
		 * Set this mode name.
		 *
		 * @param modeName	mode name, may be null
		 * @return			this instance
		 */
		public Manager setModeName(String modeName) {
			this.modeName = modeName;
			return this;
		}
	}
}
