package com.jonalmeida.project420;

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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;

public class ImageViewAsyncLoader extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = ImageViewAsyncLoader.class.toString();
    private final WeakReference imageViewReference;
    private final WeakReference nameTextViewReference;
    private final Context imageViewContext;
    private static final String[] PHOTO_ID_PROJECTION = new String[] {
            ContactsContract.Contacts.PHOTO_ID,
            ContactsContract.Contacts.DISPLAY_NAME
    };

    private String nameFromContact = null;

    private static final String[] PHOTO_BITMAP_PROJECTION = new String[] {
            ContactsContract.CommonDataKinds.Photo.PHOTO
    };

    public ImageViewAsyncLoader(Context context, ImageView imageView, TextView name) {
        imageViewContext = context;
        imageViewReference = new WeakReference<>(imageView);
        nameTextViewReference = new WeakReference<>(name);
    }

    private Bitmap getContactImage(String address) {
        // Placeholder for actual code to retrieve user's profile image
//        return BitmapFactory.decodeResource(imageViewContext.getResources(),
//                imageViewContext.getResources()
//                .getIdentifier("ic_add", "drawable", imageViewContext.getPackageName()));

        Integer thumbnailId;
        if (address != null) {
            thumbnailId = fetchThumbnailId(address);
            if (thumbnailId != null) {
                return fetchThumbnail(thumbnailId);
            }
            Log.v(TAG, "Couldn't find thumbnail ID");
        }
        Log.v(TAG, "No phone number found");
        return null;
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
        TextView nameReference = (TextView) nameTextViewReference.get();
        if (imageView != null) {

            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            if (nameReference != null) {
                if (nameFromContact != null && !nameFromContact.isEmpty()) {
                    nameReference.setText(nameFromContact);
                }
            }
            // Only use this bit for testing. By default, we use the Android face icon.
            // This bit of code sets the icon to a 'tick' if we can find a profile image.

            else {
                imageView.setImageDrawable(imageView.getContext().getResources()
                        .getDrawable(R.drawable.ic_tick));
            }
        }

    }


    private Integer fetchThumbnailId(String phoneNumber) {

        final Uri uri = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI, Uri.encode(phoneNumber));


        try( final Cursor cursor = imageViewContext.
                getContentResolver().query(
                    uri, PHOTO_ID_PROJECTION, null, null,
                    ContactsContract.Contacts.DISPLAY_NAME + " ASC") ) {
            Integer thumbnailId = null;
            if (cursor.moveToFirst()) {
                thumbnailId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
                nameFromContact = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            }
            return thumbnailId;
        }

    }

    final Bitmap fetchThumbnail(final int thumbnailId) {

        final Uri uri = ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, thumbnailId);

        try( final Cursor cursor = imageViewContext.
                getContentResolver().query(uri, PHOTO_BITMAP_PROJECTION, null, null, null) ) {
            Bitmap thumbnail = null;
            if (cursor.moveToFirst()) {
                final byte[] thumbnailBytes = cursor.getBlob(0);
                if (thumbnailBytes != null) {
                    thumbnail = BitmapFactory.decodeByteArray(thumbnailBytes, 0, thumbnailBytes.length);
                }
            }
            return thumbnail;
        }

    }

    final String fetchContactName() {
        // So hacky it hurts!
        return nameFromContact;
    }

}
