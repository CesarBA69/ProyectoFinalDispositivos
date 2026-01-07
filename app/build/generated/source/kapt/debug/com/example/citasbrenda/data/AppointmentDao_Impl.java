package com.example.citasbrenda.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
import java.lang.Class;
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
public final class AppointmentDao_Impl implements AppointmentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AppointmentEntity> __insertionAdapterOfAppointmentEntity;

  private final SharedSQLiteStatement __preparedStmtOfCancel;

  private final SharedSQLiteStatement __preparedStmtOfComplete;

  public AppointmentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppointmentEntity = new EntityInsertionAdapter<AppointmentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `appointments` (`id`,`clientId`,`clientName`,`clientPhone`,`serviceId`,`serviceName`,`servicePrice`,`branchName`,`providerId`,`providerName`,`startTimeMillis`,`endTimeMillis`,`notes`,`status`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppointmentEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getClientId());
        if (entity.getClientName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getClientName());
        }
        if (entity.getClientPhone() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getClientPhone());
        }
        statement.bindLong(5, entity.getServiceId());
        if (entity.getServiceName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getServiceName());
        }
        statement.bindDouble(7, entity.getServicePrice());
        if (entity.getBranchName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getBranchName());
        }
        statement.bindLong(9, entity.getProviderId());
        if (entity.getProviderName() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getProviderName());
        }
        statement.bindLong(11, entity.getStartTimeMillis());
        statement.bindLong(12, entity.getEndTimeMillis());
        if (entity.getNotes() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getNotes());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getStatus());
        }
      }
    };
    this.__preparedStmtOfCancel = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE appointments SET status = 'CANCELADA' WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfComplete = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE appointments SET status = 'COMPLETADA' WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final AppointmentEntity appointment,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAppointmentEntity.insertAndReturnId(appointment);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<AppointmentEntity> appointments,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfAppointmentEntity.insertAndReturnIdsList(appointments);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object cancel(final long appointmentId, final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfCancel.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, appointmentId);
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
          __preparedStmtOfCancel.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object complete(final long appointmentId,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfComplete.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, appointmentId);
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
          __preparedStmtOfComplete.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object hasOverlap(final long providerId, final long start, final long end,
      final Continuation<? super Boolean> $completion) {
    final String _sql = "\n"
            + "        SELECT EXISTS(\n"
            + "            SELECT 1 FROM appointments\n"
            + "            WHERE providerId = ?\n"
            + "              AND status = 'AGENDADA'\n"
            + "              AND (? < endTimeMillis AND ? > startTimeMillis)\n"
            + "        )\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, providerId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, start);
    _argIndex = 3;
    _statement.bindLong(_argIndex, end);
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
    }, $completion);
  }

  @Override
  public Object getByProvider(final long providerId,
      final Continuation<? super List<AppointmentEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM appointments\n"
            + "        WHERE providerId = ?\n"
            + "        ORDER BY startTimeMillis ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, providerId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppointmentEntity>>() {
      @Override
      @NonNull
      public List<AppointmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfClientId = CursorUtil.getColumnIndexOrThrow(_cursor, "clientId");
          final int _cursorIndexOfClientName = CursorUtil.getColumnIndexOrThrow(_cursor, "clientName");
          final int _cursorIndexOfClientPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "clientPhone");
          final int _cursorIndexOfServiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceId");
          final int _cursorIndexOfServiceName = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceName");
          final int _cursorIndexOfServicePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "servicePrice");
          final int _cursorIndexOfBranchName = CursorUtil.getColumnIndexOrThrow(_cursor, "branchName");
          final int _cursorIndexOfProviderId = CursorUtil.getColumnIndexOrThrow(_cursor, "providerId");
          final int _cursorIndexOfProviderName = CursorUtil.getColumnIndexOrThrow(_cursor, "providerName");
          final int _cursorIndexOfStartTimeMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "startTimeMillis");
          final int _cursorIndexOfEndTimeMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "endTimeMillis");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<AppointmentEntity> _result = new ArrayList<AppointmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppointmentEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpClientId;
            _tmpClientId = _cursor.getLong(_cursorIndexOfClientId);
            final String _tmpClientName;
            if (_cursor.isNull(_cursorIndexOfClientName)) {
              _tmpClientName = null;
            } else {
              _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
            }
            final String _tmpClientPhone;
            if (_cursor.isNull(_cursorIndexOfClientPhone)) {
              _tmpClientPhone = null;
            } else {
              _tmpClientPhone = _cursor.getString(_cursorIndexOfClientPhone);
            }
            final long _tmpServiceId;
            _tmpServiceId = _cursor.getLong(_cursorIndexOfServiceId);
            final String _tmpServiceName;
            if (_cursor.isNull(_cursorIndexOfServiceName)) {
              _tmpServiceName = null;
            } else {
              _tmpServiceName = _cursor.getString(_cursorIndexOfServiceName);
            }
            final double _tmpServicePrice;
            _tmpServicePrice = _cursor.getDouble(_cursorIndexOfServicePrice);
            final String _tmpBranchName;
            if (_cursor.isNull(_cursorIndexOfBranchName)) {
              _tmpBranchName = null;
            } else {
              _tmpBranchName = _cursor.getString(_cursorIndexOfBranchName);
            }
            final long _tmpProviderId;
            _tmpProviderId = _cursor.getLong(_cursorIndexOfProviderId);
            final String _tmpProviderName;
            if (_cursor.isNull(_cursorIndexOfProviderName)) {
              _tmpProviderName = null;
            } else {
              _tmpProviderName = _cursor.getString(_cursorIndexOfProviderName);
            }
            final long _tmpStartTimeMillis;
            _tmpStartTimeMillis = _cursor.getLong(_cursorIndexOfStartTimeMillis);
            final long _tmpEndTimeMillis;
            _tmpEndTimeMillis = _cursor.getLong(_cursorIndexOfEndTimeMillis);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new AppointmentEntity(_tmpId,_tmpClientId,_tmpClientName,_tmpClientPhone,_tmpServiceId,_tmpServiceName,_tmpServicePrice,_tmpBranchName,_tmpProviderId,_tmpProviderName,_tmpStartTimeMillis,_tmpEndTimeMillis,_tmpNotes,_tmpStatus);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getByClient(final long clientId,
      final Continuation<? super List<AppointmentEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM appointments\n"
            + "        WHERE clientId = ?\n"
            + "        ORDER BY startTimeMillis ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, clientId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppointmentEntity>>() {
      @Override
      @NonNull
      public List<AppointmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfClientId = CursorUtil.getColumnIndexOrThrow(_cursor, "clientId");
          final int _cursorIndexOfClientName = CursorUtil.getColumnIndexOrThrow(_cursor, "clientName");
          final int _cursorIndexOfClientPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "clientPhone");
          final int _cursorIndexOfServiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceId");
          final int _cursorIndexOfServiceName = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceName");
          final int _cursorIndexOfServicePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "servicePrice");
          final int _cursorIndexOfBranchName = CursorUtil.getColumnIndexOrThrow(_cursor, "branchName");
          final int _cursorIndexOfProviderId = CursorUtil.getColumnIndexOrThrow(_cursor, "providerId");
          final int _cursorIndexOfProviderName = CursorUtil.getColumnIndexOrThrow(_cursor, "providerName");
          final int _cursorIndexOfStartTimeMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "startTimeMillis");
          final int _cursorIndexOfEndTimeMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "endTimeMillis");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<AppointmentEntity> _result = new ArrayList<AppointmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppointmentEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpClientId;
            _tmpClientId = _cursor.getLong(_cursorIndexOfClientId);
            final String _tmpClientName;
            if (_cursor.isNull(_cursorIndexOfClientName)) {
              _tmpClientName = null;
            } else {
              _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
            }
            final String _tmpClientPhone;
            if (_cursor.isNull(_cursorIndexOfClientPhone)) {
              _tmpClientPhone = null;
            } else {
              _tmpClientPhone = _cursor.getString(_cursorIndexOfClientPhone);
            }
            final long _tmpServiceId;
            _tmpServiceId = _cursor.getLong(_cursorIndexOfServiceId);
            final String _tmpServiceName;
            if (_cursor.isNull(_cursorIndexOfServiceName)) {
              _tmpServiceName = null;
            } else {
              _tmpServiceName = _cursor.getString(_cursorIndexOfServiceName);
            }
            final double _tmpServicePrice;
            _tmpServicePrice = _cursor.getDouble(_cursorIndexOfServicePrice);
            final String _tmpBranchName;
            if (_cursor.isNull(_cursorIndexOfBranchName)) {
              _tmpBranchName = null;
            } else {
              _tmpBranchName = _cursor.getString(_cursorIndexOfBranchName);
            }
            final long _tmpProviderId;
            _tmpProviderId = _cursor.getLong(_cursorIndexOfProviderId);
            final String _tmpProviderName;
            if (_cursor.isNull(_cursorIndexOfProviderName)) {
              _tmpProviderName = null;
            } else {
              _tmpProviderName = _cursor.getString(_cursorIndexOfProviderName);
            }
            final long _tmpStartTimeMillis;
            _tmpStartTimeMillis = _cursor.getLong(_cursorIndexOfStartTimeMillis);
            final long _tmpEndTimeMillis;
            _tmpEndTimeMillis = _cursor.getLong(_cursorIndexOfEndTimeMillis);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new AppointmentEntity(_tmpId,_tmpClientId,_tmpClientName,_tmpClientPhone,_tmpServiceId,_tmpServiceName,_tmpServicePrice,_tmpBranchName,_tmpProviderId,_tmpProviderName,_tmpStartTimeMillis,_tmpEndTimeMillis,_tmpNotes,_tmpStatus);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getByDay(final long dayStart, final long dayEnd,
      final Continuation<? super List<AppointmentEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM appointments\n"
            + "        WHERE startTimeMillis >= ? AND startTimeMillis < ?\n"
            + "        ORDER BY startTimeMillis ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dayStart);
    _argIndex = 2;
    _statement.bindLong(_argIndex, dayEnd);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AppointmentEntity>>() {
      @Override
      @NonNull
      public List<AppointmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfClientId = CursorUtil.getColumnIndexOrThrow(_cursor, "clientId");
          final int _cursorIndexOfClientName = CursorUtil.getColumnIndexOrThrow(_cursor, "clientName");
          final int _cursorIndexOfClientPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "clientPhone");
          final int _cursorIndexOfServiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceId");
          final int _cursorIndexOfServiceName = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceName");
          final int _cursorIndexOfServicePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "servicePrice");
          final int _cursorIndexOfBranchName = CursorUtil.getColumnIndexOrThrow(_cursor, "branchName");
          final int _cursorIndexOfProviderId = CursorUtil.getColumnIndexOrThrow(_cursor, "providerId");
          final int _cursorIndexOfProviderName = CursorUtil.getColumnIndexOrThrow(_cursor, "providerName");
          final int _cursorIndexOfStartTimeMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "startTimeMillis");
          final int _cursorIndexOfEndTimeMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "endTimeMillis");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<AppointmentEntity> _result = new ArrayList<AppointmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppointmentEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpClientId;
            _tmpClientId = _cursor.getLong(_cursorIndexOfClientId);
            final String _tmpClientName;
            if (_cursor.isNull(_cursorIndexOfClientName)) {
              _tmpClientName = null;
            } else {
              _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
            }
            final String _tmpClientPhone;
            if (_cursor.isNull(_cursorIndexOfClientPhone)) {
              _tmpClientPhone = null;
            } else {
              _tmpClientPhone = _cursor.getString(_cursorIndexOfClientPhone);
            }
            final long _tmpServiceId;
            _tmpServiceId = _cursor.getLong(_cursorIndexOfServiceId);
            final String _tmpServiceName;
            if (_cursor.isNull(_cursorIndexOfServiceName)) {
              _tmpServiceName = null;
            } else {
              _tmpServiceName = _cursor.getString(_cursorIndexOfServiceName);
            }
            final double _tmpServicePrice;
            _tmpServicePrice = _cursor.getDouble(_cursorIndexOfServicePrice);
            final String _tmpBranchName;
            if (_cursor.isNull(_cursorIndexOfBranchName)) {
              _tmpBranchName = null;
            } else {
              _tmpBranchName = _cursor.getString(_cursorIndexOfBranchName);
            }
            final long _tmpProviderId;
            _tmpProviderId = _cursor.getLong(_cursorIndexOfProviderId);
            final String _tmpProviderName;
            if (_cursor.isNull(_cursorIndexOfProviderName)) {
              _tmpProviderName = null;
            } else {
              _tmpProviderName = _cursor.getString(_cursorIndexOfProviderName);
            }
            final long _tmpStartTimeMillis;
            _tmpStartTimeMillis = _cursor.getLong(_cursorIndexOfStartTimeMillis);
            final long _tmpEndTimeMillis;
            _tmpEndTimeMillis = _cursor.getLong(_cursorIndexOfEndTimeMillis);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new AppointmentEntity(_tmpId,_tmpClientId,_tmpClientName,_tmpClientPhone,_tmpServiceId,_tmpServiceName,_tmpServicePrice,_tmpBranchName,_tmpProviderId,_tmpProviderName,_tmpStartTimeMillis,_tmpEndTimeMillis,_tmpNotes,_tmpStatus);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getById(final long id, final Continuation<? super AppointmentEntity> $completion) {
    final String _sql = "SELECT * FROM appointments WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AppointmentEntity>() {
      @Override
      @Nullable
      public AppointmentEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfClientId = CursorUtil.getColumnIndexOrThrow(_cursor, "clientId");
          final int _cursorIndexOfClientName = CursorUtil.getColumnIndexOrThrow(_cursor, "clientName");
          final int _cursorIndexOfClientPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "clientPhone");
          final int _cursorIndexOfServiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceId");
          final int _cursorIndexOfServiceName = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceName");
          final int _cursorIndexOfServicePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "servicePrice");
          final int _cursorIndexOfBranchName = CursorUtil.getColumnIndexOrThrow(_cursor, "branchName");
          final int _cursorIndexOfProviderId = CursorUtil.getColumnIndexOrThrow(_cursor, "providerId");
          final int _cursorIndexOfProviderName = CursorUtil.getColumnIndexOrThrow(_cursor, "providerName");
          final int _cursorIndexOfStartTimeMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "startTimeMillis");
          final int _cursorIndexOfEndTimeMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "endTimeMillis");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final AppointmentEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpClientId;
            _tmpClientId = _cursor.getLong(_cursorIndexOfClientId);
            final String _tmpClientName;
            if (_cursor.isNull(_cursorIndexOfClientName)) {
              _tmpClientName = null;
            } else {
              _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
            }
            final String _tmpClientPhone;
            if (_cursor.isNull(_cursorIndexOfClientPhone)) {
              _tmpClientPhone = null;
            } else {
              _tmpClientPhone = _cursor.getString(_cursorIndexOfClientPhone);
            }
            final long _tmpServiceId;
            _tmpServiceId = _cursor.getLong(_cursorIndexOfServiceId);
            final String _tmpServiceName;
            if (_cursor.isNull(_cursorIndexOfServiceName)) {
              _tmpServiceName = null;
            } else {
              _tmpServiceName = _cursor.getString(_cursorIndexOfServiceName);
            }
            final double _tmpServicePrice;
            _tmpServicePrice = _cursor.getDouble(_cursorIndexOfServicePrice);
            final String _tmpBranchName;
            if (_cursor.isNull(_cursorIndexOfBranchName)) {
              _tmpBranchName = null;
            } else {
              _tmpBranchName = _cursor.getString(_cursorIndexOfBranchName);
            }
            final long _tmpProviderId;
            _tmpProviderId = _cursor.getLong(_cursorIndexOfProviderId);
            final String _tmpProviderName;
            if (_cursor.isNull(_cursorIndexOfProviderName)) {
              _tmpProviderName = null;
            } else {
              _tmpProviderName = _cursor.getString(_cursorIndexOfProviderName);
            }
            final long _tmpStartTimeMillis;
            _tmpStartTimeMillis = _cursor.getLong(_cursorIndexOfStartTimeMillis);
            final long _tmpEndTimeMillis;
            _tmpEndTimeMillis = _cursor.getLong(_cursorIndexOfEndTimeMillis);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _result = new AppointmentEntity(_tmpId,_tmpClientId,_tmpClientName,_tmpClientPhone,_tmpServiceId,_tmpServiceName,_tmpServicePrice,_tmpBranchName,_tmpProviderId,_tmpProviderName,_tmpStartTimeMillis,_tmpEndTimeMillis,_tmpNotes,_tmpStatus);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
