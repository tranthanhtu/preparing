package vn.tranthanhtu.cudermovenew.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.tranthanhtu.cudermovenew.R;
import vn.tranthanhtu.cudermovenew.controllers.MapAdapter;
import vn.tranthanhtu.cudermovenew.controllers.RecyclerItemClickListener;
import vn.tranthanhtu.cudermovenew.models.Constants;
import vn.tranthanhtu.cudermovenew.models.MapModel;
import vn.tranthanhtu.cudermovenew.utils.AnimationUtils;
import vn.tranthanhtu.cudermovenew.utils.ConvertLocationUtils;
import vn.tranthanhtu.cudermovenew.utils.LoadMapRandom;
import vn.tranthanhtu.cudermovenew.utils.NotificationUtils;

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListener.OnItemClickListener {

    private static final String TAG = MainActivity.class.toString();

    private RecyclerView rvMap;
    private ImageView imvCuder;

    private Button btnLeft;
    private Button btnRight;
    private Button btnUp;
    private Button btnDown;
    private Button btnClear;
    private Button btnStart;
    private Button btnRandomMap;
    private int positionCurrent;
    private MapAdapter adapter;

    private LinearLayout llRow;
    private LinearLayout llColumn;

    private final ArrayList<String> listMove = new ArrayList<>();
    private ListView lvMove;
    private ArrayAdapter adapterListMove;

    private TextView tvLocationStart;
    private TextView tvLocationEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReferences();
        LoadMapRandom.loadRandomMap(this);
        setRowMap();
        setColumMap();

        setAdapterListMove();
        setAdapter();

        addListenerMove();
        addListenerStartMove();
        addListenerMap();

    }

    private void setColumMap() {
        for (int i = Constants.FIRST_NUMBER_APHABET;
             i < Constants.FIRST_NUMBER_APHABET + Constants.SPAN_COUNT;
             i++
                ) {
            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER);
            textView.setText(String.valueOf((char) i));
            textView.setHeight(Constants.HEIGHT_ITEM_MAP);
            textView.setWidth(Constants.WIDTH_ITEM_MAP);
            llColumn.addView(textView);
        }
    }

    private void setRowMap() {
        for (int i = 1; i <= Constants.SPAN_COUNT; i++) {
            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER);
            textView.setText(String.valueOf(i));
            textView.setHeight(Constants.HEIGHT_ITEM_MAP);
            textView.setWidth(Constants.WIDTH_ITEM_MAP);
            llRow.addView(textView);
        }
    }


    private void addListenerStartMove() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvLocationEnd.length() != 0) {
                    AnimationUtils.playAnimation(listMove, imvCuder, adapter, getApplicationContext(), tvLocationEnd.getText().toString());
                    Log.d(TAG, String.format("onClick: %s", positionCurrent));
                } else {
                    NotificationUtils.notification(Constants.NOTI_INSERT_LOCATION_START
                            , getApplicationContext()
                    );
                }
            }
        });
    }

    private void addListenerMap() {
        rvMap.addOnItemTouchListener(new RecyclerItemClickListener(this, this));

    }

    private void getReferences() {
        rvMap = (RecyclerView) findViewById(R.id.rv_map);

        imvCuder = (ImageView) findViewById(R.id.imv_cuder);

        tvLocationStart = (TextView) findViewById(R.id.location_start);

        btnDown = (Button) findViewById(R.id.btn_down);
        btnLeft = (Button) findViewById(R.id.btn_left);
        btnRight = (Button) findViewById(R.id.btn_right);
        btnUp = (Button) findViewById(R.id.btn_up);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnRandomMap = (Button) findViewById(R.id.btn_randomMap);

        lvMove = (ListView) findViewById(R.id.lv_move);

        tvLocationEnd = (TextView) findViewById(R.id.tv_location_end);

        llRow = (LinearLayout) findViewById(R.id.ll_row);
        llColumn = (LinearLayout) findViewById(R.id.ll_column);
    }

    private void setAdapter() {
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, Constants.SPAN_COUNT);
        rvMap.setHasFixedSize(true);
        rvMap.setLayoutManager(layoutManager);
        adapter = new MapAdapter();
        rvMap.setAdapter(adapter);
    }


    private void addListenerMove() {
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMove.add(Constants.MOVE_LEFT);
                adapterListMove.notifyDataSetChanged();
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMove.add(Constants.MOVE_RIGHT);
                adapterListMove.notifyDataSetChanged();
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMove.add(Constants.MOVE_UP);
                adapterListMove.notifyDataSetChanged();
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMove.add(Constants.MOVE_DOWN);
                adapterListMove.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMove.clear();
                adapterListMove.notifyDataSetChanged();
            }
        });

        btnRandomMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapModel.list.clear();
                adapter.notifyDataSetChanged();
                LoadMapRandom.loadRandomMap(getApplicationContext());
            }
        });
    }

    private void setAdapterListMove() {
        adapterListMove = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                listMove);
        lvMove.setAdapter(adapterListMove);
    }


    @Override
    public void onItemClick(View childView, int position) {
        Log.d(TAG, "onItemClick: ");
        positionCurrent = position + Constants.DIFFERENCE_POSITION_IN_LIST;
        Log.d(TAG, String.format("onItemClick: %s", position));
        String optionSquare = MapModel.list.get(position).getSquare();
        switch (optionSquare) {
            case Constants.MEETING:
                NotificationUtils.notification(
                        Constants.NOTI_CLICK_MEETING,
                        getApplicationContext());
                break;
            case Constants.IMPEDIMENT:
                NotificationUtils.notification(
                        Constants.NOTI_CLICK_IMPEDIMENT,
                        getApplicationContext());
                break;
            case Constants.WAY:
                ConvertLocationUtils.setLocationStart(position, imvCuder);
                String locationText =
                        ConvertLocationUtils.convertPositionToString(position);
                tvLocationStart.setText(locationText);
                imvCuder.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onItemLongPress(View childView, int position) {
        Log.d(TAG, "onItemLongPress: ");
        String locationEnd = ConvertLocationUtils
                .convertPositionToString(ConvertLocationUtils.convertToLocation
                        (
                                childView.getX(),
                                childView.getY() + Constants.HEIGHT_ITEM_MAP
                        )
                );
        Log.d(TAG, String.format("onItemLongPress: %s", locationEnd));
        tvLocationEnd.setText(locationEnd);
    }
}
