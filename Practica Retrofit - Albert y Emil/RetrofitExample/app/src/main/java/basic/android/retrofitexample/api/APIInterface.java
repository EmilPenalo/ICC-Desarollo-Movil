package basic.android.retrofitexample.api;

import basic.android.retrofitexample.dto.User;
import basic.android.retrofitexample.dto.UserList;
import basic.android.retrofitexample.dto.UserSingle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("users")
    Call<UserList> findAll();

    @GET("users/{id}")
    Call<UserSingle> find(@Path("id") int id);


}
