package vn.tranthanhtu.cudermovenew.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
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
import vn.tranthanhtu.cudermovenew.controllers.Simulation;
import vn.tranthanhtu.cudermovenew.models.Constants;
import vn.tranthanhtu.cudermovenew.models.MapModel;
import vn.tranthanhtu.cudermovenew.utils.AnimationUtils;
import vn.tranthanhtu.cudermovenew.utils.ConvertLocationUtils;
import vn.tranthanhtu.cudermovenew.utils.LoadMapRandom;
import vn.tranthanhtu.cudermovenew.utils.NotificationUtils;

public class MainActivity extends AppCompatActivity
        implements RecyclerItemClickListener.OnItemClickListener {

    private static final String TAG = MainActivity.class.toString();

    private RecyclerView rvMap;

    private ImageView imvCuder;

    private Button btnLeft, btnRight, btnUp, btnDown, btnClear, btnStart, btnRandomMap;

    private MapAdapter adapter;

    private ArrayAdapter adapterListMove;

    private LinearLayout llRow, llColumn, llNumberButtonMap;

    private final ArrayList<String> listMove = new ArrayList<>();
    public static final ArrayList<Integer> listStepMovePosition = new ArrayList<>();

    private ListView lvMove;

    private TextView tvLocationStart, tvLocationEnd;

    private int positionStart, positionEnd, positionCurrent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReferences();
        LoadMapRandom.loadRandomMap(this);
        setRowMap();
        setColumMap();
        setButtonOfMap();

        setAdapterListMove();
        setAdapter();

        addListenerMove();
        addListenerStartMove();
        addListenerMap();

    }

    /* Declare references  */
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
        llNumberButtonMap = (LinearLayout) findViewById(R.id.ll_numberMap);
    }

    /* Setup Column Of Map */
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

    /* Setup Row Of Map */
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

    /* Setup number Button of Map */
    private void setButtonOfMap(){
        for (int i = 1; i <= LoadMapRandom.listNameMap.size(); i++){
            Button button = new Button(this);
            button.setText(String.valueOf(i));
            final int j = i - 1;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvLocationEnd.setText(Constants.NULL_TEXT);
                    tvLocationStart.setText(Constants.NULL_TEXT);
                    listStepMovePosition.clear();
                    listMove.clear();
                    adapterListMove.notifyDataSetChanged();
                    imvCuder.setVisibility(View.INVISIBLE);
                    MapModel.list.clear();
                    adapter.notifyDataSetChanged();
                    LoadMapRandom.loadMap(getApplicationContext(), LoadMapRandom.listNameMap.get(j));
                }
            });
            llNumberButtonMap.addView(button);
        }
    }

    /* Setup Adapter RecycleView Map  */
    private void setAdapter() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, Constants.SPAN_COUNT);
        rvMap.setHasFixedSize(true);
        rvMap.setLayoutManager(layoutManager);
        adapter = new MapAdapter();
        rvMap.setAdapter(adapter);
    }

    /* Setup Adapter List Step Move */
    private void setAdapterListMove() {
        adapterListMove = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                listMove);
        lvMove.setAdapter(adapterListMove);
    }

    /* Setup Click Button: Move && RandomMap && ResetMove  */
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
                listStepMovePosition.clear();
                listMove.clear();
                adapterListMove.notifyDataSetChanged();
                tvLocationEnd.setText(Constants.NULL_TEXT);
                tvLocationStart.setText(Constants.NULL_TEXT);
                imvCuder.setVisibility(View.INVISIBLE);
                MapModel.list.clear();
                adapter.notifyDataSetChanged();
                LoadMapRandom.checkRandom(getApplicationContext());
            }
        });

    }

    /* Setup Click Button Start */
    private void addListenerStartMove() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvLocationEnd.length() != 0 && tvLocationStart.length() != 0) {
                    getShortPath();
                    AnimationUtils.playAnimation(
                            listMove,
                            imvCuder,
                            adapter,
                            getApplicationContext(),
                            tvLocationEnd.getText().toString()
                    );
                    Log.d(TAG, String.format("onClick: %s", positionCurrent));
                } else {
                    NotificationUtils.notification(getString(R.string.NOTI_INSERT_LOCATION_START)
                            , getApplicationContext()
                    );
                }
            }
        });
    }

    /* Setup Click Map */
    private void addListenerMap() {
        rvMap.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
    }

    /* Setup Single Click In Map */
    @Override
    public void onItemClick(View childView, int position) {
        Log.d(TAG, "onItemClick: ");
    }

    /* Setup Long Click In Map */
    @Override
    public void onItemLongPress(final View childView, final int position) {
        PopupMenu popup = new PopupMenu(MainActivity.this, childView);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getTitle().equals(getString(R.string.locationstart))){
                    positionStart = position;
                    Log.d(TAG, "onItemClick: ");
                    positionCurrent = position + Constants.DIFFERENCE_POSITION_IN_LIST;
                    Log.d(TAG, String.format("onItemClick: %s", position));
                    String optionSquare = MapModel.list.get(position).getSquare();
                    switch (optionSquare) {
                        case Constants.MEETING:
                            NotificationUtils.notification(
                                    getString(R.string.NOTI_CLICK_MEETING),
                                    getApplicationContext());
                            break;
                        case Constants.IMPEDIMENT:
                            NotificationUtils.notification(
                                    getString(R.string.NOTI_CLICK_IMPEDIMENT),
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
                else if (item.getTitle().equals(getString(R.string.locationend))){
                    positionEnd = position;
                    Log.d(TAG, String.format("onItemLongPress: %s", position));
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
                return true;
            }
        });

        popup.show();//showing popup menu


    }

    /* Get Short Path By BFS algorithm */
    private void getShortPath() {
        Simulation simulation = new Simulation();
        simulation.cuderGraph();
        simulation.performBFS(positionStart, positionEnd);
        simulation.printGraph();
        Log.d(TAG, String.format("getShortPath: %s", listStepMovePosition));
        addAutoStepToList();

    }

    /* Get List Step Move From BFS algorithm */
    private void addAutoStepToList() {
        for (int i = 0; i < listStepMovePosition.size() - 1; i++) {
            Log.d(TAG, String.format("addAutoStepToList: i = %s", i));
            if (listStepMovePosition.get(i + 1) - listStepMovePosition.get(i)
                    == Constants.SPAN_COUNT) {
                listMove.add(Constants.MOVE_DOWN);
            } else if (listStepMovePosition.get(i) - listStepMovePosition.get(i + 1)
                    == Constants.SPAN_COUNT) {
                listMove.add(Constants.MOVE_UP);
                Log.d(TAG, "addAutoStepToList: UP");
            } else if (listStepMovePosition.get(i + 1) - listStepMovePosition.get(i)
                    == Constants.DIFFERENCE_POSITION_IN_LIST) {
                listMove.add(Constants.MOVE_RIGHT);
                Log.d(TAG, "addAutoStepToList: RIGHT");
            } else if (listStepMovePosition.get(i) - listStepMovePosition.get(i + 1)
                    == Constants.DIFFERENCE_POSITION_IN_LIST) {
                listMove.add(Constants.MOVE_LEFT);
                Log.d(TAG, "addAutoStepToList: LEFT");
            }
        }
        adapterListMove.notifyDataSetChanged();
        Log.d(TAG, String.format("addAutoStepToList: %s", listMove));
        if (listMove.size() == 0) {
            NotificationUtils.notification(getString(R.string.NOTI_NO_WAY_TO_MEETING), this);
        }
    }
}
