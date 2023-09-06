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

package io.github.anominy.kreedzsdk.adapters;

import com.google.auto.service.AutoService;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import io.github.anominy.kreedzsdk.adapterapi.IKreedzTypeAdapter;
import io.github.anominy.kreedzsdk.structs.types.EBanType;

import java.lang.reflect.Type;

/**
 * An {@link EBanType} JSON deserializer.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzTypeAdapter.class)
public final class BanTypeJsonDeserializer implements JsonDeserializer<EBanType>, IKreedzTypeAdapter {

	/**
	 * Initialize a {@link BanTypeJsonDeserializer} instance.
	 */
	public BanTypeJsonDeserializer() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EBanType deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
		return EBanType.fromApiNameOrNull(context.deserialize(json, String.class));
	}
}
