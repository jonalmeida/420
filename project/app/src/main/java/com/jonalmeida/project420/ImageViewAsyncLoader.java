package com.jonalmeida.project420;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class ImageViewAsyncLoader extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = ImageViewAsyncLoader.class.toString();
    private final WeakReference imageViewReference;
    private final Context imageViewContext;

    public ImageViewAsyncLoader(Context context, ImageView imageView) {
        imageViewContext = context;
        imageViewReference = new WeakReference<>(imageView);
    }

    private Bitmap getContactImage(String personId) {
        // Placeholder for actual code to retrieve user's profile image
        return BitmapFactory.decodeResource(imageViewContext.getResources(),
                imageViewContext.getResources()
                .getIdentifier("ic_add", "drawable", imageViewContext.getPackageName()));
    }

    @Override
    // Actual download method, run in the task thread
    protected Bitmap doInBackground(String... params) {
        // params comes from the execute() call: params[0] is the url.
        return getContactImage(params[0]);
    }

    @Override
    // Once the image is downloaded, associates it to the imageView
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        Log.d(TAG, "Image done loading!");

        ImageView imageView = (ImageView) imageViewReference.get();
        if (imageView != null) {

            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageDrawable(imageView.getContext().getResources()
                        .getDrawable(R.drawable.default_icon));
            }
        }

    }
}
