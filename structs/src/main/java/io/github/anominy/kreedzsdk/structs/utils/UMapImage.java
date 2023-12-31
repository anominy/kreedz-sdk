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

package io.github.anominy.kreedzsdk.structs.utils;

import io.github.anominy.kreedzsdk.structs.types.mapimage.EMapImageFormat;
import io.github.anominy.uwutils.UwObject;
import io.github.anominy.uwutils.UwString;

import java.util.function.Supplier;

/**
 * A kreedz map image utility.
 */
@SuppressWarnings("unused")
public final class UMapImage {

	/**
	 * A map image URL format string.
	 *
	 * <p>Arguments in order:
	 * <ul>
	 *     <li>String :: Branch.
	 *     <li>String :: Endpoint.
	 *     <li>String :: Map name.
	 *     <li>String :: Image extension.
	 * </ul>
	 */
	public static final String URL_FMT = "https://raw.githubusercontent.com/KZGlobalTeam/map-images/%s/%s/%s.%s";

	/**
	 * Get map image URL for the provided map name {@literal &} image format
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>Map name string is {@code null}.
	 *     <li>Map name string is empty.
	 *     <li>Image format enum is {@code null}.
	 * </ul>
	 *
	 * @param mapName		map name to get URL for
	 * @param format		image format to get URL for
	 * @param defaultValue	default value to return on failure
	 * @return				map image URL or the default value
	 */
	public static String getUrlOrElse(String mapName, EMapImageFormat format, String defaultValue) {
		if (mapName == null || format == null) {
			return defaultValue;
		}

		if (mapName.isEmpty()) {
			return defaultValue;
		}

		String branch = format.getBranch();
		String endpoint = format.getEndpoint();
		String extension = format.getExtension();

		return String.format(URL_FMT, branch, endpoint, mapName, extension);
	}

	/**
	 * Get map image URL for the provided map name {@literal &} image format
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>Map name string is {@code null}.
	 *     <li>Map name string is empty.
	 *     <li>Image format enum is {@code null}.
	 * </ul>
	 *
	 * @param mapName				map name to get URL for
	 * @param format				image format to get URL for
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						map image URL or the default value
	 */
	public static String getUrlOrElse(String mapName, EMapImageFormat format, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getUrlOrNull(mapName, format), defaultValueSupplier);
	}

	/**
	 * Get map image URL for the provided map name {@literal &} image format
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>Map name string is {@code null}.
	 *     <li>Map name string is empty.
	 *     <li>Image format enum is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getUrlOrElse(String, EMapImageFormat, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param mapName	map name to get URL for
	 * @param format	image format to get URL for
	 * @return			map image URL or the empty string
	 */
	public static String getUrlOrEmpty(String mapName, EMapImageFormat format) {
		return getUrlOrElse(mapName, format, UwString.EMPTY);
	}

	/**
	 * Get map image URL for the provided map name {@literal &} image format
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>Map name string is {@code null}.
	 *     <li>Map name string is empty.
	 *     <li>Image format enum is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getUrlOrElse(String, EMapImageFormat, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param mapName	map name to get URL for
	 * @param format	image format to get URL for
	 * @return			map image URL or {@code null}
	 */
	public static String getUrlOrNull(String mapName, EMapImageFormat format) {
		return getUrlOrElse(mapName, format, (String) null);
	}

	private UMapImage() {
		throw new UnsupportedOperationException();
	}
}
