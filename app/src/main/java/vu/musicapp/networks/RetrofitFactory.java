package vu.musicapp.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mac-vuongvu on 7/15/17.
 */

public class RetrofitFactory {
    private  static Retrofit retrofit;
    private  static  RetrofitFactory retrofitFactory = new RetrofitFactory();
    public  static  RetrofitFactory getInstance() {
        return retrofitFactory;
    }
    private RetrofitFactory() {
        retrofit = new Retrofit.Builder().baseUrl("https://music-api-for-tk.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public <T> T create(final Class<T> service) {
        return  retrofit.create(service);
    }
}

