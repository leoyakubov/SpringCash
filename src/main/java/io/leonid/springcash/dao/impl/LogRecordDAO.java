package io.leonid.springcash.dao.impl;

import io.leonid.springcash.dao.ILogRecordDAO;
import io.leonid.springcash.model.LogRecord;
import org.springframework.stereotype.Repository;

/**
 * Created by leonid on 06.11.14.
 */
@Repository
public class LogRecordDAO extends GenericDAO<LogRecord> implements ILogRecordDAO {
    public LogRecordDAO() {
        super(LogRecord.class);
    }

    public LogRecordDAO(Class<LogRecord> persistentClass) {
        super(persistentClass);
    }
}
