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

import io.github.anominy.kreedzsdk.clientapi.annotations.MethodVersion;
import io.github.anominy.kreedzsdk.structs.entities.MapEntity;
import io.github.anominy.kreedzsdk.structs.types.EVersion;
import io.github.anominy.kreedzsdk.structs.types.EDifficulty;
import org.joda.time.DateTime;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.Set;

/**
 * A map service interface.
 */
public interface IMapService {

	/**
	 * GET request to /maps/ endpoint.
	 */
	@MethodVersion(EVersion.V1_0)
	@GET("maps")
	Call<List<MapEntity>> getMaps(
			@Query("id") Set<Integer> ids,
			@Query("name") String mapName,
			@Query("larger_than_filesize") Integer fileSizeLargerThan,
			@Query("smaller_than_filesize") Integer fileSizeSmallerThan,
			@Query("is_validated") Boolean isValidated,
			@Query("difficulty") EDifficulty difficultyTier,
			@Query("created_since") DateTime createdSinceDate,
			@Query("updated_since") DateTime updatedSinceDate,
			@Query("offset") Integer offset,
			@Query("limit") Integer limit
	);

	/**
	 * GET request to /maps/.../ endpoint.
	 */
	@MethodVersion(EVersion.V1_0)
	@GET("maps/{id}")
	Call<MapEntity> getMap(
			@Path("id") Integer id
	);

	/**
	 * GET request to /maps/name/.../ endpoint.
	 */
	@MethodVersion(EVersion.V1_0)
	@GET("maps/name/{mapName}")
	Call<MapEntity> getMap(
			@Path("mapName") String mapName
	);
}
