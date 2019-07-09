package com.example.viibegif.demoOne;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;


import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.example.viibegif.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViibeActivity extends AppCompatActivity implements
        ViibeAdapter.ViibeAdapterListenerOne {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @BindView(R.id.linearlayout)
    LinearLayout linearLayout;

    @BindView(R.id.swiperefresh)
    RecyclerRefreshLayout swiperefresh;

    List<ViibeBO> viibeBOList;
    ViibeAdapter viibeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viibe);

        ButterKnife.bind(this);

        setTitle("Friend Request");

        setRecyclerViewOne();

        swiperefresh.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setRecyclerViewOne();
                swiperefresh.setRefreshing(false);
            }
        });
    }

    private void setRecyclerViewOne() {

        viibeBOList = ViibeGenerator.viibeBOList();

        viibeAdapter = new ViibeAdapter(this, viibeBOList,ViibeActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.scrollToPosition(0);
        int resId = R.anim.layout_animation_from_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        recyclerview.setLayoutAnimation(animation);

        recyclerview.setAdapter(viibeAdapter);
        recyclerview.setHasFixedSize(true);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setRemoveDuration(1000);
        recyclerview.setItemAnimator(itemAnimator);
        recyclerview.setLayoutManager(linearLayoutManager);
//        animate(recyclerview);

    }

    private void deleteItem(View viewParent,final int position) {

        Animation anim = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);
        anim.setDuration(500);
        viewParent.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                viibeBOList.remove(position); //Remove the current content from the array
                viibeAdapter.notifyDataSetChanged(); //Refresh list
            }

        }, anim.getDuration());
    }

    public void animate(RecyclerView recyclerView) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(this, R.anim.anticipate_overshoot_interpolator);
        recyclerView.setAnimation(animAnticipateOvershoot);
    }

    @Override
    public void onClickListener(View viewParent,int position) {
        deleteItem(viewParent,position);
    }
}
