package com.application.sample.selectcardviewprototype.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.application.sample.selectcardviewprototype.app.R;
import com.application.sample.selectcardviewprototype.app.model.ShoppingItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davide on 04/09/15.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ShoppingItemViewHolder> {
    private final List<ShoppingItem> shoppingItemList;
    private final WeakReference<OnItemSelectedListenerCustom> listener;


    public RecyclerviewAdapter(ArrayList<ShoppingItem> list,
                               WeakReference<OnItemSelectedListenerCustom> listener) {

        this.shoppingItemList = list;
        this.listener = listener;
    }

    @Override
    public ShoppingItemViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_item_row, parent, false);
        ShoppingItemViewHolder vh = new ShoppingItemViewHolder(view, listener);
        view.setOnClickListener(vh);
        return vh;
    }

    @Override
    public void onBindViewHolder(ShoppingItemViewHolder vh, int position) {
        vh.bindTo(shoppingItemList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return (null != shoppingItemList ? shoppingItemList.size() : 0);
    }

    public List<ShoppingItem> getAllItems() {
        return shoppingItemList;
    }

    /**
     * View Holder
     */
    public static class ShoppingItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView descriptionTextView;
        private TextView textView;
        private final WeakReference<OnItemSelectedListenerCustom> listener;
        private int currentPosition;

        public ShoppingItemViewHolder(View view, WeakReference<OnItemSelectedListenerCustom> listener) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.nameTextViewId);
            this.descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextViewId);
            this.listener = listener;
        }

        /**
         *
         * @param item
         * @param currentPosition
         */
        public void bindTo(ShoppingItem item, int currentPosition) {
            this.currentPosition = currentPosition;
            textView.setText(item.getName());
            descriptionTextView.setText(item.getDescription());
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.get().onItemClicked(currentPosition);
            }
        }
    }

    /**
     * interface to handle onItemClicked
     */
    public interface OnItemSelectedListenerCustom {
        void onItemClicked(int selectedPosition);
    }

}