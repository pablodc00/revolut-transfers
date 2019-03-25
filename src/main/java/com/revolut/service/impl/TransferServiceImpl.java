package com.revolut.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.Validate;

import com.revolut.dao.Dao;
import com.revolut.model.Transfer;
import com.revolut.model.User;
import com.revolut.service.TransferService;
import com.revolut.util.BusinessException;
import com.revolut.util.GenericUtil;
import com.revolut.util.TransferConstants;

public class TransferServiceImpl implements TransferService {
    
    private static final Logger LOGGER = Logger.getLogger(TransferServiceImpl.class.getName());
    
    private Dao<User> userDao;
    private Dao<Transfer> transferDao;
    
    public TransferServiceImpl(Dao<User> userDao, Dao<Transfer> transferDao) {
        this.userDao = userDao;
        this.transferDao = transferDao;
    }

    @Override
    public String makeTransfer(String userSenderId, String userReceiverId, Double amount) throws BusinessException {
        Validate.notEmpty(userSenderId, "User sender is empty.");
        Validate.notEmpty(userReceiverId, "User receiver is empty.");
        Validate.notNull(amount, "Aomount is null.");

        User uSender;
        User uReceiver;
        
        Optional<User> oSender = userDao.get(userSenderId);
        if (oSender.isPresent()) {
            uSender = oSender.get();
        } else {
            LOGGER.log(Level.SEVERE, TransferConstants.NOT_USER_FOUND + " {0}.", userSenderId);
            throw new BusinessException(String.format(TransferConstants.NOT_USER_FOUND + " (%s)", userSenderId));
        }
        
        Optional<User> oReceiver = userDao.get(userReceiverId);
        if (!oReceiver.isPresent()) {
            LOGGER.log(Level.SEVERE, TransferConstants.NOT_USER_FOUND + " {0}.", userReceiverId);
            throw new BusinessException(String.format(TransferConstants.NOT_USER_FOUND + " (%s)", userReceiverId));
        } else {
            uReceiver = oReceiver.get();
        }
        
        if (uSender.getBalance() < amount) {
            LOGGER.log(Level.SEVERE, "User {0} does not have enough balance.", userSenderId);
            throw new BusinessException(String.format("User (%s) has not enough balance to transfer (%s)", userReceiverId, amount));
        }

        uSender.setBalance(uSender.getBalance() - amount);
        userDao.save(uSender);
        
        uReceiver.setBalance(uReceiver.getBalance() + amount);
        userDao.save(uReceiver);

        Date dateNow = GenericUtil.getDateNowUTC();
        Transfer transfer = new Transfer(userSenderId, userReceiverId, amount, dateNow);
        transferDao.save(transfer);
        
        //TODO send an asynchronous message to update Transaction History for each User

        return transfer.getId();
    }
    
    @Override
    public List<Transfer> getTransfers() {
        return transferDao.getAll();
    }
    
    
    @Override
    public Optional<Transfer> getTransferById(String transferId) {
        return transferDao.get(transferId);
    }

}
