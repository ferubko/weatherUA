package com.svf.edu.service.client;

import com.svf.edu.dto.api.AbstractResponse;
import com.svf.edu.exceptions.InternalServiceInvocationException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by stepanferubko
 */
public abstract class AbstractClient {
    //    private final static Logger LOG = LoggerFactory.getLogger(AbstractClient.class);
    private final static Logger LOG = Logger.getLogger(AbstractClient.class.getName());


    protected final RestTemplate restTemplate;
    protected final int attemptsCount;

    public static final int DEF_ATTEMPTS_COUNT = 6;
    private final static int MAX_TIMEOUT = 300;
    private final static int[] TIMEOUTS = {1, 5, 10, 60, 60, 60, 180, MAX_TIMEOUT};


    protected AbstractClient(int attemptsCount) {
        this.restTemplate = new RestTemplate();
        this.attemptsCount = attemptsCount;
    }

    protected AbstractClient() {
        this.restTemplate = new RestTemplate();
        this.attemptsCount = DEF_ATTEMPTS_COUNT;
    }

    protected <T extends AbstractResponse> T getResponse(Supplier<T> action) throws InternalServiceInvocationException {
        return getResponse(action, attemptsCount);
    }

    protected <T extends AbstractResponse> T getResponse(Supplier<T> action, int attempts) throws InternalServiceInvocationException {
        T response = executeRestTemplate(action, attempts);
        if (response.isError()) {
            throw new InternalServiceInvocationException(response);
        }
        return response;
    }

    public static <T> T executeRestTemplate(Supplier<T> action, int attempts) {
        for (int i = 0; i < attempts - 1; i++) {
            try {
                return action.get();
            } catch (ResourceAccessException ex) {
                int timeout = i < TIMEOUTS.length ? TIMEOUTS[i] : MAX_TIMEOUT;
                LOG.log(Level.WARNING, "RestTemplate action was failed");
                sleep(timeout);
            }
        }
        return action.get();
    }

    private static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
