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
import io.github.anominy.kreedzsdk.clientapi.IRecordFilterService;
import io.github.anominy.kreedzsdk.client.services.requests.recordfilter.GetRecordDistributionsRequest;
import io.github.anominy.kreedzsdk.client.services.requests.recordfilter.GetRecordFiltersRequest;
import io.github.anominy.uwretrofit.services.impl.RetrofitServiceWrapper;
import retrofit2.Retrofit;

/**
 * A record filter service.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzService.class)
public final class RecordFilterService extends RetrofitServiceWrapper<IRecordFilterService> implements IKreedzService {

	/**
	 * Initialize a {@link RecordFilterService} instance.
	 *
	 * @param retrofit	retrofit instance
	 */
	public RecordFilterService(Retrofit retrofit) {
		super(retrofit, IRecordFilterService.class);
	}

	/**
	 * Create a request manager for /record_filters/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetRecordFiltersRequest.Manager recordFilters() {
		return new GetRecordFiltersRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /record_filters/distributions/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetRecordDistributionsRequest.Manager recordDistributions() {
		return new GetRecordDistributionsRequest.Manager(this.service);
	}
}
