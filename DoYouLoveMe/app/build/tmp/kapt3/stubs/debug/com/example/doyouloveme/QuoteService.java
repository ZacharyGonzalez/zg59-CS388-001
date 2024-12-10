package com.example.doyouloveme;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'\u00a8\u0006\u0005"}, d2 = {"Lcom/example/doyouloveme/QuoteService;", "", "getQuote", "Lretrofit2/Call;", "Lcom/example/doyouloveme/QuoteResponse;", "app_debug"})
public abstract interface QuoteService {
    
    @retrofit2.http.GET(value = "lovequote")
    @retrofit2.http.Headers(value = {"x-rapidapi-key: 6e45659ac8mshbe3dd609845de0bp17e974jsnd555dce064f2", "x-rapidapi-host: love-quote.p.rapidapi.com"})
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<com.example.doyouloveme.QuoteResponse> getQuote();
}