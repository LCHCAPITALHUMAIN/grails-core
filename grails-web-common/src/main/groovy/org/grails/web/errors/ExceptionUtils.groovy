package org.grails.web.errors

import groovy.transform.CompileStatic
import org.codehaus.groovy.control.CompilationFailedException
import org.codehaus.groovy.control.MultipleCompilationErrorsException
import org.codehaus.groovy.control.messages.SyntaxErrorMessage
import org.grails.web.util.WebUtils

/**
 * Utility methods for dealing with exception
 *
 * @since 2.4
 * @author Graeme Rocher
 */
@CompileStatic
class ExceptionUtils {

    public static final String EXCEPTION_ATTRIBUTE = WebUtils.EXCEPTION_ATTRIBUTE;


    static RuntimeException getFirstRuntimeException(Throwable e) {
        if (e instanceof RuntimeException) return (RuntimeException) e

        Throwable ex = e
        while (ex.cause && ex != ex.cause) {
            ex = ex.cause
            if (ex instanceof RuntimeException) return (RuntimeException) ex
        }
    }

    /**
     * Obtains the root cause of the given exception
     * @param ex The exception
     * @return The root cause
     */
    static Throwable getRootCause(Throwable ex) {
        while (ex.cause && ex != ex.cause) {
            ex = ex.cause
        }
        return ex
    }

    static int extractLineNumber(CompilationFailedException e) {
        int lineNumber = -1
        if (e instanceof MultipleCompilationErrorsException) {
            MultipleCompilationErrorsException mcee = (MultipleCompilationErrorsException)e
            Object message = mcee.errorCollector.errors.iterator().next()
            if (message instanceof SyntaxErrorMessage) {
                SyntaxErrorMessage sem = (SyntaxErrorMessage)message
                lineNumber = sem.cause.line
            }
        }
        return lineNumber
    }
}
