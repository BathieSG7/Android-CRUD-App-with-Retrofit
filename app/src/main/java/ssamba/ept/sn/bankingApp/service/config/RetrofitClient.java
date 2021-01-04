package ssamba.ept.sn.bankingApp.service.config;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient(String url){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor) //add logging interceptor as the last interceptor,
                .build();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("dd-MM-yyyy");
        //gsonBuilder.registerTypeAdapter(Date.class, new DateConverter()); is an alternative to the previous line wich give more personalizations
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();

        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create( gsonBuilder.create()))
                    .client(httpClient)
                    .build();
        }

        return retrofit;
    }

}