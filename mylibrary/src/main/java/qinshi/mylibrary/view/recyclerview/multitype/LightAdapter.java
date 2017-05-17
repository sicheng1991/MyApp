package qinshi.mylibrary.view.recyclerview.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lufficc on 2016/8/31.
 */

public class LightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Class> models = new ArrayList<>();
    private final List<ViewHolderProvider> viewHolderProviders = new ArrayList<>();

    private final List data = new ArrayList<>();

    private List headers = new ArrayList<>();

    private List footers = new ArrayList<>();

    private LayoutInflater layoutInflater;

    private OnDataClickListener onDataClickListener;

    private OnHeaderClickListener onHeaderClickListener;

    private OnFooterClickListener onFooterClickListener;

    public void setOnFooterClickListener(OnFooterClickListener onFooterClickListener) {
        this.onFooterClickListener = onFooterClickListener;
    }

    public void setOnHeaderClickListener(OnHeaderClickListener onHeaderClickListener) {
        this.onHeaderClickListener = onHeaderClickListener;
    }

    public void setOnDataClickListener(OnDataClickListener onDataClickListener) {
        this.onDataClickListener = onDataClickListener;
    }


    public <Model, VH extends RecyclerView.ViewHolder> void register(@NonNull Class<Model> model, @NonNull ViewHolderProvider<Model, VH> viewHolderProvider) {
        synchronized (this) {
            if (models.contains(model))
                throw new IllegalArgumentException("You have registered this model:" + model.getName());
            models.add(model);
            viewHolderProviders.add(viewHolderProvider);
        }
    }

    public void unRegister(Class model) {
        int position = models.indexOf(model);
        if (CheckUtil.checkExits(position)) {
            synchronized (this) {
                models.remove(position);
                viewHolderProviders.remove(position);
            }
        }
    }


    public boolean isHeader(int position) {
        return position >= 0 && position < headers.size();
    }

    public boolean isFooter(int position) {
        return position < getItemCount() && position >= (headers.size() + data.size());
    }

    public boolean isData(int position) {
        return position < (headers.size() + data.size()) && position >= headers.size();
    }

    private int position2Footer(int position) {
        return position - headers.size() - data.size();
    }

    private int position2Data(int position) {
        return position - headers.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if (isHeader(position))
            type = models.indexOf(headers.get(position).getClass());
        else if (isFooter(position))
            type = models.indexOf(footers.get(position2Footer(position)).getClass());
        else
            type = models.indexOf(data.get(position2Data(position)).getClass());
        if (CheckUtil.haveYouRegistered(type)) {
            throw new NullPointerException("you don't register this model");
        }
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        return viewHolderProviders.get(viewType).onCreateViewHolder(layoutInflater, parent);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        if (isHeader(position)) {
            final Object header = headers.get(position);
            viewHolderProviders.get(type).onBindViewHolder(header, holder,position);
            if (onHeaderClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onHeaderClickListener.onHeaderClick(holder.getAdapterPosition(), header);
                    }
                });
            }
        } else if (isFooter(position)) {
            final Object footer = footers.get(position2Footer(position));
            viewHolderProviders.get(type).onBindViewHolder(footer, holder,position2Footer(position));
            if (onFooterClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onFooterClickListener.onFooterClick(position2Footer(holder.getAdapterPosition()), footer);
                    }
                });
            }
        } else {
            final Object data = this.data.get(position2Data(position));
            viewHolderProviders.get(type).onBindViewHolder(data, holder,position2Data(holder.getAdapterPosition()));
            if (onDataClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDataClickListener.onDataClick(position2Data(holder.getAdapterPosition()), data);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + headers.size() + footers.size();
    }


    public GridLayoutManager.SpanSizeLookup spanSizeLookup(int headerSpan, int footerSpan) {
        return new AdapterSpanSizeLookup(headerSpan, footerSpan);
    }

    private class AdapterSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        int headerSpan;
        int footerSpan;

        AdapterSpanSizeLookup(int headerSpan, int footerSpan) {
            this.headerSpan = headerSpan;
            this.footerSpan = footerSpan;
        }

        @Override
        public int getSpanSize(int position) {
            if (isHeader(position)) {
                return headerSpan;
            }
            if (isFooter(position))
                return footerSpan;
            return 1;
        }
    }

    /**
     * **********************************************************************************************
     */

    public List getData() {
        return data;
    }

    public List getHeaders() {
        return headers;
    }

    public List getFooters() {
        return footers;
    }


    public void setData(Collection initData) {
        data.clear();
        data.addAll(initData);
        notifyDataSetChanged();
    }

    public void addData(Collection<?> newData) {
        if (data.isEmpty()) {
            setData(newData);
            return;
        }
        int size = data.size();
        data.addAll(newData);
        notifyItemRangeInserted(size + headers.size(), newData.size());
    }


    public void addHeader(Object header) {
        headers.add(header);
        notifyItemInserted(headers.size() - 1);
    }

    public void updateHeader(int position, Object header) {
        if (isHeader(position)) {
            headers.set(position, header);
            notifyItemChanged(position);
        }
    }

    public void addHeader(int position, Object header) {
        headers.add(position, header);
        notifyItemInserted(position);
    }


    public void addData(Object newData) {
        data.add(newData);
        notifyItemInserted(headers.size() + data.size() - 1);
    }

    public boolean isDataEmpty() {
        return data.isEmpty();
    }

    public void addData(int position, Object newData) {
        data.add(position, newData);
        notifyItemInserted(headers.size() + position);
    }

    public void addFooter(Object footer) {
        footers.add(footer);
        notifyItemInserted(headers.size() + data.size() + footers.size() - 1);
    }

    public void updateFooter(int position, Object footer) {
        if (isFooter(position)) {
            footers.set(position, footer);
            notifyItemChanged(data.size() + headers.size() + position);
        }
    }

    public void addFooter(int position, Object footer) {
        footers.add(position, footer);
        notifyItemInserted(headers.size() + data.size() + position);
    }

    public void removeHeader(int position) {
        if (CheckUtil.checkInRange(headers.size(), position)) {
            headers.remove(position);
            notifyItemRemoved(position);
        }

    }

    public void removeData(int position) {
        if (CheckUtil.checkInRange(data.size(), position)) {
            data.remove(position);
            notifyItemRemoved(position + headers.size());
        }
    }

    public void removeFooter(int position) {
        if (CheckUtil.checkInRange(footers.size(), position)) {
            footers.remove(position);
            notifyItemRemoved(position + headers.size() + data.size());
        }
    }

    public void removeHeader(Object header) {
        int index = headers.indexOf(header);
        if (CheckUtil.checkExits(index)) {
            headers.remove(header);
            notifyItemRemoved(index);
        }
    }

    public void removeData(Object removingData) {
        int index = data.indexOf(removingData);
        if (CheckUtil.checkExits(index)) {
            data.remove(removingData);
            notifyItemRemoved(index + headers.size());
        }
    }

    public void removeFooter(Object footer) {
        int index = footers.indexOf(footer);
        if (CheckUtil.checkExits(index)) {
            footers.remove(footer);
            notifyItemRemoved(index + data.size() + headers.size());
        }
    }

    public void clearHeaders() {
        int size = headers.size();
        if (size != 0) {
            headers.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    public void clearData() {
        int size = data.size();
        if (size != 0) {
            data.clear();
            notifyDataSetChanged();
        }
    }


    public void clearFooters() {
        int size = footers.size();
        if (size != 0) {
            footers.clear();
            notifyItemRangeRemoved(headers.size() + data.size(), size);
        }
    }
}
