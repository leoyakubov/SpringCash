package io.leonid.springcash.service.impl;

import io.leonid.springcash.dao.ILogRecordDAO;
import io.leonid.springcash.model.LogRecord;
import io.leonid.springcash.service.ILogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by leonid on 07.11.14.
 */
@Service
public class LogRecordService extends GenericService<LogRecord> implements ILogRecordService {
    @Autowired
    private ILogRecordDAO LogRecordDAO;

}
