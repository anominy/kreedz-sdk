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

package io.github.anominy.kreedzsdk.converters;

import com.google.auto.service.AutoService;
import io.github.anominy.kreedzsdk.converterapi.IKreedzQueryConverter;
import io.github.anominy.kreedzsdk.structs.types.EDifficulty;
import retrofit2.Converter;

/**
 * An {@link EDifficulty} query converter.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzQueryConverter.class)
public final class DifficultyQueryConverter implements Converter<EDifficulty, String>, IKreedzQueryConverter {

	/**
	 * Initialize a {@link DifficultyQueryConverter} instance.
	 */
	public DifficultyQueryConverter() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String convert(EDifficulty difficulty) {
		return Integer.toString(difficulty.getId());
	}
}
