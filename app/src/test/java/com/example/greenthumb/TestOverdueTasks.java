package com.example.greenthumb;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOverdueTasks {
    @Test
    public void taskOverdueTest() {
        Task task = new Task("fdfd-fdfd", "test title", new Date(3333), null);

        // should be overdue
        assertTrue(task.isOverdue());
        // mark it as finished
        task.markAsFinished();

        // now shouldn't be overdue
        assertFalse(task.isOverdue());

        // make a new task in the future
        long curDate = Calendar.getInstance().getTimeInMillis();
        task = new Task("test", "test", new Date(curDate + 1000), null);

        // this task shouldn't be over due
        assertFalse(task.isOverdue());
    }
}