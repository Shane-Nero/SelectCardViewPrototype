package com.application.sample.selectcardviewprototype.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.application.sample.selectcardviewprototype.app.R;
import com.application.sample.selectcardviewprototype.app.model.ContactItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davide on 04/09/15.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ShoppingItemViewHolder> {
    private final List<ContactItem> itemList;
    private final WeakReference<OnItemSelectedListenerCustom> listener;


    public RecyclerviewAdapter(ArrayList<ContactItem> list,
                               WeakReference<OnItemSelectedListenerCustom> listener) {
        this.itemList = list;
        this.listener = listener;
    }

    @Override
    public ShoppingItemViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_row, parent, false);
        ShoppingItemViewHolder vh = new ShoppingItemViewHolder(view, listener);
        view.setOnClickListener(vh);
        return vh;
    }

    @Override
    public void onBindViewHolder(ShoppingItemViewHolder vh, int position) {
        vh.bindTo(itemList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return (null != itemList ? itemList.size() : 0);
    }

    public List<ContactItem> getAllItems() {
        return itemList;
    }

    /**
     * View Holder
     */
    public static class ShoppingItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private final TextView nameTextView;
        private TextView surnameTextView;
        private final WeakReference<OnItemSelectedListenerCustom> listener;
        private int currentPosition;

        public ShoppingItemViewHolder(View view, WeakReference<OnItemSelectedListenerCustom> listener) {
            super(view);
            this.nameTextView = (TextView) view.findViewById(R.id.nameTextViewId);
            this.surnameTextView = (TextView) view.findViewById(R.id.surnameTextViewId);
            this.listener = listener;
        }

        /**
         *
         * @param item
         * @param currentPosition
         */
        public void bindTo(ContactItem item, int currentPosition) {
            this.currentPosition = currentPosition;
            nameTextView.setText(item.getName());
            surnameTextView.setText(item.getSurname());
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