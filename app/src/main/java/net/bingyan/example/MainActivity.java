package net.bingyan.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.jakewharton.rxbinding.view.RxView;

import net.bingyan.goodjob.GoodJob;
import net.bingyan.goodjob.IGoodJob;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {
    private ImageView mGood1;
    private ImageView mGood2;
    private ImageView mBookmark;
    private ImageView mCollection;

    private IGoodJob mGoodJobGood1;
    private IGoodJob mGoodJobGood2;
    private IGoodJob mGoodJobBookmark;
    private IGoodJob mGoodJobCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGood1();
        initGood2();
        initBookmark();
        initCollection();
        initReset();
    }

    private void initGood1() {
        mGood1 = (ImageView) findViewById(R.id.activity_main_good_1);
        mGoodJobGood1 = null;

        RxView.clicks(findViewById(R.id.activity_main_container_good_1))
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    if (mGoodJobGood1 == null) {
                        mGoodJobGood1 = new GoodJob(this)
                                .setPathStraight(0, mGood1.getHeight() * (-2))
                                .setEffectIsImage(R.mipmap.good_checked);
                    }

                    mGoodJobGood1.show(mGood1);
                });
    }

    private void initGood2() {
        mGood2 = (ImageView) findViewById(R.id.activity_main_good_2);
        mGoodJobGood2 = null;

        RxView.clicks(findViewById(R.id.activity_main_container_good_2))
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    if (mGoodJobGood2 == null) {
                        mGoodJobGood2 = new GoodJob(this)
                                .setPathStraight(0, mGood2.getHeight() * (-2))
                                .setEffectIsText("+1", Color.parseColor("#ff0000"), 12);
                    }

                    mGoodJobGood2.show(mGood2);
                });
    }

    private void initBookmark() {
        mBookmark = (ImageView) findViewById(R.id.activity_main_bookmark);
        mGoodJobBookmark = null;

        RxView.clicks(findViewById(R.id.activity_main_container_bookmark))
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    if (mGoodJobBookmark == null) {
                        mGoodJobBookmark = new GoodJob(this)
                                .setPathStraight(0, mBookmark.getHeight() * (-2))
                                .setEffectIsImage(R.mipmap.bookmark_checked);
                    }

                    mGoodJobBookmark.show(mBookmark);
                });
    }

    private void initCollection() {
        mCollection = (ImageView) findViewById(R.id.activity_main_collection);
        mGoodJobCollection = null;

        RxView.clicks(findViewById(R.id.activity_main_container_collection))
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    if (mGoodJobCollection == null) {
                        mGoodJobCollection = new GoodJob(this)
                                .setPathStraight(0, mCollection.getHeight() * (-2))
                                .setEffectIsImage(R.mipmap.collection_checked);
                    }

                    mGoodJobCollection.show(mCollection);
                });
    }

    private void initReset() {
        Button target = (Button) findViewById(R.id.activity_main_reset);

        RxView.clicks(target)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> {
                    if (mGoodJobGood1 != null) {
                        mGoodJobGood1.cancel();
                        mGoodJobGood1 = null;
                    }
                    if (mGoodJobGood2 != null) {
                        mGoodJobGood2.cancel();
                        mGoodJobGood2 = null;
                    }
                    if (mGoodJobBookmark != null) {
                        mGoodJobBookmark.cancel();
                        mGoodJobBookmark = null;
                    }
                    if (mGoodJobCollection != null) {
                        mGoodJobCollection.cancel();
                        mGoodJobCollection = null;
                    }

                    mGood1.setImageResource(R.mipmap.good);
                    mGood2.setImageResource(R.mipmap.good);
                    mBookmark.setImageResource(R.mipmap.bookmark);
                    mCollection.setImageResource(R.mipmap.collection);
                });
    }

}
