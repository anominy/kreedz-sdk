# CS:GO Kreedz Global API SDK

## Modules
 * [/structs](./structs)
   * This module collects all possible and used entities, enumeration types,
     high-level abstractions over the primitive types, and responses both for
     the [Global API](https://kztimerglobal.com/swagger/index.html?urls.primaryName=V2)
     and for third-party ones like [Health API](https://health.global-api.com)
     or [Status API](https://status.global-api.com).

 * [/client](./client)
   * The main module containing implementation of the `IRetrofitClient` & `IKreedzClient` interfaces
     and service implementation/wrapper classes. Has a strict relation to the `/client-api` module.

 * [/converters](./converters)
   * This module collects all used query converters in the client module.
     Has a strict relation to the `/converter-api` module.

 * [/adapters](./adapters)
   * This module collects all used type adapters in the client module.
     Has a strict relation to the `/adapter-api` module.

 * [/client-api](./client-api)
   * This module collects all possible retrofit service interfaces, service provider interface
     for automation binding of services, and annotations for programmatic metadata.

 * [/converter-api](./converter-api)
   * A service provider interface API for automation binding of query converter/s -factories.

 * [/adapter-api](./adapter-api)
   * A service provider interface API for automation binding of type adapter/s -factories.


## Example
First you need to create an implementation of an `IKreedzClient` interface, we use the one
from the `/client` module. Next, get a service instance and perform some API calls
& transformations. Very simple, isn't it?
```java
package ...;

import io.github.anominy.kreedzsdk.client.services.BanService;
import io.github.anominy.kreedzsdk.structs.entities.BanEntity;
import io.github.anominy.kreedzsdk.structs.types.EBanType;
import io.github.anominy.uwutils.UwArray;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Initializing a new KreedzClient instance w/ a default API version.
        KreedzClient client = new KreedzClient(/* EVersion.LATEST */);

        // Getting a BanService instance from a previously
        //  initialized KreedzClient instance.
        BanService service = client.getBanService();

        // If any exception occurred during the HTTP request
        //  this array will be filled w/ thrown Throwable objects.
        Throwable[] throwables = new Throwable[1];

        // Performing an HTTP request to /bans/ endpoint.
        List<BanEntity> entities = service.bans()
                .setBanTypes(EBanType.BAN_EVASION)
                .setIsExpired(true)
                .setLimit(1)
                // Passing a previously declared
                //  empty array of throwables.
                .execute(throwables);

        // Printing stack traces of all occurred throwables
        //  to the system error print stream.
        UwArray.consume(Throwable::printStackTrace, throwables);

        if (entities == null) {
            // There may be an incorrect HTTP response
            //  or a thrown exception during the request.

            // Do something responsive.
            return;
        }

        // Printing string representations of all BanEntity
        //  instances received from the Kreedz Global API.
        entities.forEach(System.out::println);
    }
}
```

<hr>

```text
BanEntity[id=53864, banType=EBanType::BAN_EVASION[group=EBanType:Group::OTHER, apiName="ban_evasion", fullName="Ban Evasion", shortName="Evasion"], expireDate=2021-07-09T23:43:15.000Z, steamId=SteamId[xuid=366779135, universe=ESteamUniverse::PUBLIC[id=1], instance=ESteamInstance::DESKTOP[id=1], account=ESteamAccount::INDIVIDUAL[id=1, ch='U']], notes="null", stats=null, serverId=0, dataUpdater=DataUpdater[id=76561197977956420], createDate=2021-07-10T23:43:03.000Z, updateDate=2021-07-10T23:43:15.000Z]
```


## Extensibility
The SDK provides programmatic API modules for automation binding of instances
that built under the Java SPI mechanism. To make the classes automatically possible
to be bound to the `KreedzClient`, you must implement the interface and annotate
class with `@AutoService` annotation from [google/auto](https://github.com/google/auto/tree/main/service)
service module.

### Examples
#### IKreedzQueryConverter
Taken from [RunTypeQueryConverter](https://github.com/anominy/kreedz-sdk/blob/main/converters/src/main/java/io/github/anominy/kreedzsdk/converters/RunTypeQueryConverter.java#L17-L50)
class. Make sure your 2nd generic type in the Converter interface is `String`
or you will get an `IllegalStateException` at the `KreedzClient` initialization.
```java
package ...;

import com.google.auto.service.AutoService;
import io.github.anominy.kreedzsdk.converterapi.IKreedzQueryConverter;
import io.github.anominy.kreedzsdk.structs.types.ERunType;
import retrofit2.Converter;

@AutoService(IKreedzQueryConverter.class)
public final class RunTypeQueryConverter implements Converter<ERunType, String>, IKreedzQueryConverter {
    
    @Override
    public String convert(ERunType runType) {
        Boolean hasTeleports = runType.getHasTeleports();

        if (hasTeleports == null) {
            return null;
        }

        return Boolean.toString(hasTeleports);
    }
}
```

#### IKreedzTypeAdapter
Taken from [RunTypeJsonDeserializer](https://github.com/anominy/kreedz-sdk/blob/main/adapters/src/main/java/io/github/anominy/kreedzsdk/deserializers/RunTypeJsonDeserializer.java#L17-L57) 
class. Make sure you implement at least one of these interfaces — `JsonDeserializer`, `JsonSerializer`
or extends from `TypeAdapter` class. If you implement both of them make sure you have the same
generic type, or you will get an `IllegalStateException` at the `KreedzClient` initialization.
```java
package ...;

import com.google.auto.service.AutoService;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import io.github.anominy.kreedzsdk.adapterapi.IKreedzTypeAdapter;
import io.github.anominy.kreedzsdk.structs.types.ERunType;

import java.lang.reflect.Type;

@AutoService(IKreedzTypeAdapter.class)
public final class RunTypeJsonDeserializer implements JsonDeserializer<ERunType>, IKreedzTypeAdapter {

    @Override
    public ERunType deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        return ERunType.fromHasTeleports(context.deserialize(json, Boolean.class));
    }
}
```

#### IKreedzService
Taken from [BanService](https://github.com/anominy/kreedz-sdk/blob/main/client/src/main/java/io/github/anominy/kreedzsdk/client/services/BanService.java#L17-L50) class.
Your service can be any defined class. If it extends from a `RetrofitServiceWrapper` class
or implements an `IServiceWrapper` interface — a generic type will be used to bind 
the service class otherwise own class used as a key.
```java
package ...;

import com.google.auto.service.AutoService;
import io.github.anominy.kreedzsdk.clientapi.IBanService;
import io.github.anominy.kreedzsdk.clientapi.IKreedzService;
import io.github.anominy.kreedzsdk.client.services.requests.ban.GetBansRequest;
import io.github.anominy.uwretrofit.services.impl.RetrofitServiceWrapper;
import retrofit2.Retrofit;

@AutoService(IKreedzService.class)
public final class BanService extends RetrofitServiceWrapper<IBanService> implements IKreedzService {
    
    public BanService(Retrofit retrofit) {
        super(retrofit, IBanService.class);
    }
    
    public GetBansRequest.Manager bans() {
        return new GetBansRequest.Manager(this.service);
    }
}
```

<hr>

```java
KreedzClient client = new KreedzClient();

// Returns an associated service instance as Object so we need to cast it.
BanService banService = (BanService) client.getService(IBanService.class);
```

## Dependencies
 * [anominy/uwutils](https://github.com/anominy/uwutils)
 * [anominy/steam-id](https://github.com/anominy/steam-id)
 * [anominy/uw-retrofit](https://github.com/anominy/uw-retrofit)
 * [anominy/uw-gson](https://github.com/anominy/uw-gson)
 * [anominy/gson-native](https://github.com/anominy/gson-native)
 * [retrofit2/retrofit](https://github.com/square/retrofit)
 * [retrofit2/converter-gson](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)
 * [google/gson](https://github.com/google/gson)
 * [joda/time](https://github.com/JodaOrg/joda-time)


## License
Licensed under the [Apache-2.0 license](./LICENSE).

