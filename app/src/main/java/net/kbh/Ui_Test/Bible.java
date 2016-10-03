package net.kbh.Ui_Test;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by pc on 2016-09-25.
 */
public class Bible {
    static Context context = MainActivity.context;

    static int bbNum;

    // 성경책 이름 클릭
    static AdapterView.OnItemClickListener bookClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            bbNum = position;

            if (MainActivity.rg_bibleSelect.getCheckedRadioButtonId() == R.id.rb_bookOnly) selectBook(position);
            else bJangSetup(position);
        }
    };

    static int[] bible_jang = {50, 40, 27, 36, 34, 24, 21, 4, 31, 24, 22, 25, 29, 36, 10, 13, 10, 42, 150, 31, 12, 8, 66, 52, 5, 48, 12, 14, 3, 9, 1, 4, 7, 3, 3, 3, 2, 14, 4, 28, 16, 24, 21, 28, 16, 16, 13, 6, 6, 4, 4, 5, 3, 6, 4, 3, 1, 13, 5, 5, 3, 5, 1, 1, 1, 22};

    // 장(편) 클릭
    static AdapterView.OnItemClickListener jangClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Bible.selectJang(""+(position+1));
        }
    };

    // 장 리스트뷰 선택시 처리
    static void selectJang(String jang) {
        /*
        String srch = MainActivity.bible_han.get(bbNum) + " " + jang + ":1";
        String path = bibleFilePath(MainActivity.bible_eng.get(bbNum));
        // openBible(path, srch);
         */
        MainActivity.uToast("성경책 열어서 [" + jang + "] 찾아줍니다.");
    }

    // 책 리스트뷰 선택시 처리
    static void selectBook(int position) {
        // bbNum = position; // 이미(bookClickListener) 설정됨
        /*
        String path = bibleFilePath(MainActivity.bible_eng.get(position));
        ArrayList<String> list = MainActivity.al_fileHistory;
        String srch = "";
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).startsWith(path)) {
                srch = list.get(i);
                int split = srch.indexOf("#");
                if (split != -1) srch = srch.substring(split+1);
                break;
            }
        }
        openBible(path, srch);
         */
        MainActivity.uToast("선택한 성경책 [" + MainActivity.bible_full.get(position) + "] 열어줍니다.");
    }

    // 책 리스트 뿌려서 보이기
    static void bBookSetup() {
        final ListView lv_bBook = (ListView) ((Activity)context).findViewById(R.id.lv_bBook);
        final ArrayAdapter<String> bookAdapter = new ArrayAdapter<String>(context, R.layout.single, R.id.text1, MainActivity.bible_full); // 어댑터 생성
        new Thread(new Runnable(){
            @Override
            public void run() {
            //여기서 UI 작업을 수행하면 Exception 발생 함.
                MainActivity.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //여기서는 UI 작업 가능.
                        lv_bBook.setAdapter(bookAdapter);
                    }
                });
            }
        }).start();
        lv_bBook.setOnItemClickListener(Bible.bookClickListener);
    }

    // 장 리스트 뿌려서 보이기
    static void bJangSetup(int position) {
        MainActivity.uToast("bJangSetup");
        int jangNum = Bible.bible_jang[position];
        String[] jangs = new String[jangNum];

        String book = (MainActivity.bible_full.get(position) == "시편") ? " 편" : " 장";
        for (int i=0; i<jangNum; i++) jangs[i] = "제 " + (i+1) + book;
        ArrayAdapter<String> jangAdapter = new ArrayAdapter<String>(context, R.layout.single_r, R.id.text1, jangs); // 어댑터 생성
        ListView lv_bJang = (ListView) ((Activity)context).findViewById(R.id.lv_bJang);
        lv_bJang.setAdapter(jangAdapter);
        lv_bJang.setOnItemClickListener(Bible.jangClickListener);
    }

}