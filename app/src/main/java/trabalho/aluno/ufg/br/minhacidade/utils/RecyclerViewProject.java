package trabalho.aluno.ufg.br.minhacidade.utils;

/**
 * Created by Jonathas on 29/08/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewProject<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private final List<T> mData;

    protected final int rootType;

    private final boolean withHeader;

    protected final boolean withFooter;

    public RecyclerViewProject() {
        this(0, false, false);
    }

    public RecyclerViewProject(int rootType) {
        this(rootType, false, false);
    }

    protected RecyclerViewProject(int rootType, boolean withHeader, boolean withFooter) {
        this.rootType = rootType;
        this.withHeader = withHeader;
        this.withFooter = withFooter;
        this.mData = new ArrayList<>();

    }

    public abstract RecyclerView.ViewHolder getItemView(LayoutInflater inflater, ViewGroup parent);

    public abstract RecyclerView.ViewHolder getHeaderView(LayoutInflater inflater, ViewGroup parent);

    public abstract RecyclerView.ViewHolder getFooterView(LayoutInflater inflater, ViewGroup parent);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_ITEM) {
            return getItemView(inflater, parent);
        } else if (viewType == TYPE_HEADER) {
            return getHeaderView(inflater, parent);
        } else if (viewType == TYPE_FOOTER) {
            return getFooterView(inflater, parent);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }


    @Override
    public int getItemCount() {
        int dataCount = mData.size();
        if (withHeader)
            dataCount++;
        if (withFooter)
            dataCount++;
        return dataCount;
    }

    public int getDataCount() {
        int dataCount = mData.size();
        if (withHeader)
            dataCount++;

        return dataCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (withHeader && isPositionHeader(position))
            return TYPE_HEADER;
        if (withFooter && isPositionFooter(position))
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }

    public T getItem(int position) {
        position = getPosition(position);
        return mData.get(position);
    }

    public void add(T data) {
        mData.add(data);
        notifyDataSetChanged();
    }

    public void add(int position, T data) {
        position = getPosition(position);
        mData.add(position, data);
        notifyItemInserted(position);
    }

    public void addTop(T data) {
        mData.add(0, data);
        notifyItemInserted(0);
    }

    public void addAll(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addAll(int position, List<T> data) {
        position = getPosition(position);
        mData.addAll(position, data);
        notifyDataSetChanged();
    }

    public void set(T data, int position) {
        mData.set(position, data);
        notifyItemChanged(position, data);
    }

    public void remove(int position) {
        mData.remove(getPosition(position));
        notifyItemRemoved(position);
    }

    public void remove(T data) {
        mData.remove(data);
        notifyDataSetChanged();
    }

    public void update(int position, T data) {
        set(data, position);
        notifyItemChanged(position);
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public List<T> getAll() {
        return mData;
    }

    public void replace(int position, T data) {
        remove(position);
        add(position, data);
        notifyItemChanged(position);
    }

    public int getPosition(int position) {
        return withHeader ? position - 1 : position;
    }

    public int getRootType() {
        return rootType;
    }

    public Context getContext() {
        return context;
    }
}
