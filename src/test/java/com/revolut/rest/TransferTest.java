package com.revolut.rest;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revolut.dao.impl.TransferDao;
import com.revolut.dao.impl.UserDao;
import com.revolut.model.Transfer;
import com.revolut.model.User;
import com.revolut.service.TransferService;
import com.revolut.service.impl.TransferServiceImpl;
import com.revolut.util.BusinessException;
import com.revolut.util.GenericUtil;

public class TransferTest {
    
    //TODO add more cases
    
    @Mock
    private UserDao userDao;

    @Mock
    private TransferDao transferDao;

    @InjectMocks
    private TransferService transferTervice = new TransferServiceImpl(userDao, transferDao);
    
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testGetTransferWithWrongIdAndReturnNotPresent() {
        final String wrongId = "wrongId";
        Optional<Transfer> emptyTransfer = Optional.empty();
        when(transferDao.get(wrongId)).thenReturn(emptyTransfer);
        assertEquals(transferTervice.getTransferById(wrongId), emptyTransfer);
    }
 
    @Test
    public void testGetTransferWithGoodIdAndReturnOneTransfer() {
        final String goodId = "goodId";
        final Transfer transfer = new Transfer("sender", "receiver", 105.00, GenericUtil.getDateNowUTC());
        Optional<Transfer> oTransfer = Optional.of(transfer);
        when(transferDao.get(goodId)).thenReturn(oTransfer);
        assertEquals(transferTervice.getTransferById(goodId), oTransfer);
    }    
 
    @Test
    public void testMakeValidTransferAndReturnTransferId() throws BusinessException {
        User userS = new User("aaaa", "aaaa", 105.00);
        Optional<User> oUserS = Optional.of(userS);
        when(userDao.get("idA")).thenReturn(oUserS);
        
        User userR = new User("bbbb", "bbbb", 105.00);
        Optional<User> oUserR = Optional.of(userR);
        when(userDao.get("idB")).thenReturn(oUserR);
        
        assertNotNull(transferTervice.makeTransfer("idA", "idB", 30.50));
    }

    @Test(expected = BusinessException.class)
    public void testInvalidTrasnferAndGetBusinessEx() throws BusinessException {
        User userS = new User("aaaa", "aaaa", 5.00);
        Optional<User> oUserS = Optional.of(userS);
        when(userDao.get("idA")).thenReturn(oUserS);
        
        User userR = new User("bbbb", "bbbb", 105.00);
        Optional<User> oUserR = Optional.of(userR);
        when(userDao.get("idB")).thenReturn(oUserR);

        transferTervice.makeTransfer("idA", "idB", 300.50);
    }
}
