package com.gbbc.webapplication.services.interfaces;

import com.gbbc.webapplication.beans.TerminalRequest;
import com.gbbc.webapplication.viewmodel.PinTerminalStatus;

import java.util.List;

/**
 * Interface for Service class for TerminalRequestController
 *
 * @author R. Portzgen
 */
public interface TerminalService {

    /**
     *
     * @return
     */
    List<TerminalRequest> getAllRequests();

    TerminalRequest getOneByBankAccount(int id);

    void handleRequest(PinTerminalStatus pinTerminalStatus);

    void generateCodeForRequest(TerminalRequest thisRequest, PinTerminalStatus pinTerminalStatus);

    int generateRandomCode();

    String getCurrentTime();

    boolean checkForRequest(PinTerminalStatus terminalStatus);

    PinTerminalStatus generateRequest(PinTerminalStatus terminalStatus);

    /**
     * Testje om alle gegenereerde codes terug te krijgen
     * @author E. Koo
     * @return lijst van gegenereerde koppel codes
     */
    List<String> getAllGeneratedCodes();

    /**
     * @should be valid in the right code and bankAccount id combination
     * @author E. Koo
     * @return checked code
     */
    boolean isValidCombination(String iban, int code);

    /**
     * check of account een koppelcode heeft voor een terminal en bestaat
     * @param accountNr te checken voor terminal
     * @return true/false
     */
    boolean isValidUser(String accountNr);
}
