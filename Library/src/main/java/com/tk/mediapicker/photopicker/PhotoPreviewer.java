package com.tk.mediapicker.photopicker;

import android.app.Activity;
import android.content.Intent;

import com.tk.mediapicker.photopicker.ui.activity.PhotoPreActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by TK on 2016/10/15.
 * 图片预览器
 */

public final class PhotoPreviewer {
    public static final String TAG = "PhotoPreviewer";

    public static void prePhotos(Activity activity, int requestCode, List<File> fileList, int index) {
        if (fileList == null || fileList.size() == 0) {
            throw new IllegalArgumentException("fileList is null!");
        }
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < fileList.size(); i++) {
            list.add(fileList.get(i).getAbsolutePath());
        }
        Intent intent = new Intent(activity, PhotoPreActivity.class);
        intent.putStringArrayListExtra(Constants.PrePhotoConstants.REQUEST_DATA, list);
        intent.putExtra(Constants.PrePhotoConstants.INDEX, index);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 接受回调，PhotoPreviewer(*^__^*)
     *
     * @param resultCode
     * @param data
     */
    public static final List<File> onActivityResult(int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return null;
        }
        if (data == null) {
            return null;
        }
        List<File> fileList = new ArrayList<File>();
        ArrayList<String> list = data.getStringArrayListExtra(Constants.PrePhotoConstants.REQUEST_DATA);
        for (int i = 0; i < list.size(); i++) {
            fileList.add(new File(list.get(i)));
        }
        return fileList;
    }


}