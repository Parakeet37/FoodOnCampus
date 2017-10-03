package pt.com.ua.foodoncampus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailFragment extends Fragment {
    private Item item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = (Item) getArguments().getSerializable("item");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail,
                container, false);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(item.getMeals());
        tvTitle.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }

    // ItemDetailFragment.newInstance(menu)
    public static ItemDetailFragment newInstance(Item item) {
        ItemDetailFragment fragmentDemo = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("item", item);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }
}