package com.example.masuo.weatherforecast;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Masuo on 2016/03/05.
 */
public class ImageLoaderTask
        extends AsyncTask<ImageLoaderTask.Request, Void, ImageLoaderTask.Result> {

    // AsyncTaskにダウンロードする画像のURLと表示したいImageViewを指定するクラス
    public static class Request {
        public final ImageView imageView;
        public final String url;

        public Request(ImageView imageView, String url) {
            this.imageView = imageView;
            this.url = url;
        }
    }

    // AsyncTaskの実行後にダウンロードした画像と表示するImageViewの２つの情報を
    // onPostExecuteに返すためのクラス
    public static class Result {
        public final ImageView imageView;
        public final Bitmap bitmap;
        public final Exception exception;

        public Result(ImageView imageView, Bitmap bitmap) {
            this.imageView = imageView;
            this.bitmap = bitmap;
            this.exception = null;
        }

        public Result(ImageView imageView, Exception exception) {
            this.imageView = imageView;
            this.bitmap = null;
            this.exception = exception;
        }
    }

    // バックグラウンドでURLが示す画像をダウンロードする
    @Override
    protected Result doInBackground(Request... params) {
        Request request = params[0];
        Result result = null;

        HttpURLConnection connection = null;

        try {
            URL url = new URL(request.url);
            connection = (HttpURLConnection) url.openConnection();

            Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());

            result = new Result(request.imageView, bitmap);


        } catch (IOException e) {
            result = new Result(request.imageView, e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);

        if (result.bitmap != null) {
            result.imageView.setImageBitmap(result.bitmap);
        }
    }


}
