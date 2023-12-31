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

package io.github.anominy.kreedzsdk.clientapi.interceptors;

import io.github.anominy.kreedzsdk.clientapi.annotations.ServiceBaseUrl;
import io.github.anominy.uwutils.UwReflect;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Invocation;
import retrofit2.http.*;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A service base API URL interceptor.
 */
@SuppressWarnings({"NullableProblems", "CallToPrintStackTrace"})
public final class ServiceBaseUrlInterceptor implements Interceptor {

	/**
	 * An array of the HTTP method annotation classes from retrofit.
	 */
	private static final Class<?>[] HTTP_METHOD_ANNOTATION_CLASSES = {
			GET.class,
			HEAD.class,
			POST.class,
			PUT.class,
			DELETE.class,
			OPTIONS.class,
			PATCH.class
	};

	/**
	 * Initialize a {@link ServiceBaseUrlInterceptor} instance.
	 */
	public ServiceBaseUrlInterceptor() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();

		Invocation invocation = request.tag(Invocation.class);
		if (invocation == null) {
			return chain.proceed(request);
		}

		Method method = invocation.method();
		Class<?> clazz = method.getDeclaringClass();

		ServiceBaseUrl baseUrlAnnotation
				= clazz.getAnnotation(ServiceBaseUrl.class);

		if (baseUrlAnnotation == null) {
			return chain.proceed(request);
		}

		Annotation httpMethodAnnotation
				= UwReflect.annotationOf(HTTP_METHOD_ANNOTATION_CLASSES, method);

		if (httpMethodAnnotation == null) {
			return chain.proceed(request);
		}

		String endpoint = null;

		try {
			endpoint = (String) httpMethodAnnotation.getClass()
					.getMethod("value")
					.invoke(httpMethodAnnotation);
		} catch (IllegalAccessException
				| InvocationTargetException
				| NoSuchMethodException
				| ClassCastException e) {
			e.printStackTrace();
		}

		if (endpoint == null
				|| endpoint.matches("^(https?)://.*$")) {
			return chain.proceed(request);
		}

		String baseUrl = baseUrlAnnotation.value()
				.trim()
				.replaceFirst("^(https?)://", "")
				.replaceAll("/+", "/")
				.replaceAll("/$", "");

		endpoint = endpoint.trim()
				.replaceAll("/+", "/")
				.replaceAll("^/|/$", "");

		if (baseUrl.isEmpty() && endpoint.isEmpty()) {
			throw new IllegalArgumentException("Both the base URL & endpoint are empty");
		}

		if (!baseUrl.isEmpty() && !endpoint.isEmpty()) {
			endpoint = "/" + endpoint;
		}

		request = request.newBuilder()
				.url("https://" + baseUrl + endpoint)
				.build();

		return chain.proceed(request);
	}
}
