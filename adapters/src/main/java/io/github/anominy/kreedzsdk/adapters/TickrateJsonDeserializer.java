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
import io.github.anominy.kreedzsdk.structs.types.ETickrate;

import java.lang.reflect.Type;

/**
 * An {@link ETickrate} JSON deserializer.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzTypeAdapter.class)
public final class TickrateJsonDeserializer implements JsonDeserializer<ETickrate>, IKreedzTypeAdapter {

	/**
	 * Initialize a {@link TickrateJsonDeserializer} instance.
	 */
	public TickrateJsonDeserializer() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ETickrate deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
		return ETickrate.fromIntOrNull(context.deserialize(json, Integer.class));
	}
}
