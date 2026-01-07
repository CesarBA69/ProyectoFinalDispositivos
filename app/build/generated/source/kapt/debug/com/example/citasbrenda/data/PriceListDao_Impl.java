package com.example.citasbrenda.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PriceListDao_Impl implements PriceListDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PriceListEntity> __insertionAdapterOfPriceListEntity;

  private final EntityInsertionAdapter<PriceItemEntity> __insertionAdapterOfPriceItemEntity;

  private final EntityDeletionOrUpdateAdapter<PriceListEntity> __updateAdapterOfPriceListEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteList;

  private final SharedSQLiteStatement __preparedStmtOfDeactivateAll;

  private final SharedSQLiteStatement __preparedStmtOfUpdateItemPrice;

  private final SharedSQLiteStatement __preparedStmtOfDeleteItem;

  public PriceListDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPriceListEntity = new EntityInsertionAdapter<PriceListEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `price_lists` (`id`,`name`,`isActive`,`createdAtMillis`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PriceListEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindLong(4, entity.getCreatedAtMillis());
      }
    };
    this.__insertionAdapterOfPriceItemEntity = new EntityInsertionAdapter<PriceItemEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `price_items` (`id`,`priceListId`,`serviceId`,`price`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PriceItemEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getPriceListId());
        statement.bindLong(3, entity.getServiceId());
        statement.bindDouble(4, entity.getPrice());
      }
    };
    this.__updateAdapterOfPriceListEntity = new EntityDeletionOrUpdateAdapter<PriceListEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `price_lists` SET `id` = ?,`name` = ?,`isActive` = ?,`createdAtMillis` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PriceListEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindLong(4, entity.getCreatedAtMillis());
        statement.bindLong(5, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteList = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM price_lists WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeactivateAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE price_lists SET isActive = 0";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateItemPrice = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE price_items SET price = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteItem = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM price_items WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertList(final PriceListEntity list, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPriceListEntity.insertAndReturnId(list);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertItem(final PriceItemEntity item, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPriceItemEntity.insertAndReturnId(item);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateList(final PriceListEntity list, final Continuation<? super Integer> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __updateAdapterOfPriceListEntity.handle(list);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteList(final long id, final Continuation<? super Integer> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteList.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteList.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object deactivateAll(final Continuation<? super Integer> arg0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeactivateAll.acquire();
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeactivateAll.release(_stmt);
        }
      }
    }, arg0);
  }

  @Override
  public Object updateItemPrice(final long itemId, final double price,
      final Continuation<? super Integer> arg2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateItemPrice.acquire();
        int _argIndex = 1;
        _stmt.bindDouble(_argIndex, price);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, itemId);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateItemPrice.release(_stmt);
        }
      }
    }, arg2);
  }

  @Override
  public Object deleteItem(final long itemId, final Continuation<? super Integer> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteItem.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, itemId);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteItem.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object getAllLists(final Continuation<? super List<PriceListEntity>> arg0) {
    final String _sql = "SELECT * FROM price_lists ORDER BY createdAtMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PriceListEntity>>() {
      @Override
      @NonNull
      public List<PriceListEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAtMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtMillis");
          final List<PriceListEntity> _result = new ArrayList<PriceListEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PriceListEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAtMillis;
            _tmpCreatedAtMillis = _cursor.getLong(_cursorIndexOfCreatedAtMillis);
            _item = new PriceListEntity(_tmpId,_tmpName,_tmpIsActive,_tmpCreatedAtMillis);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @Override
  public Object getItems(final long priceListId,
      final Continuation<? super List<PriceItemWithService>> arg1) {
    final String _sql = "\n"
            + "        SELECT \n"
            + "            pi.id AS itemId,\n"
            + "            pi.priceListId AS priceListId,\n"
            + "            pi.serviceId AS serviceId,\n"
            + "            s.name AS serviceName,\n"
            + "            pi.price AS price\n"
            + "        FROM price_items pi\n"
            + "        INNER JOIN services s ON s.id = pi.serviceId\n"
            + "        WHERE pi.priceListId = ?\n"
            + "        ORDER BY s.name ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, priceListId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PriceItemWithService>>() {
      @Override
      @NonNull
      public List<PriceItemWithService> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfItemId = 0;
          final int _cursorIndexOfPriceListId = 1;
          final int _cursorIndexOfServiceId = 2;
          final int _cursorIndexOfServiceName = 3;
          final int _cursorIndexOfPrice = 4;
          final List<PriceItemWithService> _result = new ArrayList<PriceItemWithService>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PriceItemWithService _item;
            final long _tmpItemId;
            _tmpItemId = _cursor.getLong(_cursorIndexOfItemId);
            final long _tmpPriceListId;
            _tmpPriceListId = _cursor.getLong(_cursorIndexOfPriceListId);
            final long _tmpServiceId;
            _tmpServiceId = _cursor.getLong(_cursorIndexOfServiceId);
            final String _tmpServiceName;
            if (_cursor.isNull(_cursorIndexOfServiceName)) {
              _tmpServiceName = null;
            } else {
              _tmpServiceName = _cursor.getString(_cursorIndexOfServiceName);
            }
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            _item = new PriceItemWithService(_tmpItemId,_tmpPriceListId,_tmpServiceId,_tmpServiceName,_tmpPrice);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Object getActiveList(final Continuation<? super PriceListEntity> arg0) {
    final String _sql = "SELECT * FROM price_lists WHERE isActive = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PriceListEntity>() {
      @Override
      @Nullable
      public PriceListEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAtMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtMillis");
          final PriceListEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAtMillis;
            _tmpCreatedAtMillis = _cursor.getLong(_cursorIndexOfCreatedAtMillis);
            _result = new PriceListEntity(_tmpId,_tmpName,_tmpIsActive,_tmpCreatedAtMillis);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @Override
  public Object getPrice(final long priceListId, final long serviceId,
      final Continuation<? super Double> arg2) {
    final String _sql = "\n"
            + "        SELECT price\n"
            + "        FROM price_items\n"
            + "        WHERE priceListId = ? AND serviceId = ?\n"
            + "        LIMIT 1\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, priceListId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, serviceId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getDouble(0);
            }
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg2);
  }

  @Override
  public Object existsItem(final long priceListId, final long serviceId,
      final Continuation<? super Boolean> arg2) {
    final String _sql = "\n"
            + "        SELECT EXISTS(\n"
            + "            SELECT 1 FROM price_items\n"
            + "            WHERE priceListId = ? AND serviceId = ?\n"
            + "        )\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, priceListId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, serviceId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp == null ? null : _tmp != 0;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg2);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
