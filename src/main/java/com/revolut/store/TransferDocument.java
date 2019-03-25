package com.revolut.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.revolut.model.Transfer;

public class TransferDocument {

    private Map<String, Transfer> transfers = null;    
    private static TransferDocument transferDocument = null;
    
    private TransferDocument() {
        transfers = new ConcurrentHashMap<>();
    }
    
    public static TransferDocument getInstance() {
        if (null == transferDocument) {
            transferDocument = new TransferDocument();
        }
        return transferDocument;
    }
    
    public Map<String, Transfer> getTransfers() {
        return transfers;
    }    
}
