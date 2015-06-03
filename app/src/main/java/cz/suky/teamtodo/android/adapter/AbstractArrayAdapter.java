package cz.suky.teamtodo.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by suky on 3.6.15.
 */
public abstract class AbstractArrayAdapter<Data> extends ArrayAdapter<Data> {

    public AbstractArrayAdapter(Context context, List<Data> objects) {
        super(context, 0, objects);
    }

    protected abstract int getViewId();

    protected abstract void fillView(View view, Data object);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Data object = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(getViewId(), parent, false);
        }

        fillView(convertView, object);
        return convertView;
    }

    @SuppressWarnings("unchecked")
    protected <T> T findViewByIdTyped(View v, int id) {
        return (T) v.findViewById(id);
    }
}
