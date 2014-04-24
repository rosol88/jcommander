package org.tpal.jcommander.command;

public interface Command
{
    void execute();

    int getProgress();
}
