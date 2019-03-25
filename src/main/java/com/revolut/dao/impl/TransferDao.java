package com.revolut.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.revolut.dao.Dao;
import com.revolut.model.Transfer;
import com.revolut.store.TransferDocument;

public class TransferDao implements Dao<Transfer> {
    
    @Override
    public Optional<Transfer> get(String id) {
        return Optional.ofNullable(TransferDocument.getInstance().getTransfers().get(id));
    }

    @Override
    public List<Transfer> getAll() {
        return TransferDocument.getInstance().getTransfers().values().stream().collect(Collectors.toList());
    }

    @Override
    public void save(Transfer transfer) {
        TransferDocument.getInstance().getTransfers().put(transfer.getId(), transfer);
        
    }

    @Override
    public void delete(Transfer transfer) {
        TransferDocument.getInstance().getTransfers().remove(transfer.getId());
    }

}
