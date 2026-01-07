package com.example.citasbrenda;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\u0012\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0014\u0010\u0018\u001a\u00020\u00112\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001d"}, d2 = {"Lcom/example/citasbrenda/ProvidersActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "container", "Landroid/widget/LinearLayout;", "db", "Lcom/example/citasbrenda/data/AppDatabase;", "getDb", "()Lcom/example/citasbrenda/data/AppDatabase;", "db$delegate", "Lkotlin/Lazy;", "userDao", "Lcom/example/citasbrenda/data/UserDao;", "getUserDao", "()Lcom/example/citasbrenda/data/UserDao;", "userDao$delegate", "confirmDelete", "", "userId", "", "loadProviders", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showProviderDialog", "existing", "Lcom/example/citasbrenda/data/UserEntity;", "toggleActive", "user", "app_debug"})
public final class ProvidersActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.widget.LinearLayout container;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy db$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy userDao$delegate = null;
    
    public ProvidersActivity() {
        super();
    }
    
    private final com.example.citasbrenda.data.AppDatabase getDb() {
        return null;
    }
    
    private final com.example.citasbrenda.data.UserDao getUserDao() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadProviders() {
    }
    
    private final void showProviderDialog(com.example.citasbrenda.data.UserEntity existing) {
    }
    
    private final void toggleActive(com.example.citasbrenda.data.UserEntity user) {
    }
    
    private final void confirmDelete(long userId) {
    }
}