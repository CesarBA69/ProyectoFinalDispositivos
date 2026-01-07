package com.example.citasbrenda;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0011H\u0002J\b\u0010\u001e\u001a\u00020\u001cH\u0002J\u0012\u0010\u001f\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\u0010\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020\u001cH\u0002J\u0010\u0010&\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020$H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\n\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/example/citasbrenda/PriceListDetailActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "container", "Landroid/widget/LinearLayout;", "db", "Lcom/example/citasbrenda/data/AppDatabase;", "getDb", "()Lcom/example/citasbrenda/data/AppDatabase;", "db$delegate", "Lkotlin/Lazy;", "priceDao", "Lcom/example/citasbrenda/data/PriceListDao;", "getPriceDao", "()Lcom/example/citasbrenda/data/PriceListDao;", "priceDao$delegate", "priceListId", "", "priceListName", "", "serviceDao", "Lcom/example/citasbrenda/data/ServiceDao;", "getServiceDao", "()Lcom/example/citasbrenda/data/ServiceDao;", "serviceDao$delegate", "tvTitle", "Landroid/widget/TextView;", "confirmDeleteItem", "", "itemId", "loadItems", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "renderItem", "item", "Lcom/example/citasbrenda/data/PriceItemWithService;", "showAddItemDialog", "showEditPriceDialog", "app_debug"})
public final class PriceListDetailActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.widget.LinearLayout container;
    private android.widget.TextView tvTitle;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy db$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy priceDao$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy serviceDao$delegate = null;
    private long priceListId = -1L;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String priceListName = "Lista";
    
    public PriceListDetailActivity() {
        super();
    }
    
    private final com.example.citasbrenda.data.AppDatabase getDb() {
        return null;
    }
    
    private final com.example.citasbrenda.data.PriceListDao getPriceDao() {
        return null;
    }
    
    private final com.example.citasbrenda.data.ServiceDao getServiceDao() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadItems() {
    }
    
    private final android.widget.LinearLayout renderItem(com.example.citasbrenda.data.PriceItemWithService item) {
        return null;
    }
    
    private final void showAddItemDialog() {
    }
    
    private final void showEditPriceDialog(com.example.citasbrenda.data.PriceItemWithService item) {
    }
    
    private final void confirmDeleteItem(long itemId) {
    }
}