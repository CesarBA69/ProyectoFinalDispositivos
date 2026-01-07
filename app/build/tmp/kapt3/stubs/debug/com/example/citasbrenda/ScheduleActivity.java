package com.example.citasbrenda;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\u001c\u0010\u000e\u001a\u00020\u000b2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b0\u0010H\u0002JD\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0014\u0010\u0018\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0004\u0012\u00020\u000b0\u0010H\u0082@\u00a2\u0006\u0002\u0010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\t\u00a8\u0006\u001b"}, d2 = {"Lcom/example/citasbrenda/ScheduleActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "defaultDurationMinutes", "", "df", "Ljava/text/SimpleDateFormat;", "selectedTimeMillis", "", "Ljava/lang/Long;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "pickDateTime", "onPicked", "Lkotlin/Function1;", "updatePrice", "priceDao", "Lcom/example/citasbrenda/data/PriceListDao;", "activeListId", "serviceId", "tvPrice", "Landroid/widget/TextView;", "onPrice", "", "(Lcom/example/citasbrenda/data/PriceListDao;JJLandroid/widget/TextView;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ScheduleActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat df = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long selectedTimeMillis;
    private final int defaultDurationMinutes = 60;
    
    public ScheduleActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final java.lang.Object updatePrice(com.example.citasbrenda.data.PriceListDao priceDao, long activeListId, long serviceId, android.widget.TextView tvPrice, kotlin.jvm.functions.Function1<? super java.lang.Double, kotlin.Unit> onPrice, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void pickDateTime(kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onPicked) {
    }
}