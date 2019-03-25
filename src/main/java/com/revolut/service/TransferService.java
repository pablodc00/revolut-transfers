package com.revolut.service;

import java.util.List;
import java.util.Optional;

import com.revolut.model.Transfer;
import com.revolut.util.BusinessException;

public interface TransferService {

    String makeTransfer(String userSenderId, String userReceiverId, Double amount) throws BusinessException;
    
    List<Transfer> getTransfers();
    
    Optional<Transfer> getTransferById(String transferId);
}
