package net.kbh.Ui_Test;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    static Handler mHandler = null;

    LinearLayout inc_main, inc_search, inc_explorer, inc_bmfh, inc_select; // 슬라이드 레이아웃
    static View[] views = null;

    static RadioGroup rg_bibleSelect;

    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        mHandler = new Handler();

        inc_main = (LinearLayout) findViewById(R.id.inc_main);
        inc_search = (LinearLayout) findViewById(R.id.inc_search);
        inc_explorer = (LinearLayout) findViewById(R.id.inc_explorer);
        inc_bmfh = (LinearLayout) findViewById(R.id.inc_bmfh);
        inc_select = (LinearLayout) findViewById(R.id.inc_select);

        views = new View[]{inc_search, inc_explorer, inc_bmfh};

        rg_bibleSelect = (RadioGroup) findViewById(R.id.rg_bibleSelect);
        RadioButton rb_bookOnly = (RadioButton) findViewById(R.id.rb_bookOnly);
        RadioButton rb_bookJang = (RadioButton) findViewById(R.id.rb_bookJang);
        if (! rb_bookOnly.isChecked() && ! rb_bookJang.isChecked() ) { rb_bookJang.setChecked(true);
        }

        Bible.bBookSetup();
    }

    // 토스트 띄우기
    static void uToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_select) selectPage(inc_select);
        if (id == R.id.action_search) selectPage(inc_search);
        if (id == R.id.action_explorer) selectPage(inc_explorer);
        if (id == R.id.action_bmth) selectPage(inc_bmfh);

        return super.onOptionsItemSelected(item);
    }

    // 성경선택 페이지 열기/닫기
    public void bibleSelect(View view) { selectPage(inc_select); }
    // 페이지 닫기
    public void closePage(View view) { view.setVisibility(View.GONE); }
    // 페이지 열기
    public void openPage(View view) {
        for(View page: MainActivity.views) page.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }
    // 페이지 열기
    public void selectPage(View view) {
        if (view.getId()==R.id.inc_select) {
            if (view.getVisibility()==View.VISIBLE) closePage(view);
            else openPage(view);
        }
        else {
            if (view.getVisibility()==View.VISIBLE) closePage(view);
            else openPage(view);
        }
    }

    // 백키 처리
    @Override
    public void onBackPressed() {
        if (inc_select.getVisibility()==View.VISIBLE) inc_select.setVisibility(View.GONE);
        else {
            View opened = null;
            for(View page:views) {
                if(page.getVisibility() == View.VISIBLE) {
                    opened = page;
                    break;
                }
            }
            if (opened == null) finish();
            else openPage(inc_main);
        }
    }

    static ArrayList<String> bible_full = new ArrayList<String>(Arrays.asList(new String[]{"창세기", "출애굽기", "레위기", "민수기", "신명기", "여호수아", "사사기", "룻기", "사무엘상", "사무엘하", "열왕기상", "열왕기하", "역대상", "역대하", "에스라", "느헤미야", "에스더", "욥기", "시편", "잠언", "전도서", "아가", "이사야", "예레미야", "예레미야애가", "에스겔", "다니엘", "호세아", "요엘", "아모스", "오바댜", "요나", "미가", "나훔", "하박국", "스바냐", "학개", "스가랴", "말라기", "마태복음", "마가복음", "누가복음", "요한복음", "사도행전", "로마서", "고린도전서", "고린도후서", "갈라디아서", "에베소서", "빌립보서", "골로새서", "데살로니가전서", "데살로니가후서", "디모데전서", "디모데후서", "디도서", "빌레몬서", "히브리서", "야고보서", "베드로전서", "베드로후서", "요한일서", "요한이서", "요한삼서", "유다서", "요한계시록"}));

}