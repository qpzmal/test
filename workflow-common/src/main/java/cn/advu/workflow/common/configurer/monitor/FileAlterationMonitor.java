package cn.advu.workflow.common.configurer.monitor;


import cn.advu.workflow.common.configurer.DynamicConfigurer;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadFactory;

public final class FileAlterationMonitor implements Runnable {
    private final long interval;
    private final List<FileAlterationObserver> observers;
    private Thread thread;
    private ThreadFactory threadFactory;
    private volatile boolean running;

    public FileAlterationMonitor() {
        this(10000L);
    }

    public FileAlterationMonitor(long interval) {
        this.observers = new CopyOnWriteArrayList();
        this.thread = null;
        this.running = false;
        this.interval = interval;
    }

    public FileAlterationMonitor(long interval, FileAlterationObserver... observers) {
        this(interval);
        if (observers != null) {
            FileAlterationObserver[] obserArr = observers;
            int arrLen = observers.length;

            for (int i = 0; i < arrLen; ++i) {
                FileAlterationObserver observer = obserArr[i];
                this.addObserver(observer);
            }
        }

    }

    public long getInterval() {
        return this.interval;
    }

    public synchronized void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public void addObserver(FileAlterationObserver observer) {
        if (observer != null) {
            this.observers.add(observer);
        }

    }

    public void removeObserver(FileAlterationObserver observer) {
        if (observer != null) {
            while (this.observers.remove(observer)) {
                ;
            }
        }

    }

    public Iterable<FileAlterationObserver> getObservers() {
        return this.observers;
    }

    public synchronized void start() throws Exception {
        if (this.running) {
            throw new IllegalStateException("Monitor is already running");
        } else {
            Iterator obIter = this.observers.iterator();

            while (obIter.hasNext()) {
                FileAlterationObserver observer = (FileAlterationObserver) obIter.next();
                observer.initialize();
            }

            this.running = true;
            if (this.threadFactory != null) {
                this.thread = this.threadFactory.newThread(this);
            } else {
                this.thread = new Thread(this);
            }

            this.thread.start();
        }
    }

    public synchronized void stop() throws Exception {
        this.stop(this.interval);
    }

    public synchronized void stop(long stopInterval) throws Exception {
        if (!this.running) {
            throw new IllegalStateException("Monitor is not running");
        } else {
            this.running = false;

            try {
                this.thread.join(stopInterval);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            Iterator obIter = this.observers.iterator();

            while (obIter.hasNext()) {
                FileAlterationObserver observer = (FileAlterationObserver) obIter.next();
                observer.destroy();
            }

        }
    }

    public void run() {
        while (true) {
            if (this.running) {
                Iterator obIter = this.observers.iterator();

                while (obIter.hasNext()) {
                    FileAlterationObserver observer = (FileAlterationObserver) obIter.next();
                    observer.checkAndNotify();
                }

                if (this.running) {
                    try {
                        Thread.sleep(DynamicConfigurer.getLong("deploy.dynamic.interval", this.interval));
                    } catch (InterruptedException ex) {
                        ;
                    }
                    continue;
                }
            }

            return;
        }
    }
}
