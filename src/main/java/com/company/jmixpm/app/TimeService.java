package com.company.jmixpm.app;

import io.jmix.core.DataManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TimeService {

    private final DataManager dataManager;

    public TimeService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Integer calculateSpentTimeInPeriod(LocalDate periodStart, LocalDate periodEnd) {
        return dataManager.loadValue("select sum(te.timeSpent) from TimeEntry te where " +
                        "te.entryDate >= :start " +
                        "and te.entryDate <= :end", Integer.class)
                .parameter("start", periodStart.atStartOfDay())
                .parameter("end", periodEnd.plusDays(1).atStartOfDay())
                .one();
    }
}
