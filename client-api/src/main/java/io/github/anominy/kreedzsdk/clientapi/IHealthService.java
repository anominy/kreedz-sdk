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

package io.github.anominy.kreedzsdk.clientapi;

import io.github.anominy.kreedzsdk.clientapi.annotations.ServiceBaseUrl;
import io.github.anominy.kreedzsdk.structs.entities.health.responses.HealthStatusResponseEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * A health service interface.
 */
@ServiceBaseUrl("health.global-api.com/api/v1")
public interface IHealthService {

	/**
	 * GET request to /endpoints/statuses/ endpoint.
	 */
	@GET("endpoints/statuses")
	Call<List<HealthStatusResponseEntity>> getHealthStatus();

	/**
	 * GET request to /endpoints/.../statuses/ endpoint.
	 */
	@GET("endpoints/{group}_{endpoint}/statuses")
	Call<HealthStatusResponseEntity> getHealthStatusByEndpoint(
			@Path("group") String group,
			@Path("endpoint") String endpoint
	);
}
