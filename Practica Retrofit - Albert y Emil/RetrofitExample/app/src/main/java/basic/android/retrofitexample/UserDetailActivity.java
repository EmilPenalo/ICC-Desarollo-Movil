package basic.android.retrofitexample;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import basic.android.retrofitexample.dto.UserSingle;
import com.bumptech.glide.Glide;

import basic.android.retrofitexample.api.APIInterface;
import basic.android.retrofitexample.api.APIClient;
import basic.android.retrofitexample.dto.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailActivity extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "EXTRA_USER_ID";

    private TextView tvEmail;
    private ImageView ivAvatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        tvEmail = findViewById(R.id.tvEmail);
        ivAvatar = findViewById(R.id.ivAvatar);

        int userId = getIntent().getIntExtra(EXTRA_USER_ID, -1);
        if (userId != -1) {
            fetchUserDetails(userId);
        }
    }

    private void fetchUserDetails(int userId) {
        APIInterface api = APIClient.getClient().create(APIInterface.class);
        api.find(userId).enqueue(new Callback<UserSingle>() {
            @Override
            public void onResponse(Call<UserSingle> call, Response<UserSingle> response) {
                if (response.isSuccessful()) {
                    User user = response.body().getData();
                    tvEmail.setText(user.email);
                    Glide.with(UserDetailActivity.this)
                            .load(user.avatar)
                            .into(ivAvatar);
                }
            }

            @Override
            public void onFailure(Call<UserSingle> call, Throwable t) {
            }
        });
    }
}