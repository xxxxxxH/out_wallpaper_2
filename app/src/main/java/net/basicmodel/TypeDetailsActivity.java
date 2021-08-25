package net.basicmodel;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.adapter.ImgAdapter;
import net.entity.DataEntity;
import net.http.RequestService;
import net.http.RetrofitUtils;
import net.utils.Constanst;
import net.utils.DialogManager;
import net.utils.OnItemClickListener;
import net.utils.ToastUtils;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TypeDetailsActivity extends AppCompatActivity implements OnItemClickListener {

    private TextView title;
    private RecyclerView recyclerView;
    private ImgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_typedetails);
        DialogManager.getInstance().showLoadingDlg(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        Retrofit retrofit = new RetrofitUtils().retrofit();
        retrofit.create(RequestService.class).getTypeDetailsData(type).enqueue(new Callback<List<DataEntity>>() {
            @Override
            public void onResponse(Call<List<DataEntity>> call, Response<List<DataEntity>> response) {
                initView(response.body(), type);
                DialogManager.getInstance().closeLoadingDlg();
            }

            @Override
            public void onFailure(Call<List<DataEntity>> call, Throwable t) {
                new ToastUtils(TypeDetailsActivity.this, "no data").showToast();
                DialogManager.getInstance().closeLoadingDlg();
            }
        });
    }

    private void initView(List<DataEntity> data, String type) {
        title = findViewById(R.id.detailsTitle);
        recyclerView = findViewById(R.id.recyclerviewDetails);
        title.setText(type);
        adapter = new ImgAdapter(data, null, this, TypeDetailsActivity.this, this, Constanst.TYPE_TYPE_DETAILS);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(int position, @NotNull String flag, @NotNull String sought) {
        if (TextUtils.equals(flag, Constanst.TYPE_TYPE_DETAILS)) {
            Intent intent = new Intent(this, GalleryActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("data", (Serializable) adapter.getData());
            startActivity(intent);
        }

    }
}
