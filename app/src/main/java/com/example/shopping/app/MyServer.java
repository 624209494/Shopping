package com.example.shopping.app;

import com.example.shopping.Beans.ShoppingBenas;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MyServer {
    String getUtl="http://fun.51fanli.com/";
    @GET
    Observable<ShoppingBenas> getShopping(@Url String url);

}
