package com.example.viibegif.demoOne;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.viibegif.R;

import java.util.List;

public class ViibeAdapter extends RecyclerView.Adapter<ViibeAdapter.ViibeViewHolder>{

    private Context context;
    private List<ViibeBO> viibeBOList;
    ViibeAdapterListenerOne viibeAdapterListenerOne;

    public ViibeAdapter(Context context, List<ViibeBO> viibeBOList, ViibeAdapterListenerOne viibeAdapterListenerOne) {
        this.context = context;
        this.viibeBOList = viibeBOList;
        this.viibeAdapterListenerOne = viibeAdapterListenerOne;
    }

    @NonNull
    @Override
    public ViibeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viibe_list, null);
        ViibeViewHolder rcv = new ViibeViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViibeViewHolder holder, final int position) {

//        animate(holder);
        holder.txtMessage.setText(viibeBOList.get(position).getMessage());
        holder.txtSubmessage.setText(viibeBOList.get(position).getSubmessage());

        holder.cancel_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_animation));
                Toast.makeText(context,"Canceled",Toast.LENGTH_SHORT).show();
                viibeAdapterListenerOne.onClickListener(holder.parentLayout,position);
            }
        });

        holder.tick_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.click_animation));
                Toast.makeText(context,"Accepted",Toast.LENGTH_SHORT).show();
                viibeAdapterListenerOne.onClickListener(holder.parentLayout,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return viibeBOList.size();
    }


    public class ViibeViewHolder extends RecyclerView.ViewHolder{

        public TextView txtMessage;
        public TextView txtSubmessage;
        public ImageView cancel_icon,tick_icon;
        public RelativeLayout parentLayout;

        public ViibeViewHolder(final View itemView) {
            super(itemView);

            txtMessage = itemView.findViewById(R.id.txtMessage);
            txtSubmessage = itemView.findViewById(R.id.txtSubmessage);
            cancel_icon = itemView.findViewById(R.id.cancel_icon);
            tick_icon = itemView.findViewById(R.id.tick_icon);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }

//    public void animate(RecyclerView.ViewHolder viewHolder) {
//        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate_overshoot_interpolator);
//        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
//    }

    public interface ViibeAdapterListenerOne {
        void onClickListener(View viewParent,int position);
    }

}

