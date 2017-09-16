package network;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by omarfaroque on 9/15/17.
 */

public class Interactor implements IService {
    public static final String BASE_URL = "https://*****.herokuapp.com";
    private static Retrofit retrofit = null;

    /**
     * constructor
     * */
    public Interactor(){

    }
    /**
     * Create the service
     * @return {@link IRespayNetworkService}
     * */
    public IRespayNetworkService createService(){
        return getService();
    }
    @Override
    public IRespayNetworkService getService() {
        getClient(BASE_URL);
        return retrofit.create(IRespayNetworkService.class);
    }

    /**
     * Create the {@link Retrofit} client to make the api call
     * */
    public static Retrofit getClient(String baseUrl) {
        retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getHttpClientBuilder())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * Client Builder
     * @return {@link OkHttpClient}
     * */
    private static OkHttpClient getHttpClientBuilder() {

        // Setup Logging interceptor
        HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor();
        mLoggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BODY);

        // Setup OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.MILLISECONDS) //
                .addInterceptor(mLoggingInterceptor)
                .addNetworkInterceptor(new MyInterceptor());
        return builder.build();
    }

    /**
     * Custom Interceptor to add header
     * */
    private static class MyInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            // Customize the request
            Request request = original.newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("accessToken","OMAR_FERDOUS_MASUD@!#")
                    .build();

            okhttp3.Response response = chain.proceed(request);
            response.cacheResponse();
            return response;
        }
    }
}
