package com.onlylemi.test2_asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * MyAsyncTask
 *
 * @author: onlylemi
 * @time: 2016-06-05 9:10
 */
public class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    private static final String TAG = MyAsyncTask.class.getSimpleName();

    private ImageView imageView;
    private TextView textView;

    public MyAsyncTask(ImageView imageView, TextView textView) {
        this.imageView = imageView;
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        textView.setText("0");
        Log.i(TAG, "onPreExecute");

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Log.i(TAG, "doInBackground");

        String urlStr = params[0];
        Bitmap bitmap = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        HttpURLConnection conn = null;
        try {
            out = new ByteArrayOutputStream();

            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.connect();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = conn.getInputStream();
                //bitmap = BitmapFactory.decodeStream(in);

                int allLength = conn.getContentLength();
                int currentLength = 0;

                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);

                    currentLength += len;
                    // 设置进度，这个方法执行后会执行 onProgressUpdate(Integer... values)
                    publishProgress(currentLength / allLength * 100);
                }
                bitmap = BitmapFactory.decodeByteArray(out.toByteArray(), 0, allLength);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
                if (null != conn) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.i(TAG, "onProgressUpdate");

        // 更新进度
        if (null != textView) {
            textView.setText(values[0].toString());
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        Log.i(TAG, "onPostExecute");

        // 设置图片
        if (null != imageView && null != bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onCancelled(Bitmap bitmap) {
        Log.i(TAG, "onCancelled");

    }
}
