/*
 * Copyright (C) 2013 Wolfram Rittmeyer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.user.contentproviderapp.lentitems;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

import com.example.user.contentproviderapp.provider.LentItemsContract;

import org.greenrobot.eventbus.EventBus;

public class LentItemService extends IntentService {

    /**
     * The action for deleting an item. please use this object.
     * <b>Note:</b> Do not use the actual value, because I use "=="
     * comparisons for performance reasons!
     */
    public static final String ACTION_DELETE_ITEM = "actionDeleteItem";
    /**
     * The action for updating an item. please use this object.
     * <b>Note:</b> Do not use the actual value, because I use "=="
     * comparisons for performance reasons!
     */
    public static final String ACTION_UPDATE_ITEM = "actionUpdateItem";
    /**
     * The action for inserting an item. please use this object.
     * <b>Note:</b> Do not use the actual value, because I use "=="
     * comparisons for performance reasons!
     */
    public static final String ACTION_CREATE_ITEM = "actionCreateItem";

    public LentItemService() {
        super("LentItemService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String action = intent.getAction();
        // note: the "==" comparison only works
        // if you're inside the same process!
        if (ACTION_CREATE_ITEM == action) {
            onActionCreateItem(intent);
        } else if (ACTION_DELETE_ITEM == action) {
            onActionDeleteItem(intent);
        } else if (ACTION_UPDATE_ITEM == action) {
            onActionUpdateItem(intent);
        }
    }

    /**
     * Deals with intents of type ACTION_UPDATE_ITEM. This
     * basically updates an item within the data store.
     */
    private void onActionUpdateItem(Intent intent) {
        long itemId = intent.getLongExtra(BaseColumns._ID, -1);
        if (itemId == -1) {
            throw new IllegalStateException("Cannot update record with itemId == -1");
        }
        String name = intent.getStringExtra(LentItemsContract.Items.NAME);
        String borrower = intent.getStringExtra(LentItemsContract.Items.BORROWER);
        ContentValues values = new ContentValues();
        values.put(LentItemsContract.Items.NAME, name);
        values.put(LentItemsContract.Items.BORROWER, borrower);
        ContentResolver resolver = getContentResolver();
        Uri updateUri = ContentUris.withAppendedId(LentItemsContract.Items.CONTENT_URI, itemId);
        long resultCount = resolver.update(updateUri, values, null, null);
        if (resultCount == 0) {
            String data = "Couldn't update item with id " + String.valueOf(itemId);
            EventBus.getDefault().post(new LentItemFormFragment.MessageEvent(data));
            return;
        }
        String photoUriStr = intent.getStringExtra(LentItemsContract.Photos._DATA);
        // always insert;
        // works, because it's an insertWithOnConflict!
        insertPhoto(values, resolver, photoUriStr, itemId);
    }

    /**
     * Deals with intents of type ACTION_DELETE_ITEM. This
     * basically deletes an item from the data store.
     */
    private void onActionDeleteItem(Intent intent) {
        long itemId = intent.getLongExtra(BaseColumns._ID, -1);
        if (itemId == -1) {
            throw new IllegalStateException("Cannot delete record with itemId == -1");
        }
        Uri delUri = ContentUris.withAppendedId(LentItemsContract.Items.CONTENT_URI, itemId);
        long resultCount = getContentResolver().delete(delUri, null, null);
        if (resultCount == 0) {
            EventBus.getDefault().post(new LentItemFormFragment.MessageEvent("Couldn't delete item with id " + String.valueOf(itemId)));
            return;
        }
    }

    /**
     * Deals with intents of type ACTION_CREATE_ITEM. This
     * basically adds a new item to the data store.
     */
    private void onActionCreateItem(Intent intent) {
        String name = intent.getStringExtra(LentItemsContract.Items.NAME);
        String borrower = intent.getStringExtra(LentItemsContract.Items.BORROWER);
        String photoUriStr = intent.getStringExtra(LentItemsContract.Photos._DATA);
        if (TextUtils.isEmpty(name) &&
                TextUtils.isEmpty(borrower) &&
                TextUtils.isEmpty(photoUriStr)) {
            return;
        }
        ContentValues values = new ContentValues();
        values.put(LentItemsContract.Items.NAME, name);
        values.put(LentItemsContract.Items.BORROWER, borrower);
        ContentResolver resolver = getContentResolver();
        Uri result = resolver.insert(LentItemsContract.Items.CONTENT_URI, values);
        if (result == null) {
            EventBus.getDefault().post(new LentItemFormFragment.MessageEvent("Couldn't add item"));
            return;
        }
        long itemId = ContentUris.parseId(result);
        insertPhoto(values, resolver, photoUriStr, itemId);
        EventBus.getDefault().post(new LentItemFormFragment.MessageEvent(String.valueOf(itemId)));
    }

    /**
     * Ads a photo to the data store if the photoUriStr is neither null nor empty.
     */
    private void insertPhoto(ContentValues values, ContentResolver resolver,
                             String photoUriStr, long itemId) {
        if (TextUtils.isEmpty(photoUriStr)) {
            return;
        }
        values.clear();
        values.put(LentItemsContract.Photos._DATA, photoUriStr);
        values.put(LentItemsContract.Photos.ITEMS_ID, itemId);
        Uri photoResult = getContentResolver().insert(LentItemsContract.Photos.CONTENT_URI, values);
        if (photoResult == null) {
            EventBus.getDefault().post(new LentItemFormFragment.MessageEvent("Couldn't add photo"));
        }
    }

}
