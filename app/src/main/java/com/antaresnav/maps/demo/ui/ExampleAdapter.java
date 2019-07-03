package com.antaresnav.maps.demo.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antaresnav.maps.demo.R;
import com.antaresnav.maps.demo.ui.ExampleAdapter.ExampleViewHolder;

import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleViewHolder> {

    private final LayoutInflater inflater;
    private List<ExampleDetails> dataSource;
    private Context context;

    public ExampleAdapter(Context context, List<ExampleDetails> examples) {
        this.inflater = LayoutInflater.from(context);
        this.dataSource = examples;
        this.context = context;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.example_list_item, parent, false);
        return new ExampleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        if (dataSource != null) {
            ExampleDetails current = dataSource.get(position);
            int sdkTypeId = 0;
            switch (current.getSdkType()) {
                case ExampleDetails.SDK_TYPE_ANTARES:
                    holder.sdkLogo.setImageResource(R.drawable.ic_antaresmaps_logo);
                    sdkTypeId = R.string.description_sdk_type_antares;
                    break;
                case ExampleDetails.SDK_TYPE_GOOGLE:
                    holder.sdkLogo.setImageResource(R.drawable.ic_googlemaps_logo);
                    sdkTypeId = R.string.description_sdk_type_google;
                    break;
            }
            holder.title.setText(current.getTitleId());
            holder.description.setText(TextUtils.concat(context.getText(current.getDescriptionId()), context.getText(sdkTypeId)));
        }
    }

    @Override
    public int getItemCount() {
        return dataSource != null ? dataSource.size() : 0;
    }

    public ExampleDetails getItemAt(int position) {
        if (position < 0 || position >= dataSource.size()) {
            return null;
        }
        return dataSource.get(position);
    }

    static class ExampleViewHolder extends RecyclerView.ViewHolder {
        private final ImageView sdkLogo;
        private final TextView title;
        private final TextView description;

        private ExampleViewHolder(View itemView) {
            super(itemView);
            sdkLogo = itemView.findViewById(R.id.example_sdk_logo);
            title = itemView.findViewById(R.id.example_title);
            description = itemView.findViewById(R.id.example_description);
        }
    }
}
