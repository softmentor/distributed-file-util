package com.softmentor.dfs.scheduler;
/**
 * Class which is used to start files into Amazon s3 asynchronously.
 * 
 * @author jiny
 */

public interface Scheduler
{

    /**
     * Start the scheduler jobs
     * 
     * @return
     */
    public void start() throws RuntimeException;

    /**
     * Start the scheduler jobs
     * 
     */
    public void stop() throws RuntimeException;

    /**
     * execution will be killed and may cause the partial job being completed.
     */
    public void forceStop() throws RuntimeException;

    /**
     * returns true if all running jobs is completed
     * 
     * @return
     */
    public boolean isStopped();

}
