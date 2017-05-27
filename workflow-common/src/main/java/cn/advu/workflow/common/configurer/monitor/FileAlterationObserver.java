package cn.advu.workflow.common.configurer.monitor;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FileAlterationObserver implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<FileAlterationListener> listeners;
    private final FileEntry rootEntry;
    private final FileFilter fileFilter;
    private final Comparator<File> comparator;

    public FileAlterationObserver(String directoryName) {
        this(new File(directoryName));
    }

    public FileAlterationObserver(String directoryName, FileFilter fileFilter) {
        this(new File(directoryName), fileFilter);
    }

    public FileAlterationObserver(File directory) {
        this((File) directory, (FileFilter) null);
    }

    public FileAlterationObserver(File directory, FileFilter fileFilter) {
        this(new FileEntry(directory), fileFilter);
    }

    protected FileAlterationObserver(FileEntry rootEntry, FileFilter fileFilter) {
        this.listeners = new CopyOnWriteArrayList();
        if (rootEntry == null) {
            throw new IllegalArgumentException("Root entry is missing");
        } else if (rootEntry.getFile() == null) {
            throw new IllegalArgumentException("Root directory is missing");
        } else {
            this.rootEntry = rootEntry;
            this.fileFilter = fileFilter;
            this.comparator = new Comparator() {
                public int compare(Object o, Object t1) {
                    return 0;
                }

                public int compare(File file1, File file2) {
                    return file1.getName().compareTo(file2.getName());
                }
            };
        }
    }

    public FileEntry getFileEntry() {
        return this.rootEntry;
    }

    public File getDirectory() {
        return this.rootEntry.getFile();
    }

    public FileFilter getFileFilter() {
        return this.fileFilter;
    }

    public void addListener(FileAlterationListener listener) {
        if (listener != null) {
            this.listeners.add(listener);
        }

    }

    public void removeListener(FileAlterationListener listener) {
        if (listener != null) {
            while (this.listeners.remove(listener)) {
                ;
            }
        }

    }

    public Iterable<FileAlterationListener> getListeners() {
        return this.listeners;
    }

    public void initialize() throws Exception {
        this.rootEntry.refresh(this.rootEntry.getFile());
        File[] files = this.listFiles(this.rootEntry.getFile());
        FileEntry[] children = files.length > 0 ? new FileEntry[files.length] : FileEntry.EMPTY_ENTRIES;

        for (int listener = 0; listener < files.length; ++listener) {
            children[listener] = this.createFileEntry(this.rootEntry, files[listener]);
        }

        this.rootEntry.setChildren(children);
        Iterator lisIter = this.listeners.iterator();

        while (lisIter.hasNext()) {
            FileAlterationListener listener = (FileAlterationListener) lisIter.next();
            listener.onInit(this);
        }

    }

    public void destroy() throws Exception {
    }

    public void checkAndNotify() {
        Iterator listener = this.listeners.iterator();

        while (listener.hasNext()) {
            FileAlterationListener rootFile = (FileAlterationListener) listener.next();
            rootFile.onStart(this);
        }

        File rootFile1 = this.rootEntry.getFile();
        if (rootFile1.exists()) {
            this.checkAndNotify(this.rootEntry, this.rootEntry.getChildren(), this.listFiles(rootFile1));
        } else if (this.rootEntry.isExists()) {
            this.checkAndNotify(this.rootEntry, this.rootEntry.getChildren(), new File[0]);
        }

        Iterator listenerIterator = this.listeners.iterator();

        while (listenerIterator.hasNext()) {
            FileAlterationListener listener1 = (FileAlterationListener) listenerIterator.next();
            listener1.onStop(this);
        }

    }

    private void checkAndNotify(FileEntry parent, FileEntry[] previous, File[] files) {
        int c = 0;
        FileEntry[] current = files.length > 0 ? new FileEntry[files.length] : FileEntry.EMPTY_ENTRIES;
        FileEntry[] fileEntries = previous;
        int length = previous.length;

        for (int i = 0; i < length; ++i) {
            FileEntry entry;
            for (entry = fileEntries[i]; c < files.length && this.comparator.compare(entry.getFile(), files[c]) > 0; ++c) {
                current[c] = this.createFileEntry(parent, files[c]);
                this.doCreate(current[c]);
            }

            if (c < files.length && this.comparator.compare(entry.getFile(), files[c]) == 0) {
                this.doMatch(entry, files[c]);
                this.checkAndNotify(entry, entry.getChildren(), this.listFiles(files[c]));
                current[c] = entry;
                ++c;
            } else {
                this.checkAndNotify(entry, entry.getChildren(), new File[0]);
                this.doDelete(entry);
            }
        }

        while (c < files.length) {
            current[c] = this.createFileEntry(parent, files[c]);
            this.doCreate(current[c]);
            ++c;
        }

        parent.setChildren(current);
    }

    private FileEntry createFileEntry(FileEntry parent, File file) {
        FileEntry entry = parent.newChildInstance(file);
        entry.refresh(file);
        File[] files = this.listFiles(file);
        FileEntry[] children = files.length > 0 ? new FileEntry[files.length] : FileEntry.EMPTY_ENTRIES;

        for (int i = 0; i < files.length; ++i) {
            children[i] = this.createFileEntry(entry, files[i]);
        }

        entry.setChildren(children);
        return entry;
    }

    private void doCreate(FileEntry entry) {
        Iterator aChildren = this.listeners.iterator();

        while (aChildren.hasNext()) {
            FileAlterationListener children = (FileAlterationListener) aChildren.next();
            if (entry.isDirectory()) {
                children.onDirectoryCreate(entry.getFile());
            } else {
                children.onFileCreate(entry.getFile());
            }
        }

        FileEntry[] entryChildren = entry.getChildren();
        FileEntry[] entries = entryChildren;
        int length = entryChildren.length;

        for (int i = 0; i < length; ++i) {
            FileEntry fileEntry = entries[i];
            this.doCreate(fileEntry);
        }

    }

    private void doMatch(FileEntry entry, File file) {
        if (entry.refresh(file)) {
            Iterator listenerIterator = this.listeners.iterator();

            while (listenerIterator.hasNext()) {
                FileAlterationListener listener = (FileAlterationListener) listenerIterator.next();
                if (entry.isDirectory()) {
                    listener.onDirectoryChange(file);
                } else {
                    listener.onFileChange(file);
                }
            }
        }

    }

    private void doDelete(FileEntry entry) {
        Iterator listenerIterator = this.listeners.iterator();

        while (listenerIterator.hasNext()) {
            FileAlterationListener listener = (FileAlterationListener) listenerIterator.next();
            if (entry.isDirectory()) {
                listener.onDirectoryDelete(entry.getFile());
            } else {
                listener.onFileDelete(entry.getFile());
            }
        }

    }

    private File[] listFiles(File file) {
        File[] children = null;
        if (file.isDirectory()) {
            children = this.fileFilter == null ? file.listFiles() : file.listFiles(this.fileFilter);
        }

        if (children == null) {
            children = new File[0];
        }

        if (this.comparator != null && children.length > 1) {
            Arrays.sort(children, this.comparator);
        }

        return children;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getSimpleName());
        builder.append("[file=\'");
        builder.append(this.getDirectory().getPath());
        builder.append('\'');
        if (this.fileFilter != null) {
            builder.append(", ");
            builder.append(this.fileFilter.toString());
        }

        builder.append(", listeners=");
        builder.append(this.listeners.size());
        builder.append("]");
        return builder.toString();
    }
}
