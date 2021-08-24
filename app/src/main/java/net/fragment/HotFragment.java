package net.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.basicmodel.ImgAdapter;
import net.basicmodel.MainActivity;
import net.basicmodel.R;
import net.entity.DataEntity;
import net.http.RequestService;
import net.http.RetrofitUtils;
import net.utils.Constanst;
import net.utils.OnItemClickListener;
import net.utils.ToastUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HotFragment extends Fragment implements OnItemClickListener {

    RecyclerView recyclerView;

    private List<DataEntity> data;
    private ImgAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_hot, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).showDlg();
        initData(view);
    }

    private void initView(List<DataEntity> dataEntities, View view) {
        recyclerView = view.findViewById(R.id.recyclerviewHot);
        adapter = new ImgAdapter(dataEntities, getActivity(), getActivity(), this, Constanst.TYPE_HOT);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void initData(View view) {
        Retrofit retrofit = new RetrofitUtils().retrofit();
        retrofit.create(RequestService.class).getHotData().enqueue(new Callback<List<DataEntity>>() {
            @Override
            public void onResponse(Call<List<DataEntity>> call, Response<List<DataEntity>> response) {
                Log.i("xxxxxxH", "response = " + response);
                initView(response.body(),view);
                ((MainActivity) getActivity()).closeDlg();
            }

            @Override
            public void onFailure(Call<List<DataEntity>> call, Throwable t) {
                new ToastUtils(getActivity(), "no data").showToast();
                ((MainActivity) getActivity()).closeDlg();
            }
        });
    }

    @Override
    public void onItemClick(int position, @NotNull String flag) {

    }
}
