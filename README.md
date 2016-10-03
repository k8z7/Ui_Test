# Ui_Test

이 프로젝트는 (1) one 액티비티 multi 레이아웃, (2) 슬라이딩 페이지 구현 두 가지를 보여줍니다.

- one 액티비티 multi 레이아웃

액티비티를 따로 만들지 않고 MainActivity 하나로 여러 페이지를 사용하도록 구성하였습니다.

https://github.com/k8z7/Ui_Test/blob/master/app/src/main/res/layout/activity_main.xml
<br>위 메인 레이아웃 파일에는 5개의 include layout 코드가 있습니다.
<br>main_page 레이아웃이 바닥에 깔리고 그 위에 차례로 4개의 보이지 않는 레이아웃이 겹쳐 있습니다.

그 4개 중 3개의 레이아웃은 1개가 열리면 다른 2개는 닫히게 되어 있습니다.
<br>마지막 bible_select 레이아웃은 독자적으로 열리고 닫힙니다.

- 슬라이딩 페이지 구현

위 메인 레이아웃 파일의 다음 코드를 참고하세요.

        <FrameLayout ... android:layout_height="match_parent">

            <!-- 일반 레이아웃 -->
            ...

            <!-- 열기/닫기 버튼 레이아웃 -->
            <LinearLayout
                ...
                android:layout_width="wrap_content"
                android:layout_height="wrap_content">
                <ImageView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="-15dp"
                    android:layout_marginBottom="70dp"
                    android:src="@drawable/slide"
                    android:onClick="bibleSelect" />
            </LinearLayout>

        </FrameLayout>
        
FrameLayout 안에 일반 레이아웃으로 화면을 꽉 채우되, 그 아래 버튼용 이미지는 wrap_content로 띄우고 있습니다.
<br>그러므로 이 버튼은 어떤 페이지가 열려 있든지 항상 위에 떠서 보이게 됩니다.
<br>그 버튼을 클릭하면 bibleSelect 메소드가 실행되는데, 슬라이딩 페이지가 보이지 않으면 보이게 하고 보이면 보이지 않게 해줍니다.
