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
import io.github.anominy.kreedzsdk.structs.entities.map.MapImageEntity;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * A map image service interface.
 */
@ServiceBaseUrl("raw.githubusercontent.com/KZGlobalTeam/map-images")
public interface IMapImageService {

	/**
	 * GET request to /public/maps.mis.json/ endpoint.
	 */
	@GET("public/maps.min.json")
	Call<List<MapImageEntity>> getMapImages();
}
