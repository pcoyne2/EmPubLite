package com.commonsware.empublite;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Patrick Coyne on 9/17/2017.
 */

public interface BookUpdateInterface {
    @GET("/misc/empublite-update.json")
    Call<BookUpdateInfo> update();
}
