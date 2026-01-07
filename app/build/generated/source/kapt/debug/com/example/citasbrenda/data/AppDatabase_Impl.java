package com.example.citasbrenda.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserDao _userDao;

  private volatile AppointmentDao _appointmentDao;

  private volatile BranchDao _branchDao;

  private volatile ServiceDao _serviceDao;

  private volatile PriceListDao _priceListDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(7) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fullName` TEXT NOT NULL, `email` TEXT NOT NULL, `passwordHash` TEXT NOT NULL, `role` TEXT NOT NULL, `isActive` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_users_email` ON `users` (`email`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `appointments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `clientId` INTEGER NOT NULL, `clientName` TEXT NOT NULL, `clientPhone` TEXT, `serviceId` INTEGER NOT NULL, `serviceName` TEXT NOT NULL, `servicePrice` REAL NOT NULL, `branchName` TEXT NOT NULL, `providerId` INTEGER NOT NULL, `providerName` TEXT NOT NULL, `startTimeMillis` INTEGER NOT NULL, `endTimeMillis` INTEGER NOT NULL, `notes` TEXT, `status` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `branches` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `address` TEXT, `isActive` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_branches_name` ON `branches` (`name`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `services` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `durationMinutes` INTEGER NOT NULL, `isActive` INTEGER NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_services_name` ON `services` (`name`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `price_lists` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `createdAtMillis` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `price_items` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `priceListId` INTEGER NOT NULL, `serviceId` INTEGER NOT NULL, `price` REAL NOT NULL, FOREIGN KEY(`priceListId`) REFERENCES `price_lists`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`serviceId`) REFERENCES `services`(`id`) ON UPDATE NO ACTION ON DELETE RESTRICT )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_price_items_priceListId` ON `price_items` (`priceListId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_price_items_serviceId` ON `price_items` (`serviceId`)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_price_items_priceListId_serviceId` ON `price_items` (`priceListId`, `serviceId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '64d2871d4fa7bf5ff5de7d1899e800b5')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `appointments`");
        db.execSQL("DROP TABLE IF EXISTS `branches`");
        db.execSQL("DROP TABLE IF EXISTS `services`");
        db.execSQL("DROP TABLE IF EXISTS `price_lists`");
        db.execSQL("DROP TABLE IF EXISTS `price_items`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(6);
        _columnsUsers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("fullName", new TableInfo.Column("fullName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("passwordHash", new TableInfo.Column("passwordHash", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(1);
        _indicesUsers.add(new TableInfo.Index("index_users_email", true, Arrays.asList("email"), Arrays.asList("ASC")));
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.example.citasbrenda.data.UserEntity).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsAppointments = new HashMap<String, TableInfo.Column>(14);
        _columnsAppointments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("clientId", new TableInfo.Column("clientId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("clientName", new TableInfo.Column("clientName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("clientPhone", new TableInfo.Column("clientPhone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("serviceId", new TableInfo.Column("serviceId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("serviceName", new TableInfo.Column("serviceName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("servicePrice", new TableInfo.Column("servicePrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("branchName", new TableInfo.Column("branchName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("providerId", new TableInfo.Column("providerId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("providerName", new TableInfo.Column("providerName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("startTimeMillis", new TableInfo.Column("startTimeMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("endTimeMillis", new TableInfo.Column("endTimeMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppointments.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppointments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppointments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppointments = new TableInfo("appointments", _columnsAppointments, _foreignKeysAppointments, _indicesAppointments);
        final TableInfo _existingAppointments = TableInfo.read(db, "appointments");
        if (!_infoAppointments.equals(_existingAppointments)) {
          return new RoomOpenHelper.ValidationResult(false, "appointments(com.example.citasbrenda.data.AppointmentEntity).\n"
                  + " Expected:\n" + _infoAppointments + "\n"
                  + " Found:\n" + _existingAppointments);
        }
        final HashMap<String, TableInfo.Column> _columnsBranches = new HashMap<String, TableInfo.Column>(4);
        _columnsBranches.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBranches.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBranches.put("address", new TableInfo.Column("address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBranches.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBranches = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBranches = new HashSet<TableInfo.Index>(1);
        _indicesBranches.add(new TableInfo.Index("index_branches_name", true, Arrays.asList("name"), Arrays.asList("ASC")));
        final TableInfo _infoBranches = new TableInfo("branches", _columnsBranches, _foreignKeysBranches, _indicesBranches);
        final TableInfo _existingBranches = TableInfo.read(db, "branches");
        if (!_infoBranches.equals(_existingBranches)) {
          return new RoomOpenHelper.ValidationResult(false, "branches(com.example.citasbrenda.data.BranchEntity).\n"
                  + " Expected:\n" + _infoBranches + "\n"
                  + " Found:\n" + _existingBranches);
        }
        final HashMap<String, TableInfo.Column> _columnsServices = new HashMap<String, TableInfo.Column>(4);
        _columnsServices.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServices.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServices.put("durationMinutes", new TableInfo.Column("durationMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServices.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysServices = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesServices = new HashSet<TableInfo.Index>(1);
        _indicesServices.add(new TableInfo.Index("index_services_name", true, Arrays.asList("name"), Arrays.asList("ASC")));
        final TableInfo _infoServices = new TableInfo("services", _columnsServices, _foreignKeysServices, _indicesServices);
        final TableInfo _existingServices = TableInfo.read(db, "services");
        if (!_infoServices.equals(_existingServices)) {
          return new RoomOpenHelper.ValidationResult(false, "services(com.example.citasbrenda.data.ServiceEntity).\n"
                  + " Expected:\n" + _infoServices + "\n"
                  + " Found:\n" + _existingServices);
        }
        final HashMap<String, TableInfo.Column> _columnsPriceLists = new HashMap<String, TableInfo.Column>(4);
        _columnsPriceLists.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceLists.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceLists.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceLists.put("createdAtMillis", new TableInfo.Column("createdAtMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPriceLists = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPriceLists = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPriceLists = new TableInfo("price_lists", _columnsPriceLists, _foreignKeysPriceLists, _indicesPriceLists);
        final TableInfo _existingPriceLists = TableInfo.read(db, "price_lists");
        if (!_infoPriceLists.equals(_existingPriceLists)) {
          return new RoomOpenHelper.ValidationResult(false, "price_lists(com.example.citasbrenda.data.PriceListEntity).\n"
                  + " Expected:\n" + _infoPriceLists + "\n"
                  + " Found:\n" + _existingPriceLists);
        }
        final HashMap<String, TableInfo.Column> _columnsPriceItems = new HashMap<String, TableInfo.Column>(4);
        _columnsPriceItems.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceItems.put("priceListId", new TableInfo.Column("priceListId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceItems.put("serviceId", new TableInfo.Column("serviceId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceItems.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPriceItems = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysPriceItems.add(new TableInfo.ForeignKey("price_lists", "CASCADE", "NO ACTION", Arrays.asList("priceListId"), Arrays.asList("id")));
        _foreignKeysPriceItems.add(new TableInfo.ForeignKey("services", "RESTRICT", "NO ACTION", Arrays.asList("serviceId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesPriceItems = new HashSet<TableInfo.Index>(3);
        _indicesPriceItems.add(new TableInfo.Index("index_price_items_priceListId", false, Arrays.asList("priceListId"), Arrays.asList("ASC")));
        _indicesPriceItems.add(new TableInfo.Index("index_price_items_serviceId", false, Arrays.asList("serviceId"), Arrays.asList("ASC")));
        _indicesPriceItems.add(new TableInfo.Index("index_price_items_priceListId_serviceId", true, Arrays.asList("priceListId", "serviceId"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoPriceItems = new TableInfo("price_items", _columnsPriceItems, _foreignKeysPriceItems, _indicesPriceItems);
        final TableInfo _existingPriceItems = TableInfo.read(db, "price_items");
        if (!_infoPriceItems.equals(_existingPriceItems)) {
          return new RoomOpenHelper.ValidationResult(false, "price_items(com.example.citasbrenda.data.PriceItemEntity).\n"
                  + " Expected:\n" + _infoPriceItems + "\n"
                  + " Found:\n" + _existingPriceItems);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "64d2871d4fa7bf5ff5de7d1899e800b5", "55ddf7b224b768e33604c3f6da643b08");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "users","appointments","branches","services","price_lists","price_items");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `appointments`");
      _db.execSQL("DELETE FROM `branches`");
      _db.execSQL("DELETE FROM `services`");
      _db.execSQL("DELETE FROM `price_lists`");
      _db.execSQL("DELETE FROM `price_items`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AppointmentDao.class, AppointmentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BranchDao.class, BranchDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ServiceDao.class, ServiceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PriceListDao.class, PriceListDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public AppointmentDao appointmentDao() {
    if (_appointmentDao != null) {
      return _appointmentDao;
    } else {
      synchronized(this) {
        if(_appointmentDao == null) {
          _appointmentDao = new AppointmentDao_Impl(this);
        }
        return _appointmentDao;
      }
    }
  }

  @Override
  public BranchDao branchDao() {
    if (_branchDao != null) {
      return _branchDao;
    } else {
      synchronized(this) {
        if(_branchDao == null) {
          _branchDao = new BranchDao_Impl(this);
        }
        return _branchDao;
      }
    }
  }

  @Override
  public ServiceDao serviceDao() {
    if (_serviceDao != null) {
      return _serviceDao;
    } else {
      synchronized(this) {
        if(_serviceDao == null) {
          _serviceDao = new ServiceDao_Impl(this);
        }
        return _serviceDao;
      }
    }
  }

  @Override
  public PriceListDao priceListDao() {
    if (_priceListDao != null) {
      return _priceListDao;
    } else {
      synchronized(this) {
        if(_priceListDao == null) {
          _priceListDao = new PriceListDao_Impl(this);
        }
        return _priceListDao;
      }
    }
  }
}
