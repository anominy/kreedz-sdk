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

package io.github.anominy.kreedzsdk.client.services.requests.health;

import io.github.anominy.kreedzsdk.clientapi.IHealthService;
import io.github.anominy.kreedzsdk.structs.entities.health.responses.HealthStatusResponseEntity;
import io.github.anominy.uwretrofit.services.requests.IRequest;
import io.github.anominy.uwretrofit.services.requests.impl.AbstractRequestManager;
import retrofit2.Call;

import java.util.List;

/**
 * A request for /endpoints/statuses/ endpoint.
 */
public final class GetHealthStatusRequest implements IRequest {

	private GetHealthStatusRequest() {
		throw new UnsupportedOperationException();
	}

	/**
	 * A request manager for /endpoints/statuses/ endpoint.
	 */
	public static final class Manager extends AbstractRequestManager<
			GetHealthStatusRequest, List<HealthStatusResponseEntity>> {

		/**
		 * A health service.
		 */
		private final IHealthService healthService;

		/**
		 * Initialize a {@link GetHealthStatusRequest.Manager} instance.
		 *
		 * @param healthService		health service
		 */
		public Manager(IHealthService healthService) {
			if (healthService == null) {
				throw new IllegalArgumentException("Health service mustn't be <null>");
			}

			this.healthService = healthService;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @return 	null, always
		 */
		@Override
		public GetHealthStatusRequest build() {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<HealthStatusResponseEntity>> call(GetHealthStatusRequest unused) {
			return this.healthService.getHealthStatus();
		}
	}
}
