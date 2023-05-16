package cse.java2.project.aspect;

import org.apache.ibatis.exceptions.PersistenceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PersistenceExceptionAspect {

    @Around("execution(* cse.java2.project.mapper.StackOverflowThreadMapper.insert*(..))")
    public Object handlePersistenceException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof PSQLException) {
                PSQLException pe = (PSQLException) cause;
                if ("23505".equals(pe.getSQLState())) {
                    System.err.println("Duplicate key found. Skipping insert.");
                    return null; // Return null or some default value
                } else {
                    throw e; // Rethrow unexpected exceptions
                }
            } else {
                throw e; // Rethrow unexpected exceptions
            }
        }
    }
}

