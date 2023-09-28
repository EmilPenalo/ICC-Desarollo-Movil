package basic.android.retrofitexample;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import basic.android.retrofitexample.adapters.Adapter;
import basic.android.retrofitexample.api.APIClient;
import basic.android.retrofitexample.api.APIInterface;
import basic.android.retrofitexample.databinding.ActivityRetrofitBinding;
import basic.android.retrofitexample.dto.User;
import basic.android.retrofitexample.dto.UserList;
import basic.android.retrofitexample.dto.UserSingle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class RetrofitActivity extends AppCompatActivity {

    ActivityRetrofitBinding binding;
//    private List<User> userList;
    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRetrofitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.recyclerViewTasks;

        APIInterface api = APIClient.getClient().create(APIInterface.class);

        adapter = new Adapter(new ArrayList<>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        api.findAll().enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body().getData();
                    adapter.setUsers(users);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.w("onFailure", t.getLocalizedMessage());
                call.cancel();
            }
        });

//        api.find(2).enqueue(new Callback<UserSingle>() {
//            @Override
//            public void onResponse(Call<UserSingle> call, Response<UserSingle> response) {
//                Log.w("onResponse", response.body().data.toString());
//            }
//
//            @Override
//            public void onFailure(Call<UserSingle> call, Throwable t) {
//                Log.w("onFailure", t.getLocalizedMessage());
//                call.cancel();
//            }
//        });
//        api.findAll().enqueue(new Callback<UserList>() {
//            @Override
//            public void onResponse(Call<UserList> call, Response<UserList> response) {
//                Log.d("onResponse", response.code() + "");
//            }
//
//            @Override
//            public void onFailure(Call<UserList> call, Throwable t) {
//                Log.i("onFailure", t.getMessage());
//                call.cancel();
//            }
//        });
//
//        api.find(1).enqueue(new Callback<UserSingle>() {
//            @Override
//            public void onResponse(Call<UserSingle> call, Response<UserSingle> response) {
//                Log.d("onResponse", response.code() + "");
//            }
//
//            @Override
//            public void onFailure(Call<UserSingle> call, Throwable t) {
//                Log.i("onFailure", t.getMessage());
//                call.cancel();
//            }
//        });


    }
}