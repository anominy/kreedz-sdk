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

package io.github.anominy.kreedzsdk.client.services.requests.status;

import io.github.anominy.kreedzsdk.clientapi.IStatusService;
import io.github.anominy.kreedzsdk.structs.entities.status.responses.StatusScheduledIncidentsResponseEntity;
import io.github.anominy.uwretrofit.services.requests.IRequest;
import io.github.anominy.uwretrofit.services.requests.impl.AbstractRequestManager;
import retrofit2.Call;

/**
 * A request for /scheduled-maintenances/upcoming.json/ endpoint.
 */
public final class GetStatusUpcomingScheduledIncidentsRequest implements IRequest {

	private GetStatusUpcomingScheduledIncidentsRequest() {
		throw new UnsupportedOperationException();
	}

	/**
	 * A request manager for /scheduled-maintenances/upcoming.json/ endpoint.
	 */
	public static final class Manager extends AbstractRequestManager<
			GetStatusUpcomingScheduledIncidentsRequest, StatusScheduledIncidentsResponseEntity> {

		/**
		 * A status service.
		 */
		private final IStatusService statusService;

		/**
		 * Initialize a {@link GetStatusUpcomingScheduledIncidentsRequest.Manager} instance.
		 *
		 * @param statusService		status service
		 */
		public Manager(IStatusService statusService) {
			if (statusService == null) {
				throw new IllegalArgumentException("Status service mustn't be <null>");
			}

			this.statusService = statusService;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @return	null, always
		 */
		@Override
		public GetStatusUpcomingScheduledIncidentsRequest build() {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<StatusScheduledIncidentsResponseEntity> call(GetStatusUpcomingScheduledIncidentsRequest unused) {
			return this.statusService.getUpcomingScheduledIncidents();
		}
	}
}