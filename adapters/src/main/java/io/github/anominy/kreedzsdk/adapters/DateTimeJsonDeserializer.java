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
import io.github.anominy.kreedzsdk.structs.utils.UKreedzDate;
import io.github.anominy.uwutils.UwArray;
import org.joda.time.DateTime;

import java.lang.reflect.Type;

/**
 * A {@link DateTime} JSON deserializer.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzTypeAdapter.class)
public final class DateTimeJsonDeserializer implements JsonDeserializer<DateTime>, IKreedzTypeAdapter {

	/**
	 * Initialize a {@link DateTimeJsonDeserializer} instance.
	 */
	public DateTimeJsonDeserializer() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
		String str = context.deserialize(json, String.class);

		if (str == null) {
			return null;
		}

		Throwable[] throwables = new Throwable[2];

		try {
			return DateTime.parse(str, UKreedzDate.FORMATTER);
		} catch (UnsupportedOperationException
				| IllegalArgumentException e) {
			throwables[0] = e;
		}

		try {
			return DateTime.parse(str);
		} catch (UnsupportedOperationException
				| IllegalArgumentException e) {
			throwables[1] = e;
		}

		UwArray.consume(Throwable::printStackTrace, throwables);

		return null;
	}
}
