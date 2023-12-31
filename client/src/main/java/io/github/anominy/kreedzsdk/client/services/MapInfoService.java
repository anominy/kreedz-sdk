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

package io.github.anominy.kreedzsdk.client.services;

import com.google.auto.service.AutoService;
import io.github.anominy.kreedzsdk.client.services.requests.mapsinfo.GetGlobalMapsInfoRequest;
import io.github.anominy.kreedzsdk.client.services.requests.mapsinfo.GetMapsInfoRequest;
import io.github.anominy.kreedzsdk.client.services.requests.mapsinfo.GetNonGlobalMapsInfoRequest;
import io.github.anominy.kreedzsdk.client.services.requests.mapsinfo.GetUncompletedMapsInfoRequest;
import io.github.anominy.kreedzsdk.clientapi.IKreedzService;
import io.github.anominy.kreedzsdk.clientapi.IMapInfoService;
import io.github.anominy.uwretrofit.services.impl.RetrofitServiceWrapper;
import retrofit2.Retrofit;

/**
 * A maps information service.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzService.class)
public final class MapInfoService extends RetrofitServiceWrapper<IMapInfoService> implements IKreedzService {

	/**
	 * Initialize a {@link MapInfoService} instance.
	 *
	 * @param retrofit	retrofit instance
	 */
	public MapInfoService(Retrofit retrofit) {
		super(retrofit, IMapInfoService.class);
	}

	/**
	 * Create a request manager for /main/maps.min.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetMapsInfoRequest.Manager all() {
		return new GetMapsInfoRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /main/global.min.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetGlobalMapsInfoRequest.Manager global() {
		return new GetGlobalMapsInfoRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /main/non-global.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetNonGlobalMapsInfoRequest.Manager nonGlobal() {
		return new GetNonGlobalMapsInfoRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /main/uncompleted.min.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetUncompletedMapsInfoRequest.Manager uncompleted() {
		return new GetUncompletedMapsInfoRequest.Manager(this.service);
	}
}
