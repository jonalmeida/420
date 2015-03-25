package com.jonalmeida.project420;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class ImageViewAsyncLoader extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = ImageViewAsyncLoader.class.toString();
    private final WeakReference imageViewReference;
    private final Context imageViewContext;

    public ImageViewAsyncLoader(Context context, ImageView imageView) {
        imageViewContext = context;
        imageViewReference = new WeakReference<>(imageView);
    }

    private Bitmap getContactImage(String address) {
        // Placeholder for actual code to retrieve user's profile image
//        return BitmapFactory.decodeResource(imageViewContext.getResources(),
//                imageViewContext.getResources()
//                .getIdentifier("ic_add", "drawable", imageViewContext.getPackageName()));

        if (address != null) {
//            ContentResolver cr = imageViewContext.getContentResolver();
//            Uri phoneBookUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(address));
//
//            Cursor contact = cr.query(phoneBookUri,
//                    new String[]{ContactsContract.Contacts._ID}, null, null, null);
//
//            if (contact.moveToFirst()) {
//                long userId = contact.getLong(contact.getColumnIndex(ContactsContract.Contacts._ID));
//                phoneBookUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, userId);
//
//            }
//            else {
//                return BitmapFactory.decodeResource(imageViewContext.getResources(), android.R.drawable.ic_menu_report_image);
//            }
//
//            if (phoneBookUri != null) {
//                InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(
//                        cr, phoneBookUri);
//                if (input != null) {
//                    return BitmapFactory.decodeStream(input);
//                }
//            }

            Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, address);
            Log.d(TAG, "Image uri found: " + uri);
            InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(imageViewContext.getContentResolver(), uri);
            if (input == null) {
                Log.d(TAG, "Couldn't retrieve image, returning null.");
                return null;
            }
            Bitmap res = BitmapFactory.decodeStream(input);
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return res;
        } else {
            return null;
        }
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

        ImageView imageView = (ImageView) imageViewReference.get();
        if (imageView != null) {

            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            // Only use this bit for testing. By default, we use the Android face icon.
            // This bit of code sets the icon to a 'tick' if we can find a profile image.

            else {
                imageView.setImageDrawable(imageView.getContext().getResources()
                        .getDrawable(R.drawable.ic_tick));
            }
        }

    }
}
