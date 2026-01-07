package com.example.citasbrenda;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\u0012\u0010\u0015\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0014\u0010\u0018\u001a\u00020\u00112\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001b"}, d2 = {"Lcom/example/citasbrenda/ServicesActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "container", "Landroid/widget/LinearLayout;", "dao", "Lcom/example/citasbrenda/data/ServiceDao;", "getDao", "()Lcom/example/citasbrenda/data/ServiceDao;", "dao$delegate", "Lkotlin/Lazy;", "db", "Lcom/example/citasbrenda/data/AppDatabase;", "getDb", "()Lcom/example/citasbrenda/data/AppDatabase;", "db$delegate", "confirmDelete", "", "id", "", "loadServices", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showServiceDialog", "existing", "Lcom/example/citasbrenda/data/ServiceEntity;", "app_debug"})
public final class ServicesActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.widget.LinearLayout container;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy db$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy dao$delegate = null;
    
    public ServicesActivity() {
        super();
    }
    
    private final com.example.citasbrenda.data.AppDatabase getDb() {
        return null;
    }
    
    private final com.example.citasbrenda.data.ServiceDao getDao() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadServices() {
    }
    
    private final void showServiceDialog(com.example.citasbrenda.data.ServiceEntity existing) {
    }
    
    private final void confirmDelete(long id) {
    }
}