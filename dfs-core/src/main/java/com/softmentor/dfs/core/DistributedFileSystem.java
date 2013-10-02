package com.softmentor.dfs.core;

import java.io.File;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.softmentor.dfs.core.config.CoreConfiguration;
import com.softmentor.dfs.core.exception.ConnectionException;
import com.softmentor.dfs.core.exception.CoreException;
import com.softmentor.dfs.core.model.dir.DDirectory;
import com.softmentor.dfs.core.model.file.DFile;


/**
 * Interface for distributed file system operations
 * 
 * @author jiny
 * 
 */
public interface DistributedFileSystem<T>
{

    void createDirectory(@Valid DDirectory<T> dir) throws CoreException, ConnectionException;

    void removeDirectory(@Valid DDirectory<T> dir) throws CoreException, ConnectionException;

    void updateDirectory(@Valid DDirectory<T> dir) throws CoreException, ConnectionException;

    DDirectory<T> viewDirectory(@NotNull DDirectory<T> dir) throws CoreException, ConnectionException;

    List<DFile<T>> listDirectory(@Valid DDirectory<T> dir) throws CoreException, ConnectionException;

    void uploadDirectory(@NotNull File fromLocalDir, @Valid DDirectory<T> toDfsDir, boolean isRecursive)
            throws CoreException, ConnectionException;

    void uploadFiles(@NotNull File[] files, @NotNull String toLocationName) throws CoreException, ConnectionException;

    void downloadDirectory(@Valid DDirectory<T> fromDfsdir, @NotNull File toLocalDir, boolean isRecursive)
            throws CoreException, ConnectionException;

    void downloadFiles(@NotBlank String fromLocationName, String prefix) throws CoreException, ConnectionException;

    void createFile(@Valid DFile<T> file) throws CoreException, ConnectionException;

    void removeFile(@Valid DFile<T> file) throws CoreException, ConnectionException;

    void updateFile(@Valid DFile<T> file) throws CoreException, ConnectionException;

}
