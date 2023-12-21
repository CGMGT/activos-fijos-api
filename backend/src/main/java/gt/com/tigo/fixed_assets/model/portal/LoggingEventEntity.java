package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;

@Entity
@Table(name = "LOGGING_EVENT", schema = "", catalog = "")
public class LoggingEventEntity {
    private long timestmp;
    private String formattedMessage;
    private String loggerName;
    private String levelString;
    private String threadName;
    private Long referenceFlag;
    private String arg0;
    private String arg1;
    private String arg2;
    private String arg3;
    private String callerFilename;
    private String callerClass;
    private String callerMethod;
    private String callerLine;
    private int eventId;

    @Basic
    @Column(name = "TIMESTMP", nullable = false, precision = 0)
    public long getTimestmp() {
        return timestmp;
    }

    public void setTimestmp(long timestmp) {
        this.timestmp = timestmp;
    }

    @Basic
    @Column(name = "FORMATTED_MESSAGE", nullable = false, length = 4000)
    public String getFormattedMessage() {
        return formattedMessage;
    }

    public void setFormattedMessage(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }

    @Basic
    @Column(name = "LOGGER_NAME", nullable = false, length = 254)
    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    @Basic
    @Column(name = "LEVEL_STRING", nullable = false, length = 254)
    public String getLevelString() {
        return levelString;
    }

    public void setLevelString(String levelString) {
        this.levelString = levelString;
    }

    @Basic
    @Column(name = "THREAD_NAME", nullable = true, length = 254)
    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Basic
    @Column(name = "REFERENCE_FLAG", nullable = true, precision = 0)
    public Long getReferenceFlag() {
        return referenceFlag;
    }

    public void setReferenceFlag(Long referenceFlag) {
        this.referenceFlag = referenceFlag;
    }

    @Basic
    @Column(name = "ARG0", nullable = true, length = 254)
    public String getArg0() {
        return arg0;
    }

    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    @Basic
    @Column(name = "ARG1", nullable = true, length = 254)
    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    @Basic
    @Column(name = "ARG2", nullable = true, length = 254)
    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    @Basic
    @Column(name = "ARG3", nullable = true, length = 254)
    public String getArg3() {
        return arg3;
    }

    public void setArg3(String arg3) {
        this.arg3 = arg3;
    }

    @Basic
    @Column(name = "CALLER_FILENAME", nullable = false, length = 254)
    public String getCallerFilename() {
        return callerFilename;
    }

    public void setCallerFilename(String callerFilename) {
        this.callerFilename = callerFilename;
    }

    @Basic
    @Column(name = "CALLER_CLASS", nullable = false, length = 254)
    public String getCallerClass() {
        return callerClass;
    }

    public void setCallerClass(String callerClass) {
        this.callerClass = callerClass;
    }

    @Basic
    @Column(name = "CALLER_METHOD", nullable = false, length = 254)
    public String getCallerMethod() {
        return callerMethod;
    }

    public void setCallerMethod(String callerMethod) {
        this.callerMethod = callerMethod;
    }

    @Basic
    @Column(name = "CALLER_LINE", nullable = false, length = 4)
    public String getCallerLine() {
        return callerLine;
    }

    public void setCallerLine(String callerLine) {
        this.callerLine = callerLine;
    }

    @Id
    @Column(name = "EVENT_ID", nullable = false, precision = 0)
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

}
