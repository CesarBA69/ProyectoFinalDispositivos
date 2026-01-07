package com.example.citasbrenda;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\u0012\u0010\u0017\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0013H\u0014J\u001e\u0010\u001b\u001a\u00020\u00132\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0018\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\b\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/citasbrenda/MyAppointmentsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "appointmentDao", "Lcom/example/citasbrenda/data/AppointmentDao;", "getAppointmentDao", "()Lcom/example/citasbrenda/data/AppointmentDao;", "appointmentDao$delegate", "Lkotlin/Lazy;", "container", "Landroid/widget/LinearLayout;", "db", "Lcom/example/citasbrenda/data/AppDatabase;", "getDb", "()Lcom/example/citasbrenda/data/AppDatabase;", "db$delegate", "df", "Ljava/text/SimpleDateFormat;", "confirmCancel", "", "id", "", "load", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "render", "items", "", "Lcom/example/citasbrenda/data/AppointmentEntity;", "role", "Lcom/example/citasbrenda/data/UserRole;", "renderRow", "appt", "app_debug"})
public final class MyAppointmentsActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat df = null;
    private android.widget.LinearLayout container;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy db$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy appointmentDao$delegate = null;
    
    public MyAppointmentsActivity() {
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
    
    private final void load() {
    }
    
    private final void render(java.util.List<com.example.citasbrenda.data.AppointmentEntity> items, com.example.citasbrenda.data.UserRole role) {
    }
    
    private final android.widget.LinearLayout renderRow(com.example.citasbrenda.data.AppointmentEntity appt, com.example.citasbrenda.data.UserRole role) {
        return null;
    }
    
    private final void confirmCancel(long id) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
}