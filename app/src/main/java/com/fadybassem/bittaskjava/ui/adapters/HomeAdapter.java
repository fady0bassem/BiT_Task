package com.fadybassem.bittaskjava.ui.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fadybassem.bittaskjava.R;
import com.fadybassem.bittaskjava.data.model.pojo.HomeModel;
import com.fadybassem.bittaskjava.utils.RoundedCornersTransform;
import com.fadybassem.bittaskjava.utils.TouchImageView;
import com.github.loadingview.LoadingView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<HomeModel.Datum> datumList;

    public HomeAdapter(Context context, List<HomeModel.Datum> datumList) {
        this.context = context;
        this.datumList = datumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeModel.Datum datum = datumList.get(position);

        if (datum != null) {
            if (datum.getImage() != null) {
                holder.loadingview.start();

                Picasso.get().load(datum.getImage())
                        .transform(new RoundedCornersTransform())
                        .error(android.R.drawable.ic_menu_report_image)
                        .into(holder.imageview, new Callback() {
                            @Override
                            public void onSuccess() {
                                holder.loadingview.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                                holder.loadingview.setVisibility(View.GONE);
                            }
                        });

                holder.itemView.setOnClickListener(v -> {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View imageDialog = inflater.inflate(R.layout.dialog_image, null);
                    Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(imageDialog);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                    TouchImageView imgImage = imageDialog.findViewById(R.id.imgImage);
                    LoadingView loadingview = imageDialog.findViewById(R.id.loadingview);
                    loadingview.start();
                    Picasso.get().load(datumList.get(position).getImage())
                            .error(android.R.drawable.ic_menu_report_image)
                            .into(imgImage, new Callback() {
                                @Override
                                public void onSuccess() {loadingview.setVisibility(View.GONE);
                                }

                                @Override
                                public void onError(Exception e) {
                                    loadingview.setVisibility(View.GONE);
                                }
                            });

                    dialog.show();


                });

            }
        }
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        LoadingView loadingview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.imageview);
            loadingview = itemView.findViewById(R.id.loadingview);
        }
    }


}
