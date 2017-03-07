package vn.tranthanhtu.cudermovenew.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.tranthanhtu.cudermovenew.R;
import vn.tranthanhtu.cudermovenew.controllers.MapAdapter;
import vn.tranthanhtu.cudermovenew.controllers.RecyclerItemClickListener;
import vn.tranthanhtu.cudermovenew.models.MapModel;
import vn.tranthanhtu.cudermovenew.utils.AnimationUtils;
import vn.tranthanhtu.cudermovenew.utils.ConvertLocationUtils;
import vn.tranthanhtu.cudermovenew.utils.LoadMapRandom;
import vn.tranthanhtu.cudermovenew.utils.NotificationUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();
    private static final int SPAN_COUNT = 14;
    private static final String MOVE_LEFT = "moveLeft";
    private static final String MOVE_RIGHT = "moveRight";
    private static final String MOVE_UP = "moveUp";
    private static final String MOVE_DOWN = "moveDown";

    private StringBuilder stringBuilder;
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

    private final ArrayList<String> listMove = new ArrayList<>();
    private ListView lvMove;
    private ArrayAdapter adapterListMove;

    private TextView tvLocationStart;
    private EditText edtLocationEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReferences();
//        getMapFromText();
        LoadMapRandom.loadRandomMap(this);

        setAdapterListMove();
        setAdapter();

        addListenerMove();
        addListenerStartMove();
        addListenerMap();


    }


    private void addListenerStartMove() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtLocationEnd.length() != 0) {
                    AnimationUtils.playAnimation(listMove, imvCuder, adapter, getApplicationContext(), edtLocationEnd.getText().toString());
                    Log.d(TAG, String.format("onClick: %s", positionCurrent));
                } else {
                    NotificationUtils.notification("Nhập vị trí phòng họp cần tới"
                            , getApplicationContext()
                    );
                }
            }
        });
    }

    private void addListenerMap() {
        rvMap.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        positionCurrent = position + 1;
                        Log.d(TAG, String.format("onItemClick: %s", position));
                        String optionSquare = MapModel.list.get(position).getSquare();
                        switch (optionSquare) {
                            case "2":
                                NotificationUtils.notification(
                                        "Đây là vị trí phòng họp! Nhập lại vị trí bắt đầu",
                                        getApplicationContext());
                                break;
                            case "1":
                                NotificationUtils.notification(
                                        "Đây là vị trí chướng ngại vật! Nhập lại vị trí ban đầu",
                                        getApplicationContext());
                                break;
                            case "0":
                                ConvertLocationUtils.setLocationStart(position, imvCuder);
                                String locationText =
                                        ConvertLocationUtils.convertPositionToString(position);
                                tvLocationStart.setText(locationText);
                                imvCuder.setVisibility(View.VISIBLE);
                                break;
                        }
                    }
                }));
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

        edtLocationEnd = (EditText) findViewById(R.id.edt_location_end);
    }

    private void setAdapter() {
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, SPAN_COUNT);
        rvMap.setHasFixedSize(true);
        rvMap.setLayoutManager(layoutManager);
        adapter = new MapAdapter();
        rvMap.setAdapter(adapter);
    }

    private void getMapFromText() {
        try {
            List<String> listNameMap = new ArrayList<>();
            String [] list;
            list = getAssets().list("maps");
            for (String nameMap: list){
                Log.d(TAG, String.format("getMapFromText: list file map %s", nameMap));
                listNameMap.add(nameMap);
            }
            Log.d(TAG, String.format("getMapFromText: Map: %s", listNameMap.get(new Random().nextInt(listNameMap.size()))));

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("maps/" + listNameMap.get(new Random().nextInt(listNameMap.size()))), "UTF-8")
            );
            stringBuilder = new StringBuilder();
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                Log.d(TAG, String.format("onCreate: %s", mLine));
                stringBuilder.append(mLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, String.format("getMapFromText: %s", stringBuilder.length()));
        Log.d(TAG, String.format("getMapFromText: %s", stringBuilder.substring(0, 1)));
        for (int i = 0; i <= stringBuilder.length() - 1; i++) {
            MapModel.list.add(new MapModel(stringBuilder.substring(i, i + 1)));
        }
    }

    private void addListenerMove() {
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMove.add(MOVE_LEFT);
                adapterListMove.notifyDataSetChanged();
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMove.add(MOVE_RIGHT);
                adapterListMove.notifyDataSetChanged();
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMove.add(MOVE_UP);
                adapterListMove.notifyDataSetChanged();
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMove.add(MOVE_DOWN);
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


}
