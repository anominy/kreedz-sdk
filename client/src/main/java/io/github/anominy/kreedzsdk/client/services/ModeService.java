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
import io.github.anominy.kreedzsdk.clientapi.IKreedzService;
import io.github.anominy.kreedzsdk.clientapi.IModeService;
import io.github.anominy.kreedzsdk.client.services.requests.mode.GetModeByIdRequest;
import io.github.anominy.kreedzsdk.client.services.requests.mode.GetModeByNameRequest;
import io.github.anominy.kreedzsdk.client.services.requests.mode.GetModesRequest;
import io.github.anominy.uwretrofit.services.impl.RetrofitServiceWrapper;
import retrofit2.Retrofit;

/**
 * A mode service.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzService.class)
public final class ModeService extends RetrofitServiceWrapper<IModeService> implements IKreedzService {

	/**
	 * Initialize a {@link ModeService} instance.
	 *
	 * @param retrofit	retrofit instance
	 */
	public ModeService(Retrofit retrofit) {
		super(retrofit, IModeService.class);
	}

	/**
	 * Create a request manager for /modes/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetModesRequest.Manager modes() {
		return new GetModesRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /modes/id/.../ endpoint.
	 *
	 * @return	request manager
	 */
	public GetModeByIdRequest.Manager modeById() {
		return new GetModeByIdRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /modes/name/.../ endpoint.
	 *
	 * @return	request manager
	 */
	public GetModeByNameRequest.Manager modeByName() {
		return new GetModeByNameRequest.Manager(this.service);
	}
}
