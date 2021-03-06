package org.telekinesis.app;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.telekinesis.client.Window;
import org.telekinesis.telekinesis.R;

import java.util.List;

public class WindowsAdapter extends RecyclerView.Adapter<WindowsAdapter.MyViewHolder> {
    private static String TAG = "WindowsAdapter";

    public interface ClickListener {
        void onClick(Window window);
    }

    private List<Window> windows;
    private ClickListener clickListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View root;
        public TextView textView;
        public ImageView imageView;
        public MyViewHolder(View root) {
            super(root);
            textView = root.findViewById(R.id.textView);
            imageView = root.findViewById(R.id.imageView);
        }
    }

    public WindowsAdapter(List<Window> windows, ClickListener clickListener) {
        this.windows = windows;
        this.clickListener = clickListener;
    }

    @Override
    public WindowsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_window, parent, false);
        return new MyViewHolder(root);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Window window = windows.get(position);
        holder.textView.setText(window.title);
        Picasso.get().load(window.iconLink).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(window);
            }
        });
    }

    @Override
    public int getItemCount() {
        return windows.size();
    }

    public void setWindows(List<Window> windows) {
        this.windows = windows;
        notifyDataSetChanged();
    }
}
