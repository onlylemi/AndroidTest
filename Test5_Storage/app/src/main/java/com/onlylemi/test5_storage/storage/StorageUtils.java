package com.onlylemi.test5_storage.storage;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * StorageUtils
 *
 * @author: onlylemi
 * @time: 2016-06-13 16:34
 */
public class StorageUtils {

    private Context context;

    public StorageUtils(Context context) {
        this.context = context;
    }

    /**
     * 写入数据到内部存储
     *
     * @param filename
     * @param data
     * @return
     */
    public String writeFileToInternal(String filename, byte[] data) {
        FileOutputStream outputStream = null;
        File cacheDir = context.getCacheDir();
        String filepath = new File(cacheDir, filename).getAbsolutePath();
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filepath;
    }

    /**
     * 从内部存储中读取文件数据
     *
     * @param filename
     * @return
     */
    public byte[] readFileFromInternal(String filename) {
        FileInputStream inputStream = null;
        File cacheDir = context.getCacheDir();
        String filepath = new File(cacheDir, filename).getAbsolutePath();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            inputStream = context.openFileInput(filepath);

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outputStream.toByteArray();
    }

    /**
     * 写入数据到外部存储（SD card）
     *
     * @param filename
     * @param data
     * @return
     */
    public String writeFileFromExternal(String filename, byte[] data) {
        File directory = Environment.getExternalStorageDirectory();
        String filepath = new File(directory, filename).getAbsolutePath();

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            FileOutputStream outputStream = null;
            try {
                if (!new File(filepath).exists()) {
                    outputStream = new FileOutputStream(filepath);
                    outputStream.write(data, 0, data.length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != outputStream) {
                        outputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return filepath;
    }

    /**
     * 从外部储存读取数据
     *
     * @param filename
     * @return
     */
    public byte[] getFileFromExternal(String filename) {
        File directory = Environment.getExternalStorageDirectory();
        String filepath = new File(directory, filename).getAbsolutePath();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(filepath);

                if (new File(filepath).exists()) {
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, len);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != inputStream) {
                        inputStream.close();
                    }
                    if (null != outputStream) {
                        outputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return outputStream.toByteArray();
    }
}
