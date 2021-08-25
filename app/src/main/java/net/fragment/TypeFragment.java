package net.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.adapter.ImgAdapter;
import net.basicmodel.R;
import net.basicmodel.TypeDetailsActivity;
import net.utils.Constanst;
import net.utils.OnItemClickListener;
import net.utils.TypeDataManager;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class TypeFragment extends Fragment implements OnItemClickListener {

    private RecyclerView recyclerview;
    private ImgAdapter adapter;
    private HashMap<String, String> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_hot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        recyclerview = view.findViewById(R.id.recyclerviewHot);
    }

    private void initData() {
        data = TypeDataManager.getInstance().getData(getActivity());
        initRecyclerView(data);
    }

    private void initRecyclerView(HashMap<String, String> data) {
        adapter = new ImgAdapter(null, data, getActivity(), getActivity(), this, Constanst.TYPE_TYPE);
        adapter.setKeyValues();
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position, @NotNull String flag, String sought) {
        if (TextUtils.equals(flag, Constanst.TYPE_TYPE)) {
            Intent intent = new Intent(getActivity(), TypeDetailsActivity.class);
            intent.putExtra("type", sought);
            getActivity().startActivity(intent);
        }
    }
}
