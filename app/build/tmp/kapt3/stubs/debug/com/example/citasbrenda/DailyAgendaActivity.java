package com.example.citasbrenda;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0012\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u0010\"\u001a\u00020\u001eH\u0002J\u0016\u0010#\u001a\u00020\u001e2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%H\u0002J\u0010\u0010\'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\rR\u0012\u0010\u000e\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\b\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/example/citasbrenda/DailyAgendaActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "appointmentDao", "Lcom/example/citasbrenda/data/AppointmentDao;", "getAppointmentDao", "()Lcom/example/citasbrenda/data/AppointmentDao;", "appointmentDao$delegate", "Lkotlin/Lazy;", "container", "Landroid/widget/LinearLayout;", "dayEndMillis", "", "Ljava/lang/Long;", "dayStartMillis", "db", "Lcom/example/citasbrenda/data/AppDatabase;", "getDb", "()Lcom/example/citasbrenda/data/AppDatabase;", "db$delegate", "dfDay", "Ljava/text/SimpleDateFormat;", "dfTime", "endHour", "", "slotMinutes", "startHour", "tvDay", "Landroid/widget/TextView;", "loadAgenda", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "pickDay", "renderSlots", "appointments", "", "Lcom/example/citasbrenda/data/AppointmentEntity;", "setDay", "dayCal", "Ljava/util/Calendar;", "app_debug"})
public final class DailyAgendaActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dfDay = null;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dfTime = null;
    private android.widget.TextView tvDay;
    private android.widget.LinearLayout container;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy db$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy appointmentDao$delegate = null;
    private final int startHour = 9;
    private final int endHour = 18;
    private final int slotMinutes = 30;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long dayStartMillis;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long dayEndMillis;
    
    public DailyAgendaActivity() {
        super();
    }
    
    private final com.example.citasbrenda.data.AppDatabase getDb() {
        return null;
    }
    
    private final com.example.citasbrenda.data.AppointmentDao getAppointmentDao() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void pickDay() {
    }
    
    private final void setDay(java.util.Calendar dayCal) {
    }
    
    private final void loadAgenda() {
    }
    
    private final void renderSlots(java.util.List<com.example.citasbrenda.data.AppointmentEntity> appointments) {
    }
}