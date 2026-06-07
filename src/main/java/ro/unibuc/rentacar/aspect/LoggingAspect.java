package ro.unibuc.rentacar.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut("within(ro.unibuc.rentacar.service..*)")
    public void serviceMethods() {}

    @Around("serviceMethods()")
    public Object logServiceCall(ProceedingJoinPoint pjp) throws Throwable {
        String metoda = pjp.getSignature().toShortString();
        log.info("-> apel {} cu argumentele {}", metoda, Arrays.toString(pjp.getArgs()));
        long start = System.currentTimeMillis();
        try {
            Object rezultat = pjp.proceed();
            log.info("<- {} terminat in {} ms", metoda, System.currentTimeMillis() - start);
            return rezultat;
        } catch (Throwable ex) {
            log.error("!! {} a aruncat: {}", metoda, ex.getMessage());
            throw ex;
        }
    }
}