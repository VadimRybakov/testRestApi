package ru.vadimio.TestRestApi.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.vadimio.TestRestApi.errors.Error;

@Service
public class MainService {

    private static final Logger log = Logger.getLogger(MainService.class);
    private volatile int N = 1;

    public void setN(int n) {
        N = n;
    }

    public String updateString(String str) {
        String strUpd = str.replaceAll("\\s","");
        String[] strArr = strUpd.split(",");
        StringBuilder sb = new StringBuilder();
        log.info("N = " + N + "\n");
        for (int i = 0; i < strArr.length; i++) {
            try {
                int num = Integer.parseInt(strArr[i]);
                if((num > 0) && (N > Integer.MAX_VALUE - num)) {
                    log.error(Error.BIG_NUMBER.getErr());
                    return Error.BIG_NUMBER.getErr();
                }
                if((num < 0) && (N < Integer.MIN_VALUE - num)){
                    log.error(Error.SMALL_NUMBER.getErr());
                    return Error.SMALL_NUMBER.getErr();
                }
                sb.append(num + N);
                sb.append(',');
            } catch (Exception e){
                log.error(Error.NOT_INTEGER_VALUE.getErr());
                return Error.NOT_INTEGER_VALUE.getErr();
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        log.info(sb);
        return sb.toString();
    }


    public boolean updateN(Integer n) {
        if((n > 0) && (N > Integer.MAX_VALUE - n)) {
            log.error(Error.BIG_NUMBER.getErr());
            return false;
        }
        if((n < 0) && (N < Integer.MIN_VALUE - n)){
            log.error(Error.SMALL_NUMBER.getErr());
            return false;
        }
        N = n;
        log.info("N = " + N + "\n");
        return true;
    }
}
